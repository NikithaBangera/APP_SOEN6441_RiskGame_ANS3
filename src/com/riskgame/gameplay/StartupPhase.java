package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

//import javax.swing.text.html.HTMLDocument.Iterator;

import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;

/**
 * StartupPhase is for the beginning of the game. Its will take the 
 * details from the participants, and randomly assign the countries 
 * to the player, and countries, armies allocation will happen to the players
 * as per the Conquest game rule.
 *
 **/

public class StartupPhase {
 
	/** Variable to store the total number of players in game */
	private int countOfthePlayers = 0;
	
	/**
	 * Method to get the count of the players
	 * 
	 * @return Integer count of the players.
	 */
	public int getCountOfthePlayers() {
        return countOfthePlayers;
    }
    
	/**
	 * Method setting the count of the players
	 * 
	 * @return Integer which has Player's count.
	 */
    public void setCountOfthePlayers(int countOfthePlayers) {
        this.countOfthePlayers = countOfthePlayers;
    }
	
	/**List which consists of players name*/
	ArrayList<RiskPlayer> playersList = new ArrayList<RiskPlayer>();

	/**
	 * Method to get the list of the player's name
	 * 
	 * @return ArrayList which has Players name.
	 */
	public ArrayList<RiskPlayer> getPlayersList() {
		return playersList;
	}

    
    /**
	 * Method for setting the Player's list.
	 * 
	 * @param playersList
	 *            It is the player's list needs to be set.
	 */
	public void setPlayersList(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * This method starts the game, obtaining the number of payers , details
	 * and initializing them
	 * 
	 * @param mapGraph
	 *            Object of mapGraph which consists of map details
	 */
	
	public void gamePlay(GameMapGraph mapGraph) throws Exception {
		RiskPlayer player = new RiskPlayer();
//		boolean isAllowedToPlay = true;

		// Startup Phase starts here
		System.out.println("****************");
		System.out.println("****************");
		System.out.println(mapGraph);
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("Enter the number of players below");
		countOfthePlayers = Integer.parseInt(br.readLine());
		System.out.println("countOfthePlayers " + countOfthePlayers);
		if (countOfthePlayers > 1 && countOfthePlayers < 7) {
			System.out.println("Great! Let's Play.");
//			isAllowedToPlay = false;
		} else {
			System.out.println("Sorry! The numbers of players can be between 2 and 6.");
		}

//		int count = 1;

		System.out.println("Enter the name of the players");
		for (int count = 1; count <= countOfthePlayers; count++) {
			boolean continue1 = true;
			RiskPlayer riskPlayer = new RiskPlayer();
			String playername = br.readLine();
			while (continue1) {
				if (playername != null) {
					riskPlayer.setName(playername);
					continue1 = false;

				} else {
					System.out.println("Player name cannot be empty");
				}
			}
			playersList.add(riskPlayer);
		}
		playersList.forEach(P -> {
			System.out.println(P + " *\n");
		});
		allocationOfCountry(mapGraph);
		allocationOfArmyToPlayers();
		allocationOfArmyToCountriesInitially(mapGraph);
		allocationOfArmyToCountries_Balance();

		// Startup Phase starts here

		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());

		// Loop for allowing players to execute actions, turn by turn
		for (int round = 1; round <= getPlayersList().size(); round++) {

			player = roundRobin.nextTurn();

			// Reinforcement Phase starts here

			System.out.println("Reinforcement Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you want to continue with Reinforcement phase? (Yes or No) ");
			if (br.readLine().trim().equalsIgnoreCase("Yes")) {
				ReinforcementPhase reinforcement = new ReinforcementPhase();
				reinforcement.startReinforcement(player);
			} else {
				System.out.println("Exited the Reinforcement Phase!");
			}

			// Reinforcement Phase starts here

			// Fortification Phase starts here

			System.out.println("Fortification Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you wish to start the Fortification phase? (Yes or No)");
			if (br.readLine().trim().equalsIgnoreCase("Yes")) {
				FortificationPhase fortification = new FortificationPhase();
				fortification.startGameFortification(player, mapGraph);
			} else {
				System.out.println("Exited the Fortification phase!");
			}
			// Fortification Phase ends here

		}

	}

