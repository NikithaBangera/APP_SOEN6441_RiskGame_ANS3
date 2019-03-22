package com.riskgame.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Card {

	public final static String INFANTRY = "infantry";
	public final static String CAVALRY = "cavalry";
	public final static String ARTILLERY = "artillery";

	private Country country;
	private Player currentPlayer;
	private String cardType;
	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
