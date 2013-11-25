package com.morgan.grid.server.args;

/**
 * Interface for a type that can convert flags from their string representations to an object
 * representation and back.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> the object type that the flag has.
 */
public interface FlagParser<T> {
  /**
   * Convert a flag from its object representation into a string.
   */
  String toString(T value);

  /**
   * Convert a flag from its string representation into its object value.
   */
  T fromString(String string);
}