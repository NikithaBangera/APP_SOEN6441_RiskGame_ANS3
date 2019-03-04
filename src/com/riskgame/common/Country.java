package com.riskgame.model;

import java.util.ArrayList;

/**
 * This class stores all the values associated to each Country. It stores Country's
 * name, Continent to which the country associated with, X and Y position values of a
 * Country. It also stores the associated list of adjacent countries to a Country. 
 * 
 * @author 
 * @author 
 *
 */
public class Country {

	/** Store Country name */
	private String name;
	
	/** Name of the Continent to which the country is part of */	
	private Continent partOfContinent;

	/** Country's horizontal distance*/
	private String xValue;

	/** Country's vertical distance */
	private String yValue;

	/** ArrayList to store list of Adjacent Countries */
	private ArrayList<String> adjacentCountries;

	/** Count of armies for a Country */
	private int noOfArmies;

	/** Name of the Continent */
	private String continent;

	/** Player for the Country */
	private String player;

	/**
	 * Gets name of the country 
	 *
	 * @return country name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the country 
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets name of the player 
	 *
	 * @return player name
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Sets the name of the player 
	 *
	 * @param player name
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	/**
	 * Gets name of the Continent 
	 *
	 * @return Continent name
	 */
	public String getContinent() {
		return continent;
	}
	
	/**
	 * Sets name of the continent 
	 *
	 * @param Continent name
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

	/**
	 * Gets horizontal distance(X value) for the country 
	 *
	 * @return X value
	 */
	public String getxValue() {
		return xValue;
	}

	/**
	 * Sets horizontal distance (X value) for the country on map  
	 *
	 * @param X value
	 */
	public void setxValue(String xValue) {
		this.xValue = xValue;
	}

	/**
	 * Gets vertical distance(Y value) for the country 
	 *
	 * @return Y value
	 */
	public String getyValue() {
		return yValue;
	}

	/**
	 * Sets vertical distance(Y value) for the country on map  
	 *
	 * @param Y value
	 */
	public void setyValue(String yValue) {
		this.yValue = yValue;
	}

	/**
	 * Gets count of armies for the country 
	 *
	 * @return Armies count
	 */	
	public int getNoOfArmies() {
		return noOfArmies;
	}

	/**
	 * Sets the number of armies for the country 
	 *
	 * @param Armies count
	 */
	public void setNoOfArmies(int noOfArmies) {
		this.noOfArmies = noOfArmies;
	}

	/**
	 * Gets the list of adjacent counties for the country 
	 *
	 * @return Adjacent countries list
	 */
	public ArrayList<String> getAdjacentCountries() {
		return adjacentCountries;
	}

	/**
	 * Sets the adjacent countries list for the country
	 *
	 * @param Adjacent countries list
	 */
	public void setAdjacentCountries(ArrayList<String> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}

	/**
	 * Checks if the country is part of the continent
	 * 
	 * @return Continent name
	 */
	public Continent getPartOfContinent() {
		return partOfContinent;
	}

	/**
	 * Sets the country as part of the continent
	 * 
	 * @param Continent name
	 */
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
