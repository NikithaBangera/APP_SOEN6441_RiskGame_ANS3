package com.riskgame.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.RiskPlayer;
import com.riskgame.service.StartupPhase;

public class StartupPhaseTest {
    RiskPlayer player1, player2;
    StartupPhase startUpPhase;
    Country country; 
    GameMapGraph mapGraph = new GameMapGraph();
    
    
    public StartupPhaseTest() {
        startUpPhase = new StartupPhase();
        startUpPhase.setCountOfthePlayers(2);
        
        player1 = new RiskPlayer();
        startUpPhase.getPlayersList().add(player1);
        
        
        HashMap<String, Country> countrySet = new HashMap<String, Country>();
        country = new Country();
        country.setName("Morocco");
        countrySet.put(country.getName(), country);
        mapGraph.setCountrySet(countrySet);
        
//        player2.setName("Ram");
//        country.setName("India");
//        country.setNoOfArmies(10);
//        player2.setArmyCount(15);
        
        
    }
    
    @Test
    public void testAllocationArmy() {
        startUpPhase.allocationOfArmyToPlayers();
        assertEquals(40, player1.getArmyCount());
    }
    
    @Test
    public void testallocationOfArmyToCountriesInitially() {
    	startUpPhase.allocationOfArmyToCountriesInitially(mapGraph);
    	assertEquals(1, country.getNoOfArmies() );
    }
    
    public void testallocationOfRemainingArmyToCountries() {
    	
    }
}