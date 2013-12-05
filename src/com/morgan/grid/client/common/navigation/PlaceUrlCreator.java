package com.morgan.grid.client.common.navigation;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.gwt.http.client.UrlBuilder;
import com.google.inject.Inject;
import com.morgan.grid.shared.common.navigation.NavigationConstants;

/**
 * A helper class to help with creating URLs (or history tokens) for {@link Place} instances.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class PlaceUrlCreator {

  private static final Joiner TOKEN_JOINER = Joiner.on(NavigationConstants.TOKEN_SEPARATOR);

  private final Location location;

  @Inject PlaceUrlCreator(Location location) {
    this.location = location;
  }

  <T extends Place> ImmutableList<String> toHistoryTokenParts(T place) {
    @SuppressWarnings("unchecked")
    PlaceHandler<T> handler = (PlaceHandler<T>) place.getPlaceHandler();
    return handler.toHistoryTokenParts(place);
  }

  /**
   * Creates a new history token from a given place instance.
   */
  String createHistoryToken(Place place) {
    PlaceHandler<?> handler = place.getPlaceHandler();

    return "!" + TOKEN_JOINER.join(Iterables.concat(
        ImmutableList.of(handler.getPlaceIdentifierToken()), toHistoryTokenParts(place)));
  }

  /**
   * Constructs a full URL, based off of the curren URL, to the given place.
   */
  String createPlaceUrl(Place place) {
    UrlBuilder builder = location.createUrlBuilder();
    builder.setPath(place.getApplication().getApplicationPath());
    builder.setHash(createHistoryToken(place));

    return builder.buildString();
  }
}
