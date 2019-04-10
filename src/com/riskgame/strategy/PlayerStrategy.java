package com.riskgame.strategy;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Player Strategy definitions to be used by all the types of players. 
 * @author Anusha
 *
 */
public interface PlayerStrategy {
	
	/**
	 * Place armies method that should implement the logic of allocating the armies
	 * from playerController.armiesAssignedToCountries() method 
	 * @param mapGraph - The object of the GameMapGraph
	 * @param country - The object of the country
	 * @param player - The object of the player
	 */
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country);

	/**
	 * Reinforcement of armies to the player owned countries
	 * @param player - The object of the player
	 * @param mapGraph - The object of the GamemapGraph
	 * @param country - The object of the Country
	 * @param reinforceArmyCount - The number of armies for reinforcement
	 */
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount);
	
	/**
	 * Attack phase where the attacker chooses the number of dice to roll(for human player implementation only)
	 *  
	 * @param gameMapGraph - The object of the GameMapGraph
	 * @param attacker - The attacker country
	 * @param defender - The defender country
	 * @param player - The object of the player
	 */
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender);
	
	/**
	 * All out attack mode where the attack happens with the maximum number of dice 
	 * that the player gets based on the number of armies present in his attacking country
	 * 
	 * @param gameMapGraph - The object of the GameMapGraph
	 * @param attackerCountry - The attacker country
	 * @param defenderCountry - The defender country
	 * @param player - The object of the player
	 */
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry, Country defenderCountry);
	
	/**
	 * Fortifying armies from one country to its adjacent country, both of which are owned by the player
	 * @param fromCountry - The country to move armies from
	 * @param toCountry - The country to move armies to
	 * @param armiesCount - Number of armies for fortification
	 * @param gameMapGraph - The object of the GameMapgraph
	 * @param player - The object of the player
	 */
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry, int armiesCount);
	
	
}
