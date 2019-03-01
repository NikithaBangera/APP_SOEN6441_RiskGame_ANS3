package com.riskgame.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores the value associated to each map. It stores the content
 * required for building the map. It has variable continents which is a HashMap
 * containing continent name as the key of String type and the value is an
 * object of Continent containing information related to the continent. It has
 * variable adjacent countries which is a HashMap containing country object as
 * the key and the value is an ArrayList of type String containing names of the
 * adjacent countries.
 * 
 * @author Shresthi Garg
 * @author Nikitha
 */
public class GameMapGraph {
	/** HashMap to store the continent names */
	private HashMap<String, Continent> continents;

	/** HashMap to store the list of adjacent countries */
	private HashMap<Country, ArrayList<String>> adjacentCountries;

	/** HashMap for set of countries */
	private HashMap<String, Country> countrySet;

	/** Count of the number of countries */
	private int countOfCountries = 0;

	/**
	 * MapGraph constructor
	 */
	public GameMapGraph() {
		this.continents = new HashMap<>();
		this.adjacentCountries = new HashMap<>();
		this.countrySet = new HashMap<>();
	}

	public HashMap<String, Continent> getContinents() {
		return continents;
	}

	public void setContinents(HashMap<String, Continent> continents) {
		this.continents = continents;
	}

	public HashMap<Country, ArrayList<String>> getAdjacentCountries() {
		return adjacentCountries;
	}

	public void setAdjacentCountries(HashMap<Country, ArrayList<String>> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	public HashMap<String, Country> getCountrySet() {
		return countrySet;
	}

	public void setCountrySet(HashMap<String, Country> countrySet) {
		this.countrySet = countrySet;
	}

	public int getCountOfCountries() {
		return countOfCountries;
	}

	public void setCountOfCountries(int countOfCountries) {
		this.countOfCountries = countOfCountries;
	}

	@Override
	public String toString() {
		return "GameMapGraph [continents=" + continents + ", adjacentCountries=" + adjacentCountries + ", countrySet="
				+ countrySet + ", countOfCountries=" + countOfCountries + "]";
	}

}
