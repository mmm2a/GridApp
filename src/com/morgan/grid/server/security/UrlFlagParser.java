package com.morgan.grid.server.security;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.inject.Inject;
import com.morgan.grid.server.args.FlagParser;

/**
 * Class to parse URIs from strings.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class UrlFlagParser implements FlagParser {

  @Inject UrlFlagParser() {
  }

  @Override public Function<? super Object, ? extends String> getForwardFunction() {
    return Functions.toStringFunction();
  }

  @Override public Function<? super String, ? extends Object> getReverseFunction() {
    return new Function<String, URL>() {
      @Override public URL apply(String input) {
        try {
          return URI.create(input).toURL();
        } catch (MalformedURLException e) {
          throw new IllegalArgumentException(String.format(
              "Unable to parse %s as a URL", input), e);
        }
      }
    };
  }
}
