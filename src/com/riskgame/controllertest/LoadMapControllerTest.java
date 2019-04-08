package com.riskgame.controllertest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.riskgame.controller.LoadMapController;

/**
 * Test Class for ReadAndWriteMap Class
 * 
 * @author Anusha
 * @author Nikitha
 * @author Shresthi
 *
 */
public class LoadMapControllerTest {

	/** Object for ReadAndWriteMap Class */
	private static LoadMapController readAndWriteMap;

	/** String to hold the valid and invalid map files */
	private static String validMap, invalidMap, notConnectedMap, connectedMap;

	/** String to hold the valid and invalid country data */
	private static String validCountryData, invalidCountryData;

	/** String to hold the valid and invalid continent data */
	private static String validContinentData, invalidContinentData;

	/** String to hold the valid and invalid meta data */
	private static String validMetaData, invalidMetaData;

	/**
	 * ReadAndWriteMapTest Constructor for initial setup
	 */
	
	
	@BeforeAll
	public static void beforeAll() {
		readAndWriteMap = new LoadMapController();
		invalidMap = "resources/maps/Invalid.map";
		validMap = "resources/maps/Valid.map";
		notConnectedMap = "resources/maps/notConnected.map";
		connectedMap = "resources/maps/connected.map";
		
		validCountryData = "[Country] Libya,314,94,Northern Africa,Tunisia,Algeria,Egypt";
		invalidCountryData = "[Country]\n,133,266,Northern Ireland,Ireland";
		validContinentData = "[Continent]\nSouth America=2";
		invalidContinentData = "[Continent]\nAsia=";
		validMetaData = "[Map] Author=Robert Image Name=World.bmp Warn=yes Scroll=vertical Wrap=yes";
		invalidMetaData = "[Map]\nAuthor";
	}

	/**
	 * Test method for testing whether the map file is valid.
	 * 
	 * @throws IOException - the input output exception
	 */
	@Test
	public void isValidMap() throws IOException {
		assertTrue(readAndWriteMap.uploadMap(validMap));
	}

	/**
	 * Test method for testing whether the map file is invalid.
	 * 
	 * @throws IOException - the input output exception
	 */
	@Test
	public void isInvalid() throws IOException {
		assertFalse(readAndWriteMap.uploadMap(invalidMap));
	}

	/**
	 * Test method for validating the valid country data.
	 */
	@Test
	public void isValidCountryData() {
		assertTrue(readAndWriteMap.validatecountries(validCountryData));
	}

	/**
	 * Test method for validating the invalid country data.
	 */
	@Test
	public void isInvalidCountryData() {
		assertFalse(readAndWriteMap.validatecountries(invalidCountryData));
	}

	/**
	 * Test method for validating the valid continent data.
	 */
	@Test
	public void isValidContinentData() {
		assertTrue(readAndWriteMap.validatecontinents(validContinentData));
	}

	/**
	 * Test method for validating the invalid continent data.
	 */
	@Test
	public void isInvalidContinentData() {
		assertFalse(readAndWriteMap.validatecontinents(invalidContinentData));
	}

	/**
	 * Test method for validating the valid meta data.
	 */
	@Test
	public void isValidMetaData() {
		assertTrue(readAndWriteMap.validatemetadata(validMetaData));
	}

	/**
	 * Test method for validating the invalid meta data.
	 */
	@Test
	public void isInvalidMetaData() {
		assertFalse(readAndWriteMap.validatemetadata(invalidMetaData));
	}
	
	/**
	 * Test method for checking the connectivity of a loaded map 
	 * @throws IOException 
	 */
	@Test
	public void isConnectedMap() throws IOException {
		assertTrue(readAndWriteMap.uploadMap(connectedMap));
	}
	/**
	 * Test method for checking the connectivity of a loaded map 
	 * @throws IOException 
	 */
	
	@Test
	public void isNotConnectedMap() throws IOException {
		assertFalse(readAndWriteMap.uploadMap(notConnectedMap));
	}
}
