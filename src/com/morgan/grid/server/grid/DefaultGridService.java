package com.morgan.grid.server.grid;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import com.morgan.grid.client.grid.GridService;

/**
 * Default implementation of the {@link GridService} interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
public class DefaultGridService extends RemoteServiceServlet implements GridService {

  static final long serialVersionUID = 1L;

  @Override public String getHelloMessage(String name) {
    return String.format("Grid hello %s", name);
  }
}
