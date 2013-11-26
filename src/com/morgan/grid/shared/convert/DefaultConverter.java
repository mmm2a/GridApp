package com.morgan.grid.shared.convert;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * A default implementation of a {@link Converter} that merely contains reference to the conversion
 * functions.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DefaultConverter<T, V> implements Converter<T, V> {

  private final Function<? super T, ? extends V> forwardConverter;
  private final Function<? super V, ? extends T> backwardConverter;

  public DefaultConverter(Function<? super T, ? extends V> forwardConverter,
      Function<? super V, ? extends T> backwardConverter) {
    this.forwardConverter = Preconditions.checkNotNull(forwardConverter);
    this.backwardConverter = Preconditions.checkNotNull(backwardConverter);
  }

  @Override public Function<? super T, ? extends V> getForwardFunction() {
    return forwardConverter;
  }

  @Override public Function<? super V, ? extends T> getReverseFunction() {
    return backwardConverter;
  }
}
