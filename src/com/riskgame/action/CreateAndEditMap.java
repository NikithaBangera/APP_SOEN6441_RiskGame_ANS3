package com.riskgame.action;

import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAndEditMap {
	
	SavingMapIntoFile saveMap=new SavingMapIntoFile();
	
	public String fileName;
	public String tag;
	
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
		case 1: tag = "[Map]";
				createMapTag();
				break;
				
		case 2: tag = "[Continents]";
				System.out.println("Please enter the number of Continents:");
				setContinentDetails();
				break;
				
		case 3:	System.out.println("Enter the continent to be removed from the map:");
				String deleteContinent = br.readLine();
				
			break;
			
			
		case 4: tag = "[Countries]";
				System.out.println("Enter the number of countries:");
				//setCountryDetails();
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

	public void createMapTag() throws Exception {
	
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
		fileName =filename.trim();
		String[] mapTagData = new String[3];
		mapTagData[0] = image;
		mapTagData[1] = author;	
		mapTagData[2] = fileName;
		saveMap.saveMapTag(mapTagData, fileName, tag);
		newMapCreation();
	}
	
	public void setContinentDetails() throws Exception
	{	
		int numberOfContinents = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			  numberOfContinents = Integer.parseInt(br.readLine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("\nPlease enter valid number");
			setContinentDetails();
			return;
		}
		System.out.println("Pleae enter continent details in below format:");
		System.out.println("Continent name=Control Value");
		Pattern pattern = Pattern.compile("[a-z, A-Z]+=[0-9]+");
		String[] continentAndControlVal = new String[numberOfContinents];
		
		for(int i=0; i<numberOfContinents; i++)
		{
			String continentName = br.readLine();
		    Matcher match = pattern.matcher(continentName.trim());
		    if(match.matches()) 
		    {
		    	String tempcontinentName = continentName.split("=")[0]; 
		    	continentAndControlVal[i] = continentName;
			    continue;
			} else {
			System.out.print(" Invalid continent details");
			--i;
			continue;
		    }
		}
		saveMap.saveMapTag(continentAndControlVal, fileName, tag);
	    newMapCreation();
	}
	
}	