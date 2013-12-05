package com.morgan.grid.client.grid.browse;

import com.morgan.grid.client.common.navigation.Place;
import com.morgan.grid.client.common.navigation.PlaceHandler;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * A {@link Place} representing browsing the grid namespace.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class BrowsePlace extends Place {

  BrowsePlace(PlaceHandler<BrowsePlace> placeHandler) {
    super(Application.GRID, placeHandler);
  }
}
