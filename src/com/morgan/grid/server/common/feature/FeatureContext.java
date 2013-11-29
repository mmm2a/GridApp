package com.morgan.grid.server.common.feature;

import com.google.inject.Inject;

/**
 * A class containing any information that the {@link ServerFeatureChecker} might need to determine
 * whether or not a feature is enabled.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class FeatureContext {

  // TODO(morgan): At the moment this is empty because we don't use context yet.

  @Inject FeatureContext() {
  }
}
