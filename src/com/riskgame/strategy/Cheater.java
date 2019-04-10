package com.riskgame.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Cheater player whose reinforcement method doubles the army count on all its countries,
 * attack method automatically conquers all the neighbors of all its countries, doubles the 
 * number of armies on its countries that have neighbors that belong to other players.
 * @author Shresthi
 * @author Anusha
 *
 */
public class Cheater implements PlayerStrategy{
	
	PlayerController playerController;
	
	/**
	 * this method is used to place armies in the countries owned by the cheater player
	 */
	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		playerController = new PlayerController();
		Random random = new Random();
		int countryNumber = random.nextInt(player.getMyCountries().size());
		playerController.armiesAssignedToCountries(mapGraph, player.getMyCountries().get(countryNumber).getName(), 1);
	}
	
	/**
	 * this method is used for the reinforcement of the cheater player's countries,
	 * it doubles the armies on each country
	 */
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		playerController = new PlayerController();
		player.setFirstReinforcement(false);
		ArrayList<Country> countryList = player.getMyCountries();
		for(int i=0; i < countryList.size();i++) {
			Country currentCountry = countryList.get(i);
			int reinforcementArmies= currentCountry.getNoOfArmies() * 2;
			currentCountry.setNoOfArmies(reinforcementArmies);
			System.out.println(currentCountry.getName()+" reinforced with "+reinforcementArmies);
		}
	}

	/**
	 * this method is for the attack of the cheater player.
	 * it attacks its neighbor countries and wins them.
	 */
	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		ArrayList<Country> cheatersWinningCountries = new ArrayList<Country>();	
		int armyCount = 1;
		for(Country country : player.getMyCountries()) {
			for(String adjCountry : country.getAdjacentCountries()) {
				Country adjacentCountry = playerController.getAdjacentCountry(gameMapGraph, adjCountry);
				Player adjacentPlayer = playerController.getPlayerForCountry(gameMapGraph, adjacentCountry.getName());
				if(!(adjacentPlayer.getName().equalsIgnoreCase(player.getName())) && country.getNoOfArmies() > 1) {
					adjacentCountry.setNoOfArmies(armyCount);
					country.setNoOfArmies(country.getNoOfArmies() - armyCount);
					cheatersWinningCountries.add(adjacentCountry);
				}
			}
		}
		
		for(Country country: cheatersWinningCountries) {
			player.getMyCountries().add(country);
			System.out.println(player.getName()+" (Cheater) has conquered "+country.getName());
			Player losingPlayer = playerController.getPlayerForCountry(gameMapGraph, country.getName());
			int i = 0;
			for(Country playerCountry : losingPlayer.getMyCountries()) {
				if(playerCountry.getName().equalsIgnoreCase(country.getName())) {
					break;
				}
				i++;
			}
			losingPlayer.getMyCountries().remove(i);
			if(losingPlayer.getMyCountries().size() == 0) {
				losingPlayer.setPlayerLostGame(true);
				if(!gameMapGraph.getGameType().equalsIgnoreCase("Tournament")) {
					JOptionPane.showMessageDialog(null, "Player "+losingPlayer.getName()+" has lost the game!!");
				}
				System.out.println("Player "+losingPlayer.getName()+" has lost the game!!");
			}
		}
	}
	
	/**
	 * this method is combined with the attack phase for the cheater player
	 */
	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		
	}

	/**
	 * this method is used for fortifying
	 * the countries of the cheater player that are neighbors 
	 * to countries owned by other players
	 */
	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		playerController = new PlayerController();
		for(Country country : player.getMyCountries()) {
			for(String adjCountry : country.getAdjacentCountries()) {
				Player adjPlayer = playerController.getPlayerForCountry(gameMapGraph, adjCountry);
				if(!player.getName().equalsIgnoreCase(adjPlayer.getName())) {
					country.setNoOfArmies(country.getNoOfArmies() * 2);
					break;
				}
			}
		}
		System.out.println("Cheater fortification complete");
	}
}
