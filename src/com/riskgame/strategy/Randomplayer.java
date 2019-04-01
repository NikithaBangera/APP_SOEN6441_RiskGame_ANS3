package com.riskgame.strategy;

import java.util.ArrayList;
import java.util.Random;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Random player reinforces random country, attacks random country a random number of times and 
 * fortifies a random country(implementation similar to human player but happens in a random fashion)
 * @author 
 *
 */
public class Randomplayer implements PlayerStrategy{

	PlayerController playerController;
	
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		playerController.armiesAssignedToCountries(mapGraph, getRandomCountry(mapGraph,player).getName(), 1);
	}

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		int reinforcement=new Random().nextInt(2);
		if(reinforcement==1) {
			int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
			player.setArmyCount(player.getArmyCount() + reinforcementArmies);
			playerController.armiesAssignedToCountries(mapGraph, getRandomCountry(mapGraph, player).getName(), player.getArmyCount());
		}
	}

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		// TODO Auto-generated method stub
		
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
	
	public Country getRandomCountry(GameMapGraph mapGraph, Player player) {
		ArrayList<Country> countrySet = new ArrayList<>(player.getMyCountries());
		int countryIndexAssignment = new Random().nextInt(countrySet.size());
		Country randomCountry = countrySet.get(countryIndexAssignment);
		return randomCountry;
	}
	
}
