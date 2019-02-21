package com.riskgame.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CreateAndEditMap {
	
	private SavingMapIntoFile saveMap;
	//create a new map
	public void newMapCreation() throws Exception{
		System.out.println("\nChoose the below options to create a new map:");
		System.out.println("1. Enter details of Map Tag:");
		System.out.println("2. Add the continents:\n3. Delete a continent:");
		System.out.println("4. Add the countries:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Show the map's contents\n9. Save the map and exit:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(br.readLine());
		
		switch(option)
		{
		case 1: createMapTag();
				break;
				
		case 2: System.out.println("Enter the number of Continents:");
				int numberOfContinents = Integer.parseInt(br.readLine());
				HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
				System.out.println("Enter the continent details in below format:");
				System.out.println("Continent name = Control Value");
				for(int i=0; i<numberOfContinents; i++) {
					String continent = br.readLine();
					String[] split = continent.split("=");
					continentDetails.put(split[0], Integer.parseInt(split[1]));
				}
				
				
				
				
			break;
		
		case 3:	System.out.println("Enter the continent to be removed from the map:");
				String deleteContinent = br.readLine();
				
			break;
			
			
		case 4: System.out.println("Enter the number of countries:");
				int numberOfCountries = Integer.parseInt(br.readLine());
				System.out.println("Enter the details of the countries in the below format:");
				System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
				for(int i=0; i<numberOfCountries; i++) {
					String country = br.readLine();
				//	String[] countryDetails = ;
				}
			break;
						
						
		case 5:	System.out.println("Enter the country to be removed:"); 
				String deleteCountry = br.readLine();
			break;
		case 6: break;
		case 7: break;
		case 8: break;
		case 9: System.exit(0);
		default: System.out.println("Invalid option. Please choose the correct option.");
				 newMapCreation();
		
		}
	}

	private void createMapTag() throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the image name of the map:");
		String image = "Image = " + br.readLine().trim();
		
		System.out.println("Do you need a wrap?Please mention yes or no:");
		String wrap = "Wrap = " + br.readLine().trim();
 		
		System.out.println("Please mention the type of scroll: vertical or horizonatal:");
		String scroll = "Scroll = " + br.readLine().trim();
		
		System.out.println("Enter the author name:");
		String author = "Author = " + br.readLine().trim();
		
		System.out.println("Please mention if warn is yes or no:");
		String warn =  "Warn = " + br.readLine().trim();
 		
		String[] mapTagData = new String[5];
		mapTagData[0] = image;
		mapTagData[1] = wrap;
		mapTagData[2] = scroll;
		mapTagData[3] = author;
		mapTagData[4] = warn;
		
		saveMap.getMapTag(mapTagData);
	}
}

