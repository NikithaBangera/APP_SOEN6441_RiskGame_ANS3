package com.riskgame.common;

import java.util.ArrayList;

public class RiskPlayer {
	/** Name of the Player */
    private String name;
    
    /**Initial army count of the Player*/
    private int armyCount=0;
    
    /** List of countries held by the Player */
    private ArrayList<Country> myCountries;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmyCount() {
		return armyCount;
	}

	public void setArmyCount(int armyCount) {
		this.armyCount = armyCount;
	}

	public ArrayList<Country> getMyCountries() {
		return myCountries;
	}

	public void setMyCountries(ArrayList<Country> myCountries) {
		this.myCountries = myCountries;
	}

	@Override
	public String toString() {
		return "RiskPlayer [name=" + name + ", armyCount=" + armyCount + ", myCountries=" + myCountries + "]";
	}

	

}
