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
	private HashMap<String, String> countryCardsList = new HashMap<String, String>();
	private HashMap<String, Integer> playersCardList = new HashMap<String, Integer>();

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

	public HashMap<String, String> getCountryCardsList() {
		return countryCardsList;
	}

	public void setCountryCardsList(HashMap<String, String> countryCardsList) {
		this.countryCardsList = countryCardsList;
	}

	public HashMap<String, Integer> getPlayersCardList() {
		return playersCardList;
	}

	public void setPlayersCardList(HashMap<String, Integer> playersCardList) {
		this.playersCardList = playersCardList;
	}
	
	

	@Override
	public String toString() {
		return "Card [country=" + country + ", currentPlayer=" + currentPlayer + ", cardType=" + cardType
				+ ", countryCardsList=" + countryCardsList + ", playersCardList=" + playersCardList + "]";
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
