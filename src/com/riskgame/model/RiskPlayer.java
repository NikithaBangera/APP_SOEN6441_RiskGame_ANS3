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
	/** Name of the Player */
	private String name;

	/** Initial army count of the Player */
	private int armyCount = 0;

	/** List of countries held by the Player */
	private ArrayList<Country> myCountries= new ArrayList<Country>();

	/**
	 * Get the Player name.
	 * 
	 * @return Name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the Player name.
	 * 
	 * @param name
	 *            To set the name of the Player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the army count of the player.
	 * 
	 * @return Army count
	 */
	public int getArmyCount() {
		return armyCount;
	}

	/**
	 * Set the army count of the player.
	 * 
	 * @param armyCount
	 *            To set the army count of the player
	 */
	public void setArmyCount(int armyCount) {
		this.armyCount = armyCount;
	}

	/**
	 * Get the list of the countries assigned to player.
	 * 
	 * @return Countries list.
	 */
	public ArrayList<Country> getMyCountries() {
		return myCountries;
	}

	/**
	 * Set the list of the countries to player.
	 * 
	 * @param myCountries
	 *            To set the list of the countries to player.
	 */
	public void setMyCountries(ArrayList<Country> myCountries) {
		this.myCountries = myCountries;
	}

	/**
	 * This method is used to assign armies to the Countries. It checks the
	 * available army and assigns the army to the requested country
	 * 
	 * @param country
	 * @param armiesCount
	 */
	public void armiesAssignedToCountries(Country country, int armiesCount) {
		if (this.getMyCountries().contains(country)) {
			if ((this.getArmyCount()) > 0 && this.getArmyCount() >= armiesCount) {
				country.setNoOfArmies(country.getNoOfArmies() + armiesCount);
				this.setArmyCount(this.getArmyCount() - armiesCount);
			} else {
				System.out.println("Insufficient number of armies.\n");
			}
		} else {
			System.out.println("This country is not owned by you!\n");
		}
	}

	/**
	 * This method is used to add the country to the player's countries list.
	 * 
	 * @param country
	 */
	public void additionOfCountry(Country country) {

		this.myCountries.add(country);
	}

	@Override
	public String toString() {
		return "RiskPlayer [name=" + name + ", armyCount=" + armyCount + ", myCountries=" + myCountries + "]";

	}
}