package com.morgan.grid.server.common.hostpage;

import java.io.Reader;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.servlet.ServletModule;

/**
 * GUICE module for the hostpage package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public abstract class HostPageServletModule extends ServletModule {

  protected void addApplication(String applicationPath,
      Class<?> contextClass, String relativeResourcePath) {
    URL resource = Resources.getResource(contextClass, relativeResourcePath);
    InputSupplier<? extends Reader> supplier = Resources.newReaderSupplier(
        resource, Charsets.UTF_8);

    MapBinder<String, InputSupplier<? extends Reader>> mapBinder = MapBinder.newMapBinder(binder(),
        new TypeLiteral<String>() {},
        new TypeLiteral<InputSupplier<? extends Reader>>() {},
        HostPageMap.class);
    mapBinder.addBinding(applicationPath).toInstance(supplier);

    serve(applicationPath, applicationPath + "/").with(HostPageServlet.class);
  }

  protected abstract void addApplications();

  @Override protected void configureServlets() {
    addApplications();
  }
}
