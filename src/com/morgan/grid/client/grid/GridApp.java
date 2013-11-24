package com.morgan.grid.client.grid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point for the grid web app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class GridApp implements EntryPoint {

  private final GridAppGinjector injector = GWT.create(GridAppGinjector.class);

  @Override public void onModuleLoad() {
    injector.getGridAppPresenter().startApp();
  }
}
