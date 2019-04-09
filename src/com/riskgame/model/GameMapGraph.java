package com.riskgame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import com.riskgame.view.CardView;
import com.riskgame.view.DiceView;
import com.riskgame.view.PlayerView;

/**
 * This class is a model and stores the value associated to each map. It stores the content
 * required for building the map. It has variable continents which is a HashMap
 * containing continent name as the key of String type and the value is an
 * object of Continent containing information related to the continent. It has
 * variable adjacent countries which is a HashMap containing country object as
 * the key and the value is an ArrayList of type String containing names of the
 * adjacent countries.
 * 
 * @author Shresthi Garg
 * @author Nikitha
 */
public class GameMapGraph extends Observable implements Serializable {
	
	/** Maptag object */
	private MapTag mapTag;

	/** Count of the number of countries */
	private int countOfContinents = 0;

	/** ArrayList of Continent objects to store the continent details */
	private ArrayList<Continent> continents;

	/** Count of the number of countries */
	private int countOfCountries = 0;

	/** ArrayList of Country objects to store the country details */
	private ArrayList<Country> countries;

	/** Filename */
	private String filename;

	/** HashMap for set of countries */
	private HashMap<String, Country> countrySet;

	/** List of players */
	private ArrayList<Player> players = new ArrayList<Player>();

	/** Gamephase name */
	private String gamePhase;

	/** Exchange card count */
	private int exchangeCount;

	/** Refresh frame */
	private boolean refreshFrame;
	
	/** Dice view message */
	private String diceViewMessage;
	
	/** Treemap for inputPlayerDetails*/
	private Map<String, String> inputPlayerDetails = new TreeMap<String, String>();
	
	/**
	 * Number of turns per game
	 */
	private int gameTurns;
	
	/**
	 * Game Type - Tournament or normal
	 */
	private String gameType = "";
	
	/**
	 * Game Result of tournament
	 */
	private String gameResult;
	
	/**
	 * GameMapGraph Constructor
	 */
	public GameMapGraph() {
		this.players = new ArrayList<Player>();
		PlayerView playerView = new PlayerView();
		DiceView diceView = new DiceView();
		CardView cardView = new CardView();
		this.addObserver(playerView);
		this.addObserver(diceView);
		this.addObserver(cardView);
	}

	/**
	 * Its a GameMapGraph constructor
	 * 
	 * @param mapTag            - Map tag info
	 * @param countOfContinents - count of continents
	 * @param continents        - continent details
	 * @param countOfCountries  - count of countries
	 * @param countries         - countries detail
	 */
	public GameMapGraph(MapTag mapTag, int countOfContinents, ArrayList<Continent> continents, int countOfCountries,
			ArrayList<Country> countries) {
		this.mapTag = mapTag;
		this.countOfContinents = countOfContinents;
		this.continents = continents;
		this.countOfCountries = countOfCountries;
		this.countries = countries;
		this.players = new ArrayList<Player>();
		PlayerView playerView = new PlayerView();
		DiceView diceView = new DiceView();
		CardView cardView = new CardView();
		this.addObserver(playerView);
		this.addObserver(diceView);
		this.addObserver(cardView);
	}

	/**
	 * Get the Country details.
	 * 
	 * @return Country details
	 */
	public HashMap<String, Country> getCountrySet() {
		return countrySet;
	}

