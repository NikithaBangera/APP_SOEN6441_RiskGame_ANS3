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
 * This class is a model stores the value associated to each player. It stores
 * player's name of String type, total armyCount of player as Integer type, and
 * the ArrayList of type Country which the player owns.
 * 
 * @author Shresthi Garg
 * @author Anusha
 *
 */
public class Player extends Observable {
	/** Name of the Player */
	private String name;

	/** Initial army count of the Player */
	private int armyCount = 0;

	/**
	 * Boolean to check the end place armies option
	 */
	private boolean endPlaceArmies;

	/** Variable to check whether the reinforcement is the first reinforcement */
	private boolean firstReinforcement;

	/** List of countries held by the Player */
	private ArrayList<Country> myCountries = new ArrayList<Country>();

	/**
	 * List of players card
	 */
	private HashMap<String, Integer> playersCardList = new HashMap<String, Integer>();

	/**
	 * Boolean variable to check if player wants to end attack phase
	 */
	private boolean completeAttack;

	/**
	 * value for the number of countries conquered by player
	 */
	private int conquerCountry;
	/**
	 * Boolean variable to check if the player has lost the game
	 */
	private boolean playerLostGame;

	/**
	 * Constructor to initialize the initial values
	 */
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
	 * gets the conquer country count
	 * 
	 * @return count of conquered country
	 */
	public int getConquerCountry() {
		return conquerCountry;
	}

	/**
	 * sets the conquered country count
	 * 
	 * @param conquerCountry conquered country
	 */
	public void setConquerCountry(int conquerCountry) {
		this.conquerCountry = conquerCountry;
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

	/**
	 * Method to get the players card list
	 * 
	 * @return playersCardList - card list of players
	 */
	public HashMap<String, Integer> getPlayersCardList() {
		return playersCardList;
	}

	/**
	 * Method to set the players card list
	 * 
	 * @param playersCardList - card list of players
	 */
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

	/**
	 * This method gets the list of the player's country names
	 * 
	 * @return list of country names
	 */
	public List<String> getPlayerCountryNames() {
		List<String> playerCountries = new ArrayList<String>();
		for (Country country : getMyCountries()) {
			playerCountries.add(country.getName());
		}
		return playerCountries;
	}

	/**
	 * This method gets the selected country of the players
	 * 
	 * @param countryName - The country name
	 * @return Country object
	 */
	public Country getSelectedCountry(String countryName) {
		for (Country country : getMyCountries()) {
			if (country.getName().equalsIgnoreCase(countryName)) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This method returns the boolean for the closure of placing armies
	 * 
	 * @return boolean
	 */
	public boolean isEndPlaceArmies() {
		return endPlaceArmies;
	}

	/**
	 * This method sets the value for the player's wish to end the place armies
	 * 
	 * @param endPlaceArmies - boolean value
	 */
	public void setEndPlaceArmies(boolean endPlaceArmies) {
		this.endPlaceArmies = endPlaceArmies;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to check if the phase is the first reinforcement phase
	 * 
	 * @return firstReinforcement - reinforcement phase
	 */
	public boolean isFirstReinforcement() {
		return firstReinforcement;
	}

	/**
	 * Method to set the first Reinforcement phase
	 * 
	 * @param firstReinforcement - first Reinforcement phase
	 */
	public void setFirstReinforcement(boolean firstReinforcement) {
		this.firstReinforcement = firstReinforcement;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to check if complete attack is checked
	 * 
	 * @return completeAttack - complete attack phase
	 */
	public boolean isCompleteAttack() {
		return completeAttack;
	}

	/**
	 * Method to set completeAttach in attack phase
	 * 
	 * @param completeAttack - complete attack phase
	 */
	public void setCompleteAttack(boolean completeAttack) {
		this.completeAttack = completeAttack;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to check if player has lost the game
	 * 
	 * @return playerLostGame - player lost the game
	 */
	public boolean isPlayerLostGame() {
		return playerLostGame;
	}

	/**
	 * Method to set the playerLostGame variable
	 * 
	 * @param playerLostGame - if player loses a game
	 */
	public void setPlayerLostGame(boolean playerLostGame) {
		this.playerLostGame = playerLostGame;
		setChanged();
		notifyObservers();
	}
}