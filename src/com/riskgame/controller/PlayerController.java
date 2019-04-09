package com.riskgame.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

/**
 * Player Controller is for the beginning of the game play. Its contains methods
 * which will take the details from the players, and starts the eventual phase.
 * 
 * @author Shresthi Garg
 * @author Nikitha
 * @author Anusha
 *
 */
public class PlayerController {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/** Variable to store the total number of players in game. */
	private int countOfthePlayers = 0;

	/** List which countries. */
	public static ArrayList<Country> countriesList = new ArrayList<Country>();

	/**
	 * Variable for the fortification phase
	 */
	public boolean doFortification = false;

	/**
	 * Method to get the count of players
	 * 
	 * @return countOfThePlayers - return count of players
	 */
	public int getCountOfthePlayers() {
		return countOfthePlayers;
	}

	/**
	 * Method to set the count of players
	 * 
	 * @param countOfthePlayers - set the count of players
	 */
	public void setCountOfthePlayers(int countOfthePlayers) {
		this.countOfthePlayers = countOfthePlayers;
	}

	/**
	 * This method starts the game, obtaining the number of payers , details and
	 * initializing them.
	 * 
	 * @param mapGraph - Object of mapGraph which consists of map details
	 * @throws Exception - IOException
	 */
	public void gamePlay(GameMapGraph mapGraph) throws Exception {
		countOfthePlayers = mapGraph.getInputPlayerDetails().size();
		Iterator<Entry<String, String>> inputPlayerIt = mapGraph.getInputPlayerDetails().entrySet().iterator();
		while(inputPlayerIt.hasNext()) {
			Entry<String, String> inputPlayer = inputPlayerIt.next();
			boolean continue1 = true;
			Player riskPlayer = new Player();
			riskPlayer.setName(inputPlayer.getValue().split(",")[0]);
			riskPlayer.setPlayerType(inputPlayer.getValue().split(",")[1]);
			riskPlayer.setConquerCountry(0);
			riskPlayer.setFirstReinforcement(true);
			mapGraph.getPlayers().add(riskPlayer);
		}
		// Startup Phase starts here
//		boolean proceed = false;
//		do {
//			System.out.println("Enter the number of players below");
//			String playerCount = br.readLine().trim();
//			Pattern numberPattern = Pattern.compile("[0-9]+");
//			Matcher match = numberPattern.matcher(playerCount);
//			while (!match.matches() || playerCount.isEmpty()) {
//				System.out.println("\nPlease enter the correct player count below:");
//
//				playerCount = br.readLine().trim();
//				match = numberPattern.matcher(playerCount);
//			}
//			countOfthePlayers = Integer.parseInt(playerCount);
//
//			if (countOfthePlayers > 1 && countOfthePlayers < 7) {
//				System.out.println("Great! Let's Play.");
//				proceed = false;
//			} else {
//				System.out.println("Sorry! The numbers of players can be between 2 and 6.");
//				proceed = true;
//			}
//		} while (proceed);

//		System.out.println("Enter the name of the players");
		
//		for (int count = 1; count <= mapGraph.getInputPlayerDetails().size(); count++) {
//			boolean continue1 = true;
//			Player riskPlayer = new Player();
//			String playername = br.readLine().trim();
//			Pattern namePattern = Pattern.compile("[a-zA-z]+");
//			Matcher match = namePattern.matcher(playername);
//			while (!match.matches() || playername.isEmpty()) {
//				System.out.println("\nPlease enter the correct player name below:");
//
//				playername = br.readLine().trim();
//				match = namePattern.matcher(playername);
//			}
//
//			while (continue1) {
//				if (playername != null) {
//					riskPlayer.setName(playername);
//					riskPlayer.setConquerCountry(0);
//					continue1 = false;
//
//				} else {
//					System.out.println("Player name cannot be empty");
//				}
//			}
//			riskPlayer.setFirstReinforcement(true);
//			mapGraph.getPlayers().add(riskPlayer);
//		}

		allocationOfCountry(mapGraph);
		allocationOfArmyToPlayers(mapGraph);
		allocationOfArmyToCountriesInitially(mapGraph);

		CardController cardController = new CardController();
		cardController.assignCardsToCountry(mapGraph);

		// allocationOfRemainingArmyToCountries(mapGraph);

		// Place Army Phase starts here

		mapGraph.setGamePhase("Place Armies");
		mapGraph.setExchangeCount(1);
		mapGraph.setRefreshFrame(false);

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
					countrySet.get(countryIndexAssignment).setPlayer(mapGraph.getPlayers().get(i).getName());
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
	 * 
	 * @param mapGraph - The GameMapGraph object
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

	// Function of StartUp Phase ends here

	// Reinforcement Phase

	/**
	 * This method assigns the number of armies to the player when certain
	 * conditions are met.
	 * 
	 * @param          player- players whose armies are to be assigned
	 * @param mapGraph - GameMapGraph object
	 * @return the reinforced armies
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

	// Attack Phase
	/**
	 * This method marks the starting of the attack phase when the player choses to
	 * attack a country.
	 * 
	 * @param gameMapGraph - The GameMapGraph object
	 * @param attacker     - The attacker country
	 * @param defender     - The defender country
	 */
	public void attackPhase(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		boolean isAttackPossible = false;
		boolean playerFound = false;
		boolean isPlayerCountry = false;
		if (attacker != null && defender != null) {
			for (Player player : gameMapGraph.getPlayers()) {
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(attacker.getName())) {
						playerFound = true;
						break;
					}
				}
				if (playerFound) {
					for (Country country : player.getMyCountries()) {
						if (country.getName().equalsIgnoreCase(defender.getName())) {
							if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
								JOptionPane.showMessageDialog(null, "Cannot attack your own country!!");
							}
							isPlayerCountry = true;
							break;
						}
					}
					break;
				}
			}

