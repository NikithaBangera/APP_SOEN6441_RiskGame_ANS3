package com.riskgame.gameplay;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.risk.model.Player;
import com.riskgame.common.*;

public class StartupPhase {
	
	//private Map playersList = new HashMap();
	private MapTag mapTag;
	private int countOfthePlayers=0;
	public int minimumPlayerCount=2;
	public int maximumPlayercount=6;
	int j;
	
	
	public void gamePlay(MapTag mapTag) {
	this.mapTag = mapTag;
	 HashMap<Integer, String> playersList = new HashMap<Integer, String>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Enter the number of players below");
	 try {
		this.countOfthePlayers = Integer.parseInt(br.readLine());
		if(this.countOfthePlayers <2) {
			System.out.println("Minimum number of players should be 2");
		}
		if(this.countOfthePlayers >6) {
			System.out.println("Maximum number of players in game is 6");
		}
	} catch (NumberFormatException e) {
		System.out.println("Enter the valid number format");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("Enter the valid number format.");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	 System.out.println("Enter the name of the players");
	 for (j = 1; j < this.countOfthePlayers; ++j) {
			RiskPlayer riskPlayer = new RiskPlayer();
			String playername = null;
			while ((playername = br.readLine()) != null) {
				riskPlayer.setName(playername);
			}
			playersList.put(j, riskPlayer);
		}
	 
	 }


	public void allocationOfCountry() {
		// TODO Auto-generated method stub
		
	}


	public void allocationOfArmyToPlayers() {
		// TODO Auto-generated method stub
		
	}


	public void allocationOfArmyToCountriesInitially() {
		// TODO Auto-generated method stub
		
	}


	public void allocationOfArmyToCountries_Balance() {
		// TODO Auto-generated method stub
		
	}
}

//public class StartupPhase {
//	public void gamePlay() {
//		FortificationPhase fortification = new FortificationPhase();
//		fortification.startGameFortification();
//	}
//
//}
