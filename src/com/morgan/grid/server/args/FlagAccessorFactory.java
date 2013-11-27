package com.morgan.grid.server.args;

import java.lang.reflect.Method;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * A factory class for creating {@link FlagAccessor} resource instances.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FlagAccessorFactory {

  private static final FlagAccessor DEFAULT_FLAG_ACCESSOR = new FlagAccessor() {
    @Override public boolean wasSet(String flagName) {
      return Args.getInstance().getArgumentStore().getFlags().containsKey(flagName);
    }
  };

  private final Injector injector;

  @Inject FlagAccessorFactory(Injector injector) {
    this.injector = injector;
  }

  public <T extends FlagAccessor> T createFlagAccessorFor(Class<T> accessorInterface) {
    Preconditions.checkArgument(accessorInterface.isInterface());

    ImmutableMap.Builder<Method, Supplier<? extends Object>> methodSupplierBuilder =
        ImmutableMap.builder();

    for (Method method : accessorInterface.getMethods()) {
      if (!method.getDeclaringClass().equals(FlagAccessor.class)) {
        Preconditions.checkState(method.getParameterTypes().length == 0,
            "Flag accessor method %s must not take any parameters", method);

        Flag flag = method.getAnnotation(Flag.class);
        Preconditions.checkState(flag != null,
            "Unable to find @Flag annotation on method %s", method);
        String value = Args.getInstance().getArgumentStore().getFlags().get(flag.name());
        if (value == null) {
          value = flag.defaultValue();
          if (value.isEmpty()) {
            Preconditions.checkState(method.getAnnotation(Nullable.class) != null,
                "Missing required flag %s -- %s", flag.name(), flag.description());
            methodSupplierBuilder.put(method, Suppliers.ofInstance(null));
            continue;
          }
        }

        Class<? extends FlagParser> parserType = flag.flagParser();
        FlagParser parser;
        if (parserType.equals(FlagParser.class)) {
          parser = DefaultFlagParsers.getFlagParserFor(method.getReturnType());
        } else {
          parser = injector.getInstance(parserType);
        }
        methodSupplierBuilder.put(
            method, Suppliers.ofInstance(parser.getReverseFunction().apply(value)));
      }
    }

    return Reflection.newProxy(accessorInterface,
        new FlagAccessorInvocationHandler(methodSupplierBuilder.build()));
  }

  /**
   * An {@link InvocationHandler} that will handle all incoming requests to the {@link FlagAccessor}
   * interfaces.
   */
  private static class FlagAccessorInvocationHandler extends AbstractInvocationHandler {

    private final ImmutableMap<Method, Supplier<? extends Object>> methodSupplierMap;

    private FlagAccessorInvocationHandler(ImmutableMap<Method,
        Supplier<? extends Object>> methodSupplierMap) {
      this.methodSupplierMap = methodSupplierMap;
    }

    @Override protected Object handleInvocation(
        Object proxy,
        Method method,
        Object[] arguments) throws Throwable {
      if (method.getDeclaringClass().equals(FlagAccessor.class)) {
        return method.invoke(DEFAULT_FLAG_ACCESSOR, arguments);
      }

      return methodSupplierMap.get(method).get();
    }
  }
}
