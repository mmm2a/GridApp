package com.morgan.grid.server.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.inject.Provides;
import com.morgan.grid.server.common.constants.AbstractConstantsModule;
import com.morgan.grid.server.common.constants.BindConstant;
import com.morgan.grid.shared.common.constants.DictionaryConstant;

/**
 * GUICE module for configuring constants related to the auth application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
final class AuthConstantsModule extends AbstractConstantsModule {

  @BindConstant(DictionaryConstant.AUTH_TEST_CONSTANT)
  @Provides protected String provideAuthTestConstant(HttpServletRequest request,
      HttpServletResponse response, HttpSession session) {
    return "Auth application for " + request.getRemoteAddr();
  }
}
