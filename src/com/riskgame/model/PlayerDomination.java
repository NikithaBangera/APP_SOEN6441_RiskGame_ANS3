package com.riskgame.model;

import java.util.HashMap;

public class PlayerDomination {
	
	public HashMap<String, Integer> dominationPercentage(GameMapGraph mapgraph) {
		HashMap<String, Integer> playerMapPercentage = new HashMap<String, Integer>();
		int totalCountries = mapgraph.getCountrySet().size();
		for(Player player : mapgraph.getPlayers()) {
			int playerCountries = player.getMyCountries().size();
			int mapPercent = (playerCountries / totalCountries) * 100;
			playerMapPercentage.put(player.getName(), mapPercent);
		}
		return playerMapPercentage;
	}
}
