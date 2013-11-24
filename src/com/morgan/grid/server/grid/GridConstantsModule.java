package com.morgan.grid.server.grid;

import com.google.inject.Provides;
import com.morgan.grid.server.common.constants.AbstractConstantsModule;
import com.morgan.grid.server.common.constants.BindConstant;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * GUICE module related to constants that have to do with the grid app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class GridConstantsModule extends AbstractConstantsModule {

  @BindConstant(DictionaryConstant.GRID_TEST_CONSTANT)
  @Provides protected String provideGridTestConstant() {
    return "Grid application";
  }
}
