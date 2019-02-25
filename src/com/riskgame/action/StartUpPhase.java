package com.riskgame.action;

import java.util.ArrayList;

import com.riskgame.common.RiskPlayer;

public class StartUpPhase {
	ArrayList<RiskPlayer> playersList;
	
	public StartUpPhase() {
		this.playersList = new ArrayList<RiskPlayer>();
	}
	public void allocateTerritory() {
		// TODO Auto-generated method stub
		
	}

	public void allocateArmyToPlayers() {
		// TODO Auto-generated method stub
		
	}

	public void allocateInitialArmyToTerritories() {
		// TODO Auto-generated method stub
		
	}

	public void assignPendingArmyToTerritories() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<RiskPlayer> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
	}
}
