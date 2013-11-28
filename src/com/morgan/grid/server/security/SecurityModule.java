package com.morgan.grid.server.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.google.common.io.Closeables;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.morgan.grid.server.args.FlagsModule;
import com.morgan.grid.server.security.CommonSecurityBindingAnnotations.SessionCookie;
import com.morgan.grid.server.security.CommonSecurityBindingAnnotations.SessionCookieDecryptionCipher;
import com.morgan.grid.server.security.CommonSecurityBindingAnnotations.SessionCookieEncryptionCipher;

public class SecurityModule extends FlagsModule {

  public SecurityModule() {
    super(SecurityFlags.class);
  }

  @Override protected void configure() {
    super.configure();
  }

  @Provides @Singleton
  protected KeyStore provideKeyStore(SecurityFlags flags)
      throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore store = KeyStore.getInstance(flags.keystoreType());
    InputSupplier<InputStream> inSupplier = Resources.newInputStreamSupplier(flags.keystoreUrl());
    InputStream in = null;
    try {
      in = inSupplier.getInput();
      store.load(in, flags.keystorePassword().toCharArray());
      return store;
    } finally {
      Closeables.close(in, true);
    }
  }

  @Provides @SessionCookie
  protected Certificate provideSessionCookieCertificate(SecurityFlags flags, KeyStore keyStore)
      throws KeyStoreException {
    return keyStore.getCertificate(flags.cookieCertificateAlias());
  }

  @Provides @SessionCookie
  protected PrivateKey provideSessionCookiePrivateKey(SecurityFlags flags, KeyStore keyStore)
      throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
    return (PrivateKey) keyStore.getKey(flags.cookiePrivateKeyAlias(),
        flags.cookiePrivateKeyPassword().toCharArray());
  }

  @Provides @SessionCookieEncryptionCipher
  protected Cipher provideSessionCookieEncryptionCipher(SecurityFlags flags,
      @SessionCookie Certificate certificate)
          throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance(flags.cookieCipherTransformation());
    cipher.init(Cipher.ENCRYPT_MODE, certificate);
    return cipher;
  }

  @Provides @SessionCookieDecryptionCipher
  protected Cipher provideSessionCookieDecryptionCipher(SecurityFlags flags,
      @SessionCookie PrivateKey key)
          throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance(flags.cookieCipherTransformation());
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher;
  }
}
