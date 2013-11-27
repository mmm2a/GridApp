package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Test for the {@link FlagsModule} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FlagsModuleTest {

  private FlagsModule module;
  private Injector injector;

  @Before public void createTestInstances() {
    Args.parse(new String[] {});
    module = new FlagsModule(FlagAccessorForTest.class);
    injector = Guice.createInjector(module);
  }

  @After public void tearDownTestInstances() {
    Args.reset();
  }

  @Test
  public void createsAccessor() {
    FlagAccessorForTest test = injector.getInstance(FlagAccessorForTest.class);
    assertEquals(7, test.flag());
  }

  private interface FlagAccessorForTest extends FlagAccessor {
    @Flag(name = "flag", description = "description", defaultValue = "7")
    int flag();
  }
}
