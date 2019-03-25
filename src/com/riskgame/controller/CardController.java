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

/**
 * This class aims to allocate the cards to the players. It also lets the players exchange those cards for armies.
 * 
 * @author Shresthi Garg
 *
 */
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
	}
	
	/**
	 * This method  allocates a card randomly to the player if the player wins the country
	 * 
	 * @param player - the current player
	 */
	public void allocateCardToPlayer(Player player) {
//		HashMap<String, String> allCards = countryObj.getCountryCardsList();
		String cardToBeAdded = card.totalCardType().get(new Random().nextInt(card.totalCardType().size()));
		int count = addCardCount(cardToBeAdded, player);
		player.getPlayersCardList().put(cardToBeAdded, count);

	}
	
	/**
	 * This method adds the cards to players card list
	 * 
	 * @param cardToBeAdded - The card that has to be added
	 * @param currentPlayer - The current player
	 * @return count - the count of the card type added
	 */
	private Integer addCardCount(String cardToBeAdded, Player currentPlayer) {
		int count = 0;
		if (cardToBeAdded.equalsIgnoreCase(Card.ARTILLERY)) {
			if(currentPlayer.getPlayersCardList() != null && currentPlayer.getPlayersCardList().size() > 0) {
				if(currentPlayer.getPlayersCardList().containsKey(Card.ARTILLERY)) {
					count = currentPlayer.getPlayersCardList().get(Card.ARTILLERY);
				}
			}
			count += 1;
		} else if (cardToBeAdded.equalsIgnoreCase(Card.CAVALRY)) {
			if(currentPlayer.getPlayersCardList() != null && currentPlayer.getPlayersCardList().size() > 0) {
				if(currentPlayer.getPlayersCardList().containsKey(Card.CAVALRY)) {
					count = currentPlayer.getPlayersCardList().get(Card.CAVALRY);
				}
			}
			count += 1;
		} else {
			if(currentPlayer.getPlayersCardList() != null && currentPlayer.getPlayersCardList().size() > 0) {
				if(currentPlayer.getPlayersCardList().containsKey(Card.INFANTRY)) {
					count = currentPlayer.getPlayersCardList().get(Card.INFANTRY);
				}
			}			
			count += 1;
		}
		return count;
	}
	
	/**
	 * This method is called when player exchanges the card in turn for army
	 * 
	 * @param cardsSelected - The cards selected by the player for exchange
	 * @param player - The current player
	 * @return - String for the operation
	 */
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

		if (total >= 3 && (a+i+c) >= 3) {
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
