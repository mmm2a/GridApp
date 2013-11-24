package com.morgan.grid.server.grid;

import com.google.inject.servlet.ServletModule;
import com.morgan.grid.client.grid.GridService;

/**
 * GUICE {@link ServletModule} for configuring grid services.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class GridServletModule extends ServletModule {
  @Override protected void configureServlets() {
    bind(GridService.class).to(DefaultGridService.class);
    serve("/services/grid-service").with(DefaultGridService.class);
  }
}
