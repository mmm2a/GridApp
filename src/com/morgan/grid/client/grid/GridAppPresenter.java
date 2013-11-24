package com.morgan.grid.client.grid;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.morgan.grid.client.common.AppPresenter;
import com.morgan.grid.client.common.constants.ConstantsDictionary;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * {@link AppPresenter} for the Grid app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class GridAppPresenter implements AppPresenter {

  private final ConstantsDictionary dictionary;
  private final GridServiceAsync gridService;

  @Inject GridAppPresenter(ConstantsDictionary dictionary, GridServiceAsync gridService) {
    this.dictionary = dictionary;
    this.gridService = gridService;
  }

  @Override public void startApp() {
    gridService.getHelloMessage(dictionary.get(DictionaryConstant.GRID_TEST_CONSTANT),
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
