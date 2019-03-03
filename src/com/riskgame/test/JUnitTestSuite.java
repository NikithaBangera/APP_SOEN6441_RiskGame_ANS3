package com.riskgame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.riskgame.test.FortificationPhaseTest;
import com.riskgame.test.ReinforcementPhaseTest;
import com.riskgame.test.StartupPhaseTest;
import com.riskgame.test.CreateAndEditMapTest;
import com.riskgame.test.ReadAndWriteMapTest;

@RunWith(Suite.class)
@SuiteClasses({CreateAndEditMapTest.class,ReadAndWriteMapTest.class,StartupPhaseTest.class,
	FortificationPhaseTest.class,ReinforcementPhaseTest.class})

public class JUnitTestSuite {

}