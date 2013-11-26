package com.morgan.grid.shared.convert;

import com.google.common.base.Function;

/**
 * An interface for a type that can convert between two different types.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> one of the types that this converter converts between.
 * @param <V> one of the types that this converter converts between.
 */
public interface Converter<T, V> {

  /**
   * Gets a function that converts from type T to type V.
   */
  Function<? super T, ? extends V> getForwardFunction();

  /**
   * Gets a function that converts from type V to type T.
   */
  Function<? super V, ? extends T> getReverseFunction();
}
