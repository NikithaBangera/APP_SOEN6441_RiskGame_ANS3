package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.MapTag;
import com.riskgame.common.GameMapGraph;

/**
 * CreateAndEditMap class provides the functionality for creating a 
 * new map according the risk game rules.
 * 
 * @author Sumeetha Kasulabad
 * 
 */

public class CreateAndEditMap {
	
	/** Variable for MapTag object */
	MapTag maptag=new MapTag();
	
	/** Variable for Continent object */
	Continent continent =new Continent();
	
	/** Variable for GameMapGraph object */
	GameMapGraph gamemapgraph = new GameMapGraph();
	
	/** Variable for Country object */
	Country country = new Country();
	
	ArrayList<Country> countries = new ArrayList<Country>();
	ArrayList<String> mapTagData = new ArrayList<String>();
	public HashMap<String, Integer> countriesctrlval = new LinkedHashMap<String, Integer>();
	public String fileName;
	public String tag;
	
	public void newMapCreation() throws Exception{
		System.out.println("\nChoose the below options to create a new map:");
		System.out.println("1. Enter Map Tag Data:");
		System.out.println("2. Add the continents:\n3. Delete a continent:");
		System.out.println("4. Add the countries:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Show map contents\n9. Save the map and exit:");
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
				
		case 2: System.out.println("Please enter the number of Continents:");
				setContinentDetails();
				break;
				
		case 3:	System.out.println("Enter the continent to be removed from the map:");
				String continent = br.readLine();
				deleteContinent(continent);
				break;
			
		case 4: System.out.println("Enter the number of countries:");
				setCountryDetails();
				break;
						
		case 5:	System.out.println("Enter the country to be removed:");
				String country = br.readLine();
				deleteCountry(country);
				break;
		
		case 6: System.out.println("Please enter the name of two countries to be connected in below format ");
				System.out.println("Countryname1,Countryname2");
				setCountryAdjacency();
				break;
				
		case 7: System.out.println("Please enter the name of two countries (in below format) to be disconnected");
				System.out.println("Countryname1,Countryname2");
				deleteAdjacency();
				break;
				
		case 8: printMapDetails();
				newMapCreation();
				break;
		
		case 9:	System.out.println("\nPlease enter the mapfile name to save the data in below format.");
	        	System.out.println("mapname.map");
				saveDataToMap();
				System.out.println("Do you want to play the game : yes or no");
				boolean gamevalue = false;
				String gameanswer =	br.readLine();
				if(gameanswer.equals("no") || gameanswer.equals("No"))
				{
					gamevalue = true;
					newMapCreation();
				}
				else {
					gamevalue = false;
				}
				break;
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
		String image = "image=" + name.trim();
		
		System.out.println("Please specify yes or no for warn");
		String warnanswer = br.readLine();
		while(!((warnanswer.equals("yes"))||(warnanswer.equals("Yes")))) 
		{
			System.out.println("Please enter yes or no ");
			warnanswer = br.readLine();
		}
		
		String warn = "warn=" + warnanswer.trim();
		
		System.out.println("Please specify horizontal or vertical scroll");
		String scrollanswer = br.readLine();
		while(!((scrollanswer.equals("horizontal"))||(scrollanswer.equals("Horizontal"))))
		{
			System.out.println("Please enter valid response ");
			scrollanswer = br.readLine();
		}
		String scroll = "scroll=" + scrollanswer.trim();
		
		System.out.println("Please specify yes or no for wrap");
		String wrapanswer = br.readLine();
		while(!((wrapanswer.equals("no"))||(wrapanswer.equals("No"))))
		{
			System.out.println("Please enter yes or no ");
			wrapanswer = br.readLine();
		}
		String wrap = "wrap=" + wrapanswer.trim();
	
		System.out.println("Please enter the author name:");
		String author = "author=" + br.readLine().trim();
			
		mapTagData.add(image);
		mapTagData.add(author);
		mapTagData.add(warn);		
		mapTagData.add(wrap);
		mapTagData.add(scroll);
		
		maptag.setMapTagData(mapTagData);		
		System.out.println("\nMaptagdata added");
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
		System.out.println("\nContinent and Controlvalue details added");
		newMapCreation();
	}
	
	
  	
