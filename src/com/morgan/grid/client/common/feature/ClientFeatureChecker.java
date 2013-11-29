package com.morgan.grid.client.common.feature;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.morgan.grid.client.common.constants.ConstantsDictionary;
import com.morgan.grid.shared.common.constants.DictionaryConstant;
import com.morgan.grid.shared.common.feature.Feature;
import com.morgan.grid.shared.common.feature.FeatureChecker;

/**
 * Client-side {@link FeatureChecker} that simply gets its values from the server.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
class ClientFeatureChecker implements FeatureChecker {

  private static final Splitter FEATURE_SPLITTER = Splitter.on(',');

  private final ImmutableSet<Feature> disabledFeatures;

  @Inject ClientFeatureChecker(ConstantsDictionary dictionary) {
    ImmutableSet.Builder<Feature> disabledFeaturesBuilder = ImmutableSet.builder();

    String disabledFeatures = dictionary.get(DictionaryConstant.DISABLED_FEATURES);
    if (disabledFeatures != null) {
      for (String disabledFeature : FEATURE_SPLITTER.split(disabledFeatures)) {
        disabledFeaturesBuilder.add(Feature.valueOf(disabledFeature));
      }
    }

    this.disabledFeatures = disabledFeaturesBuilder.build();
  }

  @Override
  public boolean isEnabled(Feature feature) {
    return !disabledFeatures.contains(feature);
  }
}
