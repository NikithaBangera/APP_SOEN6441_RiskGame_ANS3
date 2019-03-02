package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;

/**
 * This class is dedicated for the fortification phase of the game. It takes checks
 * the number of countries user has proceeds if it is not less than 2. Takes the
 * fromCountry and toCountry value which identifies from where the player wishes
 * to move army and to where. After performing requisite validation moves army
 * of the player.
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
	public void startGameFortification(RiskPlayer player) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		GameMapGraph mapData = new GameMapGraph();
		int countOfArmies = 0;
		if (player.getMyCountries().size() > 3) {
			boolean doFortification = true;
			String fromCountry = "";
			String toCountry = "";
			System.out.println("List of countries that player has are: ");
			for (Country country : player.getMyCountries()) {
				System.out.println("* " + country.getName() + ":" + country.getNoOfArmies() + "\n");
			}
			while (doFortification) {
				doFortification = true;
				System.out.println("Enter the name of country from which you want to move some armies :");
				fromCountry = br.readLine();
				System.out.println(
						"Enter the name of country to which you want to move some armies, from country " + fromCountry);
				toCountry = br.readLine();
				if (!mapData.getCountrySet().containsKey(fromCountry.trim())
						|| !mapData.getCountrySet().containsKey(toCountry.trim())) {
					doFortification = false;
					System.out.println("Country doesn't exist!");
				}
				Country givingCountry = mapData.getCountrySet().get(fromCountry.trim());
				Country receivingCountry = mapData.getCountrySet().get(fromCountry.trim());
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
					countOfArmies = Integer.parseInt(br.readLine());
					if (countOfArmies > mapData.getCountrySet().get(fromCountry).getNoOfArmies()) {
						System.out.println(
								"Insufficient armies available, fortification is not possible with asked number of armies.");
						doFortification = false;
					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid number of armies.");
				}
			}

			moveArmies(mapData.getCountrySet().get(fromCountry), mapData.getCountrySet().get(toCountry), countOfArmies);
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
		if (fromCountry.getAdjacentCountries().contains(toCountry)) {
			int fromCountryArmy = fromCountry.getNoOfArmies();
			int toCountryArmy = toCountry.getNoOfArmies();
			fromCountry.setNoOfArmies(fromCountryArmy - armiesCount);
			toCountry.setNoOfArmies(toCountryArmy + armiesCount);
		} else {
			System.out.println("Countries are not adjacanet!");
		}
	}

}
