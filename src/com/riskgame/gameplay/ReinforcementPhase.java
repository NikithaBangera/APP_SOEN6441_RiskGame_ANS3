package com.riskgame.gameplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.RiskPlayer;
/**
 * ReinforcementPhase class contains the methods needed for
 * reinforcing the armies to the players during their turn.
 * 
 * @author Nikitha
 *
 */
public class ReinforcementPhase {
	private static ArrayList<Country> countriesList;
	
	/**
	 * This method prompts the player whether he/she wants to 
	 * play the reinforcement phase.
	 * 
	 * @param player
	 * @throws Exception
	 */
	public void startReinforcement(RiskPlayer player) throws Exception  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
		//	Continent continent = validateLoadMap.getContinentDetails().get(player.getMyCountries().get(0).getContinent()) ;
		Continent continent = new Continent();
		int reinforcementArmies = armiesToBeAssigned(player, continent);
		System.out.println("Armies available for Reinforcement: "+ reinforcementArmies);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
	
		for(Country country : player.getMyCountries()) {
			if(player.getArmyCount() < 0) {
				System.out.println("Insufficient armies available for reinforcement");
				break;
				
			}
			else if(player.getArmyCount() > 0){
				System.out.println("Number of armies present in country " + country.getName() + "are " + country.getNoOfArmies());
				System.out.println("Current available armies to be reinforced: " + player.getArmyCount());
				System.out.println("Enter the number of armies to be deployed to country " + country.getName());
				int armiesCount = Integer.parseInt(in.readLine());
				player.armiesAssignedToCountries(country, armiesCount);
			}
		}
	}
	
	/**
	 * This method assigns the number of armies to the player when
	 * certain conditions are met.
	 * @param player
	 * @param continent
	 * @return armiesAssignedPerPlayer 
	 * 			returns the number of armies assigned to the player.
	 */
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
	
	/**
	 * This method checks whether the player owns the complete continent.
	 * @param player
	 * @return true if player owns the continent else return false.
	 */
	private static boolean isPlayerOwnsContinent(RiskPlayer player) {
		for(Country country : countriesList) {
			if(!player.getMyCountries().contains(country))
				return false;
		}
		return true;
		
	}

}
