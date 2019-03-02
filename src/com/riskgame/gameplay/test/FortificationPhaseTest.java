package com.riskgame.gameplay.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.riskgame.common.Country;
import com.riskgame.gameplay.FortificationPhase;

public class FortificationPhaseTest {
    Country country, toCountry, fromCountry, toCountry1;
    //String country;
    FortificationPhase fortificationPhase;
    
    public FortificationPhaseTest() {
        fortificationPhase = new FortificationPhase();
        ArrayList<String> adjacentCountries = new ArrayList<String>();
        
        country = new Country();
        country.setName("India");
        adjacentCountries.add("India");
        
        country = new Country();
        country.setName("Nepal");
        adjacentCountries.add("Nepal");
        
        country = new Country();
        country.setName("Sri Lanka");
        adjacentCountries.add("Sri Lanka");
        
        country = new Country();
        country.setName("China");
        adjacentCountries.add("China");
        
        fromCountry = new Country();
        fromCountry.setName("Bangladesh");
        fromCountry.setNoOfArmies(8);
        
        toCountry = new Country();
        toCountry.setName("India");
        toCountry.setNoOfArmies(4);
        
        toCountry1 = new Country();
        toCountry1.setName("Canada");
        toCountry1.setNoOfArmies(2);
        fromCountry.setAdjacentCountries(adjacentCountries);
        
    }
    
    @Test
    public void isFortificationComplete() {
        fortificationPhase.moveArmies(fromCountry, toCountry, 2);
        assertEquals(6, fromCountry.getNoOfArmies());
        assertEquals(6, toCountry.getNoOfArmies());
    }
    
    @Test
    public void isFortificationNotComplete() {
        fortificationPhase.moveArmies(fromCountry, toCountry1, 2);
        assertEquals(8, fromCountry.getNoOfArmies());
        assertEquals(2, toCountry1.getNoOfArmies());
    }
}