package com.riskgame.test.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.riskgame.test.strategy.AggressiveTest;
import com.riskgame.test.strategy.BenevolentTest;
import com.riskgame.test.strategy.CheaterTest;
import com.riskgame.test.strategy.RandomPlayerTest;

@RunWith(Suite.class)
@SuiteClasses({ CreateMapControllerTest.class, LoadMapControllerTest.class, PlayerControllerTest.class,
		DiceControllerTest.class, CardControllerTest.class, TournamentControllerTest.class, CheaterTest.class,
		AggressiveTest.class, BenevolentTest.class, RandomPlayerTest.class })

/**
 * A TestSuite class for testing all the test cases
 * 
 * @author Anusha
 * @author Shiva
 */
public class JUnitTestSuite {

}
