package com.morgan.grid.shared.common.feature;

/**
 * An interface for a type that can be used to determine whether or not a given feature is enabled.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface FeatureChecker {
  /**
   * Indicates whether or not a given feature is enabled for the current request/client.
   */
  boolean isEnabled(Feature feature);
}
