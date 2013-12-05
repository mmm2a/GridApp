package com.morgan.grid.client.auth.login;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.morgan.grid.client.common.navigation.PlaceHandler;

public class LoginPlaceHandler implements PlaceHandler<LoginPlace> {

  public static final String PLACE_IDENTIFIER_TOKEN = "login";

  @Override public String getPlaceIdentifierToken() {
    return PLACE_IDENTIFIER_TOKEN;
  }

  @Override public ImmutableList<String> toHistoryTokenParts(LoginPlace place) {
    return ImmutableList.of();
  }

  @Override public LoginPlace fromHistoryTokenParts(List<String> historyToken)
      throws IllegalStateException {
    Preconditions.checkState(historyToken.isEmpty());
    return createLoginPlace();
  }

  /**
   * Creates a {@link LoginPlace}.
   */
  public LoginPlace createLoginPlace() {
    return new LoginPlace(this);
  }
}
