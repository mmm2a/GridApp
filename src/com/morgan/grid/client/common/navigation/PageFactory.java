package com.morgan.grid.client.common.navigation;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * An interface for a factory type that gets called to create page views for different places in the
 * application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> the type of place that this page factory creates pages for.
 */
public interface PageFactory<T extends Place> {

  /**
   * Creates a page view widget for the given place.
   */
  IsWidget createPageFor(T place);
}
