package com.morgan.grid.server;

import com.morgan.grid.server.args.Flag;
import com.morgan.grid.server.args.FlagAccessor;

/**
 * A flag interface for the web server.  This interface should contain only flags that are directly
 * related to starting/stopping/configuring the server itself.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface WebServerFlags extends FlagAccessor {

  @Flag(name = "server-port",
      description = "The web server's listening port",
      defaultValue = "8080")
  int serverPort();

  @Flag(name = "server-type",
      description = "Indicates the type of web server to run",
      defaultValue = "FAKE")
  WebServerType webServerType();
}
