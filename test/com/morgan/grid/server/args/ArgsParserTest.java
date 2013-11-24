package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the {@link ArgsParser} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class ArgsParserTest {

  @Test public void buildStore_noArguments() {
    assertEquals(ArgumentStore.builder().build(),
        new ArgsParser().buildStore());
  }

  @Test public void buildStore_onlyArguments() {
    assertEquals(ArgumentStore.builder()
        .addArguments("one", "two", "three")
        .build(),
        new ArgsParser()
            .addArgument("one")
            .addArgument("two")
            .addArgument("three")
            .buildStore());
  }

  @Test public void buildStore_longFlags() {
    assertEquals(ArgumentStore.builder()
        .addFlag("one", "true")
        .addFlag("two", "false")
        .addFlag("three", "four")
        .addFlag("five", "false")
        .build(),
        new ArgsParser()
            .addArgument("--one")
            .addArgument("--two=false")
            .addArgument("--three=four")
            .addArgument("--nofive")
            .buildStore());
  }

  @Test public void buildStore_shortFlags() {
    assertEquals(ArgumentStore.builder()
        .addFlag("a", "true")
        .addFlag("b", "true")
        .addFlag("c", "four")
        .build(),
        new ArgsParser()
            .addArgument("-abc=four")
            .buildStore());
  }

  @Test public void buildStore_afterDoubleDash() {
    assertEquals(ArgumentStore.builder()
        .addFlag("one", "true")
        .addFlag("two", "false")
        .addFlag("three", "four")
        .addFlag("five", "false")
        .addArguments("--foobar")
        .build(),
        new ArgsParser()
            .addArgument("--one")
            .addArgument("--two=false")
            .addArgument("--three=four")
            .addArgument("--nofive")
            .addArgument("--")
            .addArgument("--foobar")
            .buildStore());

  }
}
