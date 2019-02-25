package com.riskgame.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.riskgame.common.RiskPlayer;
import com.riskgame.common.Territory;
import com.riskgame.gameplay.ReinforcementPhase;
import com.riskgame.gameplay.RoundRobinScheduler;

public class startGame {
	private void playGame() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int round  = 1;
	
		
		//set up the startUp phase 
		StartUpPhase startUp = new StartUpPhase();
		startUp.allocateTerritory();
		startUp.allocateArmyToPlayers();
		startUp.allocateInitialArmyToTerritories();
		startUp.assignPendingArmyToTerritories();
		
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(startUp.getPlayersList());
		
		while(round <= startUp.getPlayersList().size()) {
			
			RiskPlayer player = roundRobin.nextTurn();
			
			System.out.println("Reinforcement Phase begins for player " + player.getPlayerName() + "\n");
			
			int reinforcementArmies = ReinforcementPhase.armiesToBeAssigned(player, continent);
			System.out.println("Armies received for Reinforcement: "+ reinforcementArmies);
			player.setArmiesCount(player.getArmiesCount() + reinforcementArmies);
			
			for(Territory territory : player.getTerritories()) {
				if(player.getArmiesCount() > 0) {
					System.out.println("Number of armies present in country " + territory.getTerritoryName() + "are " + territory.getArmiesCount());
				}
			}
		}
	}
}
