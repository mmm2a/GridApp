package com.morgan.grid.client.grid;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Remote service for interacting with basic grid functionality on the server.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RemoteServiceRelativePath("../services/grid-service")
public interface GridService extends RemoteService {
  /**
   * Gets a string message that is displayed for debugging purposes.
   */
  String getHelloMessage(String name);
}
