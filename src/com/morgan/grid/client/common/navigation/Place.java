package com.morgan.grid.client.common.navigation;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.gwt.core.client.GWT;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * Represents a location inside the grid app.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public abstract class Place {

  private final Application application;
  private final PlaceHandler<?> placeHandler;

  protected Place(Application application, PlaceHandler<?> placeHandler) {
    this.application = Preconditions.checkNotNull(application);
    this.placeHandler = Preconditions.checkNotNull(placeHandler);
  }

  public Application getApplication() {
    return application;
  }

  public PlaceHandler<?> getPlaceHandler() {
    return placeHandler;
  }

  protected ToStringHelper addToStringFields(ToStringHelper helper) {
    return helper.add("application", application);
  }

  @Override public int hashCode() {
    return application.hashCode();
  }

  @Override public boolean equals(Object o) {
    if (!(o instanceof Place)) {
      return false;
    }

    return application == ((Place) o).application;
  }

  @Override public final String toString() {
    if (GWT.isScript()) {
      return super.toString();
    }

    return addToStringFields(Objects.toStringHelper(getClass())).toString();
  }
}
