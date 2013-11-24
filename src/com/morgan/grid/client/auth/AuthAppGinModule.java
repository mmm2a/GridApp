package com.morgan.grid.client.auth;

import com.google.gwt.inject.client.AbstractGinModule;
import com.morgan.grid.client.common.CommonGinModule;

/**
 * GIN module for the auth app package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class AuthAppGinModule extends AbstractGinModule {

  @Override protected void configure() {
    install(new CommonGinModule());
  }
}
