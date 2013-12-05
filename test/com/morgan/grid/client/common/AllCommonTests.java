package com.morgan.grid.client.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.client.common.feature.AllFeatureTests;
import com.morgan.grid.client.common.navigation.AllNavigationTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllFeatureTests.class,
  AllNavigationTests.class
})
public class AllCommonTests {

}
