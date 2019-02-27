package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;

public class FortificationPhase {

	public void startGameFortification() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		RiskPlayer player = new RiskPlayer();
		GameMapGraph mapGraph = new GameMapGraph();
		System.out.println("Fortification Phase starts!\n");
		System.out.println("Player: " + player.getName() + "\n");
		System.out.println("Do you want to continue with Fortification phase? (Yes or No)");
		try {
			if (br.readLine().trim().equalsIgnoreCase("Yes")) {
				if (player.getMyCountries().size() >= 2) {
					boolean flag = true;
					String fromCountry = "";
					String toCountry = "";
					System.out.println("List of countries that player has are: ");
					for (Country country : player.getMyCountries()) {
						System.out.println("* " + country.getName() + ":" + country.getNoOfArmies() + "\n");
					}
					while (flag) {
						flag = true;
						System.out.println("Enter the name of country from which you want to move some armies :");
						fromCountry = br.readLine();
						System.out.println(
								"Enter the name of country to which you want to move some armies, from country "
										+ fromCountry);
						toCountry = br.readLine();
						if (!mapGraph.getCountrySet().containsKey(fromCountry.trim())
								|| !mapGraph.getCountrySet().containsKey(toCountry.trim())) {
							flag = false;
							System.out.println("Please enter correct country name.");
						}
						Country givingCountry = mapGraph.getCountrySet().get(fromCountry.trim());
						Country receviningCountry = mapGraph.getCountrySet().get(fromCountry.trim());
						if (player.getMyCountries().contains(givingCountry)
								&& player.getMyCountries().contains(receviningCountry)) {
							flag = true;
						} else {
							System.out.println(
									"Entered countries doesn't exist in player's owned countries, please enter country names again");
							flag = false;
						}
					}
					int countOfArmies = 0;
					while (flag) {
						flag = true;
						System.out
								.println("Enter the number of armies to move from " + fromCountry + " to " + toCountry);
						try {
							countOfArmies = Integer.parseInt(br.readLine());
							if (countOfArmies > mapGraph.getCountrySet().get(fromCountry).getNoOfArmies()) {
								System.out.println("Sufficient number of armies is not available.");
								flag = false;
							}

						} catch (NumberFormatException e) {
							System.out.println("Invalid number of armies.");
						}
					}

					moveArmies(mapGraph.getCountrySet().get(fromCountry), mapGraph.getCountrySet().get(toCountry),
							countOfArmies);
				} else {
					System.out.println("Insufficient armies left.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveArmies(Country fromCountry, Country toCountry, int armiesCount) {
		if (fromCountry.getAdjacentCountries().contains(toCountry)) {
			fromCountry.setNoOfArmies(fromCountry.getNoOfArmies() - armiesCount);
			toCountry.setNoOfArmies(toCountry.getNoOfArmies() + armiesCount);
		}
	}

}
