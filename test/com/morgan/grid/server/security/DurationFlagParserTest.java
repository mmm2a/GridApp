package com.morgan.grid.server.security;

import static org.junit.Assert.assertEquals;

import org.joda.time.Duration;
import org.joda.time.ReadableDuration;
import org.junit.Before;
import org.junit.Test;

import com.morgan.grid.server.security.DurationFlagParser.DurationUnit;

/**
 * Tests for the {@link DurationFlagParser} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DurationFlagParserTest {

  private DurationFlagParser parser;

  @Before public void createTestInstances() {
    parser = new DurationFlagParser();
  }

  @Test public void forwardFunction() {
    assertEquals("PT180S", parser.getForwardFunction().apply(Duration.standardMinutes(3)));
  }

  @Test public void backwardFunction_standardDurationFormat() {
    assertEquals(Duration.standardDays(2).plus(Duration.standardSeconds(3).plus(123)),
        parser.getReverseFunction().apply("PT172803.123S"));
  }

  private void doLaxParsingReverseTest(
      DurationUnit unit, int value, ReadableDuration expectedDuration) {
    for (String name : unit.getNameSet()) {
      assertEquals(expectedDuration,
          parser.getReverseFunction().apply(String.format("%d%s", value, name)));

      assertEquals(expectedDuration,
          parser.getReverseFunction().apply(String.format("%d %s", value, name)));

      assertEquals(expectedDuration,
          parser.getReverseFunction().apply(String.format("   %d%s", value, name)));

      assertEquals(expectedDuration,
          parser.getReverseFunction().apply(String.format("\t%d\t%s   ", value, name)));
    }
  }

  @Test public void backwardFunction_laxParsing_days() {
    doLaxParsingReverseTest(DurationUnit.DAY, 12, Duration.standardDays(12));
  }

  @Test public void backwardFunction_laxParsing_hours() {
    doLaxParsingReverseTest(DurationUnit.HOUR, 12, Duration.standardHours(12));
  }

  @Test public void backwardFunction_laxParsing_minutes() {
    doLaxParsingReverseTest(DurationUnit.MINUTE, 12, Duration.standardMinutes(12));
  }

  @Test public void backwardFunction_laxParsing_seconds() {
    doLaxParsingReverseTest(DurationUnit.SECOND, 12, Duration.standardSeconds(12));
  }
}
