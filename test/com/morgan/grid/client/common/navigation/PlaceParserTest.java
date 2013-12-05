package com.morgan.grid.client.common.navigation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Tests for the {@link PlaceParser} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceParserTest {

  private static final String PLACE_1_TOKEN_IDENTIFIER = "one";
  private static final String PLACE_2_TOKEN_IDENTIFIER = "two";
  private static final String PLACE_3_TOKEN_IDENTIFIER = "three";

  @Mock private Place mockPlace2;

  @Mock private PlaceHandler<Place> mockPlaceHandler1;
  @Mock private PlaceHandler<Place> mockPlaceHandler2;
  @Mock private PlaceHandler<Place> mockPlaceHandler3;

  private PlaceParser parser;

  @SuppressWarnings("rawtypes")
  @Before public void createTestInstances() {
    parser = new PlaceParser(ImmutableMap.<String, PlaceHandler>of(
        PLACE_1_TOKEN_IDENTIFIER, mockPlaceHandler1,
        PLACE_2_TOKEN_IDENTIFIER, mockPlaceHandler2,
        PLACE_3_TOKEN_IDENTIFIER, mockPlaceHandler3));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void doTestParseHistoryToken(String historyToken, @Nullable Place expectedPlace,
      @Nullable PlaceHandler expectedHandler, @Nullable List<String> expectedHistoryTokenParts) {
    if (expectedHandler != null) {
      when(expectedHandler.fromHistoryTokenParts(expectedHistoryTokenParts))
          .thenReturn(expectedPlace);
    }

    assertEquals(expectedPlace, parser.parseHistoryToken(historyToken));
  }

  @Test public void parseHistoryToken_startsWithHashBang_noParts() {
    doTestParseHistoryToken("#!///", null, null, null);
  }

  @Test public void parseHistoryToken_startsWithHashBang_noHandler() {
    doTestParseHistoryToken("#!foo/bar", null, null, null);
  }

  @Test public void parseHistoryToken_startsWithHashBang() {
    doTestParseHistoryToken("#!two/foo/bar", mockPlace2, mockPlaceHandler2,
        ImmutableList.of("foo", "bar"));
  }

  @Test public void parseHistoryToken_startsWithBang_noParts() {
    doTestParseHistoryToken("!///", null, null, null);
  }

  @Test public void parseHistoryToken_startsWithBang_noHandler() {
    doTestParseHistoryToken("!foo/bar", null, null, null);
  }

  @Test public void parseHistoryToken_startsWithBang() {
    doTestParseHistoryToken("!two/foo/bar", mockPlace2, mockPlaceHandler2,
        ImmutableList.of("foo", "bar"));
  }

  @Test public void parseHistoryToken_noParts() {
    doTestParseHistoryToken("///", null, null, null);
  }

  @Test public void parseHistoryToken_noHandler() {
    doTestParseHistoryToken("foo/bar", null, null, null);
  }

  @Test public void parseHistoryToken() {
    doTestParseHistoryToken("two/foo/bar", mockPlace2, mockPlaceHandler2,
        ImmutableList.of("foo", "bar"));
  }
}
