package com.riskgame.model;

import java.util.HashMap;

/**
 * This class is a model and aims to find the domination view of the player in the game.
 * 
 * @author Shiva
 *
 */
public class PlayerDomination {
	/**
	 * This method computes the domination view and returns the same to be displayed
	 * for each player
	 * 
	 * @param mapgraph - The GameMapGraph object
	 * @return the domination hashmap
	 */
	public HashMap<String, Integer> dominationPercentage(GameMapGraph mapgraph) {
		HashMap<String, Integer> playerMapPercentage = new HashMap<String, Integer>();
		int totalCountries = mapgraph.getCountrySet().size();
		for (Player player : mapgraph.getPlayers()) {
			int playerCountries = player.getMyCountries().size();
			int mapPercent = (playerCountries / totalCountries) * 100;
			playerMapPercentage.put(player.getName(), mapPercent);
		}
		return playerMapPercentage;
	}
}
