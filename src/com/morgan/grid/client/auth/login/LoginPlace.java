package com.morgan.grid.client.auth.login;

import com.morgan.grid.client.common.navigation.Place;
import com.morgan.grid.client.common.navigation.PlaceHandler;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * A {@link Place} type for logging in to the application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class LoginPlace extends Place {

  LoginPlace(PlaceHandler<LoginPlace> placeHandler) {
    super(Application.AUTH, placeHandler);
  }
}
