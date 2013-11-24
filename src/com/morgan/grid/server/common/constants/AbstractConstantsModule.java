package com.morgan.grid.server.common.constants;

import java.lang.reflect.Method;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * An abstract base class GUICE module for code that wants to participate in binding dictionary
 * constants to the client.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class AbstractConstantsModule extends AbstractModule {

  /**
   * Adds a string value as a value in the constants dictionary.
   */
  protected void addConstantValue(DictionaryConstant constant, String value) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(value));

    MapBinder<DictionaryConstant, String> dictionaryConstantsMap = MapBinder.newMapBinder(binder(),
        DictionaryConstant.class, String.class);

    dictionaryConstantsMap.addBinding(constant).toInstance(value);
  }

  @Override protected void configure() {
    MapBinder<DictionaryConstant, String> dictionaryConstantsMap = MapBinder.newMapBinder(binder(),
        DictionaryConstant.class, String.class);

    for (Method method : getClass().getDeclaredMethods()) {
      BindConstant annotation = method.getAnnotation(BindConstant.class);
      if (annotation != null) {
        Preconditions.checkState(method.getAnnotation(Provides.class) != null,
            "Dictionary constant provider method %s must have a Provides annotation.", method);
        Class<?> returnType = method.getReturnType();
        Preconditions.checkState(String.class.isAssignableFrom(returnType),
            "Return type for %s must be assignable to a String", method);
        dictionaryConstantsMap.addBinding(annotation.value())
            .to(Key.get(String.class, annotation));
      }
    }
  }
}
