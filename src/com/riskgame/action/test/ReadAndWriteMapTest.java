package com.riskgame.action.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.riskgame.action.ReadAndWriteMap;

class ReadAndWriteMapTest {
	
	ReadAndWriteMap readAndWriteMap;
	
	private String validMap;
	
	private String invalidMap;
	
	private String validCountryData;
	
	private String invalidCountryData;
	
	private String validContinentData;
	
	
	public ReadAndWriteMapTest() {
		readAndWriteMap = new ReadAndWriteMap();
		validMap = "src/com/riskgame/maps/Valid.map";
		invalidMap = "src/com/riskgame/maps/Invalid.map";
		validCountryData = "Libya,314,94,Northern Africa,Tunisia,Algeria,Egypt";
		invalidCountryData = "133,266,Northern Ireland,Ireland";
		validContinentData = "Asia=3";
		
	}
	
	@Test
	public void isValidMap() throws IOException {
		assertTrue(readAndWriteMap.uploadMap(validMap));
	}
	
	@Test
	public void isInvalid() throws IOException {
		assertFalse(readAndWriteMap.uploadMap(invalidMap));
	}
	
	
	@Test public void isValidCountryData() {
		assertTrue(readAndWriteMap.validatecountries(validCountryData)); 
	}
	 
	@Test public void isValidContinentData() {
		assertTrue(readAndWriteMap.validatecontinents(validContinentData));
	}
	
	/*
	 * @Test public void isInvalidCountryData() {
	 * assertFalse(readAndWriteMap.validatecountries(invalidCountryData)); }
	 */
	
}
