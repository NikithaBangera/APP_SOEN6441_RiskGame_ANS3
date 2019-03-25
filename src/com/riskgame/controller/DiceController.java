package com.riskgame.controller;

import java.util.ArrayList;
import java.util.Collections;

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

		attackerDiceValue(attackerDiceCount);
		defenderDiceValue(defenderDiceCount);

		Collections.sort(dice.getAttackerDiceValues(), Collections.reverseOrder());
		Collections.sort(dice.getDefenderDiceValues(), Collections.reverseOrder());

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
	private void attackerDiceValue(int attackerDiceCount) {
		for (int i = 1; i <= attackerDiceCount; i++) {
			int diceValue = dice.generateDiceValue();
			dice.getAttackerDiceValues().add(diceValue);
		}
	}

	/**
	 * This method aims to add the dice value of the defender
	 * 
	 * @param defenderDiceCount - The dice count of the defender
	 */
	private void defenderDiceValue(int defenderDiceCount) {
		for (int i = 1; i <= defenderDiceCount; i++) {
			int diceValue = dice.generateDiceValue();
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
	 */
	public void moveArmies(int armiesToBeMoved, Country attackerCountry, Country defenderCountry,
			GameMapGraph gameMapGraph) {
		boolean attackerFound = false;
		boolean defenderFound = false;
		if ((attackerCountry.getNoOfArmies() - armiesToBeMoved) > 1) {
			attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - armiesToBeMoved);
			defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() + armiesToBeMoved);
			for (Player player : gameMapGraph.getPlayers()) {
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(attackerCountry.getName())) {
						attackerFound = true;
						break;
					}
				}
				if (attackerFound) {
					player.getMyCountries().add(defenderCountry);
					// allocateCard
					CardController cardAction = new CardController();
					// Player currentPlayer = getPlayerForCountry(gameMapGraph,
					// attackerCountry.getName());
					cardAction.allocateCardToPlayer(player);
					break;
				}
			}
			int i = 0;
			for (Player player : gameMapGraph.getPlayers()) {
				for (Country country : player.getMyCountries()) {
					if (country.getName().equalsIgnoreCase(defenderCountry.getName())) {
						defenderFound = true;
						break;
					}
					i++;
					if (defenderFound) {
						player.getMyCountries().remove(i);
						break;
					}
				}
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Allowed number of armies to be moved: " + (attackerCountry.getNoOfArmies() - 1));
		}
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
