package com.morgan.grid.client.common;

import com.google.gwt.inject.client.AbstractGinModule;
import com.morgan.grid.client.common.feature.FeatureModule;

/**
 * GIN module for the common package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class CommonGinModule extends AbstractGinModule {

  @Override protected void configure() {
    install(new FeatureModule());
  }
}
