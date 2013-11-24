package com.morgan.grid.server.auth;

import com.google.inject.servlet.ServletModule;
import com.morgan.grid.client.auth.AuthService;

/**
 * {@link ServletModule} GUICE module for the auth package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class AuthServletModule extends ServletModule {
  @Override protected void configureServlets() {
    bind(AuthService.class).to(DefaultAuthService.class);
    serve("/services/auth-service").with(DefaultAuthService.class);
  }
}
