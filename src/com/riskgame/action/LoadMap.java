package com.riskgame.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.MapTag;

public class LoadMap {
	GameMapGraph gameMapGraph = new GameMapGraph();
	
	public void loadMap(String fileName) throws Exception {
		boolean isMapValid = false, isContinentValid = false, isTerritoryValid = false;
		FileReader file = new FileReader(fileName);
		BufferedReader fileData = new BufferedReader(file);
	
		isMapValid = validateMapTag(fileData);
		isContinentValid = isContinentFormatValid(fileData);
		isTerritoryValid = isTerritoriesFormatValid(fileData);
		
		if(!isContinentFormatValid(fileData)) {
			System.out.println("Invalid Continent Information");
		}
		
		if(!isTerritoriesFormatValid(fileData)) {
			System.out.println("Invalid Territories Information");
		}
		
		if(isMapValid && isContinentValid && isTerritoryValid) {
			readMapFromFile(fileData);
		}
		
		
		
		
	}

	private void readMapFromFile(BufferedReader fileData) throws Exception {
		populateMapBuildInfo(fileData);
		populateContinents(fileData);
		populateCountries(fileData);
	}

	private void populateCountries(BufferedReader fileData) throws Exception {
		String newLine = "";
		while((newLine = fileData.readLine()) != null) {
			if(newLine.contains("[Territories]")) {
				while((newLine = fileData.readLine()) != null) {
					if(!newLine.startsWith("[")) {
						String[] newCountry = newLine.split(",",5);
						Country country = new Country();
						country.setName(newCountry[0]);
						country.setxValue(newCountry[1]);
						country.setyValue(newCountry[2]);
						String[] adjacentCountries = newCountry[4].split(",");
						
				//		country.getAdjacentCountries().addAll(Arrays.asList(adjacentCountries));
					}
				}
			}
		}
	}
				

	private void populateContinents(BufferedReader fileData) throws Exception{
		String newLine = "";
		while((newLine = fileData.readLine()) != null) {
			if(newLine.contains("[Continents]")) {
				while((newLine = fileData.readLine()) != null) {
					if(!newLine.startsWith("[")) {
						Continent continent = new Continent();
						continent.setName(newLine.split("=")[0]);
						continent.setControl_value(Integer.parseInt(newLine.split("=")[1]));
						gameMapGraph.getContinents().put(continent.getContinentName(), continent);
					}
					else {
						break;
					}
				}
			}
		}
	}

	private void populateMapBuildInfo(BufferedReader fileData) throws Exception {
		String newLine = "";
		while((newLine = fileData.readLine()) != null){
			if(newLine.contains("[Map]")) {
				MapTag mapTag = new MapTag();
				while((newLine = fileData.readLine()) != null){
					if(!newLine.startsWith("[")) {
						mapTag.getMapTagData().add(newLine);
					}
					else {
						break;
					}
				}
			}
		}
	}

	private boolean isTerritoriesFormatValid(BufferedReader fileData) throws Exception {
		String newLine = "";
		boolean territoryFormat = true;
		String regex = "[a-zA-Z]+,[0-9]+,[0-9]+,[a-zA-Z]+,(,[a-zA-Z]+)*";
		while((newLine = fileData.readLine()) != null) {
			if(newLine.contains("[Territories]")) {
				while((newLine = fileData.readLine()) != null) {
					if(!newLine.startsWith("[") && !newLine.matches(regex)) {
						territoryFormat = false;
						break;
					}
				}
			}
		}
		return territoryFormat;
	}

	private boolean isContinentFormatValid(BufferedReader fileData) throws Exception {
		String newLine = "";
		boolean continentFormat = true;
		String regex = "[a-zA-Z]+=[0-9]";
		while((newLine = fileData.readLine()) != null) {
			if(newLine.contains("[Continents]")) {
				while((newLine = fileData.readLine()) != null) {
					if(!newLine.startsWith("[") && !newLine.matches(regex)) {
						continentFormat = false;
						break;
					}
				}
			}
		}
		return continentFormat;
	}

	private boolean validateMapTag(BufferedReader fileData) throws Exception {
		boolean isMapPresent = false, isContinentPresent = false, isTerritoriesPresent = false;
		String newLine = "";
		
		while((newLine = fileData.readLine()) != null) {
			if(newLine.contains("[Map]")) {
				isMapPresent = true;
			}
			else if(newLine.contains("[Continents]")) {
				isContinentPresent = true;
			}
			else if(newLine.contains("[Territories]")) {
				isTerritoriesPresent = true;
			}
		}
		if(!isMapPresent) {System.out.println("[Map] is not present.");}
		if(!isContinentPresent) {System.out.println("[Continents] is not present.");}
		if(!isTerritoriesPresent) {System.out.println("[Territories] is not present.");}
		
		return isMapPresent && isContinentPresent && isTerritoriesPresent;
	}

}
