package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.MapTag;
import com.riskgame.common.GameMapGraph;

public class CreateAndEditMap {
	
	MapTag maptag=new MapTag();
	Continent continent =new Continent();
	GameMapGraph gamemapgraph = new GameMapGraph();
	Country country = new Country();
	
	ArrayList<Country> countries = new ArrayList<Country>();
	public HashMap<String, Integer> countriesctrlval = new LinkedHashMap<String, Integer>();
		
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
			
		case 4: tag = "[Territories]";
				System.out.println("Enter the number of countries:");
				setCountryDetails();
				break;
						
						
		case 5:	System.out.println("Enter the country to be removed:"); 
				String deleteCountry = br.readLine();
				break;
		case 6: break;
				
		case 7: System.out.println("Please Enter the Countryname to delete the adjacency:");
				String name = br.readLine();
		
			deleteadjacency(name);
			break;
		case 8: break;
		case 9:	break;
		default: System.out.println("Invalid option. Please choose the correct option.");
				 newMapCreation();
		
		}
	}
	ArrayList<String> mapTagData = new ArrayList<String>();

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
		String image = "Image=" + name.trim();
		
		System.out.println("Please specify yes or no for warn");
		String warnanswer = br.readLine();
		while(!((warnanswer.equals("yes"))||(warnanswer.equals("Yes")))) 
		{
			System.out.println("Please enter yes or no ");
			warnanswer = br.readLine();
		}
		
		String warn = "Warn=" + warnanswer.trim();
		
		System.out.println("Please specify horizontal or vertical scroll");
		String scrollanswer = br.readLine();
		while(!((scrollanswer.equals("horizontal"))||(scrollanswer.equals("Horizontal"))))
		{
			System.out.println("Please enter valid response ");
			scrollanswer = br.readLine();
		}
		String scroll = "Scroll=" + scrollanswer.trim();
		
		System.out.println("Please specify yes or no for warp");
		String wrapanswer = br.readLine();
		while(!((wrapanswer.equals("no"))||(wrapanswer.equals("No"))))
		{
			System.out.println("Please enter yes or no ");
			wrapanswer = br.readLine();
		}
		String wrap = "Wrap=" + wrapanswer.trim();
	
		System.out.println("Please enter the author name:");
		String author = "Author=" + br.readLine().trim();
			
		mapTagData.add(image);
		mapTagData.add(author);
		mapTagData.add(warn);		
		mapTagData.add(wrap);
		mapTagData.add(scroll);
		
		maptag.setMapTagData(mapTagData);		
		System.out.println("Maptagdata added");
		newMapCreation();
	}

	//String[] continentAndControlValprint = new String[32];
	
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
		for(int i=0; i<numberOfContinents; i++)
		{
			String continentName = br.readLine();
		    Matcher match = pattern.matcher(continentName.trim());
		    if(match.matches()) 
		    {
		    	String tempcontinentName = continentName.split("=")[0]; 
		    	if(continent.getContinents().containsKey(tempcontinentName))
		    	{
		    		System.out.println("Entered Continent already exists.Please enter new details in below format");
		    		System.out.println("Continent name=Control Value");
		    		--i;
		    		continue;
		    	}
		    	
		    	countriesctrlval.put(continentName.split("=")[0], Integer.parseInt(continentName.split("=")[1]));
		    	continent.setContinents(countriesctrlval);
		    	continue;
			} else {
			System.out.println(" Invalid continent details\n");
			--i;
			continue;
		    }
		}
		System.out.println("Continent and Controlvalue details added");
		newMapCreation();
	}
	 
	//String[] continentAndCountryDetailsfinal = new String[32];
	public void setCountryDetails() throws Exception
	{
		int numberOfCountries=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			numberOfCountries = Integer.parseInt(br.readLine());
		}
		catch (NumberFormatException e) 
		{
			System.out.println("\nPlease enter valid number");
			setCountryDetails();
			return;
		}
		System.out.println("Please enter the details of the countries in the below format:");
		System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
		Pattern pattern = Pattern.compile("[a-z, A-Z]+,+[0-9]+,+[0-9]+,[a-z, A-Z]+");
		String[] continentAndCountryDetails = new String[numberOfCountries];
		
		
		for(int i=0; i<numberOfCountries; i++)
		{
			HashMap<String, List<String>> adjacentCountries = new HashMap<String, List<String>>();
			Country country = new Country();
			List<String> adjacent = new ArrayList<String>();
		  	
			String countryName = br.readLine();
			Matcher match = pattern.matcher(countryName.trim());
		    
			if(match.matches()) 
		    {
				String[] input = countryName.split(",");
				if(continent.getContinents().containsKey(input[3].trim()))
				{
					continentAndCountryDetails[i] = countryName;
            		country.setName(input[0].trim());
					country.setxValue(input[1].trim());
					country.setyValue(input[2].trim());
					country.setContinent(input[3].trim());
					
            	}
                
		    }else {
		    	System.out.println(" Invalid pattern");
		    	System.out.println("Please enter valid pattern\n");
			--i;
			continue;
	}   }
		country.setCountries(countries);
		System.out.println("Country details added");
		newMapCreation();
	}
  				
	public void saveDataToMap() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String filename =br.readLine();
		Pattern pattern_filename = Pattern.compile("[a-z, A-Z, 1-9]+.[map]+");
		Matcher match_filename = pattern_filename.matcher(filename.trim());	
		while(!match_filename.matches())
		{
			System.out.println("\n Please enter valid mapfile name ");
			filename = br.readLine();
			match_filename = pattern_filename.matcher(filename.trim());
		}
		fileName =filename.trim();
		
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "\\src\\com\\riskgame\\maps\\" + fileName);
		try {
		
			if(!file.exists()) 
			{		
				PrintWriter outputStream = new PrintWriter(file);
				
				outputStream.println("[Map]");
				for(int i =0;i<mapTagData.size();i++)
				{
					outputStream.println(mapTagData.get(i));
				}
				outputStream.println("\n\n\n\n\n");
				outputStream.println("[Continents]");
				
				for(Map.Entry<String, Integer> entry: countriesctrlval.entrySet())
				{
					outputStream.println(entry.getKey() + "=" + entry.getValue());
							
				}
				outputStream.println("\n\n\n\n\n");
				
				outputStream.close();
			}
		else { 
			System.out.println("Entered file name exists.Please enter new file name");
			saveDataToMap();
			}
		}
			
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
}


	


