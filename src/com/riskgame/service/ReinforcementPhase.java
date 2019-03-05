package com.riskgame.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.RiskPlayer;

/**
 * ReinforcementPhase class contains the methods needed for reinforcing the
 * armies to the players during their turn. If each player meets certain
 * specific conditions then they will be assigned some number of armies before
 * the attack phase begins
 * 
 * @author Nikitha
 *
 */
public class ReinforcementPhase {
	
	public static ArrayList<Country> countriesList = new ArrayList<Country>();

	/**
	 * This method prompts the player whether he/she wants to play the reinforcement
	 * phase. If the player wants to play reinforcement phase then this method will
	 * call the armiesToBeAssigned function to assign armies to the player.
	 * 
	 * @param player
	 * @throws Exception
	 */
	public void startReinforcement(RiskPlayer player, GameMapGraph mapGraph) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Continent continent = player.getMyCountries().get(0).getPartOfContinent();
		for(Country country : mapGraph.getCountries()) {
			if(country.getPartOfContinent().getContinentName().equalsIgnoreCase(continent.getContinentName())) {
				countriesList.add(country);
			}
		}
		int reinforcementArmies = armiesToBeAssigned(player, continent);
		System.out.println("Armies available for Reinforcement: " + reinforcementArmies);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);

		for (Country country : player.getMyCountries()) {
			if (player.getArmyCount() > 0) {
				System.out.println(
						"Number of armies present in country " + country.getName() + " are " + country.getNoOfArmies());
				System.out.println("Current available armies to be reinforced: " + player.getArmyCount());
				System.out.println("Enter the number of armies to be deployed to country " + country.getName());
				String armyCount = in.readLine().trim();
				Pattern numberPattern = Pattern.compile("[0-9+]");
				Matcher match = numberPattern.matcher(armyCount);
				while (!match.matches() || armyCount.isEmpty()) {
					System.err.println("\nPlease enter the correct army count below:");
					System.out.flush();
					armyCount = in.readLine().trim();
					match = numberPattern.matcher(armyCount);
				}
				int armiesCount = Integer.parseInt(armyCount);
				player.armiesAssignedToCountries(country, armiesCount);

			}
			else {
				System.out.println("Sorry you have insufficient armies left! \n");
				break;
			} 
		}
	}

	/**
	 * This method assigns the number of armies to the player when certain
	 * conditions are met.
	 * 
	 * @param player
	 * @param continent
	 * @return armiesAssignedPerPlayer returns the number of armies assigned to the
	 *         player.
	 */
	public static int armiesToBeAssigned(RiskPlayer player, Continent continent) {
		int countriesPerPlayer = player.getMyCountries().size();
		int armiesAssignedPerPlayer;

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
	 * @param player
	 * @return true if player owns the continent else return false.
	 */
	private static boolean isPlayerOwnsContinent(RiskPlayer player, ArrayList<Country> countriesList) {
		boolean flag = true;
		for (Country country : countriesList) {
			if (!player.getMyCountries().contains(country))
				flag = false;
		}
		return flag;
	}
}