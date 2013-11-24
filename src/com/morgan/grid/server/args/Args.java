package com.morgan.grid.server.args;

import javax.annotation.Nullable;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;


/**
 * A class that helps with parsing and retrieval of command line arguments and flags.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class Args {

  private final ArgumentStore store;

  @VisibleForTesting protected Args(ArgumentStore store) {
    this.store = Preconditions.checkNotNull(store);
  }

  @Nullable private static Args instance;

  ArgumentStore getArgumentStore() {
    return store;
  }

  @VisibleForTesting synchronized static void reset() {
    instance = null;
  }

  /**
   * Gets the global instance of the {@link Args} class.
   *
   * @throws IllegalStateException if the global instances hasn't yet been created by a call to
   *     {@link Args#parse(String[])}.
   */
  synchronized public static Args getInstance() throws IllegalStateException {
    Preconditions.checkState(instance != null);
    return instance;
  }

  /**
   * Parses and stores the command line for an application.  This method is a static method
   * because of the way that arguments are kept in the application.  This method can only be called
   * once per application run.
   *
   * @throws IllegalStateException if this method is called more than once in an application's
   *     lifetime.
   */
  synchronized public static Args parse(String []commandLine) throws IllegalStateException {
    Preconditions.checkState(instance == null);

    ArgsParser parser = new ArgsParser();
    for (String arg : commandLine) {
      parser.addArgument(arg);
    }

    instance = new Args(parser.buildStore());
    return getInstance();
  }
}
