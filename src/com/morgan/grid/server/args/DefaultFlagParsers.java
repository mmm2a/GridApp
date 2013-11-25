package com.morgan.grid.server.args;

import com.google.common.primitives.Primitives;

/**
 * Factory class for getting {@link FlagParser} instances for the default types.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class DefaultFlagParsers {

  private DefaultFlagParsers() {
    // Prevent instantiation.
  }

  /**
   * Gets the {@link FlagParser} for flags which are shorts.
   */
  public static FlagParser<Short> shortFlagParser() {
    return new AbstractFlagParser<Short>() {
      @Override public Short fromString(String string) {
        return Short.parseShort(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are ints.
   */
  public static FlagParser<Integer> intFlagParser() {
    return new AbstractFlagParser<Integer>() {
      @Override public Integer fromString(String string) {
        return Integer.parseInt(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are longs.
   */
  public static FlagParser<Long> longFlagParser() {
    return new AbstractFlagParser<Long>() {
      @Override public Long fromString(String string) {
        return Long.parseLong(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are floats.
   */
  public static FlagParser<Float> floatFlagParser() {
    return new AbstractFlagParser<Float>() {
      @Override public Float fromString(String string) {
        return Float.parseFloat(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are doubles.
   */
  public static FlagParser<Double> doubleFlagParser() {
    return new AbstractFlagParser<Double>() {
      @Override public Double fromString(String string) {
        return Double.parseDouble(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are booleans.
   */
  public static FlagParser<Boolean> booleanFlagParser() {
    return new AbstractFlagParser<Boolean>() {
      @Override public Boolean fromString(String string) {
        return Boolean.parseBoolean(string);
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are strings.
   */
  public static FlagParser<String> stringFlagParser() {
    return new AbstractFlagParser<String>() {
      @Override public String fromString(String string) {
        return string;
      }
    };
  }

  /**
   * Gets the {@link FlagParser} for flags which are enums.
   */
  public static <T extends Enum<T>> FlagParser<T> enumFlagParser(final Class<T> enumClass) {
    return new FlagParser<T>() {
      @Override public String toString(T value) {
        return value.name();
      }

      @Override public T fromString(String string) {
        return Enum.valueOf(enumClass, string);
      }
    };
  }

  /**
   * Determines whether or not the given flag type has a default flag parser.
   */
  public static boolean hasDefaultFlagParser(Class<?> flagType) {
    if (flagType.equals(void.class) || flagType.equals(Void.class)) {
      return false;
    }

    return Primitives.allPrimitiveTypes().contains(flagType)
        || Primitives.allWrapperTypes().contains(flagType)
        || flagType.isEnum()
        || flagType.equals(String.class);
  }

  /**
   * Returns the flag parser that matches a given flag type.  If no such flag parser exists,
   * this method throws an {@link IllegalStateException}.
   */
  @SuppressWarnings("unchecked")
  public static FlagParser<?> getFlagParserFor(Class<?> flagType) {
    if (Primitives.allPrimitiveTypes().contains(flagType)) {
      flagType = Primitives.wrap(flagType);
    }

    if (flagType.isEnum()) {
      return enumFlagParser(flagType.asSubclass(Enum.class));
    }

    if (flagType.equals(String.class)) {
      return stringFlagParser();
    }

    if (flagType.equals(Short.class)) {
      return shortFlagParser();
    }

    if (flagType.equals(Integer.class)) {
      return intFlagParser();
    }

    if (flagType.equals(Long.class)) {
      return longFlagParser();
    }

    if (flagType.equals(Float.class)) {
      return floatFlagParser();
    }

    if (flagType.equals(Double.class)) {
      return doubleFlagParser();
    }

    if (flagType.equals(Boolean.class)) {
      return booleanFlagParser();
    }

    throw new IllegalStateException("Unable to find default flag parser for type " + flagType);
  }
}
