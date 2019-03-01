package com.riskgame.gameplay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import javax.swing.text.html.HTMLDocument.Iterator;

import com.riskgame.common.Country;
import com.riskgame.common.RiskPlayer;

public class StartupPhase {
	private int countOfthePlayers=0;
	private MapTag mapTag;
	HashMap<Integer, RiskPlayer> playersList = new HashMap<Integer, RiskPlayer>();
	//ArrayList<RiskPlayer> playersList;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//	public StartupPhase() {
//		this.playersList = new ArrayList<RiskPlayer>();
//	}
//
//	public ArrayList<RiskPlayer> getPlayersList() {
//		return playersList;
//	}
//
//	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
//		this.playersList = playersList;
//	}

	public void gamePlay(MapTag mapTag) throws IOException {
		RiskPlayer player = new RiskPlayer();
		
		System.out.println("Enter the number of players below");
		 try {
			this.countOfthePlayers = Integer.parseInt(br.readLine());
			if(this.countOfthePlayers <2) {
				System.out.println("Minimum number of players should be 2");
				gamePlay(MapTag mapTag);
			}
			if(this.countOfthePlayers >6) {
				System.out.println("Maximum number of players in game is 6");
				gamePlay(MapTag mapTag);
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
		 for ( int j = 1; j < this.countOfthePlayers; j++) {
				RiskPlayer riskPlayer = new RiskPlayer();
				String playername = null;
				while ((playername = br.readLine()) != null) {
					riskPlayer.setName(playername);
				}
				playersList.put(j, riskPlayer);
			}
		
       
		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());
		for (int round = 1; round <= getPlayersList().size(); round++) {

			player = roundRobin.nextTurn();

			ReinforcementPhase reinforcement = new ReinforcementPhase();
			reinforcement.startReinforcement(player);

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

	}
	
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
	
	public void allocationOfCountry() {
		// TODO Auto-generated method stub
		//mapTag object and getMapGraph will come from Sumeetha's module
		int i, countryIndexAssignment;
		ArrayList<Country> countrySet = new ArrayList<>(this.mapTag.getMapGraph().getCountrySet().values());
		if(countrySet.size() > 0) {
			for(i=1; i<playersList.size();i++) {
				while(countrySet.size()>1) {
					countryIndexAssignment = new Random().nextInt(countrySet.size());
					playersList.get(i).additionOfCountry(countrySet.get(countryIndexAssignment));
					countrySet.remove(0);
				}
				while(countrySet.size()==1) {
					playersList.get(i).additionOfCountry(countrySet.get(0));
					countrySet.remove(0);
				}
				
			}
		}		
	}
	
	public void allocationOfArmyToCountries_Balance(){
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Iterator<Entry<Integer, RiskPlayer>> playerListIterator = playersList.entrySet().iterator();
		while (playerListIterator.hasNext()) {
		    HashMap.Entry<Integer, RiskPlayer> playerEntry = playerListIterator.next();
		    RiskPlayer player = playerEntry.getValue(); 
		    System.out.println("Player Name: " + player.getName());
		    for (Country country : player.getMyCountries()) {
		    	while (player.getArmyCount() > 0) {
		    		System.out.println("Country Name : " + country.getName());
		    		System.out.println("Number of Armies assigned : " + country.getNoOfArmies());
		    		System.out.println("Available Armies: " + player.getArmyCount());
		    		System.out.println("Enter number of armies you want to assign to " + country.getName());
		    		try {
						int number_armies = Integer.parseInt(br.readLine());
						player.armiesAssignedToCountries(country, number_armies);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					}
		    		
		    	}
		    }
		    
		}
		
		
	}
	public void allocationOfArmyToCountriesInitially() {
		// TODO Auto-generated method stub
		Country country;
	int countrySetValue = mapTag.getMapGraph().getCountrySet().values();
	for(int i=0;i<countrySetValue;i++) {
		country.setNoOfArmies(1);
	}
	Iterator<Entry<Integer, RiskPlayer>> playerListIterator = playersList.entrySet().iterator();
	while (playerListIterator.hasNext()) {
	    HashMap.Entry<Integer, RiskPlayer> playerEntry = playerListIterator.next();
	    RiskPlayer player = playerEntry.getValue();
	    player.setArmyCount(player.getArmyCount() - player.getMyCountries().size());
	    }
	}
	
	

}
