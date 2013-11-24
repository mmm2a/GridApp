package com.morgan.grid.client.auth;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * GWT RPC service for interacting with the server for authentication purposes.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RemoteServiceRelativePath("../services/auth-service")
public interface AuthService extends RemoteService {
  /**
   * Gets a string message that can be displayed for debugging/testing purposes.
   */
  String getHelloMessage(String name);
}
