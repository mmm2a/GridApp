package com.morgan.grid;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.server.common.AllCommonTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllCommonTests.class
})
public class AllTests {
}
