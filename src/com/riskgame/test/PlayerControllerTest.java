package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Test Class for StartUpPhase class
 * 
 * @author Nikitha
 * @author Sumeetha
 *
 */
public class PlayerControllerTest {
	/** Object for RiskPlayer Class */
	Player player1;
	
	/** Object for StartUp Class */
	PlayerController playerController;
	
	/** Object for Country Class */
	Country country;
	
	/** Object for GameMapGraph class */
	GameMapGraph mapGraph;
	
	/** HasMap to store the information of countries */
	HashMap<String, Country> countrySet; 

	/**
	 * StartupPhaseTest Constructor for initial setup 
	 */
   public PlayerControllerTest() {
	   countrySet = new HashMap<String, Country>();
	   mapGraph = new GameMapGraph();
	   
       playerController = new PlayerController();
       playerController.setCountOfthePlayers(2);

       player1 = new Player();
       mapGraph.getPlayers().add(player1);

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
       playerController.allocationOfArmyToPlayers(mapGraph);
       assertEquals(40, player1.getArmyCount());
   }

   /**
    * Test case to validate the initial allocation of armies to each country
    * owned by players. 
    */
   @Test
   public void testallocationOfArmyToCountriesInitially() {
       playerController.allocationOfArmyToCountriesInitially(mapGraph);
       assertEquals(1, country.getNoOfArmies() );
   }
}