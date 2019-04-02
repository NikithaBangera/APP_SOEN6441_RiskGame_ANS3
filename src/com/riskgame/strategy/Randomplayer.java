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
 * @author Shiva
 *
 */
public class Randomplayer implements PlayerStrategy{

	PlayerController playerController;
	
	/**
	 * this method place armies on a random country owned by the player
	 * 
	 * @param mapGraph - The GameMapGraph object
	 * @param player - the random player
	 * @param country - random country owned by the random player
	 */
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		country = getRandomCountry(mapGraph,player);
		playerController.armiesAssignedToCountries(mapGraph, country.getName(), 1);
	}
	
	/**
	 * Reinforcement phase of the random player. 
	 * In this method we choose randomly whether to reinforce or not.
	 * if we choose to reinforce, we choose a random country
	 * and we choose a random number
	 * 
	 * @param player - The random player
	 * @param mapGraph - The GameMapGraph object
	 * @param country - Random country to be reinforced
	 * @param reinforceArmyCount - Random number of armies for reinforcement
	 */

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		int reinforcement=new Random().nextInt(2);
		if(reinforcement==1) {
			int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
			player.setArmyCount(player.getArmyCount() + reinforcementArmies);
			country = getRandomCountry(mapGraph, player);
			reinforceArmyCount=new Random().nextInt(player.getArmyCount());
			playerController.armiesAssignedToCountries(mapGraph, country.getName(), reinforceArmyCount);
		}
	}
	
	/**
	 * this method chooses two random adjacent countries and decide whether to attack or not.
	 * if it chooses to attack it randomly decide how many times to attack if the attack is possible
	 * 
	 * @param gameMapGraph - The GameMapGraph object
	 * @param player - the Random player
	 * @param attacker - Random country of the Random player
	 * @param defender - Random adjacent country of the attacker country
	 */
	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		playerController = new PlayerController();
		attacker = getRandomCountry(gameMapGraph,player);
		defender = getRandomAdjacentCountry(gameMapGraph,attacker);
		int attacktimes=new Random().nextInt(attacker.getNoOfArmies());
		while( (!player.getMyCountries().contains(defender)) && attacktimes !=0 && (!(attacker.getNoOfArmies() > 1 && defender.getNoOfArmies() > 0))) {
			playerController.attackPhase(gameMapGraph, attacker, defender);
			attacktimes--;
		}
	}
	
	/**
	 * this method chooses two random countries and does the all out attack
	 * @param gameMapGraph - The GameMapGraph object
	 * @param player - the Random player
	 * @param attackerCountry - Random country of the Random player
	 * @param defenderCountry - Random adjacent country of the attacker country
	 */
	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		playerController = new PlayerController();
		attackerCountry = getRandomCountry(gameMapGraph,player);
		defenderCountry = getRandomAdjacentCountry(gameMapGraph,attackerCountry);
		int attacktimes=attackerCountry.getNoOfArmies();
		while( (!player.getMyCountries().contains(defenderCountry)) && attacktimes !=0 && (!(attackerCountry.getNoOfArmies() > 1 && defenderCountry.getNoOfArmies() > 0))) {
			playerController.attackPhase(gameMapGraph, attackerCountry, defenderCountry);
			attacktimes--;
		}
		
	}

	/**
	 * this method chooses two random adjacent countries 
	 * and fortifies a random number of possible armies
	 * @param gameMapGraph - The GameMapGraph object
	 * @param player - the Random player
	 * @param fromCountry - Random country to move armies from
	 * @param toCountry - Random adjacent country of the fromCountry to move armies to 
	 */
	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
			playerController=new PlayerController();
			fromCountry=getRandomCountry(gameMapGraph,player);
			toCountry=getRandomAdjacentCountry(gameMapGraph,fromCountry);
			if(player.getMyCountries().contains(toCountry)) {
				armiesCount=new Random().nextInt(fromCountry.getNoOfArmies());
				playerController.moveArmies(fromCountry, toCountry, armiesCount);
			}	
	}
	/**
	 * this method gets a random country owned by the Random player
	 * @param mapGraph
	 * @param player
	 * @return randomCountry
	 */
	public Country getRandomCountry(GameMapGraph mapGraph, Player player) {
		ArrayList<Country> countrySet = new ArrayList<>(player.getMyCountries());
		int countryIndexAssignment = new Random().nextInt(countrySet.size());
		Country randomCountry = countrySet.get(countryIndexAssignment);
		return randomCountry;
	}
	
	/**
	 * this method gets a random adjacent country of a country
	 * @param mapGraph
	 * @param country
	 * @return randomAdjacentCountry
	 */
	public Country getRandomAdjacentCountry(GameMapGraph mapGraph, Country country) {
		int choice=new Random().nextInt(country.getAdjacentCountries().size());
		Country randomAdjacentCountry=mapGraph.getCountrySet().get(country.getAdjacentCountries().get(choice));
		return randomAdjacentCountry;
	}
	
}
