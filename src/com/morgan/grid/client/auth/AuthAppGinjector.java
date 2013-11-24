package com.morgan.grid.client.auth;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.morgan.grid.client.common.AppPresenter;

/**
 * {@link Ginjector} for the Auth app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@GinModules(AuthAppGinModule.class)
public interface AuthAppGinjector extends Ginjector {
  /**
   * Retrieves the main auth app {@link AppPresenter}.
   */
  AuthAppPresenter getAuthAppPresenter();
}