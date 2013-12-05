package com.morgan.grid.server.grid;

import com.morgan.grid.server.common.hostpage.HostPageServletModule;

/**
 * GUICE module for installing the grid host page.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class GridHostPageServletModule extends HostPageServletModule {

  @Override protected void addApplications() {
    addApplication("/apps/grid", GridHostPageServletModule.class, "resources/Grid.html");
  }
}
