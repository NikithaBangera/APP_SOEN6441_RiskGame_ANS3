package com.riskgame.strategy;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Cheater player whose reinforcement method doubles the army count on all its countries,
 * attack method automatically conquers all the neighbors of all its countries, doubles the 
 * number of armies on its countries that have neighbors that belong to other players.
 * @author 
 *
 */
public class Cheater implements PlayerStrategy{

	@Override
	public void placeArmies(GameMapGraph mapGraph, Player player, Country country) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void reinforcementPhase(Player player, GameMapGraph mapGraph, Country country, int reinforceArmyCount) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Player player, Country attacker, Country defender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Player player, Country attackerCountry,
			Country defenderCountry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fortificationPhase(GameMapGraph gameMapGraph, Player player, Country fromCountry, Country toCountry,
			int armiesCount) {
		// TODO Auto-generated method stub
		
	}

}
