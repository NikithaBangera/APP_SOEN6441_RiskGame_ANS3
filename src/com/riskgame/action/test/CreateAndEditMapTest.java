package com.riskgame.action.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.riskgame.action.CreateAndEditMap;
import com.riskgame.common.Continent;

class CreateAndEditMapTest {
	CreateAndEditMap createAndEditMap; 
	Continent continent1, continent2, continent3, continent4;
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
		
		continent4 = new Continent();
		continent4.setName("Europe");
		
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
