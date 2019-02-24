package com.riskgame.action;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.riskgame.common.Continent;
import com.riskgame.common.Territory;


public class LoadAndEditMap {
	
	

	public String loadMap() throws Exception{

		System.out.println("\nChoose a map:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		Pattern pattern = Pattern.compile("[a-z, A-Z]+.[map]+");
		Matcher match = pattern.matcher(name.trim());	
		while(!match.matches())
		{
			System.out.println("\n Please enter a valid map ");
			name = br.readLine();
			match = pattern.matcher(name.trim());
		}
		String workingDir = System.getProperty("user.dir"); //gets the current location of the working directory
		String fileName =name.trim();
		String filepath= workingDir+"/maps/"+fileName;
		String image_name="";
		try {
			FileReader file=new FileReader(filepath) ;
			BufferedReader br_file = new BufferedReader(file);
			String str;
			while((str=br_file.readLine()) != null) {
				Pattern image_pattern = Pattern.compile("[Image]+ +=+ +[a-z, A-Z]+.[bmp]+");
				Matcher image_match = image_pattern.matcher(str.trim());
				Pattern continents_pattern = Pattern.compile("[Continents]+");
				Matcher continent_match = continents_pattern.matcher(str.trim());
				Pattern territory_pattern = Pattern.compile("[Territories]");
				Matcher territory_match = territory_pattern.matcher(str.trim());
				if(continent_match.matches()) {
					
					while(!territory_match.matches()) {
						str=br_file.readLine();
						System.out.println(str+"\n");
						String[] cont = str.split("=");
						System.out.println(cont[0]);
						Continent continent=new Continent();
						continent.setName(cont[0]);
						//continent.setControl_value(Integer.parseInt(cont[1]));
						str=br_file.readLine();
					}
				}
				
				if(territory_match.matches()) {
					while(str!=null) {
						str=br_file.readLine();
						System.out.println(str+"\n");
						String[] ter = str.split(",");
						Territory territory=new Territory();
						territory.setTerritoryName(ter[0]);
//						if(!territoryverification(str)) {
//							System.out.println("Error in the territories");
//							loadMap();
//							return null;
//						}
						
					}
				}
				if(image_match.matches()) {
					image_name=str.substring(8);
				}
				System.out.println(str+"\n");
			}
		}
		catch(IOException e){
			System.out.println("File Not Found");
		}
		editMap(filepath);
		String image=mapImage(image_name);
		return image;
	}



	public void editMap(String file)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to edit the map?(y/n)");
		String mapedit=br.readLine();
		String mapedition=mapedit.trim();
		while (mapedition != null) {
			if(mapedition.equals("y")) {
					edition();
					editMap(file);
					mapedition="n";
			}
			else if(mapedition.equals("n")) {
				return;
			}
			else {
				System.out.println("Please enter a valid answer:(y/n)");
				mapedition=br.readLine().trim();
			}		
		}
	}
	public String mapImage(String image) throws IOException {
		System.out.println("Start or Exit?(start/exit)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start=br.readLine().trim();
		if(start.equals("start") ) {
			start=null;
			if(image !=null) { 
				return image;
			}	
			else
				System.out.println("No image found for the map");
		}
		while (start != null) {
			if(start.equals("exit") )
				System.exit(0);
			else {
				System.out.println("Please enter a valid answer(start/exit)");
				start=br.readLine().trim();
			}
		}
		return image;
			
	}
	public void edition() throws IOException {
		System.out.println("\nChoose the below options to edit the map:");
		System.out.println("1. edit Map name or Author name:");
		System.out.println("2. Add continents:\n3. Delete a continent:");
		System.out.println("4. Add a country:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Save the map and exit:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option =0;
		try{
			option = Integer.parseInt(br.readLine());
		} 
	    catch (NumberFormatException e) 
		{ 
	    	System.out.println("\nPlease enter valid number");
	    	edition();
	    	return;
		}
		
		switch(option)
		{
		case 1: changeMetadata();
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
		default: System.out.println("Invalid option. Please choose the correct option.");
				 edition();
		
		}
	}
	public void changeMetadata() {
		
	}
	public boolean territoryverification(String territory) {
		return false;
		
	}
}


































