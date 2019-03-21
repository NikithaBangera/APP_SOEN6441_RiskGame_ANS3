package com.riskgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {

	private List<Integer> attackerDiceValues;
	private List<Integer> defenderDiceValues;
	private List<String> diceResult;

	public Dice() {
		attackerDiceValues = new ArrayList<Integer>();
		defenderDiceValues = new ArrayList<Integer>();
	}

	public List<Integer> getAttackerDiceValues() {
		return attackerDiceValues;
	}

	public void setAttackerDiceValues(List<Integer> attackerDiceValues) {
		this.attackerDiceValues = attackerDiceValues;
	}

	public List<Integer> getDefenderDiceValues() {
		return defenderDiceValues;
	}

	public void setDefenderDiceValues(List<Integer> defenderDiceValues) {
		this.defenderDiceValues = defenderDiceValues;
	}
	
	public int generateDiceValue() {
		Random random = new Random();
		int number = random.nextInt(6) + 1;
		return number;
	}
}
