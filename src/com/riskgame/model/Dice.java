package com.riskgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

/**
 * This class aims to model the dice of the players in the game
 * 
 * @author Nikitha
 *
 */
public class Dice extends Observable {

	/** Attacker dice values list */
	private List<Integer> attackerDiceValues;
	
	/** Defender dice value list */
	private List<Integer> defenderDiceValues;
	
	/**
	 * The default constructor
	 */
	public Dice() {
		attackerDiceValues = new ArrayList<Integer>();
		defenderDiceValues = new ArrayList<Integer>();
		PlayerView playerView = new PlayerView();
		DiceView diceView = new DiceView();
		CardView cardView = new CardView();
		this.addObserver(playerView);
		this.addObserver(diceView);
		this.addObserver(cardView);
	}

	/**
	 * Gets the list of attacker's dice value
	 * 
	 * @return List of dice value
	 */
	public List<Integer> getAttackerDiceValues() {
		return attackerDiceValues;
	}

	/**
	 * Sets the attacker dice values
	 * 
	 * @param attackerDiceValues - the dice values
	 */
	public void setAttackerDiceValues(List<Integer> attackerDiceValues) {
		this.attackerDiceValues = attackerDiceValues;
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the list of defender's dice value
	 * 
	 * @return List of dice value
	 */
	public List<Integer> getDefenderDiceValues() {
		return defenderDiceValues;
	}

	/**
	 * Gets the list of defender's dice value
	 * 
	 * @param defenderDiceValues - List of dice value
	 */
	public void setDefenderDiceValues(List<Integer> defenderDiceValues) {
		this.defenderDiceValues = defenderDiceValues;
		setChanged();
		notifyObservers();
	}

	/**
	 * This method generates dice value
	 * 
	 * @return dice value
	 */
	public int generateDiceValue() {
		Random random = new Random();
		int number = random.nextInt(6) + 1;
		return number;
	}
}
