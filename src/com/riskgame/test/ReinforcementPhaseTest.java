package com.riskgame.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.RiskPlayer;
import com.riskgame.service.ReinforcementPhase;

/**
 * Test Class for ReinforcementPhase Class
 * 
 * @author Nikitha
 * @author Shiva
 *
 */
public class ReinforcementPhaseTest {
	
	/** Objects for RiskPlayer Class */
	RiskPlayer player, player1;
	
	/** Objects for Country Class */
	Country country1, country2, country3;
	
	/** Object for Continent Class */
	Continent continent;
	
	/**
	 * ReinforcementPhaseTest Constructor for initial setup 
	 */
	public ReinforcementPhaseTest() {
		
		continent = new Continent();
		player = new RiskPlayer();
		player1 = new RiskPlayer();
		
		country1 = new Country();
		country1.setName("Egypt");
		player.getMyCountries().add(country1);
		player1.getMyCountries().add(country1);
		continent.getCountriesInContinent().add(country1);
		
		country2 = new Country();
		country2.setName("Libya");
		player.getMyCountries().add(country2);
		player1.getMyCountries().add(country2);
		continent.getCountriesInContinent().add(country2);
		
		country3 = new Country();
		country3.setName("Morocco");
		player.getMyCountries().add(country3);
		continent.getCountriesInContinent().add(country3);
		
		
		continent.setName("Northern Africa");
		continent.setControlValue(4);
	}
	
	/**
	 * Test method for checking whether the control value gets added to the player's armies
	 * if the player owns the entire continent.
	 */
	@Test
	public void isControlValueAssignedToPlayer() {
		assertEquals(7, ReinforcementPhase.armiesToBeAssigned(player, continent));
	}
	
	/**
	 * Test method for checking the army count added to the player's armies
	 * if the player does not the entire continent.
	 */
	@Test
	public void isControlValueNotAssignedToPlayer() {
		assertNotEquals(continent.getControlValue(), ReinforcementPhase.armiesToBeAssigned(player1, continent));
	}
}

