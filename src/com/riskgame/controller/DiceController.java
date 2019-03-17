package com.riskgame.controller;

import com.riskgame.model.Dice;

public class DiceController {

	private Dice dice;

	public DiceController() {
		dice = new Dice();
	}

	public void startDiceRoll(int attackerDiceCount, int defenderDiceCount) {
		attackerDiceValue(attackerDiceCount);
		defenderDiceValue(defenderDiceCount);
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

}
