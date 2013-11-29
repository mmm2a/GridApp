package com.morgan.grid.server.common.feature;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

/**
 * An interface for a type that can parse feature predicate strings.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface FeaturePredicateParser {
  /**
   * Parses a single type of predicate string into a predicate if possible (or returns {@code null}
   * if this parser doesn't now how to parse the string).
   */
  @Nullable Predicate<FeatureContext> parsePredicateString(String predicateString);
}