	public void setCountryDetails() throws Exception
	{
	   if(!(continent.getContinents().isEmpty()))
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
			if ((Integer.parseInt(input[1]) < 400) && (Integer.parseInt(input[2]) < 400))
			{
				if(continent.getContinents().containsKey(input[3].trim()))
				{
					continentAndCountryDetails[i] = countryName;
            		country.setName(input[0].trim());
					country.setxValue(input[1].trim());
					country.setyValue(input[2].trim());
					country.setContinent(input[3].trim());
					for (int j = 4; j < input.length; j++) {
            	  		adjacent.add(input[j]);
					}    
            	  	adjacentCountries.put(input[0].trim(), adjacent);
            	  	country.setAdjacCountries(adjacentCountries);
            	  	countries.add(country);
            	}
                else {
                	System.out.println("continent " + input[3].trim() + "is not available in current continents list");
                	System.out.println("Please enter valid continent name\n");
                	--i;
                	continue;}
		    }else {
				System.out.println("Please enter valid X,Y values");
				--i;
				continue;
				}}			
			else {
		    	System.out.println(" Invalid pattern");
		    	System.out.println("Please enter valid pattern\n");
			--i;
			continue;
	}   }
		country.setCountries(countries);
		System.out.println("\nCountry details added");
		newMapCreation();
			   }
	   else {
		   System.out.println("Please enter continent to add countries");
		   newMapCreation();
	   }
	}
   
	
	public void setCountryAdjacency() throws Exception
	{
	Pattern pattern =  Pattern.compile("[a-z, A-Z]+,+[a-z, A-Z]+");
	String country1,country2;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String countrynames = br.readLine();
	Matcher match = pattern.matcher(countrynames.trim());
	if(match.matches()) 
	{			
		country1 = countrynames.split(",")[0]; 
		country2 = countrynames.split(",")[1]; 
		if(country.getCountries()!=null)
        {		
			if(!country1.equals(country2))
			{
				
    		   if(isCountry1Exists && isCountry2Exists)
			   {
				 for(Country c:country.getCountries())
				 {
					 if(c.getAdjacCountries().containsKey(country1)) 
					 { c.getAdjacCountries().get(country1).add(country2);}
					 else if(c.getAdjacCountries().containsKey(country2)) 
	    			   {c.getAdjacCountries().get(country2).add(country1);}
    			  }
				 System.out.println("Adjacency created between " +country1.toUpperCase() + " and " +country2.toUpperCase());
    		   }
			   else
			   { System.out.println("Entered countries not exists in the map ");}
			}
			else
			{
	   			System.out.println("Please enter valid data");
				setCountryAdjacency();
			}
    	}else {
    		System.out.println("Please add countries to create adjacency ");
    	}
	}
	else 
	{
	    System.out.println("Please enter in valid format : ");
	    setCountryAdjacency();
	}
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
				
				outputStream.println("[map]");
				for(int i =0;i<mapTagData.size();i++)
				{
					outputStream.println(mapTagData.get(i));
				}
				outputStream.println("\n\n");
				outputStream.println("[continents]");
				
				for(Map.Entry<String, Integer> entry: countriesctrlval.entrySet())
				{
					outputStream.println(entry.getKey() + "=" + entry.getValue());
							
				}
				outputStream.println("\n");
				outputStream.println("[territories]");
				for(Country c:country.getCountries()) {
					String format = c.getAdjacCountries().get(c.getName()).toString();
					String output =c.getName() +","+c.getxValue()+","+c.getyValue()+","+c.getContinent()+","+format.substring(1,format.length()-1);
					outputStream.println(output);
				}
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
	
	public void deleteAdjacency() throws Exception
	{
	   Pattern pattern = Pattern.compile("[a-z, A-Z]+,+[a-z, A-Z]+");

	   String country1;
	   String country2;
	   //String countryListBeforeDelete;
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   String countrynames = br.readLine();
	   Matcher match = pattern.matcher(countrynames.trim());
	   if (match.matches())
	   {
	      boolean isAnAdjacentCountry1 = false;
	      boolean isAnAdjacentCountry2 = false;
	      country1 = countrynames.split(",")[0];
	      country2 = countrynames.split(",")[1];
	      if (country.getCountries() != null)
	      {
	         for (Iterator<Country> iterator = country.getCountries().iterator(); iterator.hasNext(); )
	         {
	            Country index = iterator.next();
	            if (index.getAdjacCountries().containsKey(country1) && index.getAdjacCountries().get(country1).contains(country2))
	            {
	               isAnAdjacentCountry1 = true;

	               index.getAdjacCountries().get(country1).remove(country2);
	            }

	            else if (index.getAdjacCountries().containsKey(country2) && index.getAdjacCountries().get(country2).contains(country1))
	            {
	               isAnAdjacentCountry2 = true;
	               index.getAdjacCountries().get(country2).remove(country1);
	            }
	         }
	         if ((isAnAdjacentCountry1 && isAnAdjacentCountry2))
	         {
	            System.out.println("Adjacency deleted between " + country1.toUpperCase() + " and " + country2.toUpperCase() );
	         }
	         else
	            System.out.println("An edge does not exist between " + country1.toUpperCase() + " and " + country2.toUpperCase() + " in the map.");
	         	
	      }
	      else
	         System.out.println("The countries "+country1.toUpperCase()+" and " + country2.toUpperCase() +" specified do not exist in the map");
	   }
	   else
	   {
	      System.out.println("Please enter valid data");
	      deleteAdjacency();
	   }
	   newMapCreation();
	}
	
	
	public void deleteContinent(String value) throws Exception
	{
	   boolean exists = false;
	   if (country.getCountries() != null)
	   {
	      for (Iterator<Country> iterator = country.getCountries().iterator(); iterator.hasNext(); )
	      {
	         Country index = iterator.next();
	         if (index.getContinent().equals(value))
	         {
	            exists = true;
	            break;
	         }
	         else
	            exists = false;
	      }
	   }

	   if (exists)
	      System.out.println("Cannot delete continent " + value.toUpperCase() + " since it is connected to a country in the map");
	   else if (continent.getContinents().containsKey(value))
	   {
	      continent.getContinents().remove(value);
	      System.out.println("The continent " + value.toUpperCase() + " is deleted from the map.");
	   }
	   else
	   {
	      System.out.println("The continent " + value.toUpperCase() + " does not exist in the map.");
	   }
	   newMapCreation();
	}
	
	
	public void deleteCountry(String value) throws Exception
	{
	   boolean countryExists = false;
	   boolean edgeExists = false;
	   if (country.getCountries() != null)
	   {
	      for (Iterator<Country> iterator = country.getCountries().iterator(); iterator.hasNext(); )
	      {
	         Country index = iterator.next();
	         for (int i = 0; i < index.getAdjacCountries().size(); i++)
	         {
	            if (index.getAdjacCountries().get(index.getName()).contains(value))
	            {
	               edgeExists = true;
	               break;
	            }
	         }
	      }
	   }

	   if (edgeExists)
	   {
	      System.out.println("Cannot delete country " + value.toUpperCase() + " since it is connected to an edge in the map");
	      countryExists = true;
	   }

	   else
	   {
	      if (country.getCountries() != null)
	      {
	         for (Iterator<Country> iterator = country.getCountries().iterator(); iterator.hasNext(); )
	         {
	            Country index = iterator.next();
	            if (index.getName().equals(value))
	            {
	               countryExists = true;
	               iterator.remove();
	               System.out.println("The country " + value.toUpperCase() + " is deleted from Map.");
	               break;
	            }
	         }
	      }
	   }
	   newMapCreation();
	}
	
	public void printMapDetails() 
	{
	 if(maptag.getMapTagData().size() > 0)
	 {
		System.out.println("Entered map tag data :");
		System.out.println("----------------------");
		for (String maptag : maptag.getMapTagData())
		{
			System.out.println(maptag);
		}
	 }
	 if(continent.getContinents().size() > 0)
	 {
		System.out.println("\n");
		System.out.println("Entered continent and control value details :");
		System.out.println("---------------------------------------------");
		for(Map.Entry<String, Integer> entry: countriesctrlval.entrySet())
		{
			System.out.println("Continent name :  " + entry.getKey() + " , Control value : " + entry.getValue());
		}
	 }
	 if(country.getCountries() != null)
	 {
		System.out.println("\n");
	   	System.out.println("Entered countries and their adjacent countries : ");
	   	System.out.println("--------------------------------------------------");
		for(Country c:country.getCountries())
		{
			String format = c.getAdjacCountries().get(c.getName()).toString();
			System.out.println("Country name : " + c.getName() + " ,  Adjacent counties : " +format.substring(1,format.length()-1));
		}
	 }
	 if((maptag.getMapTagData().size() < 0) && (continent.getContinents().size() < 0) && (country.getCountries()==null))
	 {
		 System.out.println("please enter data in map to view");
	 }
	}
}


	


