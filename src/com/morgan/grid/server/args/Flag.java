package com.morgan.grid.server.args;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation the describes a specific flag in a {@link FlagAccessor} interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Flag {
  /** Indicates the name of the flag */
  String name();

  /** Indicates a description for the flag */
  String description();

  /**
   * A default value that will be given to the flag in the absence of the flag being set on the
   * command line.
   */
  String defaultValue() default "";

  /** Indiciates what flag parser to use for parsing this flag. */
  Class<? extends FlagParser> flagParser() default FlagParser.class;
}
