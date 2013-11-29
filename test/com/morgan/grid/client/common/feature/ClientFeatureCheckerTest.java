package com.morgan.grid.client.common.feature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.morgan.grid.client.common.constants.ConstantsDictionary;
import com.morgan.grid.client.common.constants.FakeConstantsDictionary;
import com.morgan.grid.shared.common.constants.DictionaryConstant;
import com.morgan.grid.shared.common.feature.Feature;

/**
 * Tests for the {@link ClientFeatureChecker} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class ClientFeatureCheckerTest {

  private ConstantsDictionary dictionary;
  private ClientFeatureChecker featureChecker;

  @Before public void createTestInstances() {
    dictionary = new FakeConstantsDictionary(ImmutableMap.of(
        DictionaryConstant.DISABLED_FEATURES, "TEST_FEATURE_2,TEST_FEATURE_3"));
    featureChecker = new ClientFeatureChecker(dictionary);
  }

  @Test public void isEnabled_readMap() {
    assertTrue(featureChecker.isEnabled(Feature.TEST_FEATURE_1));
    assertFalse(featureChecker.isEnabled(Feature.TEST_FEATURE_2));
    assertFalse(featureChecker.isEnabled(Feature.TEST_FEATURE_3));
  }
}
