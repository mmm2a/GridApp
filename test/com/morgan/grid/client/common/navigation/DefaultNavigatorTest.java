package com.morgan.grid.client.common.navigation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.morgan.grid.client.common.ui.RootPanel;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * Tests for the {@link DefaultNavigator} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultNavigatorTest {

  private static final Application THIS_APPLICATION = Application.AUTH;
  private static final Application OTHER_APPLICATION = Application.GRID;

  @Mock private RootPanel mockRootPanel;
  @Mock private ApplicationPagePresenter mockApplicationPage;
  @Mock private Location mockLocation;
  @Mock private History mockHistory;
  @Mock private PlaceParser mockPlaceParser;
  @Mock private PlaceUrlCreator mockPlaceUrlCreator;

  @Mock private Place mockPlace1;
  @Mock private Place mockPlace2;
  @Mock private Place mockPlace3;

  @Mock private PlaceHandler<Place> mockPlaceHandler1;
  @Mock private PlaceHandler<Place> mockPlaceHandler2;
  @Mock private PlaceHandler<Place> mockPlaceHandler3;

  @Captor private ArgumentCaptor<ValueChangeHandler<String>> valueChangeHandlerCaptor;

  private DefaultNavigator navigator;

  @Before public void createTestInstances() {
    navigator = new DefaultNavigator(mockRootPanel,
        THIS_APPLICATION,
        mockApplicationPage,
        mockLocation,
        mockHistory,
        mockPlaceParser,
        mockPlaceUrlCreator,
        mockPlace1);
  }

  @Before public void setUpCommonMockInteractions() {
    when(mockPlace1.getApplication()).thenReturn(THIS_APPLICATION);
    when(mockPlace2.getApplication()).thenReturn(THIS_APPLICATION);
    when(mockPlace3.getApplication()).thenReturn(OTHER_APPLICATION);
  }

  @Test public void construction() {
    verify(mockRootPanel).add(mockApplicationPage);

    InOrder order = inOrder(mockHistory);
    order.verify(mockHistory).addValueChangeHandler(Mockito.<ValueChangeHandler<String>>any());
    order.verify(mockHistory).fireCurrentHistoryState();
  }

  @Test public void handleNavigation_doesntParse() {
    verify(mockHistory).addValueChangeHandler(valueChangeHandlerCaptor.capture());

    when(mockPlaceUrlCreator.createHistoryToken(mockPlace1)).thenReturn("some-url-token");

    valueChangeHandlerCaptor.getValue()
        .onValueChange(new ValueChangeEvent<String>("some-place") {});
    verify(mockHistory).newItem("some-url-token", true);
  }

  @Test public void handleNavigation_parses() {
    verify(mockHistory).addValueChangeHandler(valueChangeHandlerCaptor.capture());

    when(mockPlaceParser.parseHistoryToken("some-place")).thenReturn(mockPlace2);
    valueChangeHandlerCaptor.getValue()
        .onValueChange(new ValueChangeEvent<String>("some-place") {});
    verify(mockApplicationPage).showPageForPlace(mockPlace2);
    assertEquals(mockPlace2, navigator.getCurrentPlace());
  }

  @Test public void navigateTo_sameApplication() {
    when(mockPlaceUrlCreator.createHistoryToken(mockPlace2)).thenReturn("some-token");
    navigator.navigateTo(mockPlace2);
    verify(mockHistory).newItem("some-token", true);
  }

  @Test public void navigateTo_differentApplication() {
    when(mockPlaceUrlCreator.createPlaceUrl(mockPlace3)).thenReturn("some-url");
    navigator.navigateTo(mockPlace3);
    verify(mockLocation).assign("some-url");
  }
}
