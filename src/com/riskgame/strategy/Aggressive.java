package com.riskgame.strategy;

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
	public void placeArmies(GameMapGraph mapGraph, Player player) {
		playerController = new PlayerController();
		playerController.armiesAssignedToCountries(mapGraph, getStrongestCountry(mapGraph, player).getName(), 1);
	}

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		// TODO Auto-generated method stub
		
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
