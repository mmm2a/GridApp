package com.morgan.grid.client.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async version of the {@link GridService} service interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface GridServiceAsync {
  /** @see GridService#getHelloMessage() */
  void getHelloMessage(String name, AsyncCallback<String> callback);
}
