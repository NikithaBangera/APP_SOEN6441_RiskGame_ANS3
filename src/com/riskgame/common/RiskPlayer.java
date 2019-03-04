package com.riskgame.model;

import java.util.ArrayList;

/**
 * This class stores the value associated to each player. It stores player's
 * name of String type, total armyCount of player as Integer type, and the
 * ArrayList of type Country which the player owns.
 * 
 * @author Shresthi Garg
 * @author Anusha
 *
 */
public class RiskPlayer {
	
	/** Stores Player name */
	private String name;

	/** Initial army count of the Player */
	private int armyCount = 0;

	/** List of countries held by the Player */
	private ArrayList<Country> myCountries= new ArrayList<Country>();

	public String getName() {
		return name;
	}

	/**
	 * Sets Player name 
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getArmyCount() {
		return armyCount;
	}

	/**
	 * Sets the Army count for the Player
	 *
	 * @param Army count
	 */
	public void setArmyCount(int armyCount) {
		this.armyCount = armyCount;
	}

	public ArrayList<Country> getMyCountries() {
		return myCountries;
	}

	/**
	 * Sets the list of Countries for player 
	 *
	 * @param Countries list
	 */
	public void setMyCountries(ArrayList<Country> myCountries) {
		this.myCountries = myCountries;
	}

	public void armiesAssignedToCountries(Country country, int armiesCount) {
		if (this.getMyCountries().contains(country)) {
			if ((this.getArmyCount()) > 0 && this.getArmyCount() >= armiesCount) {
				country.setNoOfArmies(country.getNoOfArmies() + armiesCount);
				this.setArmyCount(this.getArmyCount() - armiesCount);
			} else {
				System.out.println("Insufficient number of armies.");
			}
		} else {
			System.out.println("This country is not owned by you!");
		}
	}

	public void additionOfCountry(Country country) {
		System.out.println(country+"country");
		this.myCountries.add(country);
	}

	@Override
	public String toString() {
		return "RiskPlayer [name=" + name + ", armyCount=" + armyCount + ", myCountries=" + myCountries + "]";

	}
}