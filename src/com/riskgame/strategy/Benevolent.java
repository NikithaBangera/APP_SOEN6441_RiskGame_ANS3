package com.riskgame.strategy;

import java.util.ArrayList;
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

	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		playerController.armiesAssignedToCountries(mapGraph, getWeakestCountry(mapGraph, player).getName(), 1);
		
	}

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		playerController.armiesAssignedToCountries(mapGraph, getWeakestCountry(mapGraph, player).getName(), player.getArmyCount());
	}


	@Override
	public void fortificationPhase(GameMapGraph mapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		playerController = new PlayerController();
		Country weakestCountry = getWeakestCountry(mapGraph, player);
		ArrayList<Country> adjacentCountriesListOfWeakestCountry = weakestCountry.getAdjacentCountries1();
		player.setAdjacentCountriesforWeakestCountry(adjacentCountriesListOfWeakestCountry);
		Country strongestCountryToFortify = getStrongestCountryInAdjacentCountryList(mapGraph, player);
		if (strongestCountryToFortify != null) {
			int fortificationArmiestoweakestCountry = (strongestCountryToFortify.getNoOfArmies() - weakestCountry.getNoOfArmies()) / 2;
			weakestCountry.setNoOfArmies(weakestCountry.getNoOfArmies() + fortificationArmiestoweakestCountry);
			strongestCountryToFortify.setNoOfArmies(strongestCountryToFortify.getNoOfArmies() - fortificationArmiestoweakestCountry);
			
		}
			}
	
	public Country getStrongestCountryInAdjacentCountryList(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country strongestCountryToFortify = null;
		for(Country country: player.getAdjacentCountriesforWeakestCountry()) {
			if(country.getNoOfArmies() >= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				strongestCountryToFortify = country;
			}
		}
		return strongestCountryToFortify;
	}
	
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

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		System.out.println("Benevolent player cannot attack.");
		
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		System.out.println("Benevolent player cannot attack.");
		
	}
}
