package com.morgan.grid.server.common.feature;

import java.io.IOException;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.morgan.grid.shared.common.feature.Feature;
import com.morgan.grid.shared.common.feature.FeatureChecker;

/**
 * Server-side implementation of the {@link FeatureChecker} interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
class ServerFeatureChecker implements FeatureChecker {

  private final Provider<FeatureContext> featureContextProvider;
  private final ImmutableMap<Feature, Predicate<FeatureContext>> featurePredicateMap;

  @Inject ServerFeatureChecker(Provider<FeatureContext> featureContextProvider,
      FeatureFile featureFile) throws IOException {
    this.featureContextProvider = featureContextProvider;
    featurePredicateMap = createPredicateMap(featureFile);
  }

  private ImmutableMap<Feature, Predicate<FeatureContext>> createPredicateMap(
      FeatureFile featureFile) throws IOException {
    ImmutableMap.Builder<Feature, Predicate<FeatureContext>> mapBuilder = ImmutableMap.builder();
    ImmutableMap<Feature, Predicate<FeatureContext>> fileMap = featureFile.readFeaturesFile();
    for (Feature feature : Feature.values()) {
      Predicate<FeatureContext> predicate = fileMap.get(feature);
      if (predicate == null) {
        predicate = Predicates.alwaysTrue();
      }
      mapBuilder.put(feature, predicate);
    }

    return mapBuilder.build();
  }

  @Override public boolean isEnabled(Feature feature) {
    return featurePredicateMap.get(feature).apply(featureContextProvider.get());
  }
}
