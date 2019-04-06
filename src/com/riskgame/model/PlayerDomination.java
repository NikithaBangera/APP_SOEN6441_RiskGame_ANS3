package com.riskgame.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class is a model and aims to find the domination view of the player in the game.
 * 
 * @author Shiva
 * @author Nikitha
 *
 */
public class PlayerDomination implements Serializable{
	
	/**
	 * This method computes the domination view and returns the same to be displayed
	 * for each player
	 * 
	 * @param mapgraph - The GameMapGraph object
	 * @return the domination percentage hashmap
	 */
	public HashMap<String, Double> dominationPercentage(GameMapGraph mapgraph) {
		HashMap<String, Double> playerMapPercentage = new HashMap<String, Double>();
		int totalCountries = mapgraph.getCountrySet().size();
		for (Player player : mapgraph.getPlayers()) {
			int playerCountries = player.getMyCountries().size();
			double mapPercent = (playerCountries * 100) / totalCountries;
			playerMapPercentage.put(player.getName(), mapPercent);
		}
		return playerMapPercentage;
	}
}
