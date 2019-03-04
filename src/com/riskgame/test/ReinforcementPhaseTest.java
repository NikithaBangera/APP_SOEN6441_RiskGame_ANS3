package com.riskgame.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.RiskPlayer;
import com.riskgame.service.ReinforcementPhase;

public class ReinforcementPhaseTest {
	RiskPlayer player, player1;
	Country country;
	Continent continent;
	
	public ReinforcementPhaseTest() {
		
		continent = new Continent();
		player = new RiskPlayer();
		player1 = new RiskPlayer();
		country = new Country();
		country.setName("Egypt");
		player.getMyCountries().add(country);
		player1.getMyCountries().add(country);
		continent.getCountriesInContinent().add(country);
		
		country = new Country();
		country.setName("Libya");
		player.getMyCountries().add(country);
		player1.getMyCountries().add(country);
		continent.getCountriesInContinent().add(country);
		
		country = new Country();
		country.setName("Morocco");
		player.getMyCountries().add(country);
		continent.getCountriesInContinent().add(country);
		
		
		continent.setName("Northern Africa");
		continent.setControlValue(4);
	}
	
	@Test
	public void isControlValueAssignedToPlayer() {
		assertEquals(7, ReinforcementPhase.armiesToBeAssigned(player, continent));
	}
	
	@Test
	public void isControlValueNotAssignedToPlayer() {
		assertNotEquals(continent.getControlValue(), ReinforcementPhase.armiesToBeAssigned(player1, continent));
	}
}

