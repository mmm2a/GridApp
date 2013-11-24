package com.morgan.grid.server.common.constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Binding annotatino used to mark a dictionary constant.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface BindConstant {
  /**
   * The dictionary constant that this annotation is binding.
   */
  DictionaryConstant value();
}
