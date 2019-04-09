package com.riskgame.test.strategy;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.riskgame.controller.CardController;
import com.riskgame.controller.PlayerController;
import com.riskgame.model.Card;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.strategy.Cheater;

/**
 * This class aims to test the Cheater class
 * 
 * @author Shresthi Garg
 *
 */
public class CheaterTest {
	
	/** Object for Cheater class */
	private static Cheater cheater;
	
	/** Object for GameMapGraph class */
	private static GameMapGraph mapGraph;
	
	/** Object for StartUp Class */
	private static PlayerController playerController;
	
	/** Object for Player Class */
	private static Player player, player1;
	
	private static ArrayList<Country> getMyCountries, getMyCountries1;
	private static Country country, country1, country2, country3;
	

	/**
	 * This method is called to initialise test data before all data
	 */
	@BeforeAll
	public static void beforeAll() {
		getMyCountries = new ArrayList<Country>();
		country = new Country();
		country.setName("India");
		country.setNoOfArmies(4);
		country.setContinent("Asia");
		country.setPlayer("Bob");
		
		country1 = new Country();
		country1.setName("Bhutan");
		country1.setNoOfArmies(2);
		country1.setContinent("Asia");
		country1.setPlayer("Bob");
		
		getMyCountries.add(country);
		getMyCountries.add(country1);
		
		getMyCountries1 = new ArrayList<Country>();
		
		country2 = new Country();
		country2.setName("Egypt");
		country2.setNoOfArmies(5);
		country2.setContinent("Africa");
		country2.setPlayer("Rob");
		
		country3 = new Country();
		country3.setName("Egypt");
		country3.setNoOfArmies(5);
		country3.setContinent("Africa");
		country3.setPlayer("Rob");
		
		
		getMyCountries1.add(country2);
		getMyCountries1.add(country3);
		
		player= new Player();
		player.setMyCountries(getMyCountries);
		player.setArmyCount(1);
		player.setName("Rob");
		
		player1 = new Player();
		player1.setMyCountries(getMyCountries1);
		
		mapGraph = new GameMapGraph();
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(player1);
		mapGraph.setPlayers(players);
		playerController = new PlayerController();
		cheater = new Cheater();
		
		
	}

	/**
	 * This method tests the reinforcement for cheater
	 */
	@Test
	public void aReinforcementPhaseTest() {
		cheater.reinforcementPhase(player1, mapGraph, country2, 12);
		assertEquals(10, country2.getNoOfArmies());
	}
	
	/**
	 * This method tests the reinforcement for cheater
	 */
	@Test
	public void invalidReinforcementPhaseTest() {
		cheater.reinforcementPhase(player1, mapGraph, country3, 12);
		assertNotEquals(5, country3.getNoOfArmies());
	}
	
	/**
	 * This method tests the reinforcement for cheater
	 */
	@Test
	public void inValidReinforcementPhaseTest() {
		cheater.reinforcementPhase(player, mapGraph, country1, 12);
		assertNotEquals(2, country1.getNoOfArmies());
	}

	/**
	 * This method test fortification phase for cheater
	 */
	@Test
	public void fortificationPhaseTest() {
		ArrayList<String> adjCountries = new ArrayList<String>();
		adjCountries.add("Bhutan");
		country.setAdjacentCountries(adjCountries);
		ArrayList<String> adjCountries1 = new ArrayList<String>();
		adjCountries.add("India");
		country1.setAdjacentCountries(adjCountries1);
		cheater.fortificationPhase(mapGraph, player, country, country3, 12);
		assertEquals(4, country.getNoOfArmies());
		
	}

	

}
