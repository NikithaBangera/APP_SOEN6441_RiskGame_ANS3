package com.riskgame.action;

import com.riskgame.common.Continent;
import com.riskgame.common.Player;

public class ReinforcementPhase {

	public int initailArmiesToBeAssigned(Player player, Continent continent) {
		int countriesPerPlayer = player.getCountries().length();
		int armiesAssignedPerPlayer = (int)Math.floor(countriesPerPlayer/3);
		
		if(countriesPerPlayer < 9) {
			armiesAssignedPerPlayer = 3;
		}
		return armiesAssignedPerPlayer;
		
		
	}
}
