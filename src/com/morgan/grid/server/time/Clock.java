package com.morgan.grid.server.time;

import org.joda.time.ReadableInstant;

import com.google.inject.ImplementedBy;

/**
 * An interface representing a type that can get the current time.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@ImplementedBy(DefaultClock.class)
public interface Clock {

  /**
   * Gets the current time as a readable instant.
   */
  ReadableInstant now();
}