	/**
	 * Set the Country details.
	 * 
	 * @param countrySet To set the Country details
	 */
	public void setCountrySet(HashMap<String, Country> countrySet) {
		this.countrySet = countrySet;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the Maptag details.
	 * 
	 * @return Maptag details
	 */
	public MapTag getMapTag() {
		return mapTag;
	}

	/**
	 * Set the Maptag details.
	 * 
	 * @param mapTag To set the Maptag details
	 */
	public void setMapTag(MapTag mapTag) {
		this.mapTag = mapTag;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the count of continents.
	 * 
	 * @return total number of continents.
	 */
	public int getCountOfContinents() {
		return countOfContinents;
	}

	/**
	 * Set the total number of continents
	 * 
	 * @param countOfContinents To set the count of continents.
	 */
	public void setCountOfContinents(int countOfContinents) {
		this.countOfContinents = countOfContinents;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the list of continents.
	 * 
	 * @return list of continents.
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * Set the list of continents
	 * 
	 * @param continents To set the list of continents.
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the count of Countries.
	 * 
	 * @return total number of Countries.
	 */
	public int getCountOfCountries() {
		return countOfCountries;
	}

	/**
	 * Set the total number of Countries
	 * 
	 * @param countOfCountries To set the count of Countries.
	 */
	public void setCountOfCountries(int countOfCountries) {
		this.countOfCountries = countOfCountries;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the list of Countries.
	 * 
	 * @return list of Countries.
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * Set the list of Countries
	 * 
	 * @param countries To set the list of Countries.
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the name of the file.
	 * 
	 * @return File name.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Set the name of the file
	 * 
	 * @param filename To set the file name.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Gets the list of the Plyaers
	 * 
	 * @return the arrayList of the Player
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the list of the Players
	 * 
	 * @param players - the players array list
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the current game phase
	 * 
	 * @return the phase
	 */
	public String getGamePhase() {
		return gamePhase;
	}

	/**
	 * Sets the current game phase
	 * 
	 * @param gamePhase - the phase of the game
	 */
	public void setGamePhase(String gamePhase) {
		this.gamePhase = gamePhase;
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the exchange count
	 * 
	 * @return the exchange
	 */
	public int getExchangeCount() {
		return exchangeCount;
	}

	/**
	 * Sets the exchange count
	 * 
	 * @param exchangeCount - the exchange
	 */
	public void setExchangeCount(int exchangeCount) {
		this.exchangeCount = exchangeCount;
		setChanged();
		notifyObservers();
	}

	/**
	 * Checks whether frame is to refreshed
	 * 
	 * @return boolean value
	 */
	public boolean isRefreshFrame() {
		return refreshFrame;
	}

	/**
	 * Sets whether the frame is to be refreshed
	 * 
	 * @param refreshFrame - boolean value
	 */
	public void setRefreshFrame(boolean refreshFrame) {
		this.refreshFrame = refreshFrame;
		setChanged();
		notifyObservers();
	}

	/**
	 * Method to get the dice view message
	 * @return diceViewMessage - return the message for the dice view
	 */
	public String getDiceViewMessage() {
		return diceViewMessage;
	}

	/**
	 * Method to set the message for the dice view
	 * @param diceViewMessage - contains the dice view message
	 */
	public void setDiceViewMessage(String diceViewMessage) {
		this.diceViewMessage = diceViewMessage;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "GameMapGraph [mapTag=" + mapTag + ", countOfContinents=" + countOfContinents + ", continents="
				+ continents + ", countOfCountries=" + countOfCountries + ", countries=" + countries + ", filename="
				+ filename + ", countrySet=" + countrySet + "]";
	}

	/**
	 * Method to get player details from StartGameView class.
	 * 
	 * @return
	 */
	public Map<String, String> getInputPlayerDetails() {
		return inputPlayerDetails;
	}

	/**
	 * Method to populate player details from the StartGameView.
	 * 
	 * @param inputPlayerDetails
	 */
	public void setInputPlayerDetails(Map<String, String> inputPlayerDetails) {
		this.inputPlayerDetails = inputPlayerDetails;
	}

	/**
	 * Method to get the number of turns per game
	 * @return gameTurns - number of turns
	 */
	public int getGameTurns() {
		return gameTurns;
	}

	/**
	 * Method to set the number of turns per game
	 * @param gameTurns - number of turns
	 */
	public void setGameTurns(int gameTurns) {
		this.gameTurns = gameTurns;
	}

	/**
	 * Method to get the game type
	 * @return gameType - type of game
	 */
	public String getGameType() {
		return gameType;
	}

	/**
	 * Method to set the game type
	 * @param gameType - type of game
	 */
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	/**
	 * Method to get game result
	 * @return gameResult - result of the game
	 */
	public String getGameResult() {
		return gameResult;
	}

	/**
	 * Method ot set the result of game
	 * @param gameResult - result of game
	 */
	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
}
