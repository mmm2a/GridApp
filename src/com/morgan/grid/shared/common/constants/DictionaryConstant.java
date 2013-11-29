package com.morgan.grid.shared.common.constants;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * An enumeration representing the set of known dictionary constants.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public enum DictionaryConstant {

  DISABLED_FEATURES("disabledFeatures"),
  AUTH_TEST_CONSTANT("authTestConstant"),
  GRID_TEST_CONSTANT("gridTestConstant");

  private final String constantName;

  private DictionaryConstant(String constantName) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(constantName));

    this.constantName = constantName;
  }

  public String getConstantName() {
    return constantName;
  }
}
