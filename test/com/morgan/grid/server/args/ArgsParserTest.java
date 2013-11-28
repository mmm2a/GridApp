package com.morgan.grid.server.args;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.Test;

import com.google.common.io.InputSupplier;
import com.google.gwt.thirdparty.guava.common.base.Preconditions;

/**
 * Tests for the {@link ArgsParser} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class ArgsParserTest {

  private static final String ARGS_FILE = "the-args-file.test";

  @Test public void buildStore_noArguments() {
    assertEquals(ArgumentStore.builder().build(),
        new ArgsParser().buildStore());
  }

  @Test public void buildStore_onlyArguments() throws IOException {
    assertEquals(ArgumentStore.builder()
        .addArguments("one", "two", "three")
        .build(),
        new ArgsParser()
            .addArgument("one")
            .addArgument("two")
            .addArgument("three")
            .buildStore());
  }

  @Test public void buildStore_longFlags() throws IOException {
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

  @Test public void buildStore_shortFlags() throws IOException {
    assertEquals(ArgumentStore.builder()
        .addFlag("a", "true")
        .addFlag("b", "true")
        .addFlag("c", "four")
        .build(),
        new ArgsParser()
            .addArgument("-abc=four")
            .buildStore());
  }

  @Test public void buildStore_afterDoubleDash() throws IOException {
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

  @Test public void buildStore_argsFileOnly() throws IOException {
    assertEquals(ArgumentStore.builder()
        .addFlag("one", "true")
        .addFlag("two", "false")
        .addFlag("three", "four")
        .addFlag("five", "foobar")
        .build(),
        new TestableArgsParser()
            .addArgument("--args-file=" + ARGS_FILE)
            .buildStore());
  }

  @Test public void buildStore_argsFileWithArgs() throws IOException {
    assertEquals(ArgumentStore.builder()
        .addFlag("alpha", "beta")
        .addFlag("one", "true")
        .addFlag("two", "false")
        .addFlag("three", "override")
        .addFlag("five", "foobar")
        .addFlag("gamma", "ray")
        .build(),
        new TestableArgsParser()
            .addArgument("--alpha=beta")
            .addArgument("--two=not-this-one")
            .addArgument("--args-file=" + ARGS_FILE)
            .addArgument("--three=override")
            .addArgument("--gamma=ray")
            .buildStore());
  }

  private static class TestableArgsParser extends ArgsParser {

    @Override InputSupplier<InputStream> getInputSupplierFor(String fileUrl)
        throws MalformedURLException {
      Preconditions.checkArgument(fileUrl.equals(ARGS_FILE));
      return new InputSupplier<InputStream>() {
        @Override public InputStream getInput() throws IOException {
          StringInputStream in = new StringInputStream("one=true\n"
              + "two=false\n"
              + "three=four\n"
              + "five=foobar");
          return in;
        }
      };
    }
  }
}
