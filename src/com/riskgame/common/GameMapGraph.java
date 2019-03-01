package com.riskgame.common;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMapGraph {
	/** HashMap to store the continent names */
	private HashMap<String, Continent> continents;

	/** HashMap to store the list of adjacent countries */
	private HashMap<Country, ArrayList<Country>> adjacentCountries;

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

	public HashMap<Country, ArrayList<Country>> getAdjacentCountries() {
		return adjacentCountries;
	}

	public void setAdjacentCountries(HashMap<Country, ArrayList<Country>> adjacentCountries) {
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
