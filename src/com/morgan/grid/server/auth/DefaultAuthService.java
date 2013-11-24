package com.morgan.grid.server.auth;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import com.morgan.grid.client.auth.AuthService;

/**
 * Default implementation of the {@link AuthService} interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
public class DefaultAuthService extends RemoteServiceServlet implements AuthService {

  static final long serialVersionUID = 1L;

  @Override public String getHelloMessage(String name) {
    return String.format("Auth hello %s", name);
  }
}
