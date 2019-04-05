package com.riskgame.model;

import java.util.ArrayList;
import java.util.Observable;
import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;
import java.util.HashMap;
import java.util.List;

/**
 * Country class is a model which gives information with respect to the Countries.
 * 
 * @author Shresthi
 * @author Nikitha
 */
public class Country extends Observable {

	/** Name of country. */
	private String name;

	/** Part of continent */
	private Continent partOfContinent;

	/** X dimension */
	private String xValue;

	/** Y dimension */
	private String yValue;

	/** Adjacent Country holder */
	private ArrayList<String> adjacentCountries;
	
	private ArrayList<Country> adjacentCountries1;
	
	private ArrayList<String>adjacentCountriesforWeakestCountry;

	/** Number of armies */
	private int noOfArmies;

	/** Part of Continent */
	private String continent;

	/** Country holder */
	private String player;

	/** Card of the player */
	private Card card;
	
	/** List of Dice values */
	private List<Integer> diceValues = new ArrayList<Integer>();
 	
	/** List of country card */
	private HashMap<String, String> countryCardsList = new HashMap<String, String>();

	/**
	 * Country Constructor
	 */
	public Country() {
		PlayerView playerView = new PlayerView(); 
		DiceView diceView = new DiceView();
		CardView cardView = new CardView(); 
		this.addObserver(playerView);
		this.addObserver(diceView); 
		this.addObserver(cardView);
	}

	/**
	 * Get the Country name.
	 * 
	 * @return Name of the country
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the Country name.
	 * 
	 * @param name To set the name of the Country
	 */
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the player name to whom country belong to.
	 * 
	 * @return Name of the player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Set the player name to whom country belong to.
	 * 
	 * @param player name To set the name of the player
	 */
	public void setPlayer(String player) {
		this.player = player;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the continent name.
	 * 
	 * @return Name of the continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Set the continent name.
	 * 
	 * @param continent To set the continent
	 */
	public void setContinent(String continent) {
		this.continent = continent;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the X coordinate value of the Country.
	 * 
	 * @return X-value
	 */
	public String getxValue() {
		return xValue;
	}

	/**
	 * Set the X coordinate value of the Country.
	 * 
	 * @param xValue To set X-value to Country
	 */
	public void setxValue(String xValue) {
		this.xValue = xValue;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the Y coordinate value of the Country.
	 * 
	 * @return Y-value
	 */
	public String getyValue() {
		return yValue;
	}

	/**
	 * Set the Y coordinate value of the Country.
	 * 
	 * @param yValue To set Y-value to Country
	 */
	public void setyValue(String yValue) {
		this.yValue = yValue;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the number of armies in the Country.
	 * 
	 * @return number of armies
	 */
	public int getNoOfArmies() {
		return noOfArmies;
	}

	/**
	 * Set the number armies in country.
	 * 
	 * @param noOfArmies To set the number of the armies
	 */
	public void setNoOfArmies(int noOfArmies) {
		this.noOfArmies = noOfArmies;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the list of adjacent countries.
	 * 
	 * @return adjacent Countries
	 */
	public ArrayList<String> getAdjacentCountries() {
		return adjacentCountries;
	}
	
	public ArrayList<Country> getAdjacentCountries1() {
		return adjacentCountries1;
	}

	/**
	 * Set the adjacent countries.
	 * 
	 * @param adjacentCountries To set the list of adjacent countries
	 */
	public void setAdjacentCountries(ArrayList<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the continent name to which country belongs to.
	 * 
	 * @return Name of the continent
	 */
	public Continent getPartOfContinent() {
		return partOfContinent;
	}

	/**
	 * Set the continent name to which country belong to.
	 * 
	 * @param partOfContinent To set the continent
	 */
	public void setPartOfContinent(Continent partOfContinent) {
		this.partOfContinent = partOfContinent;
		setChanged();
		notifyObservers();
	}

	/*
	 * @Override public String toString() { return "Country [name=" + name +
	 * ", partOfContinent=" + partOfContinent + ", xValue=" + xValue + ", yValue=" +
	 * yValue + ", adjacentCountries=" + adjacentCountries + ", noOfArmies=" +
	 * noOfArmies + ", continent=" + continent + ", player=" + player +
	 * ", countryCardsList=" + countryCardsList + "]"; }
	 */

	/**
	 * This method gets the card
	 * 
	 * @return The card object
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * This method sets the card
	 * 
	 * @param card - the card object
	 */
	public void setCard(Card card) {
		this.card = card;
		setChanged();
		notifyObservers();
	}

	/**
	 * This method gets the list of dice values
	 * @return diceValues - return the list of dice values 
	 */
	public List<Integer> getDiceValues() {
		return diceValues;
	}

	/**
	 * This method sets the list of dice values
	 * @param diceValues - list of dice values
	 */
	public void setDiceValues(List<Integer> diceValues) {
		this.diceValues = diceValues;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Set the adjacent countries.
	 * 
	 * @param adjacentCountries To set the list of adjacent countries
	 * @return 
	 */
	public static ArrayList<String> setAdjacentCountriesforWeakestCountry(ArrayList<String> adjacentCountriesforWeakestCountry) {
		return adjacentCountriesforWeakestCountry;
		
	}
}
