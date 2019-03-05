package com.riskgame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CreateAndEditMapTest.class,ReadAndWriteMapTest.class,StartupPhaseTest.class,
    FortificationPhaseTest.class,ReinforcementPhaseTest.class})

/**
 * A TestSuite class for testing all the test cases
 * 
 * @author Anusha
 *
 */
public class JUnitTestSuite {

}
