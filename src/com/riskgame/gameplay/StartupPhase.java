package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int round = 1; round <= getPlayersList().size(); round++) {
			
			player = roundRobin.nextTurn();
			
		
		System.out.println("Reinforcement Phase begins for player " + player.getName() + "\n");
		System.out.println("Do you want to continue with Reinforcement phase? (Yes or No) ");
		try {
			if (br.readLine().trim().equalsIgnoreCase("Yes")) {
				ReinforcementPhase reinforcement = new ReinforcementPhase();
				reinforcement.startReinforcement(player);
			}
			else {
				System.out.println();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		FortificationPhase fortification = new FortificationPhase();
		fortification.startGameFortification();
		}
	}

	public ArrayList<RiskPlayer> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
	}

}
