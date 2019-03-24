package com.riskgame.model;

import java.util.ArrayList;
import java.util.Observable;
import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

public class Card extends Observable{

	public final static String INFANTRY = "infantry";
	public final static String CAVALRY = "cavalry";
	public final static String ARTILLERY = "artillery";

	private Country country;
	private Player currentPlayer;
	private String cardType;
	
	public Card() {
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
		setChanged();
		notifyObservers();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		setChanged();
		notifyObservers();
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
		setChanged();
		notifyObservers();
	}
	
	// Methods related to Card
	public ArrayList<String> totalCardType() {
		ArrayList<String> cardTypes = new ArrayList<>();
		cardTypes.add(ARTILLERY);
		cardTypes.add(CAVALRY);
		cardTypes.add(INFANTRY);
		return cardTypes;
	}

}
