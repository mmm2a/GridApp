package com.morgan.grid.client.common.navigation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.google.gwt.http.client.UrlBuilder;
import com.morgan.grid.shared.common.navigation.Application;

/**
 * Tests for the {@link PlaceUrlCreator} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceUrlCreatorTest {

  private static final Application APPLICATION = Application.AUTH;

  @Mock private Location mockLocation;

  @Mock private Place mockPlace;
  @Mock private PlaceHandler<Place> mockPlaceHandler;

  @Mock private UrlBuilder mockUrlBuilder;

  private PlaceUrlCreator creator;

  @Before public void createTestInstances() {
    creator = new PlaceUrlCreator(mockLocation);
  }

  @Before public void setUpCommonMockInteractions() {
    doReturn(mockPlaceHandler).when(mockPlace).getPlaceHandler();
    when(mockLocation.createUrlBuilder()).thenReturn(mockUrlBuilder);
    when(mockPlace.getApplication()).thenReturn(APPLICATION);
  }

  @Test public void toHistoryTokenParts() {
    when(mockPlaceHandler.toHistoryTokenParts(mockPlace))
        .thenReturn(ImmutableList.of("one", "two", "three"));
    assertEquals(ImmutableList.of("one", "two", "three"), creator.toHistoryTokenParts(mockPlace));
  }

  @Test public void createHistoryToken() {
    when(mockPlaceHandler.getPlaceIdentifierToken()).thenReturn("one");
    when(mockPlaceHandler.toHistoryTokenParts(mockPlace))
        .thenReturn(ImmutableList.of("two", "three"));
    assertEquals("!one/two/three", creator.createHistoryToken(mockPlace));
  }

  @Test public void createPlaceUrl() {
    when(mockPlaceHandler.getPlaceIdentifierToken()).thenReturn("one");
    when(mockPlaceHandler.toHistoryTokenParts(mockPlace))
        .thenReturn(ImmutableList.of("two", "three"));
    when(mockUrlBuilder.buildString()).thenReturn("some-url");

    assertEquals("some-url", creator.createPlaceUrl(mockPlace));

    verify(mockUrlBuilder).setPath("apps/auth");
    verify(mockUrlBuilder).setHash("!one/two/three");
    verify(mockUrlBuilder).buildString();

    verifyNoMoreInteractions(mockUrlBuilder);
  }
}
