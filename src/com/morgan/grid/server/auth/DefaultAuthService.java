package com.morgan.grid.server.auth;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.morgan.grid.client.auth.AuthService;
import com.morgan.grid.server.security.SessionSecureToken;
import com.morgan.grid.server.security.SessionSecureTokenManager;

/**
 * Default implementation of the {@link AuthService} interface.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
@Singleton
public class DefaultAuthService extends RemoteServiceServlet implements AuthService {

  static final long serialVersionUID = 1L;

  private final SessionSecureTokenManager mgr;
  private final Provider<DatabaseAccessor> accessorProvider;

  @Inject DefaultAuthService(SessionSecureTokenManager mgr,
      Provider<DatabaseAccessor> accessorProvider) {
    this.mgr = mgr;
    this.accessorProvider = accessorProvider;
  }

  @Override public String getHelloMessage(String name) {
    accessorProvider.get().printEntries();
    accessorProvider.get().addEntry();

    System.err.println("Encrypting 12");
    byte []cryptText = mgr.encryptToken(mgr.createNewSessionToken(12));
    System.err.println("Decrypting it");
    SessionSecureToken token = mgr.decryptToken(cryptText);
    System.err.println("Got back: " + token);

    return String.format("Auth hello %s", name);
  }
}
