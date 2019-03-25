package com.riskgame.controller;

import java.util.ArrayList;
import java.util.Iterator;

import com.riskgame.model.Player;

/**
 * RoundRobinScheduler class is to iterate the Player turns.
 * 
 * @author Anusha
 * @author Shiva
 *
 */
public class RoundRobinScheduler {
	
	/** List of Players */
	private ArrayList<Player> playersList;
	
	/** Iterator for players */
	private Iterator<Player> iterator;
	
	/**
	 * RoundRobinScheduler Constructor
	 * 
	 * @param playersList list of Players
	 */
	public RoundRobinScheduler(ArrayList<Player> playersList) {
		this.playersList = playersList;
		iterator = playersList.iterator();
	}
	
	/**
	 * Method to move on the turn to next player.
	 * 
	 * @return Turn of the player
	 */
	public Player nextTurn() {
		if(!iterator.hasNext()) {
			iterator = playersList.iterator();
		}
		return iterator.next();
	}
}