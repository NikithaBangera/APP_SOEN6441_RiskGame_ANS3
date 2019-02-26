package com.riskgame.common;

import java.util.ArrayList;

public class RiskPlayer {
	/** Player Name */
	private String playerName;

	
	/** Territories(Countries) owned by the player*/
	private ArrayList<Territory> territoriesOwned;
	
	/** Number of armies per player */
	private int armiesCount = 0;
	
	public RiskPlayer() {
		this.territoriesOwned = new ArrayList<Territory>();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	/**
	 * Method to get the list of countries held by player
	 * @return
	 */
	public ArrayList<Territory> getTerritories() {
		return territoriesOwned;
	}
	
	public void setTerritories(ArrayList<Territory> territoriesOwned) {
		this.territoriesOwned = territoriesOwned;
	}
	
	public int getArmiesCount() {
		return armiesCount;
	}
	
	public void setArmiesCount(int armiesCount) {
        this.armiesCount = armiesCount;
    }
	
	public void addTerritory(Territory territory) {
		this.territoriesOwned.add(territory);
	}

	public void armiesAssignedToTerritories(Territory territory, int armiesCount) {
		if(this.getTerritories().contains(territory)) {
			if((this.getArmiesCount()) > 0 && this.getArmiesCount() >= armiesCount) {
				territory.setArmiesCount(territory.getArmiesCount() + armiesCount);
				this.setArmiesCount(this.getArmiesCount() - armiesCount);
			}
			else {
				System.out.println("Insufficient number of armies.");
			}
		}
		else {
			System.out.println("This country is not owned by you!");
		}
	}
	
}
