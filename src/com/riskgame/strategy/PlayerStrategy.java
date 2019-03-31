package com.riskgame.strategy;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Player Strategy definitions to be used by all the types of players. 
 * @author 
 *
 */
public interface PlayerStrategy {
	
	/**
	 * Place armies method that should implement the logic of allocating the armies
	 * from playerController.armiesAssignedToCountries() method 
	 * @param mapGraph
	 * @param country
	 * @param armiesCount
	 */
	public void placeArmies(GameMapGraph mapGraph, Player player);

	/**
	 * Reinforcement of armies to the player owned countries
	 * @param player
	 * @param mapGraph
	 * @return
	 */
	public void reinforcementPhase(Player player, GameMapGraph mapGraph);
	
	/**
	 * Attack phase where the attacker chooses the number of dice to roll(for human player implementation only)
	 *  
	 * @param gameMapGraph
	 * @param attacker
	 * @param defender
	 */
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender);
	
	/**
	 * All out attack mode where the attack happens with the maximum number of dice 
	 * that the player gets based on the number of armies present in his attacking country
	 * 
	 * @param gameMapGraph
	 * @param attackerCountry
	 * @param defenderCountry
	 */
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry, Country defenderCountry);
	
	/**
	 * Fortifying armies from one country to its adjacent country, both of which are owned by the player
	 * @param fromCountry
	 * @param toCountry
	 * @param armiesCount
	 */
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry, int armiesCount);
	
	
}
