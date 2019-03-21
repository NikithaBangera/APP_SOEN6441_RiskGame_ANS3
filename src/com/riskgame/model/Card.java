package com.riskgame.model;

public class Card {
	
	enum CardType{
		Infantry, Cavalry, Artillery
	}
	
	private Country country;
	
	private Player cardHolder;


	public Country getCountryCard() {
		return country;
	}

	public void setCountryCard(Country country) {
		this.country = country;
	}

	public Player getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(Player cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	
	
}
