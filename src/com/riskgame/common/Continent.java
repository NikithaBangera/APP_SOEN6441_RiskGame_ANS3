package com.riskgame.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores all the values associated to each Continent. It stores Continent 
 * name,Control value, a hashmap with Continent name and associated control value and
 * an array list with list of countries in a Continent.
 * 
 * @author 
 * @author 
 *
 */
public class Continent {
	
	/** Variable to store Continent name */
	String continentName;
	
	/** Stores Control value */
	int controlValue;
	
	/** HashMap to store Continent name with associated control value */
	HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
	
	/** ArrayList to store list of Countries name associated with a Continent*/
	ArrayList<Country> countriesInContinent = new ArrayList<Country>();

	/**
	 * Gets name of the Continent 
	 *
	 * @return Continent name
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * Sets the name of the continent 
	 *
	 * @param Continent name
	 */
	public void setName(String name) {
		this.continentName = name;
	}

	/**
	 * Gets the control value 
	 *
	 * @return Country value
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * Sets the Control value as integer 
	 *
	 * @param Control value
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * Gets Continent and associated Control value 
	 *
	 * @return Continent and its Control value
	 */
	public HashMap<String, Integer> getContinents() {
		return continentDetails;
	}

	/**
	 * Sets the name of the Continent with its Control value
	 *
	 * @param Continent with its Control value
	 */
	public void setContinents(HashMap<String, Integer> continents) {
		this.continentDetails = continents;
	}

	/**
	 * Gets the list of countries in a Continent 
	 *
	 * @return country names
	 */
	public ArrayList<Country> getCountriesInContinent() {
		return countriesInContinent;
	}

	/**
	 * Sets the Countries list associated with a Continent 
	 *
	 * @param list of Countries
	 */
	public void setCountriesInContinent(ArrayList<Country> countriesInContinent) {
		this.countriesInContinent = countriesInContinent;
	}

	@Override
	public String toString() {
		return "Continents: \n[continentName=" + continentName + ", controlValue=" + controlValue + ", continentDetails="
				+ continentDetails + ", countriesInContinent=" + countriesInContinent + "]/n";
	}

}