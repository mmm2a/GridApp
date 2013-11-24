package com.morgan.grid.server.grid;

import com.google.inject.AbstractModule;

/**
 * GUICE module having to do with the grid application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class GridModule extends AbstractModule {

  @Override protected void configure() {
    install(new GridConstantsModule());
    install(new GridServletModule());
    install(new GridHostPageServletModule());
  }
}
