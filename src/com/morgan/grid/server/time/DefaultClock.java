package com.morgan.grid.server.time;

import org.joda.time.Instant;
import org.joda.time.ReadableInstant;

/**
 * Default implementation of the {@link Clock} interface.  Simply returns the time using the
 * standard system-wide clock mechanism.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class DefaultClock implements Clock {

  @Override public ReadableInstant now() {
    return new Instant();
  }
}
