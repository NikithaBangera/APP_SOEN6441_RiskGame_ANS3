package com.riskgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
	private int attackDice1, attackDice2, attackDice3;
	private int defendDice1, defendDice2;
	private List<Integer> attackerDiceList;
	private List<Integer> defenderDiceList;
	
	public Dice() {
		attackerDiceList = new ArrayList<Integer>();
		defenderDiceList = new ArrayList<Integer>();
	}
	
	public int diceRoll() {
		Random random = new Random();
		int number = random.nextInt(6) + 1;
		return number;
	}
}
