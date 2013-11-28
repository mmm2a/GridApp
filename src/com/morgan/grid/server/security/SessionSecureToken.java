package com.morgan.grid.server.security;

import org.joda.time.ReadableInstant;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * A class containing the information about a secured session token.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class SessionSecureToken implements SecureToken {

  private final long sessionId;
  private final ReadableInstant expiration;

  SessionSecureToken(long sessionId,
      ReadableInstant expiration) {
    this.sessionId = sessionId;
    this.expiration = Preconditions.checkNotNull(expiration);
  }

  ReadableInstant getExpiration() {
    return expiration;
  }

  public long getSessionId() {
    return sessionId;
  }

  @Override public boolean isValid(ReadableInstant now) {
    return now.isBefore(expiration);
  }

  @Override public int hashCode() {
    return Objects.hashCode(sessionId, expiration);
  }

  @Override public boolean equals(Object o) {
    if (!(o instanceof SessionSecureToken)) {
      return false;
    }

    SessionSecureToken other = (SessionSecureToken) o;
    return sessionId == other.sessionId
        && expiration.equals(other.expiration);
  }

  @Override public String toString() {
    return Objects.toStringHelper(SessionSecureToken.class)
        .add("sessionId", sessionId)
        .add("expiration", expiration)
        .toString();
  }
}
