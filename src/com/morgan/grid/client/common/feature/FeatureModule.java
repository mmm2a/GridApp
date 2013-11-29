package com.morgan.grid.client.common.feature;

import com.google.gwt.inject.client.AbstractGinModule;
import com.morgan.grid.shared.common.feature.FeatureChecker;

/**
 * GIN module for binding classes in the client's feature package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FeatureModule extends AbstractGinModule {

  @Override protected void configure() {
    bind(FeatureChecker.class).to(ClientFeatureChecker.class);
  }
}
