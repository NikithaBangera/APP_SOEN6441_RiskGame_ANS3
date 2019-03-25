package com.riskgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

public class Dice extends Observable{

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
		setChanged();
		notifyObservers();
	}

	public List<Integer> getDefenderDiceValues() {
		return defenderDiceValues;
	}

	public void setDefenderDiceValues(List<Integer> defenderDiceValues) {
		this.defenderDiceValues = defenderDiceValues;
		setChanged();
		notifyObservers();
	}
	
	public int generateDiceValue() {
		Random random = new Random();
		int number = random.nextInt(6) + 1;
		return number;
	}
}
