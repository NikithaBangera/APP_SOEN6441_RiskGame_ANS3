package com.riskgame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import com.riskgame.model.Card;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

public class CardController {

	Country countryObj = new Country();
	Card card = new Card();
	private int exchange = 0;

	/**
	 * This method assigns cards to the country in the game
	 * 
	 * @param gameGraph - the GameMapGraph object passed from playerController
	 */
	public void assignCardsToCountry(GameMapGraph gameGraph) {

		Iterator<Entry<String,Country>> countryIt = gameGraph.getCountrySet().entrySet().iterator(); 
		
		while(countryIt.hasNext()) {
			Entry<String, Country> country = countryIt.next();
			card = new Card();
			String cardType = card.totalCardType().get(new Random().nextInt(card.totalCardType().size()));
			card.setCardType(cardType);
			country.getValue().setCard(card);
		}
//		HashMap<String, String> countryCardList = new HashMap<String, String>();
//		ArrayList<Country> allCountries = new ArrayList<>(gameGraph.getCountrySet().values());
//
//		for (Country country : allCountries) {
//			card = new Card();
//			String cardType = card.totalCardType().get(new Random().nextInt(card.totalCardType().size()));
//			countryCardList.put(country.getName(), cardType);
//		}
//		countryObj.setCountryCardsList(countryCardList);
	}

	public void allocateCardToPlayer(Player player) {
//		HashMap<String, String> allCards = countryObj.getCountryCardsList();
		String cardToBeAdded = card.totalCardType().get(new Random().nextInt(card.totalCardType().size()));
		int count = addCardCount(cardToBeAdded, player);
		player.getPlayersCardList().put(cardToBeAdded, count);

	}

	private Integer addCardCount(String cardToBeAdded, Player currentPlayer) {
		int count = 0;
		if (cardToBeAdded.equalsIgnoreCase(Card.ARTILLERY)) {
			count = currentPlayer.getPlayersCardList().get(Card.ARTILLERY);
			count += 1;
		} else if (cardToBeAdded.equalsIgnoreCase(Card.CAVALRY)) {
			count = currentPlayer.getPlayersCardList().get(Card.CAVALRY);
			count += 1;
		} else {
			count = currentPlayer.getPlayersCardList().get(Card.INFANTRY);
			count += 1;
		}
		return count;
	}

	public String exchangeCards(HashMap<String, Integer> cardsSelected, Player player) {
		exchange += 1;
		int aCount = cardsSelected.get(Card.ARTILLERY);
		int iCount = cardsSelected.get(Card.INFANTRY);
		int cCount = cardsSelected.get(Card.CAVALRY);
		int total = aCount + iCount + cCount;

		HashMap<String, Integer> playersCard = new HashMap<String, Integer>();
		playersCard = player.getPlayersCardList();

		int a = playersCard.get(Card.ARTILLERY);
		int i = playersCard.get(Card.INFANTRY);
		int c = playersCard.get(Card.CAVALRY);
		String operation = "";

		if (total >= 3 && player.getPlayersCardList().size() >= 3) {
			if (((aCount >= 1 && a >= 1) && (iCount >= 1 && i >= 1) && (cCount >= 1 && c >= 1))) {
				player.setArmyCount(player.getArmyCount() + 5 * exchange);
				playersCard.replace(Card.ARTILLERY, a, a - 1);
				playersCard.replace(Card.CAVALRY, c, c - 1);
				playersCard.replace(Card.INFANTRY, i, i - 1);
			} else if (aCount >= 3 && playersCard.get(Card.ARTILLERY) >= 3) {
				player.setArmyCount(player.getArmyCount() + 5 * exchange);
				playersCard.replace(Card.ARTILLERY, a, a - 3);
			} else if (cCount >= 3 && playersCard.get(Card.CAVALRY) >= 3) {
				player.setArmyCount(player.getArmyCount() + 5 * exchange);
				playersCard.replace(Card.CAVALRY, c, c - 3);
			} else if (iCount >= 3 && playersCard.get(Card.INFANTRY) >= 3) {
				player.setArmyCount(player.getArmyCount() + 5 * exchange);
				playersCard.replace(Card.INFANTRY, i, i - 3);

			}
		} else {
			operation = "Cannot perform exchange. Should select atleast 3 cards";
		}
		return operation;
	}

}
