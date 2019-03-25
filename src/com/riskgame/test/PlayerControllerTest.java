package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
	
//	/** Object for Country Class */
//	Country country;
	
	/** Object for GameMapGraph class */
	GameMapGraph mapGraph;
	
	/** HasMap to store the information of countries */
	HashMap<String, Country> countrySet; 
	
	/** Objects for Country Class */
	private Country country, country1, toCountry, fromCountry, toCountry1;
	
	
	
	/** ArrayList for storing adjacent countries list for the countries */
	private ArrayList<String> adjacentCountries;

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

       adjacentCountries = new ArrayList<String>();
		
		country1 = new Country();
		country1.setName("India");
		adjacentCountries.add("India");
		
		country1 = new Country();
		country1.setName("Nepal");
		adjacentCountries.add("Nepal");
		
		country1 = new Country();
		country1.setName("Sri Lanka");
		adjacentCountries.add("Sri Lanka");
		
		country1 = new Country();
		country1.setName("China");
		adjacentCountries.add("China");
		
		fromCountry = new Country();
		fromCountry.setName("Bangladesh");
		fromCountry.setNoOfArmies(8);
		
		toCountry = new Country();
		toCountry.setName("India");
		toCountry.setNoOfArmies(4);
		
		toCountry1 = new Country();
		toCountry1.setName("Canada");
		toCountry1.setNoOfArmies(2);
		fromCountry.setAdjacentCountries(adjacentCountries);
		
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
   
   @Test
	public void isFortificationComplete() {
		playerController.moveArmies(fromCountry, toCountry, 2);
		assertEquals(6, fromCountry.getNoOfArmies());
		assertEquals(6, toCountry.getNoOfArmies());
	}
	
	/**
	 * Test method to validate the number of armies present in the fromCountry and the toCountry
	 * after the player moves the armies between fromCountry and toCountry which are not adjacent.
	 */
	@Test 
	public void isFortificationNotComplete() {
		playerController.moveArmies(fromCountry, toCountry1, 2);
		assertEquals(8, fromCountry.getNoOfArmies());
		assertEquals(2, toCountry1.getNoOfArmies());
	}
}