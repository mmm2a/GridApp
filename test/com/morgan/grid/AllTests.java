package com.morgan.grid;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.server.AllServerTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllServerTests.class
})
public class AllTests {
}
