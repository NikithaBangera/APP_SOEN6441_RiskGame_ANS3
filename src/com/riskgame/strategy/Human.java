package com.riskgame.strategy;

import java.util.Random;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Implementation of this class is based on the normal human player
 * @author Nikitha
 *
 */
public class Human implements PlayerStrategy{

	PlayerController playerController;
	
	/**
	 * Method to place the remaining armies to countries
	 */
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		playerController.armiesAssignedToCountries(mapGraph, country.getName(), 1);
	
	}
	
	/**
	 * Method to reinforce armies
	 */
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		player.setFirstReinforcement(false);
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		playerController.armiesAssignedToCountries(mapGraph, country.getName(), player.getArmyCount());
	}

	/**
	 * Method to perform attack
	 */
	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		playerController = new PlayerController();
		playerController.attackPhase(gameMapGraph, attacker, defender);
		
	}

	/**
	 * Method to perform all-out attack
	 */
	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		playerController = new PlayerController();
		playerController.allOutAttack(gameMapGraph, attackerCountry, defenderCountry);
	}

	/**
	 * Method to fortify a country
	 */
	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		if(fromCountry != null && toCountry != null) {
			playerController.moveArmies(gameMapGraph, fromCountry, toCountry, armiesCount);
		}
		
	}

}
