package com.morgan.grid.server.common.constants;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Tests for the {@link AbstractConstantsModule} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class AbstractConstantsModuleTest {

  private static final String AUTH_TEST_VALUE = "value1";
  private static final String GRID_TEST_VALUE = "value2";

  private Map<DictionaryConstant, String> constantMap =
      Guice.createInjector(new TestableAbstractConstantsModule())
      .getInstance(Key.get(new TypeLiteral<Map<DictionaryConstant, String>>() {}));

  @Test public void configure_providedMethods() {
    assertEquals(AUTH_TEST_VALUE, constantMap.get(DictionaryConstant.AUTH_TEST_CONSTANT));
  }

  @Test public void configure_staticallyAddedConstants() {
    assertEquals(GRID_TEST_VALUE, constantMap.get(DictionaryConstant.GRID_TEST_CONSTANT));
  }

  private static class TestableAbstractConstantsModule extends AbstractConstantsModule {

    @BindConstant(DictionaryConstant.AUTH_TEST_CONSTANT)
    @Provides protected String provideAuthTestConstant() {
      return AUTH_TEST_VALUE;
    }

    @Override protected void configure() {
      super.configure();

      addConstantValue(DictionaryConstant.GRID_TEST_CONSTANT, GRID_TEST_VALUE);
    }
  }
}
