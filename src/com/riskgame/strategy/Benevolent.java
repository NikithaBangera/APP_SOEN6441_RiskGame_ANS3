package com.riskgame.strategy;

import java.util.ArrayList;
import java.util.Random;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Benevolent player reinforces its weakest countries, does not attack,
 * fortifies by moving armies to weaker countries
 * @author Anusha
 *
 */
public class Benevolent implements PlayerStrategy{
	
	PlayerController playerController;

	/**
	 * This method places armies on the countries owned by Benevolent player
	 */
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		Random random = new Random();
		int countryNumber = random.nextInt(player.getMyCountries().size());
		playerController.armiesAssignedToCountries(mapGraph, player.getMyCountries().get(countryNumber).getName(), 1);
	}
	
	/**
	 * This method reinforces armies to the weakest country of the Benevolent player
	 */
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		player.setFirstReinforcement(false);
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		playerController.armiesAssignedToCountries(mapGraph, getWeakestCountry(mapGraph, player).getName(), player.getArmyCount());
		System.out.println(getWeakestCountry(mapGraph, player).getName()+" reinforced with "+reinforcementArmies);
	}

	/**
	 * This method fortifies armies from the strongest country to the weakest country of the Benevolent player
	 */
	@Override
	public void fortificationPhase(GameMapGraph mapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		playerController = new PlayerController();
		Country weakestCountry = getWeakestCountry(mapGraph, player);
		Country strongestCountryToFortify = getStrongestCountry(mapGraph, player);
		if (strongestCountryToFortify != null) {
			int fortifyArmiesToWeakestCountry = (strongestCountryToFortify.getNoOfArmies() - weakestCountry.getNoOfArmies()) / 2;
			weakestCountry.setNoOfArmies(weakestCountry.getNoOfArmies() + fortifyArmiesToWeakestCountry);
			strongestCountryToFortify.setNoOfArmies(strongestCountryToFortify.getNoOfArmies() - fortifyArmiesToWeakestCountry);
		}
		System.out.println("Benevolent fortification complete");
	}
	
	/**
	 * this method gets the strongest country owned by the benevolent player
	 * @param mapGraph - The object of the GameMapGraph
	 * @param player - The object of the player
	 * @return strongest country
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
	 * this method gets the weakest country owned by the benevolent player
	 * @param mapGraph - The object of the GameMapGraph
	 * @param player - The object of the player
	 * @return weakest country
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
	 * the benevolent player cannot attack
	 */
	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		
		
	}

	/**
	 * the benevolent player cannot attack
	 */
	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		
		
	}
}
