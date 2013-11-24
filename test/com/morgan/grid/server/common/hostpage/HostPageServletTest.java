package com.morgan.grid.server.common.hostpage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharSource;
import com.google.common.io.InputSupplier;
import com.google.inject.util.Providers;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Tests for the {@link HostPageServlet} class.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@RunWith(MockitoJUnitRunner.class)
public class HostPageServletTest {

  private static final String AUTH_VALUE = "auth-value";
  private static final String GRID_VALUE = "grid-value";

  private static final String PREAMBLE = "line1\n"
      + "line2\n"
      + "line3__GRID_CONSTANTS__\n";

  private static final String POSTABMLE = "line4\n"
      + "line5\n";

  private static final String INPUT_STREAM = PREAMBLE
      + "    \t__GRID_CONSTANTS__\t\t\n"
      + POSTABMLE;

  private static final String EXPECTED_OUTPUT_STREAM = PREAMBLE
      + String.format("%s: \"%s\",\n",
          DictionaryConstant.AUTH_TEST_CONSTANT.getConstantName(), AUTH_VALUE)
      + String.format("%s: \"%s\",\n",
          DictionaryConstant.GRID_TEST_CONSTANT.getConstantName(), GRID_VALUE)
      + POSTABMLE;

  private static final String APPLICATION_PATH = "/application";

  private static InputSupplier<? extends Reader> SUPPLIER = CharSource.wrap(INPUT_STREAM);

  private static final ImmutableMap<String, InputSupplier<? extends Reader>> HOST_PAGE_MAP =
      ImmutableMap.<String, InputSupplier<? extends Reader>>of(APPLICATION_PATH, SUPPLIER);

  private static final ImmutableMap<DictionaryConstant, String> CONSTANTS = ImmutableMap.of(
      DictionaryConstant.AUTH_TEST_CONSTANT, AUTH_VALUE,
      DictionaryConstant.GRID_TEST_CONSTANT, GRID_VALUE);

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;

  private StringWriter writer = new StringWriter();
  private HostPageServlet servlet = new HostPageServlet(HOST_PAGE_MAP,
      Providers.<Map<DictionaryConstant, String>>of(CONSTANTS));

  @Before public void setupCommonMockInteractions() throws IOException {
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(writer));
  }

  private void doTest_doGet(String servletPath, int expectedStatus) throws Throwable {
    when(mockRequest.getServletPath()).thenReturn(servletPath);
    servlet.doGet(mockRequest, mockResponse);

    verify(mockResponse).setStatus(expectedStatus);
  }

  private void verifyContent() {
    verify(mockResponse).setContentType("text/html");
    assertEquals(EXPECTED_OUTPUT_STREAM, writer.toString());
  }

  @Test public void doGet() throws Throwable {
    doTest_doGet(APPLICATION_PATH, HttpServletResponse.SC_OK);
    verifyContent();
  }

  @Test public void doGet_trailingSlash() throws Throwable {
    doTest_doGet(APPLICATION_PATH + "/", HttpServletResponse.SC_OK);
    verifyContent();
  }

  @Test public void doGet_noSuchApp() throws Throwable {
    doTest_doGet("/foobar", HttpServletResponse.SC_NOT_FOUND);
  }
}
