package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;

/**
 * Test class for FortificationPhase Class
 * 
 * @author Anusha
 * @author Sumeetha
 *
 */
public class FortificationPhaseTest {
	
	/** Objects for Country Class */
	private Country country, toCountry, fromCountry, toCountry1;
	
	/** Object for Fortification Class */
	private PlayerController playerController;
	
	/** ArrayList for storing adjacent countries list for the countries */
	private ArrayList<String> adjacentCountries;
	
	/**
	 * FortificationPhaseTest Constructor for initial setup 
	 */
	public FortificationPhaseTest() {
		
		playerController = new PlayerController();
		adjacentCountries = new ArrayList<String>();
		
		country = new Country();
		country.setName("India");
		adjacentCountries.add("India");
		
		country = new Country();
		country.setName("Nepal");
		adjacentCountries.add("Nepal");
		
		country = new Country();
		country.setName("Sri Lanka");
		adjacentCountries.add("Sri Lanka");
		
		country = new Country();
		country.setName("China");
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
	 * Test method to validate the number of armies present in the fromCountry and the toCountry
	 * after the player moves the armies between the adjacent fromCountry and toCountry.
	 */
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
