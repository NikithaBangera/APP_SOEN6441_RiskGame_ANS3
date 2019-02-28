package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.riskgame.common.Continent;
import com.riskgame.common.RiskPlayer;

public class StartupPhase {
	ArrayList<RiskPlayer> playersList;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public StartupPhase() {
		this.playersList = new ArrayList<RiskPlayer>();
	}

	public void gamePlay() throws IOException {
		RiskPlayer player = new RiskPlayer();
		int round = 1;
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());

		while (round <= getPlayersList().size()) {

			player = roundRobin.nextTurn();

			System.out.println("Fortification Phase starts!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you wish to start the Fortification phase? (Yes or No)");
			if (br.readLine().trim().equalsIgnoreCase("Yes")) {
				FortificationPhase fortification = new FortificationPhase();
				fortification.startGameFortification(player);
			} else {
				System.out.println("Exited the fortification stage!");
			}

		}

		ReinforcementPhase reinforcement = new ReinforcementPhase();
		reinforcement.startReinforcement(player);

	}

	public ArrayList<RiskPlayer> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
	}

}
