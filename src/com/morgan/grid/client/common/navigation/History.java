package com.morgan.grid.client.common.navigation;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

/**
 * A wrapper class around the {@link com.google.gwt.user.client.History} class to make testing
 * easier.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class History {

  @Inject History() {
  }

  /** @see com.google.gwt.user.client.History#addValueChangeHandler(ValueChangeHandler) */
  void addValueChangeHandler(ValueChangeHandler<String> valueChangeHandler) {
    com.google.gwt.user.client.History.addValueChangeHandler(valueChangeHandler);
  }

  /** @see com.google.gwt.user.client.History#back() */
  void back() {
    com.google.gwt.user.client.History.back();
  }

  /** @see com.google.gwt.user.client.History#encodeHistoryToken(String) */
  void encodeHistoryToken(String historyToken) {
    com.google.gwt.user.client.History.encodeHistoryToken(historyToken);
  }

  /** @see com.google.gwt.user.client.History#fireCurrentHistoryState() */
  void fireCurrentHistoryState() {
    com.google.gwt.user.client.History.fireCurrentHistoryState();
  }

  /** @see com.google.gwt.user.client.History#forward() */
  void forward() {
    com.google.gwt.user.client.History.forward();
  }

  /** @see com.google.gwt.user.client.History#getToken() */
  String getToken() {
    return com.google.gwt.user.client.History.getToken();
  }

  /** @see com.google.gwt.user.client.History#newItem(String) */
  void newItem(String historyToken) {
    com.google.gwt.user.client.History.newItem(historyToken);
  }

  /** @see com.google.gwt.user.client.History#newItem(String, boolean) */
  void newItem(String historyToken, boolean issueEvent) {
    com.google.gwt.user.client.History.newItem(historyToken, issueEvent);
  }
}
