package com.riskgame.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Continent {
	
	/** Variable to store Continent name */
	String continentName;
	
	/** Stores Control value */
	int controlValue;
	
	/** HashMap to store Continent name with associated control value */
	HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
	
	/** ArrayList to store list of Countries name associated with a Continent*/
	ArrayList<Country> countriesInContinent = new ArrayList<Country>();

	
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

	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	
	public HashMap<String, Integer> getContinents() {
		return continentDetails;
	}

	
	public void setContinents(HashMap<String, Integer> continents) {
		this.continentDetails = continents;
	}

	
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