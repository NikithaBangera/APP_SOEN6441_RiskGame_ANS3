package com.riskgame.model;

public class Card {
	
	enum CardType{
		Infantry, Cavalry, Artillery
	}
	
	private Country country;
	
	private RiskPlayer cardHolder;


	public Country getCountryCard() {
		return country;
	}

	public void setCountryCard(Country country) {
		this.country = country;
	}

	public RiskPlayer getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(RiskPlayer cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	
	
}
