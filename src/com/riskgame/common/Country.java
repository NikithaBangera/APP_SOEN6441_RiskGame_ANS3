package com.riskgame.common;

import java.util.ArrayList;

import com.risk.model.Country;

public class Country {
	
	/** Name of country. */
	private String name;

	/** Country holder */
	private String player;

	/** Part of continent */
	private String continent;
	
	private ArrayList<Country> myCountries;

	/** X dimension */
	private String xValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getxValue() {
		return xValue;
	}

	public void setxValue(String xValue) {
		this.xValue = xValue;
	}

	public String getyValue() {
		return yValue;
	}

	public void setyValue(String yValue) {
		this.yValue = yValue;
	}

	public int getNoOfArmies() {
		return noOfArmies;
	}

	public void setNoOfArmies(int noOfArmies) {
		this.noOfArmies = noOfArmies;
	}

	public ArrayList<Country> getAdjacentCountries() {
		return adjacentCountries;
	}

	public void setAdjacentCountries(ArrayList<Country> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	public Continent getPartOfContinent() {
		return partOfContinent;
	}

	public void setPartOfContinent(Continent partOfContinent) {
		this.partOfContinent = partOfContinent;
	}

	/** Y dimension */
	private String yValue;

	/** Number of armies */
	private int noOfArmies;

	/** Adjacent Country holder */
	private ArrayList<Country> adjacentCountries;

	/** Part of Continent */
	private Continent partOfContinent;

	
	public ArrayList<Country> getMyCountries() {
        return myCountries;
    }
}
