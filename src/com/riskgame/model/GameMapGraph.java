package com.riskgame.model;

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
	private MapTag mapTag;

	/** Count of the number of countries */
	private int countOfContinents = 0;

	/** ArrayList of Continent objects to store the continent details */
	private ArrayList<Continent> continents;

	/** Count of the number of countries */
	private int countOfCountries = 0;

	/** ArrayList of Country objects to store the country details */
	private ArrayList<Country> countries;

	private String filename;

	/** HashMap for set of countries */
	private HashMap<String, Country> countrySet;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private String gamePhase;

	/**
	 * GameMapGraph Constructor
	 */
	public GameMapGraph() {
		super();
	}

	/**
	 * Its a GameMapGraph constructor
	 * 
	 * @param mapTag            - Map tag info
	 * @param countOfContinents - count of continents
	 * @param continents        - continent details
	 * @param countOfCountries  - count of countries
	 * @param countries         - countries detail
	 */
	public GameMapGraph(MapTag mapTag, int countOfContinents, ArrayList<Continent> continents, int countOfCountries,
			ArrayList<Country> countries) {
		super();
		this.mapTag = mapTag;
		this.countOfContinents = countOfContinents;
		this.continents = continents;
		this.countOfCountries = countOfCountries;
		this.countries = countries;
		this.players = new ArrayList<Player>();
	}

	/**
	 * Get the Country details.
	 * 
	 * @return Country details
	 */
	public HashMap<String, Country> getCountrySet() {
		return countrySet;
	}

	/**
	 * Set the Country details.
	 * 
	 * @param countrySet To set the Country details
	 */
	public void setCountrySet(HashMap<String, Country> countrySet) {
		this.countrySet = countrySet;
	}

	/**
	 * Get the Maptag details.
	 * 
	 * @return Maptag details
	 */
	public MapTag getMapTag() {
		return mapTag;
	}

	/**
	 * Set the Maptag details.
	 * 
	 * @param mapTag To set the Maptag details
	 */
	public void setMapTag(MapTag mapTag) {
		this.mapTag = mapTag;
	}

	/**
	 * Get the count of continents.
	 * 
	 * @return total number of continents.
	 */
	public int getCountOfContinents() {
		return countOfContinents;
	}

	/**
	 * Set the total number of continents
	 * 
	 * @param countOfContinents To set the count of continents.
	 */
	public void setCountOfContinents(int countOfContinents) {
		this.countOfContinents = countOfContinents;
	}

	/**
	 * Get the list of continents.
	 * 
	 * @return list of continents.
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * Set the list of continents
	 * 
	 * @param continents To set the list of continents.
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	/**
	 * Get the count of Countries.
	 * 
	 * @return total number of Countries.
	 */
	public int getCountOfCountries() {
		return countOfCountries;
	}

	/**
	 * Set the total number of Countries
	 * 
	 * @param countOfCountries To set the count of Countries.
	 */
	public void setCountOfCountries(int countOfCountries) {
		this.countOfCountries = countOfCountries;
	}

	/**
	 * Get the list of Countries.
	 * 
	 * @return list of Countries.
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * Set the list of Countries
	 * 
	 * @param countries To set the list of Countries.
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 * Get the name of the file.
	 * 
	 * @return File name.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Set the name of the file
	 * 
	 * @param filename To set the file name.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "GameMapGraph [mapTag=" + mapTag + ", countOfContinents=" + countOfContinents + ", continents="
				+ continents + ", countOfCountries=" + countOfCountries + ", countries=" + countries + ", filename="
				+ filename + ", countrySet=" + countrySet + "]";
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(String gamePhase) {
		this.gamePhase = gamePhase;
	}
}
