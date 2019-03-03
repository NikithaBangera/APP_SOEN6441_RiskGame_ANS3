package com.riskgame.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.riskgame.gameplay.test.FortificationPhaseTest;
import com.riskgame.gameplay.test.ReinforcementPhaseTest;
import com.riskgame.gameplay.test.StartupPhaseTest;
import com.riskgame.action.test.CreateAndEditMapTest;
import com.riskgame.action.test.ReadAndWriteMapTest;

@RunWith(Suite.class)
@SuiteClasses({CreateAndEditMapTest.class,ReadAndWriteMapTest.class,StartupPhaseTest.class,
    FortificationPhaseTest.class,ReinforcementPhaseTest.class})

public class JUnitTestSuite {

}
