package com.riskgame.common;

import java.util.ArrayList;

public class RiskPlayer {
	/** Player Name */
	private String playerName;
	
	/** Territories(Countries) owned by the player*/
	private ArrayList<Territory> territoriesOwned;
	
	/** Number of armies per player */
	private int armyCount = 0;
	
	public RiskPlayer() {
		this.territoriesOwned = new ArrayList<Territory>();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territoriesOwned;
	}
	
	public void setTerritories(ArrayList<Territory> territoriesOwned) {
		this.territoriesOwned = territoriesOwned;
	}

}
