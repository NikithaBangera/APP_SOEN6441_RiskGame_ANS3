package com.riskgame.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.riskgame.model.RiskPlayer;

public class RoundRobinScheduler {
	
	private ArrayList<RiskPlayer> playersList;
	private Iterator<RiskPlayer> iterator;
	
	public RoundRobinScheduler(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
		iterator = playersList.iterator();
	}
	
	public RiskPlayer nextTurn() {
		if(!iterator.hasNext()) {
			iterator = playersList.iterator();
		}
		return iterator.next();
	}
}