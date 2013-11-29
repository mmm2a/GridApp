package com.morgan.grid.server.common.feature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.inject.util.Providers;
import com.morgan.grid.shared.common.feature.Feature;

/**
 * Tests for the {@link ServerFeatureChecker} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class ServerFeatureCheckerTest {

  @Mock private FeatureFile mockFeatureFile;

  private FeatureContext featureContext;
  private ServerFeatureChecker featureChecker;

  @Before public void createTestInstances() throws Exception {
    when(mockFeatureFile.readFeaturesFile())
        .thenReturn(ImmutableMap.<Feature, Predicate<FeatureContext>>of(
            Feature.TEST_FEATURE_1, Predicates.<FeatureContext>alwaysFalse(),
            Feature.TEST_FEATURE_2, Predicates.<FeatureContext>alwaysTrue()));
    featureContext = new FeatureContext();
    featureChecker = new ServerFeatureChecker(Providers.of(featureContext), mockFeatureFile);
  }

  @Test public void isEnabled_disabledExplicitly() {
    assertFalse(featureChecker.isEnabled(Feature.TEST_FEATURE_1));
  }

  @Test public void isEnabled_enabledExplicitly() {
    assertTrue(featureChecker.isEnabled(Feature.TEST_FEATURE_2));
  }

  @Test public void isEnabled_enabledImplicitly() {
    assertTrue(featureChecker.isEnabled(Feature.TEST_FEATURE_3));
  }
}
