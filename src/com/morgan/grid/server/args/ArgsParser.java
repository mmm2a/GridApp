package com.morgan.grid.server.args;

import com.google.common.base.Preconditions;

/**
 * A class to help with parsing command line arguments into an argument store.
 *
 * The {@link ArgsParser} parses command lines as follows:
 * <ul>
 *   <li>If a double-dash ("--") is encountered alone, then that double dash is <b>not</b> added to
 *       the argument store and all arguments that follow are added as arguments regardless of their
 *       pattern.
 *   <li>If an argument is encountered which starts with a double-dash ("--"), then the text that
 *       follows is parsed as a long flag.  Long flags which have an equals size will receive the
 *       value of the string that comes after the equals.  Those that do not have an equals sign
 *       will receive a value of true unless they start with the word "no", in which case they
 *       receive a value of false.
 *   <li>If an argument is encountered which starts with a single-dash ("-"), then every letter that
 *       follows is parsed as a short flag (meaning each letter is added as a flag with a value of
 *       true) until the end or until a single letter flag is followed by an equals sign ("=").  If
 *       a single flag letter is followed by an equals sign, then all text after the equals sign is
 *       assigned to that single letter flag (and only that single letter; all pre-ceeding lines are
 *       parsed as described earlier).
 *   <li>All arguments that do not match any of the above rules are simply added as non-flag command
 *       line arguments.
 * </ul>
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class ArgsParser {

  private static final String DOUBLE_DASH = "--";
  private static final String SINGLE_DASH = "-";
  private static final String NEGATE_PREFIX = "no";

  private final ArgumentStore.Builder builder = ArgumentStore.builder();

  private boolean seenDoubleDash = false;

  private void parseLongFlag(String longFlag) {
    int i = longFlag.indexOf('=');
    Preconditions.checkArgument(i != 0, "Illegal long flag %s", longFlag);

    String value;
    if (i > 0) {
      value = longFlag.substring(i + 1);
      longFlag = longFlag.substring(0, i);
    } else {
      if (longFlag.startsWith(NEGATE_PREFIX)) {
        longFlag = longFlag.substring(NEGATE_PREFIX.length());
        value = Boolean.toString(false);
      } else {
        value = Boolean.toString(true);
      }
    }

    builder.addFlag(longFlag, value);
  }

  private void parseShortFlag(String shortFlag) {
    int i = shortFlag.indexOf('=');
    Preconditions.checkArgument(i != 0, "Illegal short flag %s", shortFlag);

    if (i > 0) {
      parseLongFlag(shortFlag.substring(i - 1));
      shortFlag = shortFlag.substring(0, i - 1);
      for (int c = 0; c < shortFlag.length(); c++) {
        builder.addFlag(Character.toString(shortFlag.charAt(c)), Boolean.toString(true));
      }
    }
  }

  ArgsParser addArgument(String argument) {
    if (!seenDoubleDash) {
      if (argument.equals(DOUBLE_DASH)) {
        seenDoubleDash = true;
        return this;
      } else if (argument.startsWith(DOUBLE_DASH)) {
        parseLongFlag(argument.substring(DOUBLE_DASH.length()));
        return this;
      } else if (argument.startsWith(SINGLE_DASH)) {
        parseShortFlag(argument.substring(SINGLE_DASH.length()));
        return this;
      }
    }

    builder.addArguments(argument);
    return this;
  }

  ArgumentStore buildStore() {
    return builder.build();
  }
}
