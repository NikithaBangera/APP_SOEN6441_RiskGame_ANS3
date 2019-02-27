package com.riskgame.gameplay;

import java.util.ArrayList;

import com.riskgame.common.Continent;
import com.riskgame.common.RiskPlayer;

public class StartupPhase {
ArrayList<RiskPlayer> playersList;
	
	public StartupPhase() {
		this.playersList = new ArrayList<RiskPlayer>();
	}
	
	public void gamePlay() {
		RiskPlayer player = new RiskPlayer();
		int round  = 1;
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());
		
		while(round <= getPlayersList().size()) {
			
			player = roundRobin.nextTurn();
			
		}
		
		ReinforcementPhase reinforcement = new ReinforcementPhase();
		reinforcement.startReinforcement(player);
		
		FortificationPhase fortification = new FortificationPhase();
		fortification.startGameFortification();
	}

	public ArrayList<RiskPlayer> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
	}

}
