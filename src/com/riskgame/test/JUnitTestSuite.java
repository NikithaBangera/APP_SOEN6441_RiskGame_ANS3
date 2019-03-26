package com.riskgame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CreateMapControllerTest.class,LoadMapControllerTest.class,StartupPhaseTest.class,
    FortificationPhaseTest.class,ReinforcementPhaseTest.class, CardControllerTest.class})

/**
 * A TestSuite class for testing all the test cases
 * 
 * @author Anusha
 *
 */
public class JUnitTestSuite {

}
