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
	
    /** HashMap to store the continent names */
	//private HashMap<String, Continent> continents;
    
    /** Count of the number of countries */
	private int countOfCountries = 0;
    
    /** ArrayList of Country objects to store the country details */
	private ArrayList<Country> countries;

	private String filename;

	/** HashMap for set of countries */
	private HashMap<String, Country> countrySet;

	public GameMapGraph() {
		super();
	}
	
	

	public GameMapGraph(MapTag mapTag, int countOfContinents, ArrayList<Continent> continents, int countOfCountries,
			ArrayList<Country> countries) {
		super();
		this.mapTag = mapTag;
		this.countOfContinents = countOfContinents;
		this.continents = continents;
		this.countOfCountries = countOfCountries;
		this.countries = countries;
	}



	/**
	 * MapGraph constructor
	 */
	/*public GameMapGraph() {
		this.continents = new HashMap<>();
		this.adjacentCountries = new HashMap<>();
		this.countrySet = new HashMap<>();
	}*/

	/*public HashMap<String, Continent> getContinents() {
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
	}*/

	public HashMap<String, Country> getCountrySet() {
		return countrySet;
	}

	public void setCountrySet(HashMap<String, Country> countrySet) {
		this.countrySet = countrySet;
	}

	
	public MapTag getMapTag() {
		return mapTag;
	}

	

	public void setMapTag(MapTag mapTag) {
		this.mapTag = mapTag;
	}

	public int getCountOfContinents() {
		return countOfContinents;
	}

	public void setCountOfContinents(int countOfContinents) {
		this.countOfContinents = countOfContinents;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	public int getCountOfCountries() {
		return countOfCountries;
	}

	public void setCountOfCountries(int countOfCountries) {
		this.countOfCountries = countOfCountries;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	

	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	@Override
	public String toString() {
		return "GameMapGraph [mapTag=" + mapTag + ", countOfContinents=" + countOfContinents + ", continents="
				+ continents + ", countOfCountries=" + countOfCountries + ", countries=" + countries + ", filename="
				+ filename + ", countrySet=" + countrySet + "]";
	}



	

}
