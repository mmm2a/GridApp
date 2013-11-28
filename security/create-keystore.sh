#!/bin/bash

keytool -genkeypair -alias cookie-key -keyalg RSA -keysize 2048 -dname "CN=Mark Morgan, OU=Unknown, O=Unknown, L=Longmont, ST=Colorado, C=US" -keypass password -storepass password -keystore keystore.jks
