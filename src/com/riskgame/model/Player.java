package com.riskgame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

/**
 * This class stores the value associated to each player. It stores player's
 * name of String type, total armyCount of player as Integer type, and the
 * ArrayList of type Country which the player owns.
 * 
 * @author Shresthi Garg
 * @author Anusha
 *
 */
public class Player extends Observable{
	/** Name of the Player */
	private String name;

	/** Initial army count of the Player */
	private int armyCount = 0;
	
	private boolean endPlaceArmies;
	
	private boolean firstReinforcement;

	/** List of countries held by the Player */
	private ArrayList<Country> myCountries = new ArrayList<Country>();
	
	private HashMap<String, Integer> playersCardList = new HashMap<String, Integer>();

	public Player() {
		this.endPlaceArmies = false;
	
		 PlayerView playerView = new PlayerView(); 
		 DiceView diceView = new DiceView();
		 CardView cardView = new CardView(); 
		 this.addObserver(playerView);
		 this.addObserver(diceView); 
		 this.addObserver(cardView);
		 
	}
	
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
	 * @param name To set the name of the Player
	 */
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
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
	 * @param armyCount To set the army count of the player
	 */
	public void setArmyCount(int armyCount) {
		this.armyCount = armyCount;
		setChanged();
		notifyObservers();
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
	 * @param myCountries To set the list of the countries to player.
	 */
	public void setMyCountries(ArrayList<Country> myCountries) {
		this.myCountries = myCountries;
		setChanged();
		notifyObservers();
	}

	
	/**
	 * This method is used to add the country to the player's countries list.
	 * 
	 * @param country - The country that needs to be added.
	 */
	public void additionOfCountry(Country country) {

		this.myCountries.add(country);
	}

	public HashMap<String, Integer> getPlayersCardList() {
		return playersCardList;
	}

	public void setPlayersCardList(HashMap<String, Integer> playersCardList) {
		this.playersCardList = playersCardList;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", armyCount=" + armyCount + ", myCountries=" + myCountries
				+ ", playersCardList=" + playersCardList + "]";
	}

	public List<String> getPlayerCountryNames() {
		List<String> playerCountries = new ArrayList<String>();
		for (Country country : getMyCountries()) {
			playerCountries.add(country.getName());
		}
		return playerCountries;
	}

	public Country getSelectedCountry(String countryName) {
		for (Country country : getMyCountries()) {
			if (country.getName().equalsIgnoreCase(countryName)) {
				return country;
			}
		}
		return null;
	}

	public boolean isEndPlaceArmies() {
		return endPlaceArmies;
	}

	public void setEndPlaceArmies(boolean endPlaceArmies) {
		this.endPlaceArmies = endPlaceArmies;
		setChanged();
		notifyObservers();
	}

	public boolean isFirstReinforcement() {
		return firstReinforcement;
	}

	public void setFirstReinforcement(boolean firstReinforcement) {
		this.firstReinforcement = firstReinforcement;
	}

}