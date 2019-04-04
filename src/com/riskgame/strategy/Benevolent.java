package com.riskgame.strategy;

import java.util.ArrayList;
import java.util.List;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Benevolent player reinforces its weakest countries, does not attack,
 * fortifies by moving armies to weaker countries
 * @author 
 *
 */
public class Benevolent implements PlayerStrategy{
	
	PlayerController playerController;

	


	
	
	public Country getStrongestCountry(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country strongestCountry = null;
		for(Country country: player.getMyCountries()) {
			if(country.getNoOfArmies() >= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				strongestCountry = country;
			}
		}
		return strongestCountry;
	}
	
	public Country getWeakestCountry(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country weakestCountry = null;
		for(Country country: player.getMyCountries()) {
			if(country.getNoOfArmies() <= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				weakestCountry = country;
			}
		}
		return weakestCountry;
	}
}
