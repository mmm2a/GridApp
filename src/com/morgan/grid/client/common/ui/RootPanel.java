package com.morgan.grid.client.common.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

/**
 * Wrapper class around the {@link com.google.gwt.user.client.ui.RootPanel} class to help with
 * testing.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class RootPanel {

  @Inject RootPanel() {
  }

  /** @see com.google.gwt.user.client.ui.RootPanel#add(IsWidget) */
  public void add(IsWidget widget) {
    com.google.gwt.user.client.ui.RootPanel.get().add(widget);
  }
}
