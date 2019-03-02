package com.riskgame.gameplay.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.RiskPlayer;
import com.riskgame.gameplay.StartupPhase;

public class StartupPhaseTest {
    RiskPlayer player1;
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
}