package com.riskgame.action;

import java.util.HashMap;
import com.riskgame.common.Continent;

public class GameMapGraph {
	public static HashMap<String, Continent> continentData;
	
	public void setContinent(HashMap<String, Continent> continent) {
		this.continentData = continent;
	}
	
	public HashMap<String, Continent> getContinent(){
		return continentData;
	}
	public void addContinent(Continent continent) {
		continentData.put(continent.getContinentName(), continent);
	}
}
