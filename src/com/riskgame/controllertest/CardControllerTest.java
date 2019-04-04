package com.riskgame.controllertest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.riskgame.controller.CardController;
import com.riskgame.model.Card;
import com.riskgame.model.Player;

/**
 * This class aims to test the Card Controller method
 * @author Shresthi Garg
 *
 */
public class CardControllerTest {
	private static Player player;
	private static CardController cardAction;
	private static HashMap<String, Integer> cardsSelected;

	/**
	 * This method is called to initialise test data before all data
	 */
	@BeforeAll
	public static void beforeAll() {
		HashMap<String, Integer> playerCard = new HashMap<String, Integer>();
		Card cardObj = new Card();
		cardAction = new CardController();
		player = new Player();
		playerCard.put(Card.ARTILLERY, 1);
		playerCard.put(Card.CAVALRY, 2);
		playerCard.put(Card.INFANTRY, 1);
		player.setPlayersCardList(playerCard);

		player.setArmyCount(23);

	}
	
	/**
	 * This method is called before each test case
	 */
	@BeforeEach public void assignCard() {
		cardsSelected = new HashMap<String, Integer>();
		cardsSelected.put(Card.ARTILLERY, 1);
		cardsSelected.put(Card.CAVALRY, 1);
		cardsSelected.put(Card.INFANTRY, 1);
	}

	/**
	 * This method test the count for the card assigned to player
	 */
	@Test
	public void addCardCountTest() {
		int count = cardAction.addCardCount(Card.ARTILLERY, player);
		assertEquals(2, count);
	}

	/**
	 * This method test the wrong value for the card assigned to player
	 */
	@Test
	public void addCardCountInvalidTest() {
		int count = cardAction.addCardCount(Card.CAVALRY, player);
		assertTrue(count != 2);
	}
	
	/**
	 * Tests the exchange Card method
	 */
	@Test
	public void exchangeCardsTest() {
		String message = cardAction.exchangeCards(cardsSelected, player);
		System.out.println(cardsSelected);
		assertEquals("Successfully exchanged Cards with 5 armies.", message);
	}
	
	/**
	 * Tests the exchange card invalid scenario
	 */
	@Test
	public void exchangeCardsInvalidTest() {
		cardsSelected.put(Card.ARTILLERY, 0);
		String message = cardAction.exchangeCards(cardsSelected, player);
		assertTrue("Cannot perform exchange. Should select atleast 3 cards".equals(message));
	}
	
	

}
