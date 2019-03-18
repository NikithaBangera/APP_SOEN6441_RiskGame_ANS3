package com.riskgame.controller;

import java.util.Collections;

import javax.swing.JOptionPane;

import com.riskgame.model.Country;
import com.riskgame.model.Dice;

public class DiceController {

	private Dice dice;

	public DiceController() {
		dice = new Dice();
	}

	public String startDiceRoll(int attackerDiceCount, int defenderDiceCount, Country attackerCountry, Country defenderCountry) {
		String winner = "";
		int attackerLostCount = 0;
		int defenderLostCount = 0;
		
		attackerDiceValue(attackerDiceCount);
		defenderDiceValue(defenderDiceCount);
		
		Collections.sort(dice.getAttackerDiceValues(), Collections.reverseOrder());
		Collections.sort(dice.getDefenderDiceValues(), Collections.reverseOrder());
		
		int iterationSize = attackerDiceCount < defenderDiceCount ? attackerDiceCount : defenderDiceCount;
		
		for(int i=0;i<iterationSize;i++) {
			if(dice.getAttackerDiceValues().get(i).compareTo(dice.getDefenderDiceValues().get(i)) > 0) {
				defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() - 1);
				defenderLostCount++;
			}
			else {
				attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - 1);
				attackerLostCount++;
			}
		}
		return attackerLostCount+":"+defenderLostCount;
	}

	private void attackerDiceValue(int attackerDiceCount) {
		for (int i = 1; i <= attackerDiceCount; i++) {
			int diceValue = dice.generateDiceValue();
			dice.getAttackerDiceValues().add(diceValue);
		}
	}

	private void defenderDiceValue(int defenderDiceCount) {
		for (int i = 1; i <= defenderDiceCount; i++) {
			int diceValue = dice.generateDiceValue();
			dice.getDefenderDiceValues().add(diceValue);
		}
	}
	
	public void moveArmies(int armiesToBeMoved, Country attackerCountry, Country defenderCountry) {
		if((attackerCountry.getNoOfArmies() - armiesToBeMoved) > 1) {
			attackerCountry.setNoOfArmies(attackerCountry.getNoOfArmies() - armiesToBeMoved);
			defenderCountry.setNoOfArmies(defenderCountry.getNoOfArmies() + armiesToBeMoved);
		}
		else {
			JOptionPane.showMessageDialog(null, "Allowed number of armies to be moved: "+(attackerCountry.getNoOfArmies() - 1));
		}
	}
}