	// Function of StartUp Phase starts here
	/**
	 * Method to assign countries to the players. Random allocation of countries
	 * to the players will happen in this method
	 * @param mapGraph
	 *            Object of mapGraph which consists of map details
	 */
	
	
	public void allocationOfCountry(GameMapGraph mapGraph) {
		int i, countryIndexAssignment;
		ArrayList<Country> countrySet = new ArrayList<>(mapGraph.getCountrySet().values());
		while (countrySet.size() > 0) {
			for (i = 0; i < this.playersList.size(); ++i) {
				if (countrySet.size() > 1) {
					countryIndexAssignment = new Random().nextInt(countrySet.size());
					this.playersList.get(i).additionOfCountry(countrySet.get(countryIndexAssignment));
					countrySet.remove(countryIndexAssignment);
				} else if (countrySet.size() == 1) {
					this.playersList.get(i).additionOfCountry(countrySet.get(0));
					countrySet.remove(0);
					break;
				} else {
					break;
				}
			}
		}
//		if (countrySet.size() > 0) {
//			for (i = 1; i < playersList.size(); i++) {
//				if (countrySet.size() > 1) {
//					countryIndexAssignment = new Random().nextInt(countrySet.size());
//					System.out.println("countryIndexAssignment " + countryIndexAssignment);
//					playersList.get(i).additionOfCountry(countrySet.get(countryIndexAssignment));
//					countrySet.remove(countryIndexAssignment);
//				} else if (countrySet.size() == 1) {
//					playersList.get(i).additionOfCountry(countrySet.get(0));
//					countrySet.remove(0);
//				}
//
//			}
//		} else {
//			System.out.println("No more countries to assign");
//		}
	}

	/**
	 * Method to assign the number of armies to the players
	 * which differs based on the number of players.
	 * Allocation is done as per the conquest game rule
	 * 
	 */
	
	public void allocationOfArmyToPlayers() {
		playersList.forEach(player -> {
			switch (countOfthePlayers) {
			case 2:
				player.setArmyCount(40);
				break;
			case 3:
				player.setArmyCount(35);
				break;
			case 4:
				player.setArmyCount(30);
				break;
			case 5:
				player.setArmyCount(25);
				break;
			case 6:
				player.setArmyCount(20);
				break;
			}
		});
	}
   
	/**
	 * Method to assign armies to the countries so that each country will get
	 * at least one country as per the conquest game rule.
	 * @param mapGraph
	 *            Object of mapGraph which consists of map details
	 */
	
	public void allocationOfArmyToCountriesInitially(GameMapGraph mapGraph) {
		// Country country = new Country();
		// for (int i = 0; i < mapGraph.getCountrySet().size(); i++) {
		// country.setNoOfArmies(1);
		// }

		mapGraph.getCountrySet().values().forEach(country -> {
			System.out.println(country.getName() + "   country name");
			country.setNoOfArmies(1);
		});

		playersList.forEach(player -> {
			player.setArmyCount(player.getArmyCount() - player.getMyCountries().size());
		});
	}
	
	/**
	 * Method for armies assignment to the countries so that the number
	 * of armies in the countries will be balanced
	 * 
	 */
	public void allocationOfArmyToCountries_Balance() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		playersList.forEach(player -> {
			System.out.println("Player Name: " + player.getName() + "\n");
//			for (Country country : player.getMyCountries()) 
			player.getMyCountries().forEach(con -> {

//			{
				System.out.println(player.getName()+ "\n my countries " + player.getMyCountries());
				System.out.println(player.getName()+ "player  \n"+ con);
				if (player.getArmyCount() > 0) {
					System.out.println("Country Name : " + con.getName());
					System.out.println("Number of Armies assigned : " + con.getNoOfArmies());
					System.out.println("Available Armies: " + player.getArmyCount());
					System.out.println("Enter number of armies you want to assign to " + con.getName());
					try {
						int number_armies = Integer.parseInt(br.readLine());
						player.armiesAssignedToCountries(con, number_armies);
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					}

				}
//			}
			});
		});

	}
	// Function of StartUp Phase ends here


}