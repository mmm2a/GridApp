package com.morgan.grid.server.args;

/**
 * An interface for a type that can access command line flags.  Clients are expected to extend
 * this interface to create their own flag accessors.  Stubs should be created using the
 * {@link FlagAccessorFactory}.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface FlagAccessor {
  /**
   * A method to determine whether or not a given flag was set.
   */
  boolean wasSet(String flagName);
}
