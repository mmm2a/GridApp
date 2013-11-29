package com.morgan.grid.server.common.feature;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.morgan.grid.shared.common.feature.FeatureChecker;

/**
 * A GUICE module for the features package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class FeatureModule extends AbstractModule {

  @Override protected void configure() {
    install(new FeatureConstantsModule());

    addFeaturePredicateParsers(Multibinder.newSetBinder(binder(), FeaturePredicateParser.class));

    bind(FeatureChecker.class).to(ServerFeatureChecker.class);
  }

  private void addFeaturePredicateParsers(Multibinder<FeaturePredicateParser> multibinder) {
    multibinder.addBinding().to(DisabledFeaturePredicateParser.class);
  }
}
