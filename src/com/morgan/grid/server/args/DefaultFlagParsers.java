package com.morgan.grid.server.args;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Primitives;
import com.morgan.grid.shared.convert.DefaultConverter;

/**
 * A factory for default flag parsers (mostly the primitive types).
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class DefaultFlagParsers {

  private DefaultFlagParsers() {
    // Prevent instantiation.
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Byte} and {@link byte} flag types.
   */
  public static FlagParser byteFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Byte>() {
      @Override protected Byte applyNonNull(String input) {
        return Byte.parseByte(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Character} and {@link char} flag types.
   */
  public static FlagParser charFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Character>() {
      @Override protected Character applyNonNull(String input) {
        Preconditions.checkArgument(input.length() == 1);
        return input.charAt(0);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Short} and {@link short} flag types.
   */
  public static FlagParser shortFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Short>() {
      @Override protected Short applyNonNull(String input) {
        return Short.parseShort(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Integer} and {@link int} flag types.
   */
  public static FlagParser intFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Integer>() {
      @Override protected Integer applyNonNull(String input) {
        return Integer.parseInt(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Long} and {@link long} flag types.
   */
  public static FlagParser longFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Long>() {
      @Override protected Long applyNonNull(String input) {
        return Long.parseLong(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Float} and {@link float} flag types.
   */
  public static FlagParser floatFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Float>() {
      @Override protected Float applyNonNull(String input) {
        return Float.parseFloat(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Double} and {@link double} flag types.
   */
  public static FlagParser doubleFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Double>() {
      @Override protected Double applyNonNull(String input) {
        return Double.parseDouble(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Boolean} and {@link boolean} flag types.
   */
  public static FlagParser booleanFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Boolean>() {
      @Override protected Boolean applyNonNull(String input) {
        return Boolean.parseBoolean(input);
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link Void} and {@link void} flag types.
   */
  public static FlagParser voidFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<Void>() {
      @Override protected Void applyNonNull(String input) {
        Preconditions.checkArgument(input.isEmpty());
        return null;
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling {@link String} flag types.
   */
  public static FlagParser stringFlagParser() {
    return new DefaultFlagParser(new DelegatingNullFunction<String>() {
      @Override protected String applyNonNull(String input) {
        return input;
      }
    });
  }

  /**
   * Gets a {@link FlagParser} suitable for handling enum instance flag types.
   */
  public static <T extends Enum<T>> FlagParser enumFlagParser(final Class<T> enumType) {
    return new DefaultFlagParser(new DelegatingNullFunction<T>() {
      @Override protected T applyNonNull(String input) {
        return Enum.valueOf(enumType, input);
      }
    });
  }

  @SuppressWarnings("unchecked")
  public static FlagParser getFlagParserFor(Class<?> valueType) {
    if (valueType.isEnum()) {
      return enumFlagParser(valueType.asSubclass(Enum.class));
    }

    if (valueType.equals(String.class)) {
      return stringFlagParser();
    }

    if (valueType.isPrimitive()) {
      valueType = Primitives.wrap(valueType);
    }

    if (valueType.equals(Byte.class)) {
      return byteFlagParser();
    } else if (valueType.equals(Character.class)) {
      return charFlagParser();
    } else if (valueType.equals(Short.class)) {
      return shortFlagParser();
    } else if (valueType.equals(Integer.class)) {
      return intFlagParser();
    } else if (valueType.equals(Long.class)) {
      return longFlagParser();
    } else if (valueType.equals(Float.class)) {
      return floatFlagParser();
    } else if (valueType.equals(Double.class)) {
      return doubleFlagParser();
    } else if (valueType.equals(Boolean.class)) {
      return booleanFlagParser();
    }

    throw new UnsupportedOperationException(String.format(
        "Can't find default flag parser for type %s", valueType));
  }

  /**
   * Abstract implementation of a {@link Function} that automatically returns {@code null} for
   * {@code null} inputs, and delegates all other values to an abstract method.
   *
   * @param <T> the type being created from the input string.
   */
  private static abstract class DelegatingNullFunction<T> implements Function<String, T> {
    protected abstract T applyNonNull(String input);

    @Override @Nullable public T apply(@Nullable String input) {
      if (input == null) {
        return null;
      }

      return applyNonNull(input);
    }
  }

  /**
   * Default implementation of a {@link FlagParser} that uses {@link Object#toString()} to convert
   * types to their {@link String} representation.
   */
  private static class DefaultFlagParser
      extends DefaultConverter<Object, String> implements FlagParser {

    public DefaultFlagParser(Function<? super String, ? extends Object> backwardConverter) {
      super(Functions.toStringFunction(), backwardConverter);
    }
  }
}
