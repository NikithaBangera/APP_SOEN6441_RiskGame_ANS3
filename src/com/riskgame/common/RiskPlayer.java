package com.riskgame.common;

import java.util.ArrayList;

public class RiskPlayer {


	/** Name of the Player */
    private String name;
    
    /**Initial army count of the Player*/
    private int armyCount=0;
    
    /** List of countries held by the Player */
    private ArrayList<Country> myCountries = new ArrayList<Country>();

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

	public void armiesAssignedToCountries(Country country, int armiesCount) {
		if(this.getMyCountries().contains(country)) {
			if((this.getArmyCount()) > 0 && this.getArmyCount() >= armiesCount) {
				country.setNoOfArmies(country.getNoOfArmies() + armiesCount);
				this.setArmyCount(this.getArmyCount() - armiesCount);
			}
			else {
				System.out.println("Insufficient number of armies.");
			}
		}
		else {
			System.out.println("This country is not owned by you!");
		}
	}
	
	public void additionOfCountry(Country country) {
		this.myCountries.add(country);
	}

//	public void addingArmies(Country country, int number_armies) {
//		// TODO Auto-generated method stub
//		if(this.getArmyCount()>0 ) {
//			while(this.getArmyCount()>=number_armies) {
//    		if(!this.getMyCountries().contains(country)) {
//    			System.out.println("This country is not under your territory.");
//    		}
//    		else {
//    			country.setNoOfArmies(country.getNoOfArmies() + number_armies);
//    			this.setArmyCount(this.getArmyCount() - number_armies);
//    		}
//    	}}
//    	else {
//    		System.out.println("Sufficient number of armies not available.");
//    	}
//		
//	}
	
	@Override
	public String toString() {
		return "RiskPlayer [name=" + name + ", armyCount=" + armyCount + ", myCountries=" + myCountries + "]";
	}

}
