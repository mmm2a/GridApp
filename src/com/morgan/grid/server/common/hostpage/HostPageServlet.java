package com.morgan.grid.server.common.hostpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Closeables;
import com.google.common.io.InputSupplier;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * Servlet implementation that resolves requests for host pages that have been registered with the
 * server and returns them to the caller.  This servlet also fills in the constants dictionary
 * values in the resultant out stream as well.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
public final class HostPageServlet extends HttpServlet {

  static final long serialVersionUID = 1L;

  private static final Pattern CONSTANTS_MARKER = Pattern.compile("^\\s*__GRID_CONSTANTS__\\s*$");

  private final ImmutableMap<String, InputSupplier<? extends Reader>> hostPageMap;
  private final Provider<Map<DictionaryConstant, String>> dictionaryMapProvider;

  @Inject HostPageServlet(@HostPageMap Map<String, InputSupplier<? extends Reader>> hostPageMap,
      Provider<Map<DictionaryConstant, String>> dictionaryMapProvider) {
    this.hostPageMap = ImmutableMap.copyOf(hostPageMap);
    this.dictionaryMapProvider = dictionaryMapProvider;
  }

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String servletPath = req.getServletPath();
    while (servletPath.endsWith("/")) {
      servletPath = servletPath.substring(0, servletPath.lastIndexOf('/'));
    }

    InputSupplier<? extends Reader> supplier = hostPageMap.get(servletPath);

    if (supplier == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } else {
      Map<DictionaryConstant, String> dictionaryMap = dictionaryMapProvider.get();
      Reader streamReader = null;
      PrintWriter writer = resp.getWriter();
      try {
        streamReader = supplier.getInput();
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        while ((line = reader.readLine()) != null) {
          if (CONSTANTS_MARKER.matcher(line).matches()) {
            for (Map.Entry<DictionaryConstant, String> entry : dictionaryMap.entrySet()) {
              writer.format("%s: \"%s\",\n", entry.getKey().getConstantName(), entry.getValue());
            }
          } else {
            writer.println(line);
          }
        }
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
      } finally {
        Closeables.close(streamReader, true);
        Closeables.close(writer, true);
      }
    }
  }
}
