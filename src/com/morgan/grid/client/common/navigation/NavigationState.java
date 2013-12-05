package com.morgan.grid.client.common.navigation;

import com.google.inject.ImplementedBy;

/**
 * An interface to get information about the navigation state for an application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@ImplementedBy(DefaultNavigator.class)
public interface NavigationState {
  /**
   * Gets the current location within the app.
   */
  Place getCurrentPlace();
}
