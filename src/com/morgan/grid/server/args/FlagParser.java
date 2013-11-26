package com.morgan.grid.server.args;

import com.morgan.grid.shared.convert.Converter;

/**
 * An interface for a type that can parse flags from string representations and print them back
 * out as string representations.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public interface FlagParser extends Converter<Object, String> {
}
