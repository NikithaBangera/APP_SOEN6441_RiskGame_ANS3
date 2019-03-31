package com.riskgame.strategy;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

/**
 * Benevolent player reinforces its weakest countries, does not attack,
 * fortifies by moving armies to weaker countries
 * @author 
 *
 */
public class Benevolent implements PlayerStrategy{

	@Override
	public void placeArmies(GameMapGraph mapGraph, String country, int armiesCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int reinforcementPhase(Player player, GameMapGraph mapGraph) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void attackPhase(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allOutAttack(GameMapGraph gameMapGraph, Country attackerCountry, Country defenderCountry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fortificationPhase(Country fromCountry, Country toCountry, int armiesCount) {
		// TODO Auto-generated method stub
		
	}

}
