package com.riskgame.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.controller.PlayerController;
import com.riskgame.controller.TournamentController;
import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

public class TournamentControllerTest {
	
	/** Object for Player Class */
	Player player1,player,attacker,defender,toCountrier;
	
	/** Object for Continent Class */
	Continent continent,continent2;
	
	/** Object for StartUp Class */
	PlayerController playerController;

	/** Object for Tournament startup Class */
	TournamentController tournamentController;
	
	/** Object for GameMapGraph class */
	GameMapGraph mapGraph;
	
	/** HasMap to store the information of countries */
	HashMap<String, Country> countrySet; 
	
	/** Objects for Country Class */
	private Country country, country1, toCountry, fromCountry, toCountry1, attackercountry_1, defendercountry_1, toCountry_1, fromCountry_1;
	
	/** ArrayList for storing adjacent countries list for the countries */
    private ArrayList<String> adjacentCountries_1;
    /** ArrayList for storing adjacent countries list for the countries */
	private ArrayList<String> adjacentCountries1_1;
	
	/** HashMap for saving the country set */
	private HashMap<String,Country>	countryset_1;
	
	/** ArrayList for setting countries of the players */
	private ArrayList<Country> attackercountries,defendercountries,toCountrycountries ;
	
	/** Object of the GameMapGraph for creating the map graph */
	private GameMapGraph gameMapGraph_1;
	
	/** ArrayList for saving the players */
	private ArrayList<Player> players;
	
	/** ArrayList for storing adjacent countries list for the countries */
	private ArrayList<String> adjacentCountries;
	
	

	/**
	 * StartupPhaseTest Constructor for initial setup 
	 */
   public TournamentControllerTest() {
	   tournamentController = new TournamentController();
	   countrySet = new HashMap<String, Country>();
	   mapGraph = new GameMapGraph();
	   players=new ArrayList<Player>();
	   attacker=new Player();
	   defender=new Player();
	   toCountrier=new Player();
	   toCountrier.setName("toCountry");
	   
	   attackercountries=new ArrayList<Country>();
	   defendercountries=new ArrayList<Country>();
	   toCountrycountries=new ArrayList<Country>();
	   continent=new Continent();
	   continent.setName("Asia");
	   continent.setControlValue(4);
	   continent2=new Continent();
	   continent2.setName("Europe");
	   continent2.setControlValue(2);
	   
	   attacker.setName("attacker");
	   defender.setName("defender");
       playerController = new PlayerController();
       playerController.setCountOfthePlayers(2);

       player1 = new Player();
       mapGraph.getPlayers().add(player1);

       country = new Country();
       country.setName("Morocco");
       countrySet.put(country.getName(), country);
       mapGraph.setCountrySet(countrySet);

       adjacentCountries = new ArrayList<String>();
		
		country1 = new Country();
		country1.setName("India");
		adjacentCountries.add("India");
		
		country1 = new Country();
		country1.setName("Nepal");
		adjacentCountries.add("Nepal");
		
		country1 = new Country();
		country1.setName("Sri Lanka");
		adjacentCountries.add("Sri Lanka");
		
		country1 = new Country();
		country1.setName("China");
		adjacentCountries.add("China");
		
		fromCountry = new Country();
		fromCountry.setName("Bangladesh");
		fromCountry.setNoOfArmies(8);
		
		toCountry = new Country();
		toCountry.setName("India");
		toCountry.setNoOfArmies(4);
		
		toCountry1 = new Country();
		toCountry1.setName("Canada");
		toCountry1.setNoOfArmies(2);
		fromCountry.setAdjacentCountries(adjacentCountries);
		
		adjacentCountries_1 = new ArrayList<String>();
		adjacentCountries1_1 = new ArrayList<String>();

		gameMapGraph_1=new GameMapGraph();
		countryset_1=new HashMap<String,Country>();

		attackercountry_1 = new Country();
		attackercountry_1.setName("Canada");
		attackercountry_1.setPlayer("attacker");
		attackercountry_1.setPartOfContinent(continent2);
		attackercountry_1.setNoOfArmies(5);
		players.add(attacker);
		countryset_1.put("Canada", attackercountry_1);
		
		toCountry_1= new Country();
		toCountry_1.setName("Russia");
		toCountry_1.setPartOfContinent(continent);
		toCountry_1.setPlayer("toCountry");
		toCountrycountries.add(toCountry_1);
		toCountry_1.setNoOfArmies(1);
		
		
		defendercountry_1 = new Country();
		defendercountry_1.setName("USA");
		defendercountry_1.setPartOfContinent(continent2);
		defendercountry_1.setPlayer("defender");
		defendercountry_1.setNoOfArmies(1);
		
		
		fromCountry_1 = new Country();
		fromCountry_1.setName("Alaska");
		fromCountry_1.setPartOfContinent(continent2);
		fromCountry_1.setPlayer("toCountry");
		fromCountry_1.setNoOfArmies(5);

		
		
		adjacentCountries_1.add("USA");
		adjacentCountries1_1.add("Russia");
		fromCountry_1.setAdjacentCountries(adjacentCountries1_1);
		
		adjacentCountries1_1.add("Canada");
		gameMapGraph_1.setCountries(new ArrayList<Country>());
		gameMapGraph_1.getCountries().add(toCountry_1);
		gameMapGraph_1.getCountries().add(defendercountry_1);
		gameMapGraph_1.getCountries().add(fromCountry_1);
		
		
		attackercountry_1.setAdjacentCountries(adjacentCountries_1);
		
		toCountry_1.setAdjacentCountries(adjacentCountries_1);
		toCountry_1.getAdjacentCountries().add("Alaska");
		
		defendercountry_1.setAdjacentCountries(adjacentCountries1_1);
		attacker.setArmyCount(3);
		defender.setArmyCount(3);
		attacker.setPlayerType("Aggressive");
		defender.setPlayerType("Benevolent");
		attacker.setEndPlaceArmies(false);
		defender.setEndPlaceArmies(false);
		players.add(attacker);
		players.add(defender);
		attackercountries.add(attackercountry_1);
		defendercountries.add(defendercountry_1);
		attacker.setMyCountries(attackercountries);
		defender.setMyCountries(defendercountries);
		toCountrier.setMyCountries(toCountrycountries);
		gameMapGraph_1.setPlayers(players);
		
		mapGraph.setGameType("Test");
		gameMapGraph_1.setGameType("Test");
		
   }
   /**
    * this method checks the placing armies of the tournament mode
    */
   @Test
   public void testTournamentPlaceArmies() {
	   tournamentController.tournamentPlaceArmies(gameMapGraph_1);
	   assertEquals(0, gameMapGraph_1.getPlayers().get(0).getArmyCount());
	   assertEquals(0, gameMapGraph_1.getPlayers().get(1).getArmyCount());
	   assertTrue(gameMapGraph_1.getPlayers().get(0).isEndPlaceArmies());
	   assertTrue(gameMapGraph_1.getPlayers().get(1).isEndPlaceArmies());
   }
}
