package com.morgan.grid.client.auth;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async version of the {@link AuthService} service interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface AuthServiceAsync {
  /** @see AuthService#getHelloMessagge() */
  void getHelloMessage(String name, AsyncCallback<String> callback);
}
