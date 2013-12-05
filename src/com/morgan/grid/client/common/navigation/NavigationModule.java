package com.morgan.grid.client.common.navigation;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * A GIN module for hooking in the navigation package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class NavigationModule extends AbstractGinModule {

  @Override protected void configure() {
    bind(DefaultNavigator.class).asEagerSingleton();
  }
}
