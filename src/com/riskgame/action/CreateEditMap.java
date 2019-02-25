package com.riskgame.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.regex.Pattern;

import com.riskgame.common.Continent;

public class CreateEditMap {
	public static ArrayList<String> mapTagData = new ArrayList<String>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	GameMapGraph gameMap = new GameMapGraph();

	// create a new map
	public void newMapCreation() throws Exception {
		System.out.println("\nChoose the below options to create a new map:");
		System.out.println("1. Enter details of Map Tag:");
		System.out.println("2. Add the continents:\n3. Delete a continent:");
		System.out.println("4. Add the countries:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Show the map's contents\n9. Save the map and exit:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(br.readLine());

		switch (option) {
		case 1:
			createMapTag();
			newMapCreation();
			break;

		case 2:
			System.out.println("Enter the number of Continents:");
			int numberOfContinents = Integer.parseInt(br.readLine());
		//	HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
			System.out.println("Enter the continent details in below format:");
			System.out.println("Continent name=Control Value");
			String[] contSplit;
			for (int i = 0; i < numberOfContinents; ++i) {
				String continentInfo = br.readLine();
				contSplit = continentInfo.split("=");
			//	continentDetails.put(conSplit[0], Integer.parseInt(contSplit[1]));
				/*
				 * if(gameMap.getContinent().containsKey((contSplit)[0])) {
				 * System.out.println("Continent "+ contSplit[0] + "is already present." ); --i;
				 * continue; }
				 */
			Continent continent = new Continent(contSplit[0],Integer.parseInt(contSplit[1]));
			gameMap.addContinent(continent);
			}
			break;

		case 3:
			System.out.println("Enter the continent to be removed from the map:");
			String deleteContinent = br.readLine();

			break;

		case 4:
			System.out.println("Enter the number of countries:");
			int numberOfCountries = Integer.parseInt(br.readLine());
			System.out.println("Enter the details of the countries in the below format:");
			System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
			for (int i = 0; i < numberOfCountries; i++) {
				String country = br.readLine();
				// String[] countryDetails = ;
			}
			break;

		case 5:
			System.out.println("Enter the country to be removed:");
			String deleteCountry = br.readLine();
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			System.out.println("\nPlease enter the file name to save map file.");
			String fileName = br.readLine();
			writeToFile(true, fileName);
			break;
		default:
			System.out.println("Invalid option. Please choose the correct option.");
			newMapCreation();

		}
	}

	private void createMapTag() throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the image name of the map:");
		String image = "image=" + br.readLine().trim();
		mapTagData.add(image);

		System.out.println("Do you need a wrap?Please mention yes or no:");
		String wrap = "wrap=" + br.readLine().trim();
		mapTagData.add(wrap);

		System.out.println("Please mention the type of scroll: vertical or horizonatal:");
		String scroll = "scroll=" + br.readLine().trim();
		mapTagData.add(scroll);

		System.out.println("Enter the author name:");
		String author = "author=" + br.readLine().trim();
		mapTagData.add(author);

		System.out.println("Please mention if warn is yes or no:");
		String warn = "warn=" + br.readLine().trim();
		mapTagData.add(warn);

		System.out.println("Added Map Tag Data!");

	}
	
	
	public boolean writeToFile(boolean fileFlag, String mapFileName) {
        File fileName;
        StringBuffer mapDetails = new StringBuffer();
        System.out.println("hello");
        if(fileFlag) {
            System.out.println("hi");
            fileName = new File(System.getProperty("user.dir")+"/src/maps/"+mapFileName+".map");
        }
        else {
            fileName = new File(System.getProperty("user.dir")+"/src/maps/"+ mapFileName+".map");
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            
            mapDetails.append("[Map]");
            mapDetails.append(System.lineSeparator());
            for(String line : mapTagData) {
                mapDetails.append(line);
                mapDetails.append(System.lineSeparator());
            }
            
            mapDetails.append("[Continent]");
            mapDetails.append(System.lineSeparator());
            Iterator<Map.Entry<String, Continent>> mapIterator = gameMap.continentData.entrySet().iterator();
            System.out.println("Hi");
            while(mapIterator.hasNext()) {
            	mapDetails.append(mapIterator.next().getValue().getContinentName() + "=" + mapIterator.next().getValue().getControlValue());
            	mapDetails.append(System.lineSeparator());
            }
            
            
            bw.write(mapDetails.toString());
            bw.close();
        }
        catch(Exception e) {
            System.out.println("Error while writing");
            return false;
        }
        return true;
    }
}
