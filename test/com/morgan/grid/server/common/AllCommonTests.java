package com.morgan.grid.server.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.server.common.constants.AllConstantsTests;
import com.morgan.grid.server.common.hostpage.AllHostPageTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllConstantsTests.class,
  AllHostPageTests.class
})
public class AllCommonTests {
}
