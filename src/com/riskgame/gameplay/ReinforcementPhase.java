package com.riskgame.gameplay;

import java.util.TreeSet;

import com.riskgame.common.Continent;
import com.riskgame.common.RiskPlayer;
import com.riskgame.common.Territory;

public class ReinforcementPhase {
	private static TreeSet<Territory> territoriesList;
	
	public static int armiesToBeAssigned(RiskPlayer player, Continent continent) {
		territoriesList = continent.getTerritoriesInContinent();
		int countriesPerPlayer = player.getTerritories().size();
		int armiesAssignedPerPlayer = (int)Math.floor(countriesPerPlayer/3);
		
		
		if(countriesPerPlayer < 9) {
			armiesAssignedPerPlayer = 3;
		}
		
		if(isPlayerOwnsContinent(player)) {
			armiesAssignedPerPlayer = continent.getControlValue();
		}
		
		return armiesAssignedPerPlayer;
	}
	
	private static boolean isPlayerOwnsContinent(RiskPlayer player) {
		for(Territory territory : territoriesList) {
			if(!player.getTerritories().contains(territory))
				return false;
		}
		return true;
		
	}
}
