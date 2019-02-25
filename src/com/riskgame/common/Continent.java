package com.riskgame.common;

import java.util.HashMap;

public class Continent {
	String continentName;
	int controlValue;
	public HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
	
	public Continent(String continentName, int controlValue) {
		this.continentName = continentName;
		this.controlValue = controlValue;
	}
	
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String name) {
		this.continentName = name;
	}

	public int getControlValue() {
		return controlValue;
	}

	public void setControl_value(int controlValue) {
		this.controlValue = controlValue;
	}

	public HashMap<String, Integer> getContinents() {
		return continentDetails;
	}

	public void setContinents(HashMap<String, Integer> continents) {
		this.continentDetails = continents;
	}

	

}