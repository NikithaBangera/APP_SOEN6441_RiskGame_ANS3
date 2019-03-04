package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.riskgame.service.ReadAndWriteMap;

public class ReadAndWriteMapTest {
	
	
	ReadAndWriteMap readAndWriteMap;
	
	private String validMap;
	
	private String invalidMap;
	
	private String validCountryData;
	
	private String invalidCountryData;
	
	private String validContinentData;
	
	private String invalidContinentData;
	
	private String validMetaData;
	
	private String invalidMetaData;
	
	
	public ReadAndWriteMapTest() {
		
		readAndWriteMap = new ReadAndWriteMap();
		validMap = "resources\\maps\\Valid.map";
		invalidMap = "resources\\maps\\Invalid.map"; 
		validCountryData = "[Country] Libya,314,94,Northern Africa,Tunisia,Algeria,Egypt";
		invalidCountryData = "[Country]\n,133,266,Northern Ireland,Ireland";
		validContinentData = "[Continent]\nSouth America=2"; 
		invalidContinentData ="[Continent]\nAsia="; 
		validMetaData ="[Map] Author=Robert Image Name=World.bmp Warn=yes Scroll=vertical Wrap=yes";
		invalidMetaData = "[Map]\nAuthor";
	}
	
	@Test
	public void isValidMap() throws IOException {
		assertTrue(readAndWriteMap.uploadMap(validMap));
	}
	
	@Test 
	public void isInvalid() throws IOException {
	  assertFalse(readAndWriteMap.uploadMap(invalidMap)); 
	}
	 
	@Test 
	public void isValidCountryData() {
	  assertTrue(readAndWriteMap.validatecountries(validCountryData)); 
	}
	  
	@Test 
	public void isValidContinentData() {
	  assertTrue(readAndWriteMap.validatecontinents(validContinentData)); 
	}
	  
	@Test 
	public void isValidMetaData() {
	  assertTrue(readAndWriteMap.validatemetadata(validMetaData)); 
	}
	  
	@Test 
	public void isInvalidMetaData() {
	  assertFalse(readAndWriteMap.validatemetadata(invalidMetaData)); 
	}
	  
	@Test 
	public void isInvalidContinentData() {
	  assertFalse(readAndWriteMap.validatecontinents(invalidContinentData)); 
	 }
	  
	@Test public void isInvalidCountryData() {
	  assertFalse(readAndWriteMap.validatecountries(invalidCountryData)); 
	}
	 
	 
	 
	 
	
}
