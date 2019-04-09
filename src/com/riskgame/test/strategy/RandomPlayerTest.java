package com.riskgame.test.strategy;

import static org.junit.Assert.assertNotEquals;
/**
 * This class aims to test the RandomPlayer class
 * 
 * @author Shiva
 *
 */
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.strategy.RandomPlayer;

public class RandomPlayerTest {
	/** Object for Aggressive class */
	private static RandomPlayer randomPlayer;
	
	/** Object for GameMapGraph class */
	private static GameMapGraph mapGraph;
	
	/** Object for Player Class */
	private static Player player, player1;
	
	private static ArrayList<Country> getMyCountries, getMyCountries1;
	private static Country country, country1, country2, country3;
	/**
	 * This method is called to initialize test data before all data
	 */
	
	public RandomPlayerTest() {
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
		
		
		randomPlayer=new RandomPlayer();
		
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
		mapGraph.setGameType("test");
	}
	/**
	 * This method checks the attack phase of the random player
	 */
	@Test
	public void attackPahseTest() {
		randomPlayer.attackPhase(mapGraph, player, country, country3);
		assertNotEquals(country.getNoOfArmies(),0);
	}
}
