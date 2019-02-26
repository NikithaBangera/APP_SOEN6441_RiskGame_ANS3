package com.riskgame.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.riskgame.common.Continent;
import com.riskgame.common.RiskPlayer;
import com.riskgame.common.Territory;
import com.riskgame.gameplay.ReinforcementPhase;
import com.riskgame.gameplay.RoundRobinScheduler;

import javafx.scene.control.cell.MapValueFactory;

public class StartGame {
	public static void playGame() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int round  = 1;
		ValidateLoadMap validateLoadMap = new ValidateLoadMap();
		
		//set up the startUp phase 
		StartUpPhase startUp = new StartUpPhase();
		startUp.allocateTerritory();
		startUp.allocateArmyToPlayers();
		startUp.allocateInitialArmyToTerritories();
		startUp.assignPendingArmyToTerritories();
		
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(startUp.getPlayersList());
		
		while(round <= startUp.getPlayersList().size()) {
			
			RiskPlayer player = roundRobin.nextTurn();
			Continent continent = validateLoadMap.getContinentDetails().get(player.getTerritories().get(0).getContinentName()) ;
			armyReinforcement(player,continent); 
			
		}
	}

	private static void armyReinforcement(RiskPlayer player, Continent continent) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Reinforcement Phase begins for player " + player.getPlayerName() + "\n");
		
		int reinforcementArmies = ReinforcementPhase.armiesToBeAssigned(player, continent);
		System.out.println("Armies available for Reinforcement: "+ reinforcementArmies);
		player.setArmiesCount(player.getArmiesCount() + reinforcementArmies);
		
		for(Territory territory : player.getTerritories()) {
			if(player.getArmiesCount() > 0) {
				System.out.println("Number of armies present in country " + territory.getTerritoryName() + "are " + territory.getArmiesCount());
				System.out.println("Current available armies to be reinforced: " + player.getArmiesCount());
				System.out.println("Enter the number of armies to be deployed to country " + territory.getTerritoryName());
				int armiesCount = Integer.parseInt(in.readLine());
				player.armiesAssignedToTerritories(territory, armiesCount);
			}
			else {
				System.out.println("Insufficient armies available for reinforcement");
				break;
			}
		}
	}
	
	
}
