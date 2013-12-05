package com.morgan.grid.shared.common.navigation;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * An enumeration representing the different GWT applications in the server.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public enum Application {
  AUTH("auth"),
  GRID("grid");

  private final String applicationPathPostfix;

  private Application(String applicationPathPostfix) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(applicationPathPostfix));
    this.applicationPathPostfix = applicationPathPostfix;
  }

  /**
   * Retrieves the relative path on the server to this application.
   */
  public String getApplicationPath() {
    return NavigationConstants.APP_PATH_PREFIX + "/" + applicationPathPostfix;
  }

  /**
   * Returns only the application postfix for this application on the server.
   */
  public String getApplicationPathPostfix() {
    return applicationPathPostfix;
  }
}
