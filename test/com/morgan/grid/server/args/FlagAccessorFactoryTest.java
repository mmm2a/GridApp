package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Function;
import com.google.inject.Injector;

/**
 * Tests for the {@link FlagAccessorFactory} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class FlagAccessorFactoryTest {

  @Mock private Injector mockInjector;

  private FlagAccessorForTest accessor;

  @Before public void setUpArguments() throws IllegalStateException, IOException {
    Args.parse(new String[] {
        "--int-flag=7",
        "--integer-flag=42",
        "--string-flag=Hello",
        "--debug",
        "--noverbose",
        "--time-units=DAYS",
        "--weird-parse=alpha"
    });

    when(mockInjector.getInstance(TestFlagParser.class)).thenReturn(new TestFlagParser());
    accessor = new FlagAccessorFactory(mockInjector)
        .createFlagAccessorFor(FlagAccessorForTest.class);
  }

  @After public void tearDownArguments() {
    Args.reset();
  }

  @Test public void getPrimitiveFlag() {
    assertEquals(7, accessor.intFlag());
  }

  @Test public void getWrapperFlag() {
    assertEquals(Integer.valueOf(42), accessor.integerFlag());
  }

  @Test public void getStringFlag() {
    assertEquals("Hello", accessor.stringFlag());
  }

  @Test public void getBooleanFlag() {
    assertTrue(accessor.isDebug());
  }

  @Test public void getFalseBooleanFlag() {
    assertFalse(accessor.isVerbose());
  }

  @Test public void getEnumFlag() {
    assertEquals(TimeUnit.DAYS, accessor.timeUnits());
  }

  @Test public void getWeirdParse() {
    assertEquals(5, accessor.weirdParseFlag());
  }

  @Test public void getNullableFlag() {
    assertNull(accessor.someNullable());
  }

  @Test public void wasSet() {
    assertTrue(accessor.wasSet("debug"));
    assertFalse(accessor.wasSet("nullable"));
    assertFalse(accessor.wasSet("port"));
  }

  @Test public void useDefaultValue() {
    assertEquals(8080, accessor.port());
  }

  private static class TestFlagParser implements FlagParser {

    @Override public Function<? super Object, ? extends String> getForwardFunction() {
      return new Function<Object, String>() {
        @Override public String apply(Object input) {
          return "alpha";
        }
      };
    }

    @Override public Function<? super String, ? extends Object> getReverseFunction() {
      return new Function<String, Object>() {
        @Override public Object apply(String input) {
          return input.length();
        }
      };
    }
  }

  private interface FlagAccessorForTest extends FlagAccessor {

    @Flag(name = "int-flag", description = "description")
    int intFlag();

    @Flag(name = "integer-flag", description = "description")
    Integer integerFlag();

    @Flag(name = "string-flag", description = "description")
    String stringFlag();

    @Flag(name = "debug", description = "description")
    boolean isDebug();

    @Flag(name = "verbose", description = "description")
    boolean isVerbose();

    @Flag(name = "time-units", description = "description")
    TimeUnit timeUnits();

    @Flag(name = "weird-parse", description = "desc", flagParser = TestFlagParser.class)
    int weirdParseFlag();

    @Flag(name = "nullable", description = "desc")
    @Nullable Integer someNullable();

    @Flag(name = "port", description = "desc", defaultValue = "8080")
    int port();
  }
}
