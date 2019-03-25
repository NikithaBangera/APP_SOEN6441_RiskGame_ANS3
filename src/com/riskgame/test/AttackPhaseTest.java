package com.riskgame.test;



import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for AttackPhase
 * 
 * @author Shiva
 *
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


import com.riskgame.controller.PlayerController;

import com.riskgame.model.Country;

import com.riskgame.model.GameMapGraph;



public class AttackPhaseTest {

	
	/** Object for countries 
	 * Canada(5)	USA(1)	Russia(1)	Alaska(5)
	 * with linear adjacency
	 */
	private Country attackercountry, defendercountry, toCountry, fromCountry;

	

	/** Object of PlayerController Class for the attack Phase */

	private PlayerController player;

	

	/** ArrayLists for storing adjacent countries list for the countries */

	private ArrayList<String> adjacentCountries;

	
	private ArrayList<String> adjacentCountries1;
	
	
	/** HashMap for saving the country set */
	private HashMap<String,Country>	countryset;

	
	/** Object of the GameMapGraph for creating the map graph */
	private GameMapGraph gameMapGraph;

	

	public AttackPhaseTest() {

		

		player=new PlayerController();

		adjacentCountries = new ArrayList<String>();
		
		adjacentCountries1 = new ArrayList<String>();

		gameMapGraph=new GameMapGraph();

		countryset=new HashMap<String,Country>();

		

		attackercountry = new Country();

		attackercountry.setName("Canada");

		attackercountry.setPlayer("attacker");

		attackercountry.setNoOfArmies(5);
		countryset.put("Canada", attackercountry);

		gameMapGraph.setCountrySet(countryset);

		

		toCountry= new Country();

		toCountry.setName("Russia");

		toCountry.setPlayer("toCountry");

		toCountry.setNoOfArmies(1);

		gameMapGraph.getCountrySet().put("Russia", toCountry);
		
		

		

		defendercountry = new Country();

		defendercountry.setName("USA");

		defendercountry.setPlayer("defender");

		defendercountry.setNoOfArmies(1);

		gameMapGraph.getCountrySet().put("USA", defendercountry);
		
		fromCountry = new Country();

		fromCountry.setName("Alaska");

		fromCountry.setPlayer("toCountry");

		fromCountry.setNoOfArmies(5);

		gameMapGraph.getCountrySet().put("Alaska", fromCountry);
		
		adjacentCountries.add("USA");
		adjacentCountries1.add("Russia");
		fromCountry.setAdjacentCountries(adjacentCountries1);
		
		adjacentCountries1.add("Canada");
		
		
		attackercountry.setAdjacentCountries(adjacentCountries);
		
		toCountry.setAdjacentCountries(adjacentCountries);
		toCountry.getAdjacentCountries().add("Alaska");
		
		defendercountry.setAdjacentCountries(adjacentCountries1);

		

		

	

	}
	/**
	 * for testing whether the countries are adjacent or not
	 */

	@Test

	public void isAttack() {

		player.attackPhase(gameMapGraph,attackercountry,toCountry);

		assertEquals(5, attackercountry.getNoOfArmies());

		assertEquals(1, toCountry.getNoOfArmies());

	}

	/**
	 * for testing whether the attacker country have enough armies or not
	 */

	@Test 

	public void isAttackvalid() {

		player.attackPhase(gameMapGraph, defendercountry, attackercountry);

		assertEquals(1, defendercountry.getNoOfArmies());

		assertEquals(5, attackercountry.getNoOfArmies());

	}

	/**
	 * for testing both whether the countries are adjacent or not and the attacker has enough armies
	 */
	@Test 

	public void isAttackviceversa() {

		player.attackPhase(gameMapGraph, toCountry, attackercountry);

		assertEquals(1, toCountry.getNoOfArmies());

		assertEquals(5, attackercountry.getNoOfArmies());

	}
	
	
	/**
	 * for testing whether the countries are for the same player or not
	 */
	@Test 

	public void isAttackPossible() {

		player.attackPhase(gameMapGraph, fromCountry, toCountry);

		assertEquals(1, toCountry.getNoOfArmies());

		assertEquals(5, fromCountry.getNoOfArmies());

	}
	
	
	/**
	 * for testing whether the all out attack works
	 */ 
	
	@Test 

	public void isAlloutAttackworks() {
		
		player.allOutAttack(gameMapGraph, attackercountry, defendercountry);
		
		if(defendercountry.getNoOfArmies()==1)

			assertEquals(1,attackercountry.getNoOfArmies());
		
		else
			assertEquals("attacker",defendercountry.getPlayer());

		

	}
	
	
	
}
