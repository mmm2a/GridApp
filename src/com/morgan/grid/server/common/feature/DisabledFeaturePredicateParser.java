package com.morgan.grid.server.common.feature;

import javax.annotation.Nullable;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Implementation of a {@link FeaturePredicateParser} that looks for the string token "disabled" to
 * disable a feature.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class DisabledFeaturePredicateParser implements FeaturePredicateParser {

  @VisibleForTesting static final String DISABLED_TOKEN = "disabled";

  @Override @Nullable public Predicate<FeatureContext> parsePredicateString(
      String predicateString) {
    if (predicateString.equals(DISABLED_TOKEN)) {
      return Predicates.alwaysFalse();
    } else {
      return null;
    }
  }
}
