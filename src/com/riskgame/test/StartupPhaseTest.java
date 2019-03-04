package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.RiskPlayer;
import com.riskgame.service.StartupPhase;

/**
 * Test Class for StartUpPhase class
 * 
 * @author Nikitha
 * @author Sumeetha
 *
 */
public class StartupPhaseTest {
	/** Object for RiskPlayer Class */
	RiskPlayer player1;
	
	/** Object for StartUp Class */
	StartupPhase startUpPhase;
	
	/** Object for Country Class */
	Country country;
	
	/** Object for GameMapGraph class */
	GameMapGraph mapGraph;
	
	/** HasMap to store the information of countries */
	HashMap<String, Country> countrySet; 

	/**
	 * StartupPhaseTest Constructor for initial setup 
	 */
   public StartupPhaseTest() {
	   countrySet = new HashMap<String, Country>();
	   mapGraph = new GameMapGraph();
	   
       startUpPhase = new StartupPhase();
       startUpPhase.setCountOfthePlayers(2);

       player1 = new RiskPlayer();
       startUpPhase.getPlayersList().add(player1);

       country = new Country();
       country.setName("Morocco");
       countrySet.put(country.getName(), country);
       mapGraph.setCountrySet(countrySet);

   }

   /**
    * Test method to validate the allocation of armies to each player
    * as per the number of players.
    */
   @Test
   public void testAllocationArmy() {
       startUpPhase.allocationOfArmyToPlayers();
       assertEquals(40, player1.getArmyCount());
   }

   /**
    * Test case to validate the initial allocation of armies to each country
    * owned by players. 
    */
   @Test
   public void testallocationOfArmyToCountriesInitially() {
       startUpPhase.allocationOfArmyToCountriesInitially(mapGraph);
       assertEquals(1, country.getNoOfArmies() );
   }
}