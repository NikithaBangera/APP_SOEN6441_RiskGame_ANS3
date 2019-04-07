package com.riskgame.strategy;

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
public class RandomPlayer implements PlayerStrategy{

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
		Random random = new Random();
		int countryNumber = random.nextInt(player.getMyCountries().size()) + 1;
		playerController.armiesAssignedToCountries(mapGraph, player.getMyCountries().get(countryNumber-1).getName(), 1);
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
		reinforceArmyCount = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforceArmyCount);
		country = getRandomCountry(mapGraph, player);
		playerController.armiesAssignedToCountries(mapGraph, country.getName(), reinforceArmyCount);
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
		Player adjPlayer = null;
		attacker = getRandomCountry(gameMapGraph,player);
		if(getRandomCountryWithAdjCountry(gameMapGraph, player, attacker)) {
			do {
				int randomAdjCountry = new Random().nextInt(attacker.getAdjacentCountries().size()) + 1;
				defender = playerController.getAdjacentCountry(gameMapGraph, attacker.getAdjacentCountries().get(randomAdjCountry));
				adjPlayer = playerController.getPlayerForCountry(gameMapGraph, defender.getName());
				
			}while(player.getName().equalsIgnoreCase(adjPlayer.getName()));
		
		}
		
		if(defender != null) {
			playerController.allOutAttack(gameMapGraph, attacker, defender);
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
			boolean countryFound = false;
			fromCountry=getRandomCountry(gameMapGraph,player);
			do {
				toCountry = getRandomCountry(gameMapGraph, player);
				if(!fromCountry.getName().equalsIgnoreCase(toCountry.getName())) {
					countryFound = true;
				}
			}while(!countryFound);
			
			armiesCount=new Random().nextInt(fromCountry.getNoOfArmies()) + 1;
			if(armiesCount > 1) {
				playerController.moveArmies(fromCountry, toCountry, armiesCount - 1);
			}
	}
	/**
	 * this method gets a random country owned by the Random player
	 * @param mapGraph
	 * @param player
	 * @return randomCountry
	 */
	public Country getRandomCountry(GameMapGraph mapGraph, Player player) {
		int countryIndexAssignment = new Random().nextInt(player.getMyCountries().size()) + 1;
		return player.getMyCountries().get(countryIndexAssignment - 1);
	}
	
	/**
	 * this method gets a random country of player with adjacent country belonging to a different player
	 * @param mapGraph
	 * @param country
	 * @return randomAdjacentCountry
	 */
	public boolean getRandomCountryWithAdjCountry(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		boolean hasValidAdjCountry = false;
		for(String adjCountry : country.getAdjacentCountries()) {
			Player adjPlayer = playerController.getPlayerForCountry(mapGraph, adjCountry);
			if(!player.getName().equalsIgnoreCase(adjPlayer.getName())) {
				hasValidAdjCountry = true;
				break;
			}
		}
		return hasValidAdjCountry;
	}
	
}
