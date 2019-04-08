package com.riskgame.test.strategy;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * This class aims to test the Cheater class
 * 
 * @author Shiva
 *
 */
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.strategy.Aggressive;

public class AggressiveTest {
	/** Object for Aggressive class */
	private static Aggressive aggressive;
	
	/** Object for GameMapGraph class */
	private static GameMapGraph mapGraph;
	
	/** Object for Player Class */
	private static Player player, player1;
	
	private static ArrayList<Country> getMyCountries, getMyCountries1;
	private static Country country, country1, country2, country3;
	/**
	 * This method is called to initialize test data before all data
	 */
	@BeforeAll
	public static void beforeAll() {
		getMyCountries = new ArrayList<Country>();
		country = new Country();
		country.setName("Canada");
		country.setNoOfArmies(2);
		country.setContinent("America");
		country.setPlayer("attacker");
		
		
		country1 = new Country();
		country1.setName("United states");
		country1.setNoOfArmies(4);
		country1.setContinent("America");
		country1.setPlayer("attacker");
		
		getMyCountries.add(country);
		getMyCountries.add(country1);
		
		getMyCountries1 = new ArrayList<Country>();
		
		country2 = new Country();
		country2.setName("Egypt");
		country2.setNoOfArmies(5);
		country2.setContinent("Africa");
		country2.setPlayer("defender");
		
		country3 = new Country();
		country3.setName("Cameron");
		country3.setNoOfArmies(5);
		country3.setContinent("Africa");
		country3.setPlayer("defender");
		
		
		getMyCountries1.add(country2);
		getMyCountries1.add(country3);
		
		player= new Player();
		player.setMyCountries(getMyCountries);
		player.setArmyCount(2);
		player.setName("attacker");
		
		player1= new Player();
		player1.setMyCountries(getMyCountries1);
		player1.setArmyCount(1);
		player1.setName("defender");
		
		player1 = new Player();
		player1.setMyCountries(getMyCountries1);
		
		mapGraph = new GameMapGraph();
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(player1);
		mapGraph.setPlayers(players);
		mapGraph.setCountries(new ArrayList<Country>());
		mapGraph.getCountries().add(country);
		mapGraph.getCountries().add(country1);
		mapGraph.getCountries().add(country2);
		mapGraph.getCountries().add(country3);
		
		
		aggressive=new Aggressive();
		
		Continent continent=new Continent();
		continent.setName("America");
		Continent continent1=new Continent();
		continent1.setName("Africa");
		
		country.setPartOfContinent(continent);
		country1.setPartOfContinent(continent);
		country2.setPartOfContinent(continent1);
		country3.setPartOfContinent(continent1);
		
		country.setAdjacentCountries(new ArrayList<String>());
		country.getAdjacentCountries().add("United states");
		
		country1.setAdjacentCountries(new ArrayList<String>());
		country1.getAdjacentCountries().add("Canada");
		country1.getAdjacentCountries().add("cameron");
		
		country2.setAdjacentCountries(new ArrayList<String>());
		country2.getAdjacentCountries().add("cameron");
		
		country3.setAdjacentCountries(new ArrayList<String>());
		country3.getAdjacentCountries().add("egypt");
		country3.getAdjacentCountries().add("united states");
	}
	
	/**
	 * This method tests the reinforcement for aggressive
	 * checks whether it reinforces its strongest country or not
	 */
	@Test
	public void reinforcementPhaseTest() {
		aggressive.reinforcementPhase(player, mapGraph, country1, 12);
		assertEquals(6,country1.getNoOfArmies());
	}
	
	/**
	 * This method tests the attack for aggressive
	 * checks whether it attack with its strongest country or not
	 */
	@Test
	public void attackPahseTest() {
		aggressive.attackPhase(mapGraph, player, country, country3);
		assertNotEquals(country.getNoOfArmies(),4);
	}
	
	/**
	 * This method tests the fortification for aggressive
	 * checks whether it fortifies its strongest country or not
	 */
	@Test
	public void fortificationPhaseTest() {
		aggressive.fortificationPhase(mapGraph, player, country, country1, 2);
		assertEquals(5,country1.getNoOfArmies());
	}
}
