package com.riskgame.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.RiskPlayer;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 *@author Shresthi
 *@author Nikitha
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

		// Startup Phase starts here
//		System.out.println("****************");
//		System.out.println("****************");
//		System.out.println(mapGraph);
//		System.out.println("****************");
//		System.out.println("****************");
		boolean proceed = false;
		do {
			System.out.println("Enter the number of players below");
			String playerCount = br.readLine().trim();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			Matcher match = numberPattern.matcher(playerCount);
			while(!match.matches() || playerCount.isEmpty()) {
				System.err.println("\nPlease enter the correct player count below:");
				System.out.flush();
				playerCount = br.readLine().trim();
				match = numberPattern.matcher(playerCount);
			}
			countOfthePlayers = Integer.parseInt(playerCount);
	
	//		System.out.println("countOfthePlayers " + countOfthePlayers);
			if (countOfthePlayers > 1 && countOfthePlayers < 7) {
				System.out.println("Great! Let's Play.");
				proceed = false;
			} else {
				System.out.println("Sorry! The numbers of players can be between 2 and 6.");
				proceed = true;
			}
		}while(proceed);
		
		System.out.println("Enter the name of the players");
		for (int count = 1; count <= countOfthePlayers; count++) {
			boolean continue1 = true;
			RiskPlayer riskPlayer = new RiskPlayer();
			String playername = br.readLine().trim();
			Pattern namePattern = Pattern.compile("[a-zA-z]+");
			Matcher match = namePattern.matcher(playername);
			while (!match.matches() || playername.isEmpty()) {
				System.err.println("\nPlease enter the correct player name below:");
				System.out.flush();
				playername = br.readLine().trim();
				match = namePattern.matcher(playername);
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
		// playersList.forEach(P -> {
		// System.out.println(P + " *\n");
		// });
		allocationOfCountry(mapGraph);
		allocationOfArmyToPlayers();
		allocationOfArmyToCountriesInitially(mapGraph);
		allocationOfRemainingArmyToCountries();

		// Startup Phase starts here

		RoundRobinScheduler roundRobin = new RoundRobinScheduler(getPlayersList());

		// Loop for allowing players to execute actions, turn by turn
		for (int round = 1; round <= getPlayersList().size(); round++) {

			player = roundRobin.nextTurn();

			// Reinforcement Phase starts here

			System.out.println("\nReinforcement Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you want to continue with Reinforcement phase? (Yes or No) ");
			String choice = br.readLine().trim();
			while (!((choice.equalsIgnoreCase("Yes")) || (choice.equalsIgnoreCase("No")) || choice == null)) {
				System.err.println("Invalid/Blank value entered, please enter Yes or No");
				System.out.flush();
				choice = br.readLine().trim();
			}

			if (choice.equalsIgnoreCase("Yes")) {
				ReinforcementPhase reinforcement = new ReinforcementPhase();
				reinforcement.startReinforcement(player, mapGraph);
			} else if (choice.equalsIgnoreCase("No")){
				System.out.println("Exited the Reinforcement Phase!");
			}

			// Reinforcement Phase ends here

			// Fortification Phase starts here

			System.out.println("\nFortification Phase begins!\n");
			System.out.println("Player: " + player.getName() + "\n");
			System.out.println("Do you wish to start the Fortification phase? (Yes or No)");
			String choice1 = br.readLine().trim();
			while (!((choice1.equalsIgnoreCase("Yes")) || (choice1.equalsIgnoreCase("No")) || choice1 == null)) {
				System.err.println("Invalid/Blank value entered, please enter Yes or No");
				System.out.flush();
				choice1 = br.readLine().trim();
			}
			if (choice1.equalsIgnoreCase("Yes")) {
				FortificationPhase fortification = new FortificationPhase();
				fortification.startGameFortification(player, mapGraph);
			} else if (choice.equalsIgnoreCase("No")){
				System.out.println("Exited the Fortification phase!");
			}
			// Fortification Phase ends here

		}
	}
	
	// Function of StartUp Phase starts here

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
			System.out.println("\nPlayer Name: " + player.getName() + "\n");
			player.getMyCountries().forEach(con -> {
				if (player.getArmyCount() > 0) {
					System.out.println("Country Name : " + con.getName());
					System.out.println("Number of Armies assigned : " + con.getNoOfArmies());
					System.out.println("Available Armies: " + player.getArmyCount());
					System.out.println("Enter number of armies you want to assign to " + con.getName());
					try {
						String numArmies = br.readLine().trim();
						Pattern numberPattern = Pattern.compile("[0-9]+");
						Matcher match = numberPattern.matcher(numArmies);
						while (!match.matches() || numArmies.isEmpty()) {
							System.err.println("\nPlease enter the correct number of armies below:");
							System.out.flush();
							numArmies = br.readLine().trim();
							match = numberPattern.matcher(numArmies);
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
	// Function of StartUp Phase ends here

}
