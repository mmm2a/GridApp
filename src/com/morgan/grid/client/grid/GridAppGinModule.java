package com.morgan.grid.client.grid;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.multibindings.GinMapBinder;
import com.google.inject.Provides;
import com.morgan.grid.client.common.CommonGinModule;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.DefaultPlace;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PageFactories;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PlaceHandlers;
import com.morgan.grid.client.common.navigation.PageFactory;
import com.morgan.grid.client.common.navigation.Place;
import com.morgan.grid.client.common.navigation.PlaceHandler;
import com.morgan.grid.client.grid.browse.BrowsePageFactory;
import com.morgan.grid.client.grid.browse.BrowsePlace;
import com.morgan.grid.client.grid.browse.BrowsePlaceHandler;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * GIN module for the grid app package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class GridAppGinModule extends AbstractGinModule {

  @Override protected void configure() {
    install(new CommonGinModule());

    addPlaceHandlerBindings();
    addPageFactoryBindings();
  }

  private void addPlaceHandlerBindings() {
    @SuppressWarnings("rawtypes")
    GinMapBinder<String, PlaceHandler> binder =
        GinMapBinder.newMapBinder(binder(), String.class, PlaceHandler.class, PlaceHandlers.class);

    binder.addBinding(BrowsePlaceHandler.PLACE_IDENTIFIER_TOKEN).to(BrowsePlaceHandler.class);
  }

  private void addPageFactoryBindings() {
    @SuppressWarnings("rawtypes")
    GinMapBinder<Class, PageFactory> binder =
        GinMapBinder.newMapBinder(binder(), Class.class, PageFactory.class, PageFactories.class);

    binder.addBinding(BrowsePlace.class).to(BrowsePageFactory.class);
  }

  @Provides protected Application provideThisApplication() {
    return Application.GRID;
  }

  @Provides @DefaultPlace Place provideDefaultPlace(BrowsePlaceHandler handler) {
    return handler.createBrowsePlace();
  }
}
