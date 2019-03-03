package com.riskgame.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.riskgame.service.CreateAndEditMap;
import com.riskgame.model.Continent;

public class CreateAndEditMapTest {
    CreateAndEditMap createAndEditMap;
    Continent continent1, continent2, continent3;
    ArrayList<Continent> listofContinents = new ArrayList<Continent>();
    
    public CreateAndEditMapTest() {
        createAndEditMap = new CreateAndEditMap();
        
        continent1 = new Continent();
        continent1.setName("Asia");
        listofContinents.add(continent1);
        
        continent2 = new Continent();
        continent2.setName("Africa");
        listofContinents.add(continent2);
        
        continent3 = new Continent();
        continent3.setName("North America");
        listofContinents.add(continent3);
        
        createAndEditMap.setListOfContinents(listofContinents);
        
    }
    
    @Test
    public void isContinentInContinentList() {
        assertTrue(createAndEditMap.alreadyDefinedContinent("North America"));
    }

    @Test
    public void isContinentNotInContinentList() {
        assertFalse(createAndEditMap.alreadyDefinedContinent("Europe"));
    }
}

//public class CreateAndEditMapTest {
//
//CreateAndEditMap createAndEditMap;
//Country country, country1, country2;
//Continent continent;
//GameMapGraph mapGraph = new GameMapGraph();
//ArrayList<String> adjacentCountries = new ArrayList<String>();
//
//public CreateAndEditMapTest() {
//	
//	
//	country = new Country();
//    country.setName("India");
//    adjacentCountries.add("Pakistan");
//    adjacentCountries.add("China");
//    adjacentCountries.add("Srilanka");
//    country.setAdjacentCountries(adjacentCountries);
//    
//    
//    country1 = new Country();
//    country1.setName("Pakistan");
//    adjacentCountries.add("India");
//    adjacentCountries.add("China");
//    adjacentCountries.add("Canada");
//    country1.setAdjacentCountries(adjacentCountries);
//    
//   
//    country2 = new Country();
//    country2.setName("Canada");
//    adjacentCountries.add("Pakistan");
//    adjacentCountries.add("China");
//    adjacentCountries.add("Srilanka");
//    country2.setAdjacentCountries(adjacentCountries);
//   
//	
//}
//
//@Test
//void testcheckandupdateAdjacentCountries() {
//	ArrayList<String> testAdjacentCountry = new ArrayList<String>();
//	testAdjacentCountry.add("Pakistan");
//	testAdjacentCountry.add("China");
//	testAdjacentCountry.add("Srilanka");
//	//mapGraph.getCountrySet();
//	createAndEditMap.checkandupdateAdjacentCountries(country.getName(), country1.getName());
//	assertEquals(testAdjacentCountry, country.getAdjacentCountries());
//	
//}
//
//}


