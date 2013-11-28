package com.morgan.grid.server.db;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * A GUICE module for adding a J2EE style persistence unit to the guice injection tree.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class DatabaseModule extends AbstractModule {

  private static final String PERSISTENCE_UNIT = "Grid";

  private final String persistenceUnit;

  public DatabaseModule() {
    this.persistenceUnit = PERSISTENCE_UNIT;
  }

  @Override protected void configure() {
    install(new JpaPersistModule(persistenceUnit));

    bind(DatabaseInitializationService.class).asEagerSingleton();
  }
}