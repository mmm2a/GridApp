package com.morgan.grid.client.common.constants;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gwt.i18n.client.Dictionary;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * A constants dictionary that gets filled by the server when the page is loaded.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
public class ConstantsDictionary {

  private static final String CONSTANTS_DICTIONARY_NAME = "Constants";

  private final ImmutableMap<DictionaryConstant, String> constantMap;

  @Inject @VisibleForTesting protected ConstantsDictionary() {
    ImmutableMap.Builder<DictionaryConstant, String> mapBuilder = ImmutableMap.builder();
    Dictionary dictionary = getDictionary();
    for (String key : dictionary.keySet()) {
      mapBuilder.put(findConstant(key), dictionary.get(key));
    }
    constantMap = mapBuilder.build();
  }

  protected Dictionary getDictionary() {
    return Dictionary.getDictionary(CONSTANTS_DICTIONARY_NAME);
  }

  public String get(DictionaryConstant constant) {
    String result = constantMap.get(constant);
    Preconditions.checkState(result != null);
    return result;
  }

  public int getInteger(DictionaryConstant constant) {
    return Integer.parseInt(get(constant));
  }

  public double getDouble(DictionaryConstant constant) {
    return Double.parseDouble(get(constant));
  }

  private static DictionaryConstant findConstant(String name) {
    for (DictionaryConstant constant : DictionaryConstant.values()) {
      if (name.equals(constant.getConstantName())) {
        return constant;
      }
    }

    throw new IllegalStateException("Unable to find constant \"" + name + "\"");
  }
}
