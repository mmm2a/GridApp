package com.morgan.grid.client.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.morgan.grid.client.common.feature.AllFeatureTests;

@RunWith(Suite.class)
@SuiteClasses({
  AllFeatureTests.class
})
public class AllCommonTests {

}
