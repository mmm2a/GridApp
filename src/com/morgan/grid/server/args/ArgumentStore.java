package com.morgan.grid.server.args;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Storage class for storing the result of the parsing of command line arguments.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class ArgumentStore {

  private final ImmutableMap<String, String> parsedFlags;
  private final ImmutableList<String> parsedArguments;

  private ArgumentStore(ImmutableMap<String, String> parsedFlags,
      ImmutableList<String> parsedArguments) {
    this.parsedFlags = Preconditions.checkNotNull(parsedFlags);
    this.parsedArguments = Preconditions.checkNotNull(parsedArguments);
  }

  ImmutableMap<String, String> getFlags() {
    return parsedFlags;
  }

  ImmutableList<String> getArguments() {
    return parsedArguments;
  }

  @Override public int hashCode() {
    return Objects.hashCode(parsedFlags, parsedArguments);
  }

  @Override public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof ArgumentStore)) {
      return false;
    }

    ArgumentStore other = (ArgumentStore) o;
    return parsedFlags.equals(other.parsedFlags)
        && parsedArguments.equals(other.parsedArguments);
  }

  static Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for the {@link ArgumentStore} class.
   *
   * @author mark@mark-morgan.net (Mark Morgan)
   */
  static class Builder {

    private final ImmutableMap.Builder<String, String> flagsBuilder = ImmutableMap.builder();
    private final ImmutableList.Builder<String> argumentsBuilder = ImmutableList.builder();

    private Builder() {
    }

    public Builder addFlag(String name, String value) {
      flagsBuilder.put(name, value);
      return this;
    }

    public Builder addArguments(String argument, String... otherArguments) {
      argumentsBuilder.add(argument);
      argumentsBuilder.add(otherArguments);
      return this;
    }

    public ArgumentStore build() {
      return new ArgumentStore(flagsBuilder.build(), argumentsBuilder.build());
    }
  }
}
