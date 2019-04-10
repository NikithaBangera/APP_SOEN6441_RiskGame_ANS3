package com.riskgame.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TournamentMapGraph {

	/** Treemap for inputPlayerDetails*/
	private Map<String, String> inputPlayerDetails = new TreeMap<String, String>();
	
	/**
	 * HashMap for number of games in tournament
	 */
	private Map<String, GameMapGraph> tournamentMapGraphs = new HashMap<String, GameMapGraph>();
	
	/**
	 * Number of turns per game
	 */
	private int gameTurns;

	/**
	 * Number of player strategies
	 */
	private int numberOfPlayers;
	
	/**
	 * Number of maps
	 */
	private int numberOfMaps;
	
	/**
	 * Number of games
	 */
	private int numberOfGames;
	
	/**
	 * Result of the tournament
	 */
	private Map<String, String> tournamentResult = new TreeMap<String, String>();
	
	/**
	 * Method to get player details from StartGameView class.
	 * 
	 * @return inputPlayerDetails
	 */
	public Map<String, String> getInputPlayerDetails() {
		return inputPlayerDetails;
	}
	
	/**
	 * Method to populate player details from the StartGameView.
	 * 
	 * @param inputPlayerDetails - the details of the player
	 */
	public void setInputPlayerDetails(Map<String, String> inputPlayerDetails) {
		this.inputPlayerDetails = inputPlayerDetails;
	}

	/**
	 * Method get the number of games
	 * @return tournamentMapGraphs - number of games
	 */
	public Map<String, GameMapGraph> getTournamentMapGraphs() {
		return tournamentMapGraphs;
	}

	/**
	 * Method to set the number of games
	 * @param tournamentMapGraphs - tournament map graph
	 */
	public void setTournamentMapGraphs(Map<String, GameMapGraph> tournamentMapGraphs) {
		this.tournamentMapGraphs = tournamentMapGraphs;
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
	 * Method to get number of players
	 * @return numberOfPlayers - number of players
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * Method to set the number of players
	 * @param numberOfPlayers - number of players
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Method to get the number of maps
	 * @return numberOfMaps - number of maps
	 */
	public int getNumberOfMaps() {
		return numberOfMaps;
	}

	/**
	 * Method to set number of maps
	 * @param numberOfMaps - number of maps
	 */
	public void setNumberOfMaps(int numberOfMaps) {
		this.numberOfMaps = numberOfMaps;
	}

	/**
	 * Method to get number of games
	 * @return numberOfGames - number of games
	 */
	public int getNumberOfGames() {
		return numberOfGames;
	}

	/**
	 * Method to set number of games
	 * @param numberOfGames - number of games
	 */
	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	/**
	 * Method to get the tournament result
	 * @return tournamentResult - result
	 */
	public Map<String, String> getTournamentResult() {
		return tournamentResult;
	}

	/**
	 * Method to set the tournament result
	 * @param tournamentResult - result
	 */
	public void setTournamentResult(Map<String, String> tournamentResult) {
		this.tournamentResult = tournamentResult;
	}
}
