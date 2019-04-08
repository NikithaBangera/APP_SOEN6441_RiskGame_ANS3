package com.riskgame.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Aggressive player reinforces the strongest country, attacks until the army count of the attacking country is one,
 * fortifies the strongest country.
 *
 * @author Nikitha
 *
 */
public class Aggressive implements PlayerStrategy{

	PlayerController playerController;
	
	/**
	 * This method places armies on the countries owned by Aggressive player
	 */
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		Random random = new Random();
		int countryNumber = random.nextInt(player.getMyCountries().size()) + 1;
		playerController.armiesAssignedToCountries(mapGraph, player.getMyCountries().get(countryNumber-1).getName(), 1);
	}

	/**
	 * This method reinforces armies to the strongest country of the Aggressive player
	 */
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		player.setFirstReinforcement(false);
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		playerController.armiesAssignedToCountries(mapGraph, getStrongestCountryWithAdjCountry(mapGraph, player).getName(), player.getArmyCount());
	}

	/**
	 * This method calls the allOutAttack method of playerController class by passing the aggressive player's
	 * strongest country as the attacking country and the adjacent country of the strongest country which 
	 * belongs to other player as the defender country
	 */
	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attackerCountry, Country defenderCountry) {
		playerController = new PlayerController();
		int numberOfArmies = 0;
		attackerCountry = getStrongestCountryWithAdjCountry(gameMapGraph, player);
		numberOfArmies = attackerCountry.getNoOfArmies();
		List<String> adjacentCountriesList = attackerCountry.getAdjacentCountries();
		for(String adjCountry : adjacentCountriesList) {
			Player adjPlayer = playerController.getPlayerForCountry(gameMapGraph, adjCountry);
			Country adjPlayerCountry = adjPlayer.getSelectedCountry(adjCountry);
			if(adjPlayerCountry.getNoOfArmies() <= numberOfArmies && !(player.getName().equalsIgnoreCase(adjPlayer.getName()))) {
				numberOfArmies = adjPlayerCountry.getNoOfArmies();
				defenderCountry = adjPlayerCountry;
			}
		}
		if(defenderCountry != null) {
			playerController.allOutAttack(gameMapGraph, attackerCountry, defenderCountry);
		}
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
	}

	/**
	 * This method is used for fortifying armies in the strongest armies
	 */
	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		int numberOfArmies = 0;
		Country strongestCountry = getStrongestCountryWithAdjCountry(gameMapGraph, player);
		Country strongestPlayerCountry = getStrongestCountry(gameMapGraph, player);
		if(strongestCountry.getName().equalsIgnoreCase(strongestPlayerCountry.getName())) {
			numberOfArmies = strongestCountry.getNoOfArmies();
			
			for(Country country: player.getMyCountries()) {
				if(country.getNoOfArmies() <= numberOfArmies && country.getNoOfArmies() > 1 
						&& !country.getName().equalsIgnoreCase(strongestCountry.getName())) {
					numberOfArmies = country.getNoOfArmies();
					fromCountry = country;
					armiesCount = fromCountry.getNoOfArmies() - 1;
				}
			}
		}
		else {
			numberOfArmies = strongestCountry.getNoOfArmies();
			fromCountry = strongestPlayerCountry;
			armiesCount = (fromCountry.getNoOfArmies() - 1)/ 2;
		}
		if(fromCountry != null && strongestCountry != null) {
			playerController.moveArmies(fromCountry, strongestCountry, armiesCount);
		}
	}

	/**
	 * Method to get the strongest country from the Aggressive player's countries list
	 * @param mapGraph - GameMapGraph object
	 * @param player - current player which is the aggressive player
	 * @return strongestCountry - strongest country of aggressive player
	 */
	public Country getStrongestCountry(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country strongestCountry = null;
		for(Country country: player.getMyCountries()) {
			if(country.getNoOfArmies() >= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				strongestCountry = country;
			}
		}
		return strongestCountry;
	}

	/**
	 * Method to get the weakest country from the Aggressive player's countries list
	 * @param mapGraph - GameMapGraph object
	 * @param player - current player which is the aggressive player
	 * @return weakestCountry - weakest country of aggressive player
	 */
	public Country getWeakestCountry(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country weakestCountry = null;
		numberOfArmies = player.getMyCountries().get(0).getNoOfArmies();
		for(Country country: player.getMyCountries()) {
			if(country.getNoOfArmies() <= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				weakestCountry = country;
			}
		}
		return weakestCountry;
	}
	
	/**
	 * Method to get the strongest country 
	 * @param mapGraph - GameMapGraph object
	 * @param player - current player which is the aggressive player
	 * @return strongestCountry - strongest country of aggressive player
	 */
	public Country getStrongestCountryWithAdjCountry(GameMapGraph mapGraph, Player player) {
		int numberOfArmies = 0;
		Country strongestCountry = null;
		playerController = new PlayerController();
		List<Country> countriesWithAdjCountries = new ArrayList<Country>();
		for(Country country : player.getMyCountries()) {
			for(String adjCountry : country.getAdjacentCountries()) {
				Player adjPlayer = playerController.getPlayerForCountry(mapGraph, adjCountry);
				Country adjCountryObject = playerController.getAdjacentCountry(mapGraph, adjCountry);
				if(!player.getName().equalsIgnoreCase(adjPlayer.getName())) {
					countriesWithAdjCountries.add(country);
					break;
				}
			}
		}
		
		for(Country country : countriesWithAdjCountries) {
			if(country.getNoOfArmies() >= numberOfArmies) {
				numberOfArmies = country.getNoOfArmies();
				strongestCountry = country;
			}
		}
		
		if(strongestCountry == null) {
			strongestCountry = getStrongestCountry(mapGraph, player);
		}
		
		return strongestCountry;
	}
}
