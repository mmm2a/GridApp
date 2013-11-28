package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Tests for the {@link Args} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class ArgsTest {

  @After public void resetArgs() {
    Args.reset();
  }

  @Test(expected = IllegalStateException.class) public void getInstance_notInitialized() {
    Args.getInstance();
  }

  @Test public void getInstance() throws IllegalStateException, IOException {
    Args args = Args.parse(new String[] { "one", "two", "--three", "--four=five" });
    assertSame(args, Args.getInstance());
    ArgumentStore store = args.getArgumentStore();
    assertEquals(ImmutableList.of("one", "two"), store.getArguments());
    assertEquals(ImmutableMap.of("three", "true", "four", "five"), store.getFlags());
  }
}
