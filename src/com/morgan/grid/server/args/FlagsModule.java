package com.morgan.grid.server.args;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A type of GUICE {@link AbstractModule} that automatically creates {@link FlagAccessor} stubs
 * using the {@link FlagAccessorFactory} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FlagsModule extends AbstractModule {

  private final ImmutableList<Class<? extends FlagAccessor>> flagAccessors;

  @SafeVarargs
  public FlagsModule(Class<? extends FlagAccessor> firstFlagAccessor,
      Class<? extends FlagAccessor>... otherFlagAccessors) {
    this.flagAccessors = ImmutableList.<Class<? extends FlagAccessor>>builder()
        .add(firstFlagAccessor)
        .addAll(ImmutableList.copyOf(otherFlagAccessors))
        .build();
  }

  @Override protected void configure() {
    for (Class<? extends FlagAccessor> iface : flagAccessors) {
      bindProviderFor(iface);
    }
  }

  private <T extends FlagAccessor> void bindProviderFor(
      Class<T> flagAccessorInterface) {
    bind(flagAccessorInterface).toProvider(new FlagAccessorProvider<T>(flagAccessorInterface));
  }

  /**
   * An internal provider of a {@link FlagAccessor} that creates stubs using the
   * {@link FlagAccessorFactory} class.
   *
   * @author mark@mark-morgan.net (Mark Morgan)
   */
  private static class FlagAccessorProvider<T extends FlagAccessor> implements Provider<T> {

    private final Class<T> flagAccessorInterface;

    @Inject private FlagAccessorFactory flagAccessorFactory;
    @Nullable private T accessorInstance;

    private FlagAccessorProvider(Class<T> flagAccessorInterface) {
      this.flagAccessorInterface = Preconditions.checkNotNull(flagAccessorInterface);
    }

    @Override public T get() {
      synchronized(this) {
        if (accessorInstance == null) {
          accessorInstance = flagAccessorFactory.createFlagAccessorFor(flagAccessorInterface);
        }
      }

      return accessorInstance;
    }
  }
}
