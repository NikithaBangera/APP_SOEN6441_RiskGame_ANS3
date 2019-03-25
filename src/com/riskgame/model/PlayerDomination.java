package com.riskgame.model;

import java.util.HashMap;

public class PlayerDomination {
	
	public HashMap<String, Double> dominationPercentage(GameMapGraph mapgraph) {
		HashMap<String, Double> playerMapPercentage = new HashMap<String, Double>();
		int totalCountries = mapgraph.getCountrySet().size();
		for(Player player : mapgraph.getPlayers()) {
			int playerCountries = player.getMyCountries().size();
			double mapPercent = (playerCountries * 100) / totalCountries;
			playerMapPercentage.put(player.getName(), mapPercent);
		}
		return playerMapPercentage;
	}
}
