package com.riskgame.action;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadMap {
	
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
