package com.morgan.grid.client.grid.browse;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.morgan.grid.client.common.navigation.PlaceHandler;

/**
 * A {@link PlaceHandler} for handling browse places.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class BrowsePlaceHandler implements PlaceHandler<BrowsePlace> {

  public static final String PLACE_IDENTIFIER_TOKEN = "browse";

  @Override public String getPlaceIdentifierToken() {
    return PLACE_IDENTIFIER_TOKEN;
  }

  @Override public ImmutableList<String> toHistoryTokenParts(BrowsePlace place) {
    // TODO(morgan): Implement this
    throw new UnsupportedOperationException();
  }

  @Override public BrowsePlace fromHistoryTokenParts(List<String> historyToken)
      throws IllegalStateException {
    // TODO(morgan): Implement this
    throw new UnsupportedOperationException();
  }

  /**
   * Creates a {@link BrowsePlace} instance.
   */
  public BrowsePlace createBrowsePlace() {
    return new BrowsePlace(this);
  }
}
