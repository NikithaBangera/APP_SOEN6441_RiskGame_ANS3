package com.riskgame.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.riskgame.model.RiskPlayer;

public class RoundRobinScheduler {
	
	private ArrayList<RiskPlayer> playersList;
	private Iterator<RiskPlayer> iterator;
	
	public RoundRobinScheduler(ArrayList<RiskPlayer> playersList) {
		this.playersList = playersList;
		this.iterator = playersList.iterator();
	}
	
	public RiskPlayer nextTurn() {
		if(!this.iterator.hasNext()) {
			this.iterator = this.playersList.iterator();
		}
		return this.iterator.next();
	}
}