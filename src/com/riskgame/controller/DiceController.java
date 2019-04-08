package com.riskgame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import com.riskgame.model.Country;
import com.riskgame.model.Dice;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * This class aims to assign dice to the player based on the armies and then if
 * the attacker wins it lets him move the armies to the defender's countries.
 * 
 * @author Nikitha
 *
 */
public class DiceController {

	private Dice dice;

	/**
	 * This is a default constructor
	 */
	public DiceController() {
		dice = new Dice();
	}

	/**
	 * This method is called when the player rolls the dice during the attack phase.
	 * The number of the dice is based on the army count.
	 * 
	 * @param attackerDiceCount - The dice count of the attacker
	 * @param defenderDiceCount - The dice count of the defender
	 * @param attackerCountry   - The attacker's country
	 * @param defenderCountry   - The defender's country
	 * @return the attacker and defender's lost count
	 */
	public String startDiceRoll(int attackerDiceCount, int defenderDiceCount, Country attackerCountry,
			Country defenderCountry) {
		String winner = "";
		int attackerLostCount = 0;
		int defenderLostCount = 0;
		attackerCountry.getDiceValues().clear();
		defenderCountry.getDiceValues().clear();
		dice.getAttackerDiceValues().clear();
		dice.getDefenderDiceValues().clear();
		Random random = new Random();

		attackerDiceValue(attackerDiceCount, random);
		defenderDiceValue(defenderDiceCount, random);

		Collections.sort(dice.getAttackerDiceValues(), Collections.reverseOrder());
		Collections.sort(dice.getDefenderDiceValues(), Collections.reverseOrder());

		for (Integer diceValue : dice.getAttackerDiceValues()) {
			attackerCountry.getDiceValues().add(diceValue);
		}

		for (Integer diceValue : dice.getDefenderDiceValues()) {
			defenderCountry.getDiceValues().add(diceValue);
		}

		int iterationSize = attackerDiceCount < defenderDiceCount ? attackerDiceCount : defenderDiceCount;

		for (int i = 0; i < iterationSize; i++) {
			if (dice.getAttackerDiceValues().get(i).compareTo(dice.getDefenderDiceValues().get(i)) > 0) {
				defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() - 1);
				defenderLostCount++;
			} else {
				attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - 1);
				attackerLostCount++;
			}
		}
		return attackerLostCount + ":" + defenderLostCount;
	}

	/**
	 * This method aims to add the dice value of the attacker
	 * 
	 * @param attackerDiceCount - the dice count of the attacker
	 */
	private void attackerDiceValue(int attackerDiceCount, Random random) {
		for (int i = 1; i <= attackerDiceCount; i++) {
			int diceValue = random.nextInt(6) + 1;
			dice.getAttackerDiceValues().add(diceValue);
		}
	}

	/**
	 * This method aims to add the dice value of the defender
	 * 
	 * @param defenderDiceCount - The dice count of the defender
	 */
	private void defenderDiceValue(int defenderDiceCount, Random random) {
		for (int i = 1; i <= defenderDiceCount; i++) {
			int diceValue = random.nextInt(6) + 1;
			dice.getDefenderDiceValues().add(diceValue);
		}
	}

	/**
	 * This method is called when after a win by the attacker it moves armies to the
	 * country it has won.
	 * 
	 * @param armiesToBeMoved - the number of armies that is wishes to be moved
	 * @param attackerCountry - the attacker country
	 * @param defenderCountry - the defender country
	 * @param gameMapGraph    - the GameMapGraph object
	 * @param attackerDiceCount - attacker's dice count
	 * @param defenderDiceCount - defender's dice count
	 * @return boolean to suggest the move of army was successful
	 */
	public boolean moveArmies(int armiesToBeMoved, Country attackerCountry, Country defenderCountry,
			GameMapGraph gameMapGraph, int attackerDiceCount, int defenderDiceCount) {
		boolean attackerFound = false;
		boolean defenderFound = false;
		boolean moveSuccessful = false;
		if ((attackerCountry.getNoOfArmies() - armiesToBeMoved) >= 1) {
			if (armiesToBeMoved >= attackerDiceCount) {
				attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - armiesToBeMoved);
				defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() + armiesToBeMoved);

				for (Player player : gameMapGraph.getPlayers()) {
					int i = 0;
					for (Country country : player.getMyCountries()) {
						if (country.getName().equalsIgnoreCase(defenderCountry.getName())) {
							defenderFound = true;
							break;
						}
						i++;
					}
					if (defenderFound) {
						player.getMyCountries().remove(i);
						break;
					}
				}

				for (Player player : gameMapGraph.getPlayers()) {
					for (Country country : player.getMyCountries()) {
						if (country.getName().equalsIgnoreCase(attackerCountry.getName())) {
							attackerFound = true;
							break;
						}
					}
					if (attackerFound) {
						player.getMyCountries().add(defenderCountry);
						player.setConquerCountry(player.getConquerCountry() + 1);
						moveSuccessful = true;
						break;
					}
				}
			} else {
				if(!gameMapGraph.getGameType().equalsIgnoreCase("Tournament")) {
					JOptionPane.showMessageDialog(null, "Should move a minimum of " + attackerDiceCount + " armies");
				}
			}
		} else {
			if(!gameMapGraph.getGameType().equalsIgnoreCase("Tournament")) {
				JOptionPane.showMessageDialog(null,
					"Allowed number of armies to be moved: " + (attackerCountry.getNoOfArmies() - 1));
			}
		}
		return moveSuccessful;
	}

	/**
	 * This method gets the player based on the entered country.
	 * 
	 * @param mapGraph    - The GameMapGraph object
	 * @param countryName - The entered country in game.
	 * @return - The player object
	 */
	public Player getPlayerForCountry(GameMapGraph mapGraph, String countryName) {
		for (Player player : mapGraph.getPlayers()) {
			for (Country country : player.getMyCountries()) {
				if (country.getName().equalsIgnoreCase(countryName)) {
					return player;
				}
			}
		}
		return null;
	}
}
