package com.riskgame.gameplay;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import com.risk.model.Player;
import com.riskgame.common.*;

public class StartupPhase {
	
	//private Map playersList = new HashMap();
	private MapTag mapTag;
	private int countOfthePlayers=0;
	public int minimumPlayerCount=2;
	public int maximumPlayercount=6;
	int j;
	HashMap<Integer, RiskPlayer> playersList = new HashMap<Integer, RiskPlayer>();
	
	public void allocationOfArmyToPlayers() {
		// TODO Auto-generated method stub
		Iterator<Entry<Integer, RiskPlayer>> playerListIterator = playersList.entrySet().iterator();
		while (playerListIterator.hasNext()) {
		    HashMap.Entry<Integer, RiskPlayer> playerEntry = playerListIterator.next();
		    RiskPlayer player = playerEntry.getValue();
		    switch(this.countOfthePlayers) {
		    case 2: player.setArmyCount(40); 
		            break;
		    case 3: player.setArmyCount(35);
		            break;
		    case 4: player.setArmyCount(30);
	                break;
		    case 5: player.setArmyCount(25);
            break;
		    case 6: player.setArmyCount(20);
            break;
		  }
		}
	}
	
	public void gamePlay(MapTag mapTag) throws Exception {
	this.mapTag = mapTag;
	// HashMap<Integer, String> playersList = new HashMap<Integer, String>();
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
