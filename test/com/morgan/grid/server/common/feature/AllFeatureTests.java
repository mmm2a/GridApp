package com.morgan.grid.server.common.feature;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DisabledFeaturePredicateParserTest.class, FeatureFileTest.class,
    ServerFeatureCheckerTest.class })
public class AllFeatureTests {

}
