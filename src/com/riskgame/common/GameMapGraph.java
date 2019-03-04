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
	
	/** Variable for MapTag object */
	private MapTag mapTag;
    
    /** Stores count of Continents */
	private int countOfContinents = 0;
	
    /** ArrayList of Continent objects to store the continent details */
    private ArrayList<Continent> continents;
	
    /** HashMap to store the continent names */
	//private HashMap<String, Continent> continents;
    
    /** Stores the count of Countries */
	private int countOfCountries = 0;
    
    /** ArrayList of Country objects to store the country details */
	private ArrayList<Country> countries;

	/** Stores file name */
	private String filename;

	/** HashMap for set of countries */
	private HashMap<String, Country> countrySet;

	public GameMapGraph() {
		super();
	}
	
	/** GameMapGraph Constructor
	 *   
	 * @param mapTag
	 * @param countOfContinents
	 * @param continents
	 * @param countOfCountries
	 * @param countries
	 */
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
	 * Gets Country with all the details associated with the Country 
	 *
	 * @return Country details
	 */
	public HashMap<String, Country> getCountrySet() {
		return countrySet;
	}

	/**
	 * Sets Country with all the details associated with it. 
	 *
	 * @param Country set
	 */	
	public void setCountrySet(HashMap<String, Country> countrySet) {
		this.countrySet = countrySet;
	}

	/**
	 * Gets all the MapTag details  
	 *
	 * @return MapTag data
	 */
	public MapTag getMapTag() {
		return mapTag;
	}

	/**
	 * Sets all the MapTag details 
	 *
	 * @param MapTag data
	 */
	public void setMapTag(MapTag mapTag) {
		this.mapTag = mapTag;
	}


	/**
	 * Gets number of Continents
	 * 
	 * @return Continents count
	 */
	public int getCountOfContinents() {
		return countOfContinents;
	}

	/**
	 * Sets the total number of Continents 
	 *
	 * @param Continents count
	 */
	public void setCountOfContinents(int countOfContinents) {
		this.countOfContinents = countOfContinents;
	}

	/**
	 * Gets list of all the Continents 
	 *
	 * @return Continents list
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * Sets the list of all the Continents
	 *
	 * @param Continents list
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	/**
	 * Gets number of Countries 
	 *
	 * @return Countries count
	 */
	public int getCountOfCountries() {
		return countOfCountries;
	}

	/**
	 * Sets the total name of Countries 
	 *
	 * @param Countries count
	 */
	public void setCountOfCountries(int countOfCountries) {
		this.countOfCountries = countOfCountries;
	}

	/**
	 * Gets list of all the Countries 
	 *
	 * @return Countries list
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * Sets the list of all the Countries 
	 *
	 * @param Countries list
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 * Gets name of the File 
	 *
	 * @return File name
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the name of the File  
	 *
	 * @param File name
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



	

}
