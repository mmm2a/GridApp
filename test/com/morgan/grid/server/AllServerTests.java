package com.morgan.grid.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.server.args.AllArgsTests;
import com.morgan.grid.server.common.AllCommonTests;
import com.morgan.grid.server.security.AllSecurityTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllArgsTests.class,
  AllCommonTests.class,
  AllSecurityTests.class
})
public class AllServerTests {
}
