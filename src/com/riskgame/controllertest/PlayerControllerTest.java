package com.riskgame.controllertest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Continent;
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
	
	Continent continent;
	
	/** Object for StartUp Class */
	PlayerController playerController;
	
	Player player;
	
//	/** Object for Country Class */
//	Country country;
	
	/** Object for GameMapGraph class */
	GameMapGraph mapGraph;
	
	/** HasMap to store the information of countries */
	HashMap<String, Country> countrySet; 
	
	/** Objects for Country Class */
	private Country country, country1, toCountry, fromCountry, toCountry1, attackercountry_1, defendercountry_1, toCountry_1, fromCountry_1;
	
	/** ArrayLists for storing adjacent countries list for the countries */
    private ArrayList<String> adjacentCountries_1;
	private ArrayList<String> adjacentCountries1_1;
	
	/** HashMap for saving the country set */
	private HashMap<String,Country>	countryset_1;

	
	/** Object of the GameMapGraph for creating the map graph */
	private GameMapGraph gameMapGraph_1;
	
	
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
		
       adjacentCountries_1 = new ArrayList<String>();
		adjacentCountries1_1 = new ArrayList<String>();

		gameMapGraph_1=new GameMapGraph();
		countryset_1=new HashMap<String,Country>();

		attackercountry_1 = new Country();
		attackercountry_1.setName("Canada");
		attackercountry_1.setPlayer("attacker");
		attackercountry_1.setNoOfArmies(5);
		countryset_1.put("Canada", attackercountry_1);
		gameMapGraph_1.setCountrySet(countryset_1);
		toCountry_1= new Country();
		toCountry_1.setName("Russia");
		toCountry_1.setPlayer("toCountry");
		toCountry_1.setNoOfArmies(1);
		gameMapGraph_1.getCountrySet().put("Russia", toCountry_1);
		
		defendercountry_1 = new Country();
		defendercountry_1.setName("USA");
		defendercountry_1.setPlayer("defender");
		defendercountry_1.setNoOfArmies(1);
		gameMapGraph_1.getCountrySet().put("USA", defendercountry_1);
		
		fromCountry_1 = new Country();
		fromCountry_1.setName("Alaska");
		fromCountry_1.setPlayer("toCountry");
		fromCountry_1.setNoOfArmies(5);

		gameMapGraph_1.getCountrySet().put("Alaska", fromCountry_1);
		
		adjacentCountries_1.add("USA");
		adjacentCountries1_1.add("Russia");
		fromCountry_1.setAdjacentCountries(adjacentCountries1_1);
		
		adjacentCountries1_1.add("Canada");
		
		attackercountry_1.setAdjacentCountries(adjacentCountries_1);
		
		toCountry_1.setAdjacentCountries(adjacentCountries_1);
		toCountry_1.getAdjacentCountries().add("Alaska");
		
		defendercountry_1.setAdjacentCountries(adjacentCountries1_1);

		
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
	
//	@Test
//	public void isControlValueAssignedToPlayer() {
//		assertEquals(continent.getControlValue(), playerController.armiesToBeAssigned(player, continent));
//	}
//	
//	/**
//	 * Test method for checking the army count added to the player's armies
//	 * if the player does not the entire continent.
//	 */
//	@Test
//	public void isControlValueNotAssignedToPlayer() {
//		assertNotEquals(continent.getControlValue(), playerController.armiesToBeAssigned(player1, continent));
//	}
	
	/**
	 * for testing whether the countries are adjacent or not
	 */

	@Test

	public void isAttack() {

		playerController.attackPhase(gameMapGraph_1,attackercountry_1,toCountry_1);

		assertEquals(5, attackercountry_1.getNoOfArmies());

		assertEquals(1, toCountry_1.getNoOfArmies());

	}

	/**
	 * for testing whether the attacker country have enough armies or not
	 */

	@Test 

	public void isAttackvalid() {

		playerController.attackPhase(gameMapGraph_1, defendercountry_1, attackercountry_1);

		assertEquals(1, defendercountry_1.getNoOfArmies());

		assertEquals(5, attackercountry_1.getNoOfArmies());

	}

	/**
	 * for testing both whether the countries are adjacent or not and the attacker has enough armies
	 */
	@Test 

	public void isAttackviceversa() {

		playerController.attackPhase(gameMapGraph_1, toCountry_1, attackercountry_1);

		assertEquals(1, toCountry_1.getNoOfArmies());

		assertEquals(5, attackercountry_1.getNoOfArmies());

	}
	
	
	/**
	 * for testing whether the countries are for the same player or not
	 */
	@Test 

	public void isAttackPossible() {

		playerController.attackPhase(gameMapGraph_1, fromCountry_1, toCountry_1);

		assertEquals(1, toCountry_1.getNoOfArmies());

		assertEquals(5, fromCountry_1.getNoOfArmies());

	}
	
	
	/**
	 * for testing whether the all out attack works
	 */ 
	
	@Test 

	public void isAlloutAttackworks() {
		
		playerController.allOutAttack(gameMapGraph_1, attackercountry_1, defendercountry_1);
		
		if(defendercountry_1.getNoOfArmies()==1)

			assertEquals(1,attackercountry_1.getNoOfArmies());
		
		else
			assertEquals("attacker",defendercountry_1.getPlayer());

		

	}
	
	
//	@Test
//	public void testattackPhase(){
//		playerController.attackPhase(mapGraph, attacker, defender);
//		
//	}
}