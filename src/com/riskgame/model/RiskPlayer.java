package com.riskgame.common;

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
