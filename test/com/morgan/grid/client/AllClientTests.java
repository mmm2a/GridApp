package com.morgan.grid.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.client.common.AllCommonTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllCommonTests.class
})
public class AllClientTests {

}
