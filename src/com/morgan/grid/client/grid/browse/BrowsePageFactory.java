package com.morgan.grid.client.grid.browse;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.morgan.grid.client.common.navigation.PageFactory;

/**
 * A {@link PageFactory} for creating browse pages.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class BrowsePageFactory implements PageFactory<BrowsePlace> {

  @Override public IsWidget createPageFor(BrowsePlace place) {
    return new Label("Browse page");
  }
}
