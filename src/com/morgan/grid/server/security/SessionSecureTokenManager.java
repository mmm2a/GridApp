package com.morgan.grid.server.security;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

import javax.annotation.Nullable;
import javax.crypto.Cipher;

import org.joda.time.Instant;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.morgan.grid.server.log.FormattingLogger;
import com.morgan.grid.server.security.CommonSecurityBindingAnnotations.SessionCookieDecryptionCipher;
import com.morgan.grid.server.security.CommonSecurityBindingAnnotations.SessionCookieEncryptionCipher;
import com.morgan.grid.server.time.Clock;

/**
 * A {@link SecureTokenManager} for the {@link SessionSecureToken}.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class SessionSecureTokenManager implements SecureTokenManager<SessionSecureToken> {

  private static final FormattingLogger logger = FormattingLogger.getInstanceFor(
      SessionSecureTokenManager.class);

  @VisibleForTesting static final int SIZE_OF_LONG = 8;

  private final SecurityFlags flags;
  private final Clock clock;

  private final Provider<Cipher> encryptionCipherProvider;
  private final Provider<Cipher> decryptionCipherProvider;

  @Inject SessionSecureTokenManager(SecurityFlags flags, Clock clock,
      @SessionCookieEncryptionCipher Provider<Cipher> encryptionCipherProvider,
      @SessionCookieDecryptionCipher Provider<Cipher> decryptionCipherProvider) {
    this.flags = flags;
    this.clock = clock;

    this.encryptionCipherProvider = encryptionCipherProvider;
    this.decryptionCipherProvider = decryptionCipherProvider;
  }

  /**
   * Creates a new {@link SessionSecureToken} with the given session id and the configured
   * expiration in the future from now.
   */
  public SessionSecureToken createNewSessionToken(long sessionId) {
    return new SessionSecureToken(sessionId, clock.now().toInstant().plus(flags.sessionDuration()));
  }

  @Override public byte[] encryptToken(SessionSecureToken token) {
    ByteBuffer buffer = ByteBuffer.allocate(SIZE_OF_LONG * 2);
    buffer.putLong(token.getSessionId());
    buffer.putLong(token.getExpiration().getMillis());
    byte[] result = new byte[buffer.position()];
    buffer.flip();
    buffer.get(result);
    try {
      return encryptionCipherProvider.get().doFinal(result);
    } catch (GeneralSecurityException e) {
      logger.severe(e, "Unable to encrypt session token.");
      throw new IllegalStateException();
    }
  }

  @Override @Nullable public SessionSecureToken decryptToken(byte[] tokenBytes) {
    try {
      byte[] clearText = decryptionCipherProvider.get().doFinal(tokenBytes);
      ByteBuffer buffer = ByteBuffer.wrap(clearText);
      SessionSecureToken token = new SessionSecureToken(
          buffer.getLong(), new Instant(buffer.getLong()));
      if (token.isValid(clock.now())) {
        return token;
      }
    } catch (GeneralSecurityException e) {
      logger.fine(e, "Got an exception while trying to decode an encrypted secure session token.");
    }

    return null;
  }
}
