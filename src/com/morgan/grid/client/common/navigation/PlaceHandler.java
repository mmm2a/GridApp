package com.morgan.grid.client.common.navigation;

import java.util.List;

import com.google.common.collect.ImmutableList;


/**
 * An interface for a type that can generate and parse place tokens.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> the type of place handled by this place handler.
 */
public interface PlaceHandler<T extends Place> {

  /**
   * Retrieves the token used to identify the place that goes with this place handler.
   */
  String getPlaceIdentifierToken();

  /**
   * Returns the history token for a given place of the correct type.
   */
  ImmutableList<String> toHistoryTokenParts(T place);

  /**
   * Returns a parsed place for the given history token.
   *
   * @throws IllegalStateException if the place cannot be parsed from the history token.
   */
  T fromHistoryTokenParts(List<String> historyTokenParts) throws IllegalStateException;
}
