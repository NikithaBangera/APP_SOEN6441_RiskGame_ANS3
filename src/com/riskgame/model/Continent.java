package com.riskgame.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Continent class which gives information with respect to the Continents.
 * 
 * @author Shiva
 * @author Nikitha
 */
public class Continent {
	
	/** Name of the continent. */
	String continentName;
	
	/** Control Value of continent. */
	int controlValue;
	
	 /**HashMap for continent details*/
	HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
	
	/** List of countries in the continent*/
	ArrayList<Country> countriesInContinent = new ArrayList<Country>();

	/**
	 * Get the continent name.
	 * 
	 * @return continent's Name
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * Set the continent name.
	 * 
	 * @param name
	 *            To set the name of the continent
	 */
	public void setName(String name) {
		this.continentName = name;
	}

	/**
	 * Get the continent's control value.
	 * 
	 * @return control value
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * Set the continent's control value.
	 * 
	 * @param controlValue
	 *            To set the control value
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * Get the continent's name and control value.
	 * 
	 * @return continent details
	 */
	public HashMap<String, Integer> getContinents() {
		return continentDetails;
	}

	/**
	 * Set the continent's name and control value.
	 * 
	 * @param continents
	 *            To set the control value and name.
	 */
	public void setContinents(HashMap<String, Integer> continents) {
		this.continentDetails = continents;
	}

	/**
	 * Get the list of countries in the particular continent.
	 * 
	 * @return countries in continent
	 */
	public ArrayList<Country> getCountriesInContinent() {
		return countriesInContinent;
	}

	/**
	 * Set the list of countries to the particular continent.
	 * 
	 * @param countriesInContinent
	 *            To set the countries to continent.
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