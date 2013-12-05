package com.morgan.grid.client.common.navigation;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.UrlBuilder;

/**
 * A wrapper around the {@link com.google.gwt.user.client.Window.Location} class to make testing
 * easier.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public class Location {

  /** @see com.google.gwt.user.client.Window.Location#getHash() */
  public String getHash() {
    return com.google.gwt.user.client.Window.Location.getHash();
  }

  /** @see com.google.gwt.user.client.Window.Location#getHost() */
  public String getHost() {
    return com.google.gwt.user.client.Window.Location.getHost();
  }

  /** @see com.google.gwt.user.client.Window.Location#getHostName() */
  public String getHostName() {
    return com.google.gwt.user.client.Window.Location.getHostName();
  }

  /** @see com.google.gwt.user.client.Window.Location#getHref() */
  public String getHref() {
    return com.google.gwt.user.client.Window.Location.getHref();
  }

  /** @see com.google.gwt.user.client.Window.Location#getPath() */
  public String getPath() {
    return com.google.gwt.user.client.Window.Location.getPath();
  }

  /** @see com.google.gwt.user.client.Window.Location#getPort() */
  public String getPort() {
    return com.google.gwt.user.client.Window.Location.getPort();
  }

  /** @see com.google.gwt.user.client.Window.Location#getProtocol() */
  public String getProtocol() {
    return com.google.gwt.user.client.Window.Location.getProtocol();
  }

  /** @see com.google.gwt.user.client.Window.Location#getQueryString() */
  public String getQueryString() {
    return com.google.gwt.user.client.Window.Location.getQueryString();
  }

  /** @see com.google.gwt.user.client.Window.Location#assign(String) */
  public void assign(String newUrl) {
    com.google.gwt.user.client.Window.Location.assign(newUrl);
  }

  /** @see com.google.gwt.user.client.Window.Location#createUrlBuilder() */
  public UrlBuilder createUrlBuilder() {
    return com.google.gwt.user.client.Window.Location.createUrlBuilder();
  }

  /** @see com.google.gwt.user.client.Window.Location#getParameter(String) */
  public String getParameter(String name) {
    return com.google.gwt.user.client.Window.Location.getParameter(name);
  }

  /** @see com.google.gwt.user.client.Window.Location#getParameterMap() */
  public Map<String, List<String>> getParameterMap() {
    return com.google.gwt.user.client.Window.Location.getParameterMap();
  }

  /** @see com.google.gwt.user.client.Window.Location#reload() */
  public void reload() {
    com.google.gwt.user.client.Window.Location.reload();
  }

  /** @see com.google.gwt.user.client.Window.Location#replace(String) */
  public void replace(String newUrl) {
    com.google.gwt.user.client.Window.Location.replace(newUrl);
  }
}
