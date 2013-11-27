package com.morgan.grid.server;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * An enumeration representing the type of web server to run.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public enum WebServerType {

  /** Indicates a web server that uses live, real data from a grid */
  PRODUCTION,

  /** Indicates a web server that uses fake data */
  FAKE;

  @Nullable private static WebServerType thisServerType;

  /**
   * Sets the server type for this running instance.  This has to be called exactly once in the
   * lifetime of the application by the main method.
   */
  public static void setThisServerType(WebServerType serverType) {
    Preconditions.checkState(thisServerType == null);
    thisServerType = Preconditions.checkNotNull(serverType);
  }

  /**
   * Gets the web server type for this server.  Throws an {@link IllegalStateException} if the
   * server type hasn't yet been set by a call to {@link #setThisServerType(WebServerType)}.
   */
  public static WebServerType getThisServerType() {
    Preconditions.checkState(thisServerType != null);
    return thisServerType;
  }
}
