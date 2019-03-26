package com.riskgame.controllertest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.controller.DiceController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

class DiceControllerTest {
	
	private DiceController dice;
	
	private Player attacker,defender;
	
	private ArrayList<Player> players;
	
	/** Object of the GameMapGraph for creating the map graph */
	private GameMapGraph mapGraph;
	
	private Country attackercountry,defendercountry, toCountry;
	
	/** HashMap for saving the country set */
	private HashMap<String,Country>	countryset;

	private ArrayList<Country> myCountry,others;
	/** ArrayList for storing adjacent countries list for the countries */
	private ArrayList<String> adjacentCountries,adjacentCountries_1;
	
	public DiceControllerTest() {
		
		dice=new DiceController();
		adjacentCountries=new ArrayList<String>();
		countryset=new HashMap<String,Country>();
		mapGraph=new GameMapGraph();
		myCountry=new ArrayList<Country>();
		others=new ArrayList<Country>();
		players=new ArrayList<Player>();
		attacker=new Player();
		defender=new Player();
		adjacentCountries_1=new ArrayList<String>();
		
		attacker.setName("attacker");
		defender.setName("defender");
		players.add(attacker);
		players.add(defender);
		mapGraph.setPlayers(players);
		
		attackercountry = new Country();
		attackercountry.setName("Canada");
		attackercountry.setPlayer("attacker");
		attackercountry.setNoOfArmies(10);
		countryset.put("Canada", attackercountry);
		mapGraph.setCountrySet(countryset);
		myCountry.add(attackercountry);
		
		toCountry= new Country();
		toCountry.setName("Russia");
		toCountry.setPlayer("attacker");
		adjacentCountries.add("Canada");
		toCountry.setAdjacentCountries(adjacentCountries);
		toCountry.setNoOfArmies(1);
		mapGraph.getCountrySet().put("Russia", toCountry);
		myCountry.add(toCountry);
		
		defendercountry = new Country();
		defendercountry.setName("USA");
		defendercountry.setPlayer("defender");
		defendercountry.setNoOfArmies(1);
		defendercountry.setAdjacentCountries(adjacentCountries);
		mapGraph.getCountrySet().put("USA", defendercountry);
		others.add(defendercountry);
		adjacentCountries_1.add("USA");
		adjacentCountries_1.add("Russia");
		attackercountry.setAdjacentCountries(adjacentCountries_1);
		
		attacker.setMyCountries(myCountry);
		defender.setMyCountries(others);
		
		
	}

	@Test 

	public void isdiceworks() {

		dice.moveArmies(2, attackercountry, defendercountry,mapGraph,2,5);

		assertEquals(3, defendercountry.getNoOfArmies());

		assertEquals(8, attackercountry.getNoOfArmies());

	}
	
	
	@Test 

	public void isdiceValid() {

		dice.moveArmies(2,toCountry,attackercountry,mapGraph,5,5);

		assertEquals(1, toCountry.getNoOfArmies());

		assertEquals(10, attackercountry.getNoOfArmies());

	}
	
	@Test 

	public void isdicegetsplayer() {
		Player result=new Player();

		result=dice.getPlayerForCountry(mapGraph,"Canada");

		assertEquals("attacker", result.getName());


	}
	
	
	

}
