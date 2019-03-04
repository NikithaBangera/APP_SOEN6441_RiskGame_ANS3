package com.riskgame.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.riskgame.model.RiskPlayer;

/**
 * RoundRobinScheduler class is to iterate the Player turns.
 * 
 * @author
 *
 */
public class RoundRobinScheduler {
	
	/** List of Players */
	private ArrayList<RiskPlayer> playersList;
	
	/** Iterator of players */
	private Iterator<RiskPlayer> iterator;
	
	/**
	 * RoundRobinScheduler Constructor
	 * 
	 * @param playersList list of Players
	 */
	public RoundRobinScheduler(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
		iterator = playersList.iterator();
	}
	
	/**
	 * Method to move on the turn to next player.
	 * 
	 * @return Turn of the player
	 */
	public RiskPlayer nextTurn() {
		if(!iterator.hasNext()) {
			iterator = this.playersList.iterator();
		}
		return iterator.next();
	}
}