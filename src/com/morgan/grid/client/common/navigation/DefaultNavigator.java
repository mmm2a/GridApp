package com.morgan.grid.client.common.navigation;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.DefaultPlace;
import com.morgan.grid.client.common.ui.RootPanel;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * An implementation of a {@link NavigationState} that also manages the navigation for an
 * application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class DefaultNavigator implements NavigationState, Navigator {

  private final ValueChangeHandler<String> valueChangeHandler = new ValueChangeHandler<String>() {
    @Override public void onValueChange(ValueChangeEvent<String> event) {
      handleNavigation(event.getValue());
    }
  };

  private final Application thisApplication;
  private final ApplicationPagePresenter applicationPage;

  private final Location location;

  private final History history;
  private final PlaceParser placeParser;
  private final PlaceUrlCreator placeUrlCreator;

  private final Place defaultPlace;

  @Nullable private Place currentPlace;

  @Inject DefaultNavigator(
      RootPanel rootPanel,
      Application thisApplication,
      ApplicationPagePresenter applicationPage,
      Location location,
      History history,
      PlaceParser placeParser,
      PlaceUrlCreator placeUrlCreator,
      @DefaultPlace Place defaultPlace) {
    this.thisApplication = thisApplication;
    this.applicationPage = applicationPage;
    this.location = location;
    this.history = history;
    this.placeParser = placeParser;
    this.placeUrlCreator = placeUrlCreator;

    this.defaultPlace = defaultPlace;

    rootPanel.add(applicationPage);

    history.addValueChangeHandler(valueChangeHandler);
    history.fireCurrentHistoryState();
  }

  private void handleNavigation(String newHistoryToken) {
    Place newPlace = placeParser.parseHistoryToken(newHistoryToken);
    if (newPlace == null) {
      navigateTo(defaultPlace);
    } else {
      currentPlace = newPlace;
      applicationPage.showPageForPlace(newPlace);
    }
  }

  @Override public Place getCurrentPlace() {
    Preconditions.checkState(currentPlace != null);
    return currentPlace;
  }

  @Override public void navigateTo(Place place) {
    if (place.getApplication() == thisApplication) {
      history.newItem(placeUrlCreator.createHistoryToken(place), true);
    } else {
      location.assign(placeUrlCreator.createPlaceUrl(place));
    }
  }
}
