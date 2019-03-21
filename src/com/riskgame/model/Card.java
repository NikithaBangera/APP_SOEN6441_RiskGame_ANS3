package com.riskgame.model;

import java.util.ArrayList;
import java.util.List;

public class Card {
	
//	enum CardType{
//		Infantry, Cavalry, Artillery
//	}
	
	private Country country;
	
	private Player playerCardHolder;
	
	private String cardType;
	
	private List<String> cardCategories;
	
	public Card() {
		cardCategories = new ArrayList<String>();
	}

	public Country getCountryToCard() {
		return country;
	}

	public void setCountryToCard(Country country) {
		this.country = country;
	}

	public Player getPlayerCardHolder() {
		return playerCardHolder;
	}

	public void setPlayerCardHolder(Player cardHolder) {
		this.playerCardHolder = cardHolder;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public void cardTypeList() {
		cardCategories.add("Infantry");
		cardCategories.add("Cavalry");
		cardCategories.add("Artillery");
	}
	
}
