package com.morgan.grid.client.auth;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.multibindings.GinMapBinder;
import com.google.inject.Provides;
import com.morgan.grid.client.auth.login.LoginPageFactory;
import com.morgan.grid.client.auth.login.LoginPlace;
import com.morgan.grid.client.auth.login.LoginPlaceHandler;
import com.morgan.grid.client.common.CommonGinModule;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.DefaultPlace;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PageFactories;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PlaceHandlers;
import com.morgan.grid.client.common.navigation.NavigationModule;
import com.morgan.grid.client.common.navigation.PageFactory;
import com.morgan.grid.client.common.navigation.Place;
import com.morgan.grid.client.common.navigation.PlaceHandler;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * GIN module for the auth app package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class AuthAppGinModule extends AbstractGinModule {

  @Override protected void configure() {
    install(new CommonGinModule());
    install(new NavigationModule());

    addPlaceHandlerBindings();
    addPageFactoryBindings();
  }

  private void addPlaceHandlerBindings() {
    @SuppressWarnings("rawtypes")
    GinMapBinder<String, PlaceHandler> binder =
        GinMapBinder.newMapBinder(binder(), String.class, PlaceHandler.class, PlaceHandlers.class);

    binder.addBinding(LoginPlaceHandler.PLACE_IDENTIFIER_TOKEN).to(LoginPlaceHandler.class);

  }

  private void addPageFactoryBindings() {
    @SuppressWarnings("rawtypes")
    GinMapBinder<Class, PageFactory> binder =
        GinMapBinder.newMapBinder(binder(), Class.class, PageFactory.class, PageFactories.class);

    binder.addBinding(LoginPlace.class).to(LoginPageFactory.class);
  }

  @Provides protected Application provideThisApplication() {
    return Application.AUTH;
  }

  @Provides @DefaultPlace Place provideDefaultPlace(LoginPlaceHandler handler) {
    return handler.createLoginPlace();
  }
}
