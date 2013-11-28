package com.morgan.grid.server.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Container of common {@link BindingAnnotation} annotations for binding security classes.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
public final class CommonSecurityBindingAnnotations {

  private CommonSecurityBindingAnnotations() {
    // Don't allow instantiation.
  }

  /**
   * Identifies bindings related to the session cookie.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface SessionCookie {
  }

  /**
   * Identifies the encryption cipher used for encrypting the session token.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface SessionCookieEncryptionCipher {
  }

  /**
   * Identifies the encryption cipher used for encrypting the session token.
   */
  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
  public @interface SessionCookieDecryptionCipher {
  }
}
