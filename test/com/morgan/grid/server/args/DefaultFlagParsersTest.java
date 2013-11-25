package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Primitives;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * Tests for the {@link DefaultFlagParsers} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DefaultFlagParsersTest {

  private static final ImmutableMap<Class<?>, Object> TEST_VALUES =
      ImmutableMap.<Class<?>, Object>builder()
          .put(short.class, (short) 7)
          .put(Short.class, (short) 7)
          .put(int.class, 7)
          .put(Integer.class, 7)
          .put(long.class, 7L)
          .put(Long.class, 7L)
          .put(float.class, 7.2F)
          .put(Float.class, 7.2F)
          .put(double.class, 7.2)
          .put(Double.class, 7.2)
          .put(boolean.class, true)
          .put(Boolean.class, true)
          .build();

  private Set<Class<?>> primitivesAndWrappers;

  @Before public void createTestInstances() {
    primitivesAndWrappers = Sets.newHashSet();
    primitivesAndWrappers.addAll(Primitives.allPrimitiveTypes());
    primitivesAndWrappers.addAll(Primitives.allWrapperTypes());
    primitivesAndWrappers.remove(void.class);
    primitivesAndWrappers.remove(Void.class);
    primitivesAndWrappers.remove(byte.class);
    primitivesAndWrappers.remove(Byte.class);
    primitivesAndWrappers.remove(char.class);
    primitivesAndWrappers.remove(Character.class);
  }

  private <T> void runFlagParserTest(FlagParser<T> flagParser, T rawValue, String stringValue) {
    assertEquals(stringValue, flagParser.toString(rawValue));
    assertEquals(rawValue, flagParser.fromString(stringValue));
  }

  @Test public void shortFlagParser() {
    runFlagParserTest(DefaultFlagParsers.shortFlagParser(), (short) 7, "7");
  }

  @Test public void integerFlagParser() {
    runFlagParserTest(DefaultFlagParsers.intFlagParser(), 7, "7");
  }

  @Test public void longFlagParser() {
    runFlagParserTest(DefaultFlagParsers.longFlagParser(), 7L, "7");
  }

  @Test public void floatFlagParser() {
    runFlagParserTest(DefaultFlagParsers.floatFlagParser(), 7.2f, "7.2");
  }

  @Test public void doubleFlagParser() {
    runFlagParserTest(DefaultFlagParsers.doubleFlagParser(), 7.2, "7.2");
  }

  @Test public void booleanFlagParser() {
    runFlagParserTest(DefaultFlagParsers.booleanFlagParser(), true, "true");
  }

  @Test public void stringFlagParser() {
    runFlagParserTest(DefaultFlagParsers.stringFlagParser(), "hello", "hello");
  }

  @Test public void enumFlagParser() {
    runFlagParserTest(DefaultFlagParsers.enumFlagParser(TimeUnit.class),
        TimeUnit.HOURS, "HOURS");
  }

  @Test public void hasDefaultFlagParser() {
    for (Class<?> primitive : primitivesAndWrappers) {
      assertTrue(String.format("Flag parser for %s", primitive),
          DefaultFlagParsers.hasDefaultFlagParser(primitive));
    }

    assertTrue(DefaultFlagParsers.hasDefaultFlagParser(String.class));
    assertTrue(DefaultFlagParsers.hasDefaultFlagParser(TimeUnit.class));

    assertFalse(DefaultFlagParsers.hasDefaultFlagParser(SafeHtml.class));
  }

  @SuppressWarnings("unchecked")
  @Test public void getFlagParserFor() {
    for (Class<?> primitive : primitivesAndWrappers) {
      Object testValue = TEST_VALUES.get(primitive);
      assertNotNull(String.format("Can't find test value for %s", primitive), testValue);
      FlagParser<?> flagParser = DefaultFlagParsers.getFlagParserFor(primitive);
      runFlagParserTest((FlagParser<Object>) flagParser, testValue, testValue.toString());
    }

    runFlagParserTest((FlagParser<String>) DefaultFlagParsers.getFlagParserFor(String.class),
        "Hello", "Hello");

    runFlagParserTest((FlagParser<TimeUnit>) DefaultFlagParsers.getFlagParserFor(TimeUnit.class),
        TimeUnit.DAYS, "DAYS");
  }
}
