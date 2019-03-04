package com.riskgame.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.RiskPlayer;

import java.util.Random;
import java.io.IOException;

/**
 * StartupPhase class is for the beginning of the game play. Its contains 
 * methods which will take the details from the players, and randomly assign 
 * the countries to the them. 
 * 
 * Armies allocation will happen to the players
 * as per the Conquest game rule.
 *
 *@author Anusha
 *
 */
public class StartupPhase {

	/** Variable to store the total number of players in game. */
	private int countOfthePlayers = 0;
	
	/**List which consists of players name.*/
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

	/**
	 * Method for setting the Player's list
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
	 * and initializing them.
	 * 
	 * @param mapGraph
	 *            Object of mapGraph which consists of map details
	 */
	public void gamePlay(GameMapGraph mapGraph) throws Exception {
		RiskPlayer player = new RiskPlayer();
		System.out.println("Enter the number of players below");
		String playerCount = br.readLine().trim();
		while (playerCount.isEmpty()) {
			System.err.println("\nPlayer name cannot be blank. Please enter the correct player name below:");
			System.out.flush();
			playerCount = br.readLine().trim();
		}
		countOfthePlayers = Integer.parseInt(playerCount);

		if (countOfthePlayers > 1 && countOfthePlayers < 7) {
			System.out.println("Great! Let's Play.");
		} 
		else {
			System.out.println("Sorry! The numbers of players can be between 2 and 6.");
		}

		System.out.println("Enter the name of the players");
		for (int count = 1; count <= countOfthePlayers; count++) {
			boolean continue1 = true;
			RiskPlayer riskPlayer = new RiskPlayer();
			String playername = br.readLine().trim();
			while (playername.isEmpty()) {
				System.err.println("\nPlayer name cannot be blank. Please enter the correct player name below:");
				System.out.flush();
				playername = br.readLine().trim();
			}

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
		allocationOfCountry(mapGraph);
		allocationOfArmyToPlayers();
		allocationOfArmyToCountriesInitially(mapGraph);
		allocationOfRemainingArmyToCountries();

		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());

		for (int round = 1; round <= getPlayersList().size(); round++) {
			player = roundRobin.nextTurn();
			System.out.println("Reinforcement Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you want to continue with Reinforcement phase? (Yes or No) ");
			String choice = br.readLine().trim();
			while (choice.isEmpty()) {
				System.err.println("\nChoice cannot be blank. Please enter the correct choice below:");
				System.out.flush();
				choice = br.readLine().trim();
			}

			if (choice.equalsIgnoreCase("Yes")) {
				ReinforcementPhase reinforcement = new ReinforcementPhase();
				reinforcement.startReinforcement(player);
			} else {
				System.out.println("Exited the Reinforcement Phase!");
			}
			System.out.println("Fortification Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you wish to start the Fortification phase? (Yes or No)");
			String choice1 = br.readLine().trim();
			while (choice1.isEmpty()) {
				System.err.println("\nChoice cannot be blank. Please enter the correct choice below:");
				System.out.flush();
				choice1 = br.readLine().trim();
			}
			if (choice1.equalsIgnoreCase("Yes")) {
				FortificationPhase fortification = new FortificationPhase();
				fortification.startGameFortification(player, mapGraph);
			} else {
				System.out.println("Exited the Fortification phase!");
			}
		}
	}

	/**
	 * Method to assign countries to the players. Random allocation of countries
	 * to the players will happen in this method.
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

	}

	/**
	 * Method to assign the number of armies to the players which differs 
	 * based on the players count. Army allocation is done as per the 
	 * conquest game rule.
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
	 * at least one army as per the conquest game rule.
	 * @param mapGraph
	 *            Object of mapGraph which consists of map details
	 */
	public void allocationOfArmyToCountriesInitially(GameMapGraph mapGraph) {
		mapGraph.getCountrySet().values().forEach(country -> {
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
	public void allocationOfRemainingArmyToCountries() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		playersList.forEach(player -> {
			System.out.println("Player Name: " + player.getName() + "\n");
			player.getMyCountries().forEach(con -> {
				if (player.getArmyCount() > 0) {
					System.out.println("Country Name : " + con.getName());
					System.out.println("Number of Armies assigned : " + con.getNoOfArmies());
					System.out.println("Available Armies: " + player.getArmyCount());
					System.out.println("Enter number of armies you want to assign to " + con.getName());
					try {
						String numArmies = br.readLine().trim();
						while (numArmies.isEmpty()) {
							System.err.println(
									"\nNumber of armies cannot be blank. Please enter the correct number below:");
							System.out.flush();
							numArmies = br.readLine().trim();
						}
						int numberOFarmies = Integer.parseInt(numArmies);
						player.armiesAssignedToCountries(con, numberOFarmies);
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
					}

				}
			});
		});

	}
}