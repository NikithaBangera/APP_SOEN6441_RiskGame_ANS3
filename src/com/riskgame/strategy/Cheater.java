package com.riskgame.strategy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Cheater player whose reinforcement method doubles the army count on all its countries,
 * attack method automatically conquers all the neighbors of all its countries, doubles the 
 * number of armies on its countries that have neighbors that belong to other players.
 * @author 
 *
 */
public class Cheater implements PlayerStrategy{
	
	PlayerController playerController;

	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "Army count of country " + country.getName() + " has been doubled to " + country.getNoOfArmies() + "\n");
		
	}
	
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		//int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		ArrayList<Country> countryList = player.getMyCountries();
		for(int i=0; i<=countryList.size();i++) {
			Country currentCountry = countryList.get(i);
			int reinforcementArmies= currentCountry.getNoOfArmies() + currentCountry.getNoOfArmies();
			currentCountry.setNoOfArmies(reinforcementArmies);
		//playerController.armiesAssignedToCountries(mapGraph, currentCountry, player.getArmyCount());
			JOptionPane.showMessageDialog(null, "Army count of country " + currentCountry.getName() + " has been doubled to " + currentCountry.getNoOfArmies() + "\n");
		}
	}

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		// TODO Auto-generated method 
		ArrayList<Country> cheatersWinningCountries = new ArrayList<Country>();	
		for(Country country : player.getMyCountries()) {
			//ArrayList<Country> cheaterAdjacentCountries = country.getAdjacentCountries1();
			for(Country country1: country.getAdjacentCountries1()) {
				if(country1 != country) {
					cheatersWinningCountries.add(country1);
				}
			}
		}
		ArrayList<Country> allCountriesOfCheater= player.getMyCountries();
		allCountriesOfCheater.addAll(cheatersWinningCountries);
		player.setMyCountries(allCountriesOfCheater);
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		// TODO Auto-generated method stub
		
	}

	

}
