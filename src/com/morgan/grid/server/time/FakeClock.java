package com.morgan.grid.server.time;

import org.joda.time.Instant;
import org.joda.time.ReadableInstant;

/**
 * A fake implementation of a {@link Clock} for testing purposes.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FakeClock implements Clock {

  private long nextTime;
  private long tickDuration;

  public FakeClock(long time, long tickDuration) {
    this.nextTime = time;
    this.tickDuration = tickDuration;
  }

  public FakeClock(long time) {
    this(time, 1L);
  }

  public void setTime(long time) {
    this.nextTime = time;
  }

  public void setTickDuration(long tickDuration) {
    this.tickDuration = tickDuration;
  }

  @Override public ReadableInstant now() {
    ReadableInstant result = new Instant(nextTime);
    nextTime += tickDuration;
    return result;
  }
}
