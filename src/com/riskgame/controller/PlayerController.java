package com.riskgame.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.Dice;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

public class PlayerController extends Observable implements Observer{

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/** Variable to store the total number of players in game. */
	private int countOfthePlayers = 0;

	/** List which countries. */
	public static ArrayList<Country> countriesList = new ArrayList<Country>();
	
	public boolean doFortification = false;

	public int getCountOfthePlayers() {
		return countOfthePlayers;
	}

	public void setCountOfthePlayers(int countOfthePlayers) {
		this.countOfthePlayers = countOfthePlayers;
	}

	public void gamePlay(GameMapGraph mapGraph) throws Exception {
		Player player = new Player();
		// Startup Phase starts here
		boolean proceed = false;
		do {
			System.out.println("Enter the number of players below");
			String playerCount = br.readLine().trim();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			Matcher match = numberPattern.matcher(playerCount);
			while (!match.matches() || playerCount.isEmpty()) {
				System.out.println("\nPlease enter the correct player count below:");

				playerCount = br.readLine().trim();
				match = numberPattern.matcher(playerCount);
			}
			countOfthePlayers = Integer.parseInt(playerCount);

			if (countOfthePlayers > 1 && countOfthePlayers < 7) {
				System.out.println("Great! Let's Play.");
				proceed = false;
			} else {
				System.out.println("Sorry! The numbers of players can be between 2 and 6.");
				proceed = true;
			}
		} while (proceed);

		System.out.println("Enter the name of the players");
		for (int count = 1; count <= countOfthePlayers; count++) {
			boolean continue1 = true;
			Player riskPlayer = new Player();
			String playername = br.readLine().trim();
			Pattern namePattern = Pattern.compile("[a-zA-z]+");
			Matcher match = namePattern.matcher(playername);
			while (!match.matches() || playername.isEmpty()) {
				System.out.println("\nPlease enter the correct player name below:");

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
			mapGraph.getPlayers().add(riskPlayer);
		}
		
		
		allocationOfCountry(mapGraph);
		allocationOfArmyToPlayers(mapGraph);
		allocationOfArmyToCountriesInitially(mapGraph);
		
		CardController cardController = new CardController();
		cardController.assignCountryToCard(mapGraph);
		
		
		
		
		
		setChanged();
		notifyObservers();
		allocationOfRemainingArmyToCountries(mapGraph);

		// Place Army Phase starts here

		mapGraph.setGamePhase("Place Armies");
		PlayerView playerView = new PlayerView(mapGraph);
		
	}



	// Function of StartUp Phase starts here
	
	

	/**
	 * Method to assign countries to the players. Random allocation of countries to
	 * the players will happen in this method.
	 * 
	 * @param mapGraph Object of mapGraph which consists of map details
	 */
	public void allocationOfCountry(GameMapGraph mapGraph) {
		int i, countryIndexAssignment;
		ArrayList<Country> countrySet = new ArrayList<>(mapGraph.getCountrySet().values());
		while (countrySet.size() > 0) {
			for (i = 0; i < mapGraph.getPlayers().size(); ++i) {
				if (countrySet.size() > 1) {
					countryIndexAssignment = new Random().nextInt(countrySet.size());
					mapGraph.getPlayers().get(i).additionOfCountry(countrySet.get(countryIndexAssignment));
					countrySet.remove(countryIndexAssignment);
				} else if (countrySet.size() == 1) {
					mapGraph.getPlayers().get(i).additionOfCountry(countrySet.get(0));
					countrySet.remove(0);
					break;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * Method to assign the number of armies to the players which differs based on
	 * the players count. Army allocation is done as per the conquest game rule.
	 * @param mapGraph 
	 * 
	 */
	public void allocationOfArmyToPlayers(GameMapGraph mapGraph) {
		mapGraph.getPlayers().forEach(player -> {
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
	 * Method to assign armies to the countries so that each country will get at
	 * least one army as per the conquest game rule.
	 * 
	 * @param mapGraph Object of mapGraph which consists of map details
	 */
	public void allocationOfArmyToCountriesInitially(GameMapGraph mapGraph) {

		mapGraph.getCountrySet().values().forEach(country -> {
			country.setNoOfArmies(1);
		});

		mapGraph.getPlayers().forEach(player -> {
			player.setArmyCount(player.getArmyCount() - player.getMyCountries().size());
		});
	}

	/**
	 * Method for armies assignment to the countries so that the number of armies in
	 * the countries will be balanced
	 * @param mapGraph 
	 * 
	 */
	public void allocationOfRemainingArmyToCountries(GameMapGraph mapGraph) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mapGraph.getPlayers().forEach(player -> {
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
							System.out.println("\nPlease enter the correct number of armies below:");

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

	// Reinforcement Phase
	/**
	 * This method prompts the player whether he/she wants to play the reinforcement
	 * phase. If the player wants to play reinforcement phase then this method will
	 * call the armiesToBeAssigned function to assign armies to the player.
	 * 
	 * @param player   - player for reinforcement
	 * @param mapGraph - GameMapGraph Object
	 * @throws Exception - IOException
	 */
	public void startReinforcement(Player player, GameMapGraph mapGraph) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		
//		int reinforcementArmies = armiesToBeAssigned(player, continent);
//		System.out.println("Armies available for Reinforcement: " + reinforcementArmies);
//		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
//
//		for (Country country : player.getMyCountries()) {
//			if (player.getArmyCount() > 0) {
//				System.out.println(
//						"Number of armies present in country " + country.getName() + " are " + country.getNoOfArmies());
//				System.out.println("Current available armies to be reinforced: " + player.getArmyCount());
//				System.out.println("Enter the number of armies to be deployed to country " + country.getName());
//				String armyCount = in.readLine().trim();
//				Pattern numberPattern = Pattern.compile("[0-9+]");
//				Matcher match = numberPattern.matcher(armyCount);
//				while (!match.matches() || armyCount.isEmpty()) {
//					System.out.println("\nPlease enter the correct army count below:");
//					armyCount = in.readLine().trim();
//					match = numberPattern.matcher(armyCount);
//				}
//				int armiesCount = Integer.parseInt(armyCount);
//				player.armiesAssignedToCountries(country, armiesCount);
//
//			} else {
//				System.out.println("Sorry you have insufficient armies left! \n");
//				break;
//			}
//		}
	}

	/**
	 * This method assigns the number of armies to the player when certain
	 * conditions are met.
	 * 
	 * @param player    - players whose armies are to be assigned
	 * @param continent - continent which has the country
	 * @return armiesAssignedPerPlayer returns the number of armies assigned to the
	 *         player.
	 */
	public int reinforcementPhase(Player player, GameMapGraph mapGraph) {
		int countriesPerPlayer = player.getMyCountries().size();
		int armiesAssignedPerPlayer;
		Continent continent = player.getMyCountries().get(0).getPartOfContinent();
		for (Country country : mapGraph.getCountries()) {
			if (country.getPartOfContinent().getContinentName().equalsIgnoreCase(continent.getContinentName())) {
				countriesList.add(country);
			}
		}

		if (countriesPerPlayer < 9) {
			armiesAssignedPerPlayer = 3;
		} else {
			armiesAssignedPerPlayer = (int) Math.floor(countriesPerPlayer / 3);
		}

		if (isPlayerOwnsContinent(player, countriesList))
			armiesAssignedPerPlayer = continent.getControlValue();

		return armiesAssignedPerPlayer;
	}

	/**
	 * This method checks whether the player owns the complete continent.
	 * 
	 * @param player - player whose turn is going on
	 * @return true if player owns the continent else return false.
	 */
	private static boolean isPlayerOwnsContinent(Player player, ArrayList<Country> countriesList) {
		boolean flag = true;
		for (Country country : countriesList) {
			if (!player.getMyCountries().contains(country))
				flag = false;
		}
		return flag;
	}

	//Attack Phase
	
	public void attackPhase(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		boolean isAttackPossible = false;
		boolean playerFound = false;
		boolean isPlayerCountry = false;
		if(attacker != null && defender != null) {
			for(Player player : gameMapGraph.getPlayers()) {
				for(Country country : player.getMyCountries()) {
					if(country.getName().equalsIgnoreCase(attacker.getName())) {
						playerFound = true;
						break;
					}
				}
				if(playerFound) {
					for(Country country: player.getMyCountries()) {
						if(country.getName().equalsIgnoreCase(defender.getName())) {
							JOptionPane.showMessageDialog(null, "Cannot attack your own country!!");
							isPlayerCountry = true;
							break;
						}
					}
				}
			}
			
			if(!isPlayerCountry) {
				if(attacker.getAdjacentCountries().contains(defender.getName())) {
					if(attacker.getNoOfArmies() > 1 && defender.getNoOfArmies() > 0) {
						isAttackPossible = true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Insufficient armies in the attacker country/defender country");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Attacker and Defender Countries are not adjacent!");
				}	
			}
		}
		
		if(isAttackPossible) {
			DiceView diceView = new DiceView(gameMapGraph, attacker, defender);
		}
	}
	
	public void allOutAttack(GameMapGraph gameMapGraph, Country attackerCountry, Country defenderCountry) {
		int attackerDiceCount = 0;
		int defenderDiceCount = 0;
		boolean playerFound = false;
		boolean isPlayerCountry = false;
		String message = "";
		
		for(Player player : gameMapGraph.getPlayers()) {
			for(Country country : player.getMyCountries()) {
				if(country.getName().equalsIgnoreCase(attackerCountry.getName())) {
					playerFound = true;
					break;
				}
			}
			if(playerFound) {
				for(Country country: player.getMyCountries()) {
					if(country.getName().equalsIgnoreCase(defenderCountry.getName())) {
						isPlayerCountry = true;
						break;
					}
				}
			}
		}
		
		if(!isPlayerCountry) {
			while(attackerCountry.getNoOfArmies() > 1 && defenderCountry.getNoOfArmies() > 0) {
				attackerDiceCount = attackerCountry.getNoOfArmies() > 3 ? 3 :(attackerCountry.getNoOfArmies() > 2 ? 2 : 1);
				defenderDiceCount = defenderCountry.getNoOfArmies() >= 2 ? 2 : 1;
				
				DiceController diceController = new DiceController();
				diceController.startDiceRoll(attackerDiceCount, defenderDiceCount, attackerCountry, defenderCountry);
			}
			
			if(defenderCountry.getNoOfArmies() == 0) {
				attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - 1);
				defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() + 1);
				JOptionPane.showMessageDialog(null, "Defender has lost the country to attacker!");
			}
		}
	}
	
	// Fortification Phase

	/**
	 * This method is called from the Startup phase when the user opts to start the
	 * fortification. It internally calls the moveArmies method once all the
	 * validation with respect to fortification are performed.
	 * 
	 * @param player  - The player whose turn is to do fortification.
	 * @param mapData - GameMapGraph object
	 * @throws IOException - throws Input output exception
	 */
//	public void startGameFortification(Player player, GameMapGraph mapData) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int countOfArmies = 0;
//		if (player.getMyCountries().size() >= 2) {
//			doFortification = true;
//			String fromCountry = "";
//			String toCountry = "";
//
//			while (doFortification) {
//				doFortification = true;
//				Country givingCountry = new Country();
//				Country receivingCountry = new Country();
//
//				do {
//					System.out.println("\nPlayer has the following list of countries with armies: \n");
//					for (Country country : player.getMyCountries()) {
//						System.out.println("* " + country.getName() + ":" + country.getNoOfArmies() + "\n");
//					}
//					System.out.println("Enter the name of country from which you want to move some armies :");
//					fromCountry = br.readLine().trim().toUpperCase();
//					Pattern namePattern1 = Pattern.compile("[a-zA-Z\\s]+");
//					Matcher match = namePattern1.matcher(fromCountry);
//					while (!match.matches() || fromCountry.isEmpty()) {
//						System.out.println("\nPlease enter the correct country name below:");
//						fromCountry = br.readLine().trim().toUpperCase();
//						match = namePattern1.matcher(fromCountry);
//					}
//
//					System.out.println("Enter the name of country to which you want to move some armies, from country "
//							+ fromCountry);
//					toCountry = br.readLine().trim().toUpperCase();
//					Pattern namePattern2 = Pattern.compile("[a-zA-Z\\s]+");
//					match = namePattern2.matcher(toCountry);
//					while (!match.matches() || toCountry.isEmpty()) {
//						System.out.println("\nPlease enter the correct country name below:");
//
//						toCountry = br.readLine().trim().toUpperCase();
//						match = namePattern2.matcher(toCountry);
//					}
//
//					if (!mapData.getCountrySet().containsKey(fromCountry)
//							|| !mapData.getCountrySet().containsKey(toCountry)) {
//						doFortification = false;
//						System.out.println("Country doesn't exist!\n");
//					}
//
//					givingCountry = mapData.getCountrySet().get(fromCountry);
//					receivingCountry = mapData.getCountrySet().get(toCountry);
//					if (player.getMyCountries().contains(givingCountry)
//							&& player.getMyCountries().contains(receivingCountry)) {
//						doFortification = true;
//					} else {
//						System.out.println(
//								"Entered countries doesn't exist in player's owned country list, please enter country names again\n");
//						doFortification = false;
//
//					}
//				} while (!doFortification);
//				if (doFortification) {
//					System.out.println("\nEnter the number of armies to move from " + fromCountry + " to " + toCountry);
//					try {
//						String countOfArmy = br.readLine().trim();
//						Pattern numberPattern3 = Pattern.compile("[0-9]+");
//						Matcher match = numberPattern3.matcher(countOfArmy);
//						while (!match.matches() || countOfArmy.isEmpty()) {
//							System.out.println("\nPlease enter the correct army count below:");
//
//							countOfArmy = br.readLine().trim();
//							match = numberPattern3.matcher(countOfArmy);
//						}
//						countOfArmies = Integer.parseInt(countOfArmy);
//						if (countOfArmies > mapData.getCountrySet().get(fromCountry).getNoOfArmies()) {
//							System.out.println(
//									"Insufficient armies available, fortification is not possible with asked number of armies.");
//							doFortification = false;
//						}
//
//					} catch (NumberFormatException e) {
//						System.out.println("Invalid number of armies.");
//					}
//				}
//
//				if (doFortification) {
//					boolean fortify = false;
//					for (Country country : player.getMyCountries()) {
//						for (String country1 : country.getAdjacentCountries()) {
//							if (country1.equalsIgnoreCase(fromCountry) || country1.equalsIgnoreCase(toCountry)) {
//								fortify = true;
//							}
//						}
//					}
//					if (fortify) {
//						moveArmies(givingCountry, receivingCountry, countOfArmies);
//					} else {
//						doFortification = false;
//						System.out
//								.println("None of the player's countries are adjacent\n Fortification phase ends..!!");
//					}
//
//				}
//
//			}
//
//		} else {
//			System.out.println("Sorry, Fortification is not possible if the country owned is less than 2");
//		}
//	}

	/**
	 * This method takes the values for each player from the startFortification
	 * method and does the manipulation of armies and assign the armies
	 * 
	 * @param fromCountry - The country from where player want to move army
	 * @param toCountry   - The country to where player want to move army
	 * @param armiesCount - Count of armies player wish to move
	 */
	public void moveArmies(Country fromCountry, Country toCountry, int armiesCount) {

		boolean adjacentCountries = false;
		for (String country : fromCountry.getAdjacentCountries()) {
			if (country.equalsIgnoreCase(toCountry.getName())) {
				int fromCountryArmy = fromCountry.getNoOfArmies();
				int toCountryArmy = toCountry.getNoOfArmies();
				fromCountry.setNoOfArmies(fromCountryArmy - armiesCount);
				toCountry.setNoOfArmies(toCountryArmy + armiesCount);
				adjacentCountries = true;
				doFortification = false;
			//	System.out.println("\nArmies successfully moved!");
			//	System.out.println("\nFortification phase ends!");
				break;
			}
		}

		if (!adjacentCountries) {
			JOptionPane.showMessageDialog(null, "Countries are not adjacanet!");
			doFortification = true;
		}
	}
	
	public Country getAdjacentCountry(GameMapGraph mapGraph, String attackerAdjCountry) {
		for(Player player : mapGraph.getPlayers()) {
			for(Country country : player.getMyCountries()) {
				if(country.getName().equalsIgnoreCase(attackerAdjCountry)) {
					return country;
				}
			}
		}
		return null;
	}
	
	public Player getPlayerForCountry(GameMapGraph mapGraph, String countryName) {
		for(Player player : mapGraph.getPlayers()) {
			for(Country country : player.getMyCountries()) {
				if(country.getName().equalsIgnoreCase(countryName)) {
					return player;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
