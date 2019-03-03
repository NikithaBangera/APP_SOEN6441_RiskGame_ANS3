package com.riskgame.action.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.riskgame.action.CreateAndEditMap;
import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.gameplay.StartupPhase;

public class CreateAndEditMapTest {
	
	CreateAndEditMap createAndEditMap;
	Country country;
    Continent continent;
    GameMapGraph mapGraph = new GameMapGraph();
    ArrayList<String> adjacentCountries = new ArrayList<String>();
    
    public CreateAndEditMapTest() {
    	
    	
    	country = new Country();
        country.setName("India");
        adjacentCountries.add("Pakistan");
        adjacentCountries.add("Chaina");
        adjacentCountries.add("Srilanka");
        country.setAdjacentCountries(adjacentCountries);
        
        
        country = new Country();
        country.setName("Pakistan");
        adjacentCountries.add("India");
        adjacentCountries.add("Chaina");
        adjacentCountries.add("Canada");
        country.setAdjacentCountries(adjacentCountries);
        
        
        country = new Country();
        country.setName("Canada");
        adjacentCountries.add("Pakistan");
        adjacentCountries.add("Chaina");
        adjacentCountries.add("Srilanka");
        country.setAdjacentCountries(adjacentCountries);
       
    	
    }

	@Test
	void testisCountryInAdjacentCountryList() {
		//countrySet = country.getName();
		createAndEditMap.isCountryInAdjacentCountryList();
		
	}

}
