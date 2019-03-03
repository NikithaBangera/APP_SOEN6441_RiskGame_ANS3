package com.riskgame.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Continent {
	String continentName;
	int controlValue;
	HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
	ArrayList<Country> countriesInContinent = new ArrayList<Country>();

	public String getContinentName() {
		return continentName;
	}

	public void setName(String name) {
		this.continentName = name;
	}

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

	public void setCountriesInContinent(ArrayList<Country> countriesInContinent) {
		this.countriesInContinent = countriesInContinent;
	}

	@Override
	public String toString() {
		return "Continents: \n[continentName=" + continentName + ", controlValue=" + controlValue + ", continentDetails="
				+ continentDetails + ", countriesInContinent=" + countriesInContinent + "]/n";
	}

}