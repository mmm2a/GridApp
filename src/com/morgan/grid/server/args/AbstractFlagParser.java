package com.morgan.grid.server.args;

/**
 * An abstract implementation of a {@link FlagParser} that handles the case where values use their
 * {@link Object#toString()} method for the to string conversion.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> the object type representation of this flag parser.
 */
public abstract class AbstractFlagParser<T> implements FlagParser<T> {

  @Override public String toString(T value) {
    return value.toString();
  }
}
