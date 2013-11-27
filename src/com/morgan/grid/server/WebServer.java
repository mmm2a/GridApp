package com.morgan.grid.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

import com.google.inject.Guice;
import com.morgan.grid.server.args.Args;
import com.morgan.grid.server.args.FlagsModule;

/**
 * Main entry point for starting the web server.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class WebServer {

  public static void main(String[] args) throws Throwable {

    Args.parse(args);

    WebServerFlags flags = Guice.createInjector(new FlagsModule(WebServerFlags.class))
        .getInstance(WebServerFlags.class);
    WebServerType.setThisServerType(flags.webServerType());

    // Create an embedded Jetty server on port 8080
    Server server = new Server(flags.serverPort());

    // Create a handler for processing our GWT app
    WebAppContext handler = new WebAppContext();

    /* If we were a WAR
     * handler.setContextPath("/");
     * handler.setWar("./apps/GwtApplication.war");
     */

    // For when we aren't packaged as a WAR
    handler.setResourceBase("./war");
    handler.setDescriptor("./war/WEB-INF/web.xml");
    handler.setContextPath("/");
    handler.setParentLoaderPriority(true);

    // Add it to the server
    server.setHandler(handler);

    // Other misc. options
    server.setThreadPool(new QueuedThreadPool());

    // And start it up
    server.start();
    server.join();
}
}
