package com.morgan.grid.client.grid;

import com.google.gwt.inject.client.AbstractGinModule;
import com.morgan.grid.client.common.CommonGinModule;

/**
 * GIN module for the grid app package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class GridAppGinModule extends AbstractGinModule {

  @Override protected void configure() {
    install(new CommonGinModule());
  }
}
