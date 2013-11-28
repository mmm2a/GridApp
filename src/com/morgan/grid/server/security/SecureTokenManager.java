package com.morgan.grid.server.security;

import java.security.GeneralSecurityException;

import javax.annotation.Nullable;

/**
 * An interface for a type that manages encrypted tokens for the grid application.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 *
 * @param <T> the type of secure token managed by this manager.
 */
public interface SecureTokenManager<T extends SecureToken> {

  /**
   * Encrypt the secure token.
   */
  byte[] encryptToken(T token) throws GeneralSecurityException;

  /**
   * Decrypt the secure token.  Returns {@code null} if the token is not valid for any reason.
   */
  @Nullable T decryptToken(byte[] tokenBytes);
}
