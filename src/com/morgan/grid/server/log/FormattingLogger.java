package com.morgan.grid.server.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * A wrapper around the {@link Logger} class that makes it easier to use.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class FormattingLogger {

  private final Logger delegate;

  public FormattingLogger(Logger delegate) {
    this.delegate = Preconditions.checkNotNull(delegate);
  }

  private FormattingLogger log(Level level,
      @Nullable Throwable cause, String format, Object...args) {
    if (cause != null) {
      delegate.log(level, String.format(format, args), cause);
    } else {
      delegate.log(level, String.format(format, args));
    }

    return this;
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#FINEST} log level.
   */
  public FormattingLogger finest(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.FINEST, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#FINER} log level.
   */
  public FormattingLogger finer(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.FINER, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#FINE} log level.
   */
  public FormattingLogger fine(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.FINE, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#CONFIG} log level.
   */
  public FormattingLogger config(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.CONFIG, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#INFO} log level.
   */
  public FormattingLogger info(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.INFO, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#WARNING} log level.
   */
  public FormattingLogger warning(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.WARNING, cause, format, args);
  }

  /**
   * Log a message, with an optional exception, at the {@link Level#SEVERE} log level.
   */
  public FormattingLogger severe(@Nullable Throwable cause, String format, Object...args) {
    return log(Level.SEVERE, cause, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#FINEST} log level.
   */
  public FormattingLogger finest(String format, Object...args) {
    return log(Level.FINEST, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#FINER} log level.
   */
  public FormattingLogger finer(String format, Object...args) {
    return log(Level.FINER, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#FINE} log level.
   */
  public FormattingLogger fine(String format, Object...args) {
    return log(Level.FINE, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#CONFIG} log level.
   */
  public FormattingLogger config(String format, Object...args) {
    return log(Level.CONFIG, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#INFO} log level.
   */
  public FormattingLogger info(String format, Object...args) {
    return log(Level.INFO, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#WARNING} log level.
   */
  public FormattingLogger warning(String format, Object...args) {
    return log(Level.WARNING, null, format, args);
  }

  /**
   * Log a message without an exception at the {@link Level#SEVERE} log level.
   */
  public FormattingLogger severe(String format, Object...args) {
    return log(Level.SEVERE, null, format, args);
  }

  /**
   * Creates and returns a new {@link FormattingLogger} that logs in the namespace of the class
   * that is given.
   */
  static public FormattingLogger getInstanceFor(Class<?> clazz) {
    return new FormattingLogger(Logger.getLogger(clazz.getName()));
  }
}