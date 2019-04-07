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
 * @author 
 *
 */
public class Benevolent implements PlayerStrategy{
	
	PlayerController playerController;

	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		Random random = new Random();
		int countryNumber = random.nextInt(player.getMyCountries().size()) + 1;
		playerController.armiesAssignedToCountries(mapGraph, player.getMyCountries().get(countryNumber-1).getName(), 1);
		
	}

	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		player.setFirstReinforcement(false);
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		playerController.armiesAssignedToCountries(mapGraph, getWeakestCountry(mapGraph, player).getName(), player.getArmyCount());
	}


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
			}
	
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

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		//System.out.println("Benevolent player cannot attack.");
		
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		//System.out.println("Benevolent player cannot attack.");
		
	}
}
