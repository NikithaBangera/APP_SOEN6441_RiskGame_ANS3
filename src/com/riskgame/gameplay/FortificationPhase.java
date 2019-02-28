package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;

public class FortificationPhase {

	public void startGameFortification(RiskPlayer player) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		GameMapGraph mapData = new GameMapGraph();
		int countOfArmies = 0;
		while (!(player.getMyCountries().size() <= 2)) {
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
		}
	}

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
