package com.morgan.grid.server.auth;

import com.google.inject.AbstractModule;

/**
 * GUICE module related to the auth application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class AuthModule extends AbstractModule {

  @Override protected void configure() {
    install(new AuthConstantsModule());
    install(new AuthServletModule());
    install(new AuthHostPageServletModule());
  }
}
