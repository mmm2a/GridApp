package com.morgan.grid.server;

import com.google.inject.AbstractModule;
import com.morgan.grid.server.args.FlagsModule;
import com.morgan.grid.server.auth.AuthModule;
import com.morgan.grid.server.common.CommonModule;
import com.morgan.grid.server.db.DatabaseModule;
import com.morgan.grid.server.grid.GridModule;

/**
 * Main GUICe module for the grid application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class GridServerModule extends AbstractModule {

  @Override protected void configure() {
    install(new FlagsModule(WebServerFlags.class));
    install(new DatabaseModule());
    install(new CommonModule());
    install(new GridModule());
    install(new AuthModule());
  }
}
