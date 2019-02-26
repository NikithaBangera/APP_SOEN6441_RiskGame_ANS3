package com.riskgame.common;

import java.util.TreeSet;

public class Continent {
	String continentName;
	int controlValue;
	private TreeSet<Territory> territoriesInContinent;
	
	public Continent(String continentName, int controlValue) {
		this.continentName = continentName;
		this.controlValue = controlValue;
		territoriesInContinent = new TreeSet<Territory>();
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

	public void addTerritory(Territory territory) {
		territoriesInContinent.add(territory);
	}
	
	public TreeSet<Territory> getTerritoriesInContinent(){
		return territoriesInContinent;
	}
	
	public void setTerritoriesInContinent(TreeSet<Territory> territoriesInContinent) {
		this.territoriesInContinent = territoriesInContinent;
	}

}