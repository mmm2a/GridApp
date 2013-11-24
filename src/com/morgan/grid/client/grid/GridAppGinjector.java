package com.morgan.grid.client.grid;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.morgan.grid.client.common.AppPresenter;

/**
 * {@link Ginjector} interface for the grid web app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@GinModules(GridAppGinModule.class)
public interface GridAppGinjector extends Ginjector {
  /**
   * Gets the main grid app {@link AppPresenter}.
   */
  GridAppPresenter getGridAppPresenter();
}
