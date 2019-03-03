package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;

/**
 * This class is dedicated for the fortification phase of the game. It takes
 * checks the number of countries user has proceeds if it is not less than 2.
 * Takes the fromCountry and toCountry value which identifies from where the
 * player wishes to move army and to where. After performing requisite
 * validation moves army of the player.
 * 
 * @author Shresthi Garg
 *
 */
public class FortificationPhase {
	/**
	 * This method is called from the Startup phase when the user opts to start the
	 * fortification. It internally calls the moveArmies method once all the
	 * validation with respect to fortification are performed.
	 * 
	 * @param player
	 * @throws IOException
	 */
	public void startGameFortification(RiskPlayer player, GameMapGraph mapData) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int countOfArmies = 0;
		if (player.getMyCountries().size() >= 2) {
			boolean doFortification = true;
			String fromCountry = "";
			String toCountry = "";

			while (doFortification) {
				doFortification = true;
				System.out.println("Player has the following list of countries with armies: \n");
				for (Country country : player.getMyCountries()) {
					System.out.println("* " + country.getName() + ":" + country.getNoOfArmies() + "\n");
				}
				System.out.println("Enter the name of country from which you want to move some armies :");
				fromCountry = br.readLine().trim();
				while (fromCountry.isEmpty()) {
					System.err.println("\nFrom Country cannot be blank. Please enter the correct country name below:");
					System.out.flush();
					fromCountry = br.readLine().trim();
				}
				System.out.println(
						"Enter the name of country to which you want to move some armies, from country " + fromCountry);
				toCountry = br.readLine().trim();
				while (toCountry.isEmpty()) {
					System.err.println("\nTo Country cannot be blank. Please enter the correct country name below:");
					System.out.flush();
					toCountry = br.readLine().trim();
				}

				System.out.println("\nfrom country " + fromCountry);
				System.out.println("\ntoCountry " + toCountry);

				if (!mapData.getCountrySet().containsKey(fromCountry)
						|| !mapData.getCountrySet().containsKey(toCountry)) {
					doFortification = false;
					System.out.println("Country doesn't exist!");
				}
				Country givingCountry = mapData.getCountrySet().get(fromCountry);
				Country receivingCountry = mapData.getCountrySet().get(toCountry);
				if (player.getMyCountries().contains(givingCountry)
						&& player.getMyCountries().contains(receivingCountry)) {
					doFortification = true;
				} else {
					System.out.println(
							"Entered countries doesn't exist in player's owned country list, please enter country names again");
					doFortification = false;
				}
				System.out.println("Enter the number of armies to move from " + fromCountry + " to " + toCountry);
				try {
					String countOfArmy = br.readLine().trim();
					while (countOfArmy.isEmpty()) {
						System.err.println("\nArmy count cannot be blank. Please enter the correct number below:");
						System.out.flush();
						countOfArmy = br.readLine().trim();
					}
					countOfArmies = Integer.parseInt(countOfArmy);
					if (countOfArmies > mapData.getCountrySet().get(fromCountry).getNoOfArmies()) {
						System.out.println(
								"Insufficient armies available, fortification is not possible with asked number of armies.");
						doFortification = false;
					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid number of armies.");
				}
				if (doFortification)
					moveArmies(givingCountry, receivingCountry, countOfArmies);

				System.out.println("Do you wish to continue with fortification? (Yes or No)");
				String choice = br.readLine().trim();
				if (choice.equalsIgnoreCase("No")) {
					doFortification = false;
				} else {
					doFortification = true;
				}

			}

		} else {
			System.out.println("Sorry, Fortification is not possible if the country owned is less than 2");
		}
	}

	/**
	 * This method takes the values for each player from the startFortification
	 * method and does the manipulation of armies and assign the armies
	 * 
	 * @param fromCountry
	 * @param toCountry
	 * @param armiesCount
	 */
	public void moveArmies(Country fromCountry, Country toCountry, int armiesCount) {

		System.out.println("from country " + fromCountry);
		System.out.println("from country adjacent countries " + fromCountry.getAdjacentCountries());
		System.out.println("to country " + toCountry);
		boolean adjacentCountries = false;
		for (String country : fromCountry.getAdjacentCountries()) {
			if (country.equalsIgnoreCase(toCountry.getName())) {
				int fromCountryArmy = fromCountry.getNoOfArmies();
				int toCountryArmy = toCountry.getNoOfArmies();
				fromCountry.setNoOfArmies(fromCountryArmy - armiesCount);
				toCountry.setNoOfArmies(toCountryArmy + armiesCount);
				adjacentCountries = true;
				break;
			}
		}

		if (!adjacentCountries) {
			System.out.println("Countries are not adjacanet!");
		}
	}

}
