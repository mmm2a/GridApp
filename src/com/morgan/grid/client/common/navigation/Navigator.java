package com.morgan.grid.client.common.navigation;

import com.google.inject.ImplementedBy;

/**
 * An interface for a type that controls navigation within the application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@ImplementedBy(DefaultNavigator.class)
public interface Navigator {
  /**
   * Navigate to a specific place within the web application.
   */
  void navigateTo(Place place);
}
