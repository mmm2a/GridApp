package com.morgan.grid.client.common.navigation;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.morgan.grid.client.common.navigation.NavigationBindingAnnotations.PageFactories;

/**
 * A widget that represents the ENTIRE application page.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
class ApplicationPagePresenter implements IsWidget {

  @SuppressWarnings("rawtypes")
  private final ImmutableMap<Class, PageFactory> pageFactoryMap;

  private final SimplePanel contentPanel;

  @Inject ApplicationPagePresenter(
      @SuppressWarnings("rawtypes") @PageFactories Map<Class, PageFactory> pageFactoryMap) {
    this.pageFactoryMap = ImmutableMap.copyOf(pageFactoryMap);
    contentPanel = new SimplePanel();
  }

  /**
   * Sets the current view to be the view for the indicated place.
   */
  <T extends Place> void showPageForPlace(T place) {
    @SuppressWarnings("unchecked")
    PageFactory<T> pageFactory = pageFactoryMap.get(place.getClass());
    Preconditions.checkState(pageFactory != null);
    contentPanel.setWidget(pageFactory.createPageFor(place));
  }

  @Override public Widget asWidget() {
    return contentPanel.asWidget();
  }
}
