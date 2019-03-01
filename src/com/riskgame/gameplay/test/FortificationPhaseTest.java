package com.riskgame.gameplay.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.riskgame.common.Country;
import com.riskgame.gameplay.FortificationPhase;

public class FortificationPhaseTest {
	Country toCountry, fromCountry, country, toCountry1;
	FortificationPhase fortificationPhase;
	
	public FortificationPhaseTest() {
		fortificationPhase = new FortificationPhase();
		ArrayList<Country> adjacentCountries = new ArrayList<Country>();
		
		country = new Country();
		country.setName("India");
		adjacentCountries.add(country);
		
		country = new Country();
		country.setName("Nepal");
		adjacentCountries.add(country);
		
		country = new Country();
		country.setName("Sri Lanka");
		adjacentCountries.add(country);
		
		country = new Country();
		country.setName("China");
		adjacentCountries.add(country);
		
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
	
	@Test
	public void isFortificationComplete() {
		fortificationPhase.moveArmies(fromCountry, toCountry, 2);
		assertEquals(6, fromCountry.getNoOfArmies());
		assertEquals(6, toCountry.getNoOfArmies());
	}
	
	@Test 
	public void isFortificationNotComplete() {
		fortificationPhase.moveArmies(fromCountry, toCountry1, 2);
		assertEquals(8, fromCountry.getNoOfArmies());
		assertEquals(2, toCountry1.getNoOfArmies());
	}
}
