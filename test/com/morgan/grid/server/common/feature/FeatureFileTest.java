package com.morgan.grid.server.common.feature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.InputSupplier;
import com.morgan.grid.shared.common.feature.Feature;

/**
 * Tests for the {@link FeatureFile} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class FeatureFileTest {

  @Mock private FeaturePredicateParser mockParser1;
  @Mock private FeaturePredicateParser mockParser2;
  @Mock private FeaturePredicateParser mockParser3;

  @Test public void readFeaturesFile_simple() throws Exception {
    TestableFeatureFile file = new TestableFeatureFile("TEST_FEATURE_1(one)",
        "TEST_FEATURE_2(two)",
        "TEST_FEATURE_3(three)");

    when(mockParser1.parsePredicateString("one"))
        .thenReturn(Predicates.<FeatureContext>alwaysFalse());
    when(mockParser2.parsePredicateString("two"))
        .thenReturn(Predicates.<FeatureContext>alwaysTrue());
    when(mockParser3.parsePredicateString("three"))
        .thenReturn(Predicates.<FeatureContext>alwaysFalse());
    ImmutableMap<Feature, Predicate<FeatureContext>> map = file.readFeaturesFile();

    verify(mockParser1).parsePredicateString("one");
    verify(mockParser2).parsePredicateString("two");
    verify(mockParser3).parsePredicateString("three");

    assertFalse(map.get(Feature.TEST_FEATURE_1).apply(null));
    assertTrue(map.get(Feature.TEST_FEATURE_2).apply(null));
    assertFalse(map.get(Feature.TEST_FEATURE_3).apply(null));
  }

  private void doTestFeaturesFile_complex(FeatureFile file) throws Exception {
    when(mockParser1.parsePredicateString("one"))
        .thenReturn(Predicates.<FeatureContext>alwaysFalse());
    when(mockParser2.parsePredicateString("two"))
        .thenReturn(Predicates.<FeatureContext>alwaysFalse());
    when(mockParser3.parsePredicateString("three"))
        .thenReturn(Predicates.<FeatureContext>alwaysTrue());
    when(mockParser1.parsePredicateString("foobar"))
        .thenReturn(Predicates.<FeatureContext>alwaysTrue());
    when(mockParser3.parsePredicateString("four:five,six"))
        .thenReturn(Predicates.<FeatureContext>alwaysFalse());
    ImmutableMap<Feature, Predicate<FeatureContext>> map = file.readFeaturesFile();

    verify(mockParser1).parsePredicateString("one");
    verify(mockParser1).parsePredicateString("foobar");
    verify(mockParser2).parsePredicateString("two");
    verify(mockParser3).parsePredicateString("three");
    verify(mockParser3).parsePredicateString("four:five,six");

    assertFalse(map.get(Feature.TEST_FEATURE_1).apply(null));
    assertTrue(map.get(Feature.TEST_FEATURE_2).apply(null));
    assertTrue(map.get(Feature.TEST_FEATURE_3).apply(null));
  }

  @Test public void readFeaturesFile_complex() throws Exception {
    TestableFeatureFile file = new TestableFeatureFile("TEST_FEATURE_1(one)",
        "TEST_FEATURE_2(two|three)",
        "TEST_FEATURE_3(foobar|four:five,six)");
    doTestFeaturesFile_complex(file);
  }

  @Test public void readFeaturesFile_complex_withWhitespace() throws Exception {
    TestableFeatureFile file = new TestableFeatureFile("    TEST_FEATURE_1(one)",
        "\t\t",
        "TEST_FEATURE_2\t(two | three)",
        "TEST_FEATURE_3(    foobar|   four:five,six)\t  ");
    doTestFeaturesFile_complex(file);
  }

  private class TestableFeatureFile extends FeatureFile {

    private final ImmutableList<String> lines;

    private TestableFeatureFile(String... lines) {
      super(ImmutableSet.of(mockParser1, mockParser2, mockParser3));

      this.lines = ImmutableList.copyOf(lines);
    }

    @Override InputSupplier<? extends Reader> getFileReader() {
      StringBuilder builder = new StringBuilder();
      for (String line : lines) {
        builder.append(line + "\n");
      }
      final String text = builder.toString();
      return new InputSupplier<Reader>() {
        @Override public Reader getInput() throws IOException {
          return new StringReader(text);
        }
      };
    }
  }
}
