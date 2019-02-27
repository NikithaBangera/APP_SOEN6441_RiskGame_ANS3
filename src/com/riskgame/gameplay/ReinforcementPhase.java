package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.RiskPlayer;

public class ReinforcementPhase {
	private static ArrayList<Country> countriesList;
	
	public void startReinforcement(RiskPlayer player) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Reinforcement Phase begins for player " + player.getName() + "\n");
		System.out.println("Do you want to continue with Reinforcement phase? (Yes or No) ");
		try {
			if (in.readLine().trim().equalsIgnoreCase("Yes")) {
			Continent continent = validateLoadMap.getContinentDetails().get(player.getMyCountries().get(0).getContinent()) ;
			int reinforcementArmies = armiesToBeAssigned(player, continent);
			System.out.println("Armies available for Reinforcement: "+ reinforcementArmies);
			player.setArmyCount(player.getArmyCount() + reinforcementArmies);
			
			for(Country country : player.getMyCountries()) {
				if(player.getArmyCount() > 0) {
					System.out.println("Number of armies present in country " + country.getName() + "are " + country.getNoOfArmies());
					System.out.println("Current available armies to be reinforced: " + player.getArmyCount());
					System.out.println("Enter the number of armies to be deployed to country " + country.getName());
					int armiesCount = Integer.parseInt(in.readLine());
					player.armiesAssignedToCountries(country, armiesCount);
				}
				else {
					System.out.println("Insufficient armies available for reinforcement");
					break;
				}
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static int armiesToBeAssigned(RiskPlayer player, Continent continent) {
		countriesList = continent.getCountriesListInContinent();
		int countriesPerPlayer = player.getMyCountries().size();
		int armiesAssignedPerPlayer = (int)Math.floor(countriesPerPlayer/3);
		
		
		if(countriesPerPlayer < 9) {
			armiesAssignedPerPlayer = 3;
		}
		
		if(isPlayerOwnsContinent(player)) {
			armiesAssignedPerPlayer = continent.getControlValue();
		}
		
		return armiesAssignedPerPlayer;
	}
	
	private static boolean isPlayerOwnsContinent(RiskPlayer player) {
		for(Country country : countriesList) {
			if(!player.getMyCountries().contains(country))
				return false;
		}
		return true;
		
	}

}
