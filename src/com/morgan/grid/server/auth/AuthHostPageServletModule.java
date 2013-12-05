package com.morgan.grid.server.auth;

import com.morgan.grid.server.common.hostpage.HostPageServletModule;

/**
 * GUICE module for locating the auth host page.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class AuthHostPageServletModule extends HostPageServletModule {

  @Override protected void addApplications() {
    addApplication("/apps/auth", AuthHostPageServletModule.class, "resources/Auth.html");
  }
}