			if (!isPlayerCountry) {
				if (attacker.getAdjacentCountries().contains(defender.getName())) {
					if (attacker.getNoOfArmies() > 1 && defender.getNoOfArmies() > 0) {
						isAttackPossible = true;
					} else {
						if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
							JOptionPane.showMessageDialog(null,
								"Insufficient armies in the attacker country/defender country");
						}
					}
				} else {
					if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
						JOptionPane.showMessageDialog(null, "Attacker and Defender Countries are not adjacent!");
					}
				}
			}
		}

		if (isAttackPossible) {
			DiceView diceView = new DiceView(gameMapGraph, attacker, defender);
		}
	}

	/**
	 * This method is called when the player choses to opt for all out attack mode.
	 * 
	 * @param gameMapGraph    - The GameMapGraph object
	 * @param attackerCountry - The attacker country
	 * @param defenderCountry - The defender country
	 */
	public void allOutAttack(GameMapGraph gameMapGraph, Country attackerCountry, Country defenderCountry) {
		int attackerDiceCount = 0;
		int defenderDiceCount = 0;
		boolean playerFound = false;
		boolean isPlayerCountry = false;
		boolean moveComplete = false;
		String message = "";

		for (Player player : gameMapGraph.getPlayers()) {
			for (Country country : player.getMyCountries()) {
				if (country.getName().equalsIgnoreCase(attackerCountry.getName())) {
					playerFound = true;
					break;
				}
			}
			if (playerFound) {
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(defenderCountry.getName())) {
						if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
							JOptionPane.showMessageDialog(null, "Cannot attack your own country!!");
						}
						isPlayerCountry = true;
						break;
					}
				}
				break;
			}
		}

		if (!isPlayerCountry) {
			DiceController diceController = new DiceController();
			while (attackerCountry.getNoOfArmies() > 1 && defenderCountry.getNoOfArmies() > 0) {
				attackerDiceCount = attackerCountry.getNoOfArmies() > 3 ? 3
						: (attackerCountry.getNoOfArmies() > 2 ? 2 : 1);
				defenderDiceCount = defenderCountry.getNoOfArmies() >= 2 ? 2 : 1;

				diceController.startDiceRoll(attackerDiceCount, defenderDiceCount, attackerCountry, defenderCountry);
			}

			if (defenderCountry.getNoOfArmies() == 0) {

				Player attacker = diceController.getPlayerForCountry(gameMapGraph, attackerCountry.getName());
				Player defender = diceController.getPlayerForCountry(gameMapGraph, defenderCountry.getName());
				if(attacker.getPlayerType().equalsIgnoreCase("Aggressive")) {
					moveComplete = moveArmies(attackerCountry.getNoOfArmies() / 2, attackerCountry, defenderCountry, gameMapGraph);
				}
				else {
					moveComplete = moveArmies(1, attackerCountry, defenderCountry, gameMapGraph);
				}
				if (moveComplete) {
					if(defender.getMyCountries().size() == 0) {
						attacker.getPlayersCardList().putAll(defender.getPlayersCardList());
						attacker.setConquerCountry(attacker.getConquerCountry() - 1);
						defender.setPlayerLostGame(true);
						if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
							JOptionPane.showMessageDialog(null, "Player "+defender.getName()+" has lost the game!!");
						}
					}
				}

			} else if (attackerCountry.getNoOfArmies() == 1) {
				if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
					JOptionPane.showMessageDialog(null, "Attacker cannot attack anymore");
				}
			}
		}
	}

	// Fortification Phase

	/**
	 * This method takes the values for each player from the startFortification
	 * method and does the manipulation of armies and assign the armies
	 * 
	 * @param fromCountry - The country from where player want to move army
	 * @param toCountry   - The country to where player want to move army
	 * @param armiesCount - Count of armies player wish to move
	 */
	public void moveArmies(GameMapGraph mapGraph, Country fromCountry, Country toCountry, int armiesCount) {

		boolean adjacentCountries = false;
		if(fromCountry.getNoOfArmies() > 1 && (fromCountry.getNoOfArmies()-armiesCount) >= 1) {
			for (String country : fromCountry.getAdjacentCountries()) {
				if (country.equalsIgnoreCase(toCountry.getName())) {
					int fromCountryArmy = fromCountry.getNoOfArmies();
					int toCountryArmy = toCountry.getNoOfArmies();
					fromCountry.setNoOfArmies(fromCountryArmy - armiesCount);
					toCountry.setNoOfArmies(toCountryArmy + armiesCount);
					adjacentCountries = true;
					doFortification = false;
					break;
				}
			}
			
			if (!adjacentCountries) {
				if(!(mapGraph.getGameType().equalsIgnoreCase("Tournament") || mapGraph.getGameType().equalsIgnoreCase("Test"))) {
					JOptionPane.showMessageDialog(null, "Countries are not adjacanet!");
				}
				doFortification = true;
			}
	
			if (!doFortification) {
				if(!(mapGraph.getGameType().equalsIgnoreCase("Tournament") || mapGraph.getGameType().equalsIgnoreCase("Test"))) {
					JOptionPane.showMessageDialog(null,
						"Armies moved from " + fromCountry.getName() + " to " + toCountry.getName() + " successfully!");
				}
			}
		}
	}

	/**
	 * This method gets the adjacent country object for the entered country
	 * 
	 * @param mapGraph           - The GameMapGraph object
	 * @param attackerAdjCountry - the adjacent country of the attacker
	 * @return Country object
	 */
	public Country getAdjacentCountry(GameMapGraph mapGraph, String attackerAdjCountry) {
		for (Player player : mapGraph.getPlayers()) {
			for (Country country : player.getMyCountries()) {
				if (country.getName().equalsIgnoreCase(attackerAdjCountry)) {
					return country;
				}
			}
		}
		return null;
	}

	/**
	 * This method returns the player who owns the given country
	 * 
	 * @param mapGraph    - The GameMapGraph object
	 * @param countryName - The country whose owner is to searched
	 * @return -the player object
	 */
	public Player getPlayerForCountry(GameMapGraph mapGraph, String countryName) {
		for (Player player : mapGraph.getPlayers()) {
			for (Country country : player.getMyCountries()) {
				if (country.getName().equalsIgnoreCase(countryName)) {
					return player;
				}
			}
		}
		return null;
	}

	/**
	 * This method returns the current player
	 * 
	 * @param mapGraph   - The GameMapGraph object
	 * @param playerName - The name of the player
	 * @return Player object
	 */
	public Player getCurrentPlayer(GameMapGraph mapGraph, String playerName) {
		for (Player player : mapGraph.getPlayers()) {
			if (player.getName().equalsIgnoreCase(playerName)) {
				return player;
			}
		}
		return null;
	}

	/**
	 * This method is to check the completion of placing an army
	 * 
	 * @param mapGraph - The GameMapGraph object
	 * @return boolean values
	 */
	public boolean isPlaceArmiesComplete(GameMapGraph mapGraph) {
		boolean complete = true;
		for (Player player : mapGraph.getPlayers()) {
			if (player.isEndPlaceArmies() || player.getArmyCount() == 0) {
				complete &= true;
			} else {
				complete &= false;
			}
		}
		return complete;
	}

	/**
	 * This method is used to assign armies to the Countries. It checks the
	 * available army and assigns the army to the requested country
	 * 
	 * @param mapGraph    - GameMapGraph object
	 * @param country     - the country given to players
	 * @param armiesCount - the count of the armies player has
	 */
	public void armiesAssignedToCountries(GameMapGraph mapGraph, String country, int armiesCount) {
		Player player = getPlayerForCountry(mapGraph, country);
		if (getPlayerForCountry(mapGraph, country) != null) {
			if ((player.getArmyCount()) > 0 && player.getArmyCount() >= armiesCount) {
				getPlayerForCountry(mapGraph, country).setArmyCount(player.getArmyCount() - armiesCount);
				if (getPlayerForCountry(mapGraph, country).getArmyCount() == 0) {
					getPlayerForCountry(mapGraph, country).setEndPlaceArmies(true);
				}
				int i = 0;
				for (Country playerCountry : getPlayerForCountry(mapGraph, country).getMyCountries()) {
					if (playerCountry.getName().equalsIgnoreCase(country)) {
						getPlayerForCountry(mapGraph, country).getMyCountries().get(i)
								.setNoOfArmies(playerCountry.getNoOfArmies() + armiesCount);
						break;
					}
					i++;
				}

			} else {
				if(!(mapGraph.getGameType().equalsIgnoreCase("Tournament") || mapGraph.getGameType().equalsIgnoreCase("Test"))) {
					JOptionPane.showMessageDialog(null, "Insufficient number of armies.");
				}
			}
		} else {
			if(!(mapGraph.getGameType().equalsIgnoreCase("Tournament") || mapGraph.getGameType().equalsIgnoreCase("Test"))) {
				JOptionPane.showMessageDialog(null, "This country is not owned by you!");
			}
		}
	}

	/**
	 * This method is called when after a win by the attacker it moves armies to the
	 * country it has won.
	 * 
	 * @param armiesToBeMoved - the number of armies that is wishes to be moved
	 * @param attackerCountry - the attacker country
	 * @param defenderCountry - the defender country
	 * @param gameMapGraph    - the GameMapGraph object
	 * @return moveSuccessful - moving of the army is done
	 */
	public boolean moveArmies(int armiesToBeMoved, Country attackerCountry, Country defenderCountry,
			GameMapGraph gameMapGraph) {
		boolean attackerFound = false;
		boolean defenderFound = false;
		boolean moveSuccessful = false;
		if ((attackerCountry.getNoOfArmies() - armiesToBeMoved) >= 1) {
			attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - armiesToBeMoved);
			defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() + armiesToBeMoved);

			for (Player player : gameMapGraph.getPlayers()) {
				int i = 0;
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(defenderCountry.getName())) {
						defenderFound = true;
						break;
					}
					i++;
				}
				if (defenderFound) {
					player.getMyCountries().remove(i);
					break;
				}
			}

			for (Player player : gameMapGraph.getPlayers()) {
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(attackerCountry.getName())) {
						attackerFound = true;
						break;
					}
				}
				if (attackerFound) {
					player.getMyCountries().add(defenderCountry);
					player.setConquerCountry(player.getConquerCountry() + 1);
					moveSuccessful = true;
					break;
				}
			}

		} else {
			if(!(gameMapGraph.getGameType().equalsIgnoreCase("Tournament") || gameMapGraph.getGameType().equalsIgnoreCase("Test"))) {
				JOptionPane.showMessageDialog(null,
					"Allowed number of armies to be moved: " + (attackerCountry.getNoOfArmies() - 1));
			}
		}
		return moveSuccessful;
	}

}