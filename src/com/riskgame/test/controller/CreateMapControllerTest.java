package com.riskgame.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import com.riskgame.controller.CreateMapController;
import com.riskgame.model.Continent;

/**
 * Test class for CreateAndEditMap class
 * 
 * @author Shresthi
 * @author Shiva
 *
 */
public class CreateMapControllerTest {
	
	/** Object for CreateAndEditMap Class */
	private CreateMapController createAndEditMap; 
	
	/** Objects for Continent class */
	private Continent continent1, continent2, continent3;
	
	/** ArrayList to store the list of continents */
	private ArrayList<Continent> listofContinents;
	
	/**
	 * CreateAndEditMapTest Constructor for initial setup 
	 */
	public CreateMapControllerTest() {
		
		createAndEditMap = new CreateMapController();
		listofContinents = new ArrayList<Continent>();
		
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
	
	/**
	 * Test method for checking whether the continent is already defined
	 *  in the continent list
	 */
	@Test
	public void isContinentInContinentList() {
		assertTrue(createAndEditMap.alreadyDefinedContinent("North America"));
	}

	/**
	 * Test method for checking if the continent is not already defined in the list
	 */
	@Test
	public void isContinentNotInContinentList() {
		assertFalse(createAndEditMap.alreadyDefinedContinent("Europe"));
	}
}
