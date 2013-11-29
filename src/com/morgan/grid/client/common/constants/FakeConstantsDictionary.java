package com.morgan.grid.client.common.constants;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Fake version of the {@link ConstantsDictionary} for testing purposes.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FakeConstantsDictionary extends ConstantsDictionary {

  public FakeConstantsDictionary(Map<DictionaryConstant, String> constantMap) {
    super(ImmutableMap.copyOf(constantMap));
  }
}
