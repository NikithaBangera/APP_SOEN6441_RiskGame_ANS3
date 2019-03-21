package com.riskgame.model;

import java.io.IOException;
import java.util.ArrayList;

public class Card {

	public final static String INFANTRY = "infantry";
	public final static String CAVALRY = "cavalry";
	public final static String ARTILLERY = "artillery";

	private Country country;
	private Player currentPlayer;
	private String cardType;
	private ArrayList<Card> countryCards = new ArrayList<Card>();
	private ArrayList<Card> cardsToExchange;
	

	public ArrayList<Card> getCountryCards() {
		return countryCards;
	}

	public void setCountryCards(ArrayList<Card> countryCards) {
		this.countryCards = countryCards;
	}

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

	public ArrayList<Card> getCardsToExchange() {
		return cardsToExchange;
	}

	public void setCardsToExchange(ArrayList<Card> cardsToExchange) {
		this.cardsToExchange = cardsToExchange;
	}

	@Override
	public String toString() {
		return "Card [country=" + country + ", currentPlayer=" + currentPlayer + ", cardType=" + cardType
				+ ", countryCards=" + countryCards + ", cardsToExchange=" + cardsToExchange + "]";
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
