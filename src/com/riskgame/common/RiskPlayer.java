package com.riskgame.common;

import java.util.ArrayList;

import com.risk.model.Country;
import com.risk.model.ICardType;

public class RiskPlayer {
	/** Name of the Player */
	private String name;

	/** Initial army count of the Player */
	private int armyCount = 0;

	/** List of countries held by the Player */
	private ArrayList<Country> myCountries;
	
	 public RiskPlayer() {
	        this.myCountries = new ArrayList<Country>();
	        //this.listOfCards = new ArrayList<ICardType>();
	    }

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
}
