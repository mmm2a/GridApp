package com.morgan.grid.client.common.navigation;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PlaceHandlers;
import com.morgan.grid.shared.common.navigation.NavigationConstants;

/**
 * A class that can be used to parse history tokens into {@link Place} instances.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class PlaceParser {

  private static final Splitter TOKEN_PART_SPLITTER = Splitter.on(
      NavigationConstants.TOKEN_SEPARATOR).omitEmptyStrings().trimResults();

  @SuppressWarnings("rawtypes")
  private final ImmutableMap<String, PlaceHandler> placeHandlerMap;

  @Inject PlaceParser(
      @SuppressWarnings("rawtypes") @PlaceHandlers Map<String, PlaceHandler> placeHandlerMap) {
    this.placeHandlerMap = ImmutableMap.copyOf(placeHandlerMap);
  }

  /**
   * Parses a given history token into the place it represents (if possible).  Otherwise, returns
   * {@code null}.
   */
  public Place parseHistoryToken(String historyToken) {
    // Strip off any leading #! or ! characters
    if (historyToken.startsWith("#!")) {
      historyToken = historyToken.substring(2);
    } else if (historyToken.startsWith("!")) {
      historyToken = historyToken.substring(1);
    }

    // Turn the toke into its parts
    List<String> parts = TOKEN_PART_SPLITTER.splitToList(historyToken);
    if (parts.isEmpty()) {
      return null;
    }

    PlaceHandler<?> handler = placeHandlerMap.get(parts.get(0));
    if (handler == null) {
      return null;
    }

    return handler.fromHistoryTokenParts(parts.subList(1, parts.size()));
  }
}
