package com.morgan.grid.client.auth.login;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.morgan.grid.client.common.navigation.PageFactory;

/**
 * A page factory for displaying the login page.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class LoginPageFactory implements PageFactory<LoginPlace> {

  @Override public IsWidget createPageFor(LoginPlace place) {
    return new Label("Login page");
  }
}
