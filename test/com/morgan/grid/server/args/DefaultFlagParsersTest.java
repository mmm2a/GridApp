package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Primitives;

/**
 * Tests for the {@link DefaultFlagParsers} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DefaultFlagParsersTest {

  private static final ImmutableMap<Class<?>, Object> TEST_VALUES =
      ImmutableMap.<Class<?>, Object>builder()
          .put(char.class, 'c')
          .put(Character.class, 'c')
          .put(byte.class, (byte)2)
          .put(Byte.class, (byte)2)
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
  }

  private <T> void runFlagParserTest(FlagParser flagParser, T rawValue, String stringValue) {
    assertEquals(stringValue, flagParser.getForwardFunction().apply(rawValue));
    assertEquals(rawValue, flagParser.getReverseFunction().apply(stringValue));
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

  @Test public void getFlagParserFor() {
    for (Class<?> primitive : primitivesAndWrappers) {
      Object testValue = TEST_VALUES.get(primitive);
      assertNotNull(String.format("Can't find test value for %s", primitive), testValue);
      FlagParser flagParser = DefaultFlagParsers.getFlagParserFor(primitive);
      runFlagParserTest(flagParser, testValue, testValue.toString());
    }

    runFlagParserTest(DefaultFlagParsers.getFlagParserFor(String.class), "Hello", "Hello");

    runFlagParserTest(DefaultFlagParsers.getFlagParserFor(TimeUnit.class), TimeUnit.DAYS, "DAYS");
  }
}
