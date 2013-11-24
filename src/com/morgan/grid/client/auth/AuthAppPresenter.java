package com.morgan.grid.client.auth;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.morgan.grid.client.common.AppPresenter;
import com.morgan.grid.client.common.constants.ConstantsDictionary;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Main {@link AppPresenter} for the auth app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class AuthAppPresenter implements AppPresenter {

  private final ConstantsDictionary dictionary;
  private final AuthServiceAsync authService;

  @Inject AuthAppPresenter(ConstantsDictionary dictionary, AuthServiceAsync authService) {
    this.dictionary = dictionary;
    this.authService = authService;
  }

  @Override public void startApp() {
    authService.getHelloMessage(dictionary.get(DictionaryConstant.AUTH_TEST_CONSTANT),
        new AsyncCallback<String>() {
          @Override public void onSuccess(String result) {
            RootPanel.get().add(new Label(result));
          }

          @Override public void onFailure(Throwable caught) {
            RootPanel.get().add(new Label(caught.toString()));
          }
        });
  }
}
