package com.morgan.grid.client.auth;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point for the authentication web app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class AuthApp implements EntryPoint {

  private final AuthAppGinjector injector = GWT.create(AuthAppGinjector.class);

  @Override public void onModuleLoad() {
    injector.getAuthAppPresenter().startApp();
  }
}
