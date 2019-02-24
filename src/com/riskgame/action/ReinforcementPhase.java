package com.riskgame.action;

import com.riskgame.shared.Continent;
import com.riskgame.shared.RiskPlayer;

public class ReinforcementPhase {

	public int initailArmiesToBeAssigned(RiskPlayer player, Continent continent) {
		int countriesPerPlayer = player.getCountries().length();
		int armiesAssignedPerPlayer = (int)Math.floor(countriesPerPlayer/3);
		
		if(countriesPerPlayer < 9) {
			armiesAssignedPerPlayer = 3;
		}
		return armiesAssignedPerPlayer;
		
		
	}
}