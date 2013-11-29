package com.morgan.grid.server.common.feature;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;
import com.morgan.grid.server.common.constants.AbstractConstantsModule;
import com.morgan.grid.server.common.constants.BindConstant;
import com.morgan.grid.shared.common.constants.DictionaryConstant;
import com.morgan.grid.shared.common.feature.Feature;

/**
 * GUICE module to bind some dictionary constants for features.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class FeatureConstantsModule extends AbstractConstantsModule {

  private static final Joiner FEATURE_JOINER = Joiner.on(',');

  @BindConstant(DictionaryConstant.DISABLED_FEATURES)
  @Provides
  protected String provideDisabledFeatures(ServerFeatureChecker featureChecker) {
    ImmutableList.Builder<String> disabledFeaturesListBuilder = ImmutableList.builder();
    for (Feature feature : Feature.values()) {
      if (!featureChecker.isEnabled(feature))  {
        disabledFeaturesListBuilder.add(feature.name());
      }
    }

    return FEATURE_JOINER.join(disabledFeaturesListBuilder.build());
  }
}
