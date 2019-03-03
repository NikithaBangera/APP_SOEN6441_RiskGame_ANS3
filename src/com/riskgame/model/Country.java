package com.riskgame.model;

import java.util.ArrayList;

public class Country {

	/** Name of country. */
	private String name;

	/** Part of continent */
	private Continent partOfContinent;

	/** X dimension */
	private String xValue;

	/** Y dimension */
	private String yValue;

	/** Adjacent Country holder */
	private ArrayList<String> adjacentCountries;

	/** Number of armies */
	private int noOfArmies;

	/** Part of Continent */
	private String continent;

	/** Country holder */
	private String player;

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

	public ArrayList<String> getAdjacentCountries() {
		return adjacentCountries;
	}

	public void setAdjacentCountries(ArrayList<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	public Continent getPartOfContinent() {
		return partOfContinent;
	}

	public void setPartOfContinent(Continent partOfContinent) {
		this.partOfContinent = partOfContinent;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", partOfContinent=" + partOfContinent + ", xValue=" + xValue + ", yValue="
				+ yValue + ", adjacentCountries=" + adjacentCountries + ", noOfArmies=" + noOfArmies + ", continent="
				+ continent + ", player=" + player + "]";
	}

}
