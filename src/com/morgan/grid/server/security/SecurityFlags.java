package com.morgan.grid.server.security;

import java.net.URL;

import org.joda.time.ReadableDuration;

import com.morgan.grid.server.args.Flag;
import com.morgan.grid.server.args.FlagAccessor;

/**
 * Flags interface for the security package.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
interface SecurityFlags extends FlagAccessor {
  @Flag(name = "keystore-url", description = "The URL to the location of the keystore to use.",
      flagParser = UrlFlagParser.class)
  URL keystoreUrl();

  @Flag(name = "keystore-type", description = "A flag indicating the type of keystore being loaded",
      defaultValue = "JKS")
  String keystoreType();

  @Flag(name = "keystore-password", description = "The password used to access the keystore.")
  String keystorePassword();

  @Flag(name = "cookie-certificate-alias", description = "The alias in the keystore for the certificate "
      + "containing the public key for the cookie certificate")
  String cookieCertificateAlias();

  @Flag(name = "cookie-private-key-alias",
      description = "The alias in the keystore for the cookie private key entry")
  String cookiePrivateKeyAlias();

  @Flag(name = "cookie-private-key-password",
      description = "The password used to access the private key for the cookie in the keystore.")
  String cookiePrivateKeyPassword();

  @Flag(name = "cookie-cipher-transformation",
      description = "The cipher transformation to use for encrypting and decrypting the session id")
  String cookieCipherTransformation();

  @Flag(name = "session-duration", description = "The duration of time that a session is valid for",
      defaultValue = "30 days", flagParser = DurationFlagParser.class)
  ReadableDuration sessionDuration();
}
