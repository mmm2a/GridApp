package com.morgan.grid.server.args;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArgsParserTest.class, ArgsTest.class, DefaultFlagParsersTest.class,
    FlagAccessorFactoryTest.class, FlagsModuleTest.class })
public class AllArgsTests {

}
