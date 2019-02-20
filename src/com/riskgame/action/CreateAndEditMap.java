package com.riskgame.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAndEditMap {
	
	//private SavingMapIntoFile saveMap;
	//create a new map
	public void newMapCreation() throws Exception{
		System.out.println("\nChoose the below options to create a new map:");
		System.out.println("1. Enter Map name and Author name:");
		System.out.println("2. Add the continents:\n3. Delete a continent:");
		System.out.println("4. Add the countries:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Show the map's contents\n9. Save the map and exit:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option =0;
		try{
			option = Integer.parseInt(br.readLine());
		} 
	    catch (NumberFormatException e) 
		{ 
	    	System.out.println("\nPlease enter valid number");
	    	newMapCreation();
	    	return;
		}
		
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
		case 9:System.exit(0);
		default: System.out.println("Invalid option. Please choose the correct option.");
				 newMapCreation();
		
		}
	}

	private void createMapTag() throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter the image name of the map in below format:");
		System.out.println("Imagename.bmp");
		Pattern pattern = Pattern.compile("[a-z, A-Z]+.[bmp]+");
		String name = br.readLine();
		Matcher match = pattern.matcher(name.trim());	
		while(!match.matches())
		{
			System.out.println("\n Please enter valid image name ");
			name = br.readLine();
			match = pattern.matcher(name.trim());
		}
		String image = "Image = " + name.trim();
		
		System.out.println("Please enter the author name:");
		String author = "Author = " + br.readLine().trim();
		System.out.println("Please enter the map name in below format:");
		System.out.println("mapname.map");
		String filename =br.readLine();
		Pattern pattern_filename = Pattern.compile("[a-z, A-Z, 1-9]+.[map]+");
		Matcher match_filename = pattern_filename.matcher(filename.trim());	
		while(!match_filename.matches())
		{
			System.out.println("\n Please enter valid map name ");
			filename = br.readLine();
			match_filename = pattern_filename.matcher(filename.trim());
		}
		String fileName =filename.trim();
		String[] mapTagData = new String[3];
		mapTagData[0] = image;
		mapTagData[1] = author;	
		mapTagData[2] = fileName;
		SavingMapIntoFile saveMap=new SavingMapIntoFile();
		saveMap.saveMapTag(mapTagData);
		newMapCreation();
	}

		
	}


