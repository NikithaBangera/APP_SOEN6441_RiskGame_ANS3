package com.riskgame.model;

import java.util.ArrayList;
import java.util.Observable;
import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

/**
 * This class is a model for the card that is owned by the players in the game
 * 
 * @author Shresthi
 *
 */
public class Card extends Observable {
	
	/** The constant for infantry */
	public final static String INFANTRY = "infantry";
	
	/** The constant for cavalry */
	public final static String CAVALRY = "cavalry";
	
	/** The constant for artillery */
	public final static String ARTILLERY = "artillery";
	
	/** The object of Country model */
	private Country country;
	
	/** The current player */
	private Player currentPlayer;
	
	/** The card type */
	private String cardType;

	/**
	 * Card Constructor
	 */
	public Card() {
		PlayerView playerView = new PlayerView();
		DiceView diceView = new DiceView();
		CardView cardView = new CardView();
		this.addObserver(playerView);
		this.addObserver(diceView);
		this.addObserver(cardView);
	}

	/**
	 * Gets the country
	 * 
	 * @return - Country object
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country
	 * 
	 * @param country - Country Object
	 */
	public void setCountry(Country country) {
		this.country = country;
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the current player
	 * 
	 * @return the player object
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player
	 * 
	 * @param currentPlayer - the player object
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gets the card type
	 * @return Card type
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * Sets the card type
	 * @param cardType - the card type
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
		setChanged();
		notifyObservers();
	}

	// Methods related to Card
	/**
	 * This method creates an arrayList of cards
	 * @return the list of card
	 */
	public ArrayList<String> totalCardType() {
		ArrayList<String> cardTypes = new ArrayList<>();
		cardTypes.add(ARTILLERY);
		cardTypes.add(CAVALRY);
		cardTypes.add(INFANTRY);
		return cardTypes;
	}
}
