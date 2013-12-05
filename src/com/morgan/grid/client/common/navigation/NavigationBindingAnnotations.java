package com.morgan.grid.client.common.navigation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Binding annotations (see {@link BindingAnnotation}) that related to navigation.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class NavigationBindingAnnotations {

  /**
   * Identifies a default place to navigate to when a destination is unknown.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface DefaultPlace {
  }

  /**
   * Identifies a map multi-binding for place token identifiers to {@link PlaceHandler} instances.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface PlaceHandlers {
  }

  /**
   * Identifies a map multi-binding for place token identifiers to {@link PageFactory} instances.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface PageFactories {
  }
}
