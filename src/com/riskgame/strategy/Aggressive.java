package com.riskgame.strategy;

import java.util.List;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Aggressive player reinforces the strongest country, attacks until the army count of the attacking country is one,
 * fortifies the strongest country.
 *
 * @author 
 *
 */
public class Aggressive implements PlayerStrategy{

	PlayerController playerController;
	
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		playerController.armiesAssignedToCountries(mapGraph, getStrongestCountry(mapGraph, player).getName(), 1);
	}

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		playerController.armiesAssignedToCountries(mapGraph, getStrongestCountry(mapGraph, player).getName(), player.getArmyCount());
	}

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		playerController = new PlayerController();
		int numberOfArmies = 0;
		attackerCountry = getStrongestCountry(gameMapGraph, player);
		List<String> adjacentCountriesList = attackerCountry.getAdjacentCountries();
		for(String adjCountry : adjacentCountriesList) {
			Player adjPlayer = playerController.getPlayerForCountry(gameMapGraph, adjCountry);
			Country adjPlayerCountry = adjPlayer.getSelectedCountry(adjCountry);
			if(adjPlayerCountry.getNoOfArmies() <= numberOfArmies) {
				numberOfArmies = adjPlayerCountry.getNoOfArmies();
				defenderCountry = adjPlayerCountry;
			}
		}
		playerController.allOutAttack(gameMapGraph, attackerCountry, defenderCountry);
		
	}

	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		int numberOfArmies = 0;
		Country strongestCountry = getStrongestCountry(gameMapGraph, player);
		Country weakestCountry = null;
		for(Country country: player.getMyCountries()) {
			if(country.getNoOfArmies() <= numberOfArmies && country.getNoOfArmies() > 1) {
				numberOfArmies = country.getNoOfArmies();
				weakestCountry = country;
			}
		}
		playerController.moveArmies(weakestCountry, strongestCountry, weakestCountry.getNoOfArmies()-1);
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
	

	
}
