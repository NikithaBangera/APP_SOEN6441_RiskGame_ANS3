package com.riskgame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.riskgame.model.Card;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;

public class CardController {

	private List<Country> countriesList;
	private Stack<Card> stackOfCards;
	private Card card;
	
	public CardController() {
		countriesList = new ArrayList<Country>();
		stackOfCards = new Stack<Card>();
		card = new Card();
	}
	
	
	public void assignCountryToCard(GameMapGraph mapGraph) {
		countriesList.addAll(mapGraph.getCountrySet().values());
		
		
		
	}
	
}
