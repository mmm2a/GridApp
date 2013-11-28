package com.morgan.grid.server.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Duration;
import org.joda.time.ReadableDuration;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.gwt.thirdparty.guava.common.base.Preconditions;
import com.google.inject.Inject;
import com.morgan.grid.server.args.FlagParser;

/**
 * A {@link FlagParser} for parsing durations.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DurationFlagParser implements FlagParser {

  private static final Pattern STANDARD_DURATION_FORMAT = Pattern.compile(
      "^\\s*PT\\d*(?:\\.\\d+)?S\\s*$");

  private static final Pattern LAX_DURATION_FORMAT = Pattern.compile(
      "^\\s*(\\d+)\\s*([a-zA-Z]+)\\s*$");

  @VisibleForTesting enum DurationUnit {
    DAY("days", "day", "d"),
    HOUR("hours", "hour", "hrs", "hr", "h"),
    MINUTE("minutes", "minute", "mins", "min", "m"),
    SECOND("seconds", "second", "secs", "sec", "s");

    private final ImmutableSet<String> nameSet;

    private DurationUnit(String name, String... additionalNames) {
      ImmutableSet.Builder<String> nameSetBuilder = ImmutableSet.builder();
      Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
      nameSetBuilder.add(name.toLowerCase());
      for (String additionalName : additionalNames) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(additionalName));
        nameSetBuilder.add(additionalName.toLowerCase());
      }
      this.nameSet = nameSetBuilder.build();
    }

    @VisibleForTesting ImmutableSet<String> getNameSet() {
      return nameSet;
    }

    public static DurationUnit fromName(String name) {
      name = name.toLowerCase();
      for (DurationUnit unit : DurationUnit.values()) {
        if (unit.nameSet.contains(name)) {
          return unit;
        }
      }

      throw new IllegalArgumentException("Unknown duration unit " + name);
    }
  }

  private static final Function<String, ReadableDuration> REVERSE_FUNCTION =
      new Function<String, ReadableDuration>() {
        @Override public ReadableDuration apply(String input) {
          if (STANDARD_DURATION_FORMAT.matcher(input).matches()) {
            return Duration.parse(input);
          } else {
            Matcher matcher = LAX_DURATION_FORMAT.matcher(input);
            Preconditions.checkArgument(matcher.matches());
            long value = Long.parseLong(matcher.group(1));
            DurationUnit unit = DurationUnit.fromName(matcher.group(2));

            switch (unit) {
              case DAY:
                return Duration.standardDays(value);

              case HOUR:
                return Duration.standardHours(value);

              case MINUTE:
                return Duration.standardMinutes(value);

              case SECOND:
                return Duration.standardSeconds(value);
            }

            throw new IllegalArgumentException("Don't know how to parse duration " + input);
          }
        }
      };

  @Inject DurationFlagParser() {
  }

  @Override public Function<? super Object, ? extends String> getForwardFunction() {
    return Functions.toStringFunction();
  }

  @Override public Function<? super String, ? extends Object> getReverseFunction() {
    return REVERSE_FUNCTION;
  }
}
