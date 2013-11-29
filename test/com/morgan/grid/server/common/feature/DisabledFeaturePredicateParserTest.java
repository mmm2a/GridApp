package com.morgan.grid.server.common.feature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the {@link DisabledFeaturePredicateParser} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class DisabledFeaturePredicateParserTest {

  private FeatureContext featureContext;
  private DisabledFeaturePredicateParser parser;

  @Before public void createTestInstances() {
    featureContext = new FeatureContext();
    parser = new DisabledFeaturePredicateParser();
  }

  @Test public void parsePredicateString_isDisabled() {
    assertFalse(parser.parsePredicateString(DisabledFeaturePredicateParser.DISABLED_TOKEN)
        .apply(featureContext));
  }

  @Test public void parsePredicateString_noIsDisabled() {
    assertNull(parser.parsePredicateString("some other string"));
  }
}
