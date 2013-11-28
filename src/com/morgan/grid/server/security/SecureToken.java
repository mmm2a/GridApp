package com.morgan.grid.server.security;

import org.joda.time.ReadableInstant;

/**
 * An interface for a type that can be managed as a secure (encrypted) token.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface SecureToken {
  /**
   * Indicates whether or not this security token is valid.
   */
  boolean isValid(ReadableInstant now);
}
