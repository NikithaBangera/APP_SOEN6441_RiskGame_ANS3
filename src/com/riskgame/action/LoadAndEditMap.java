package com.riskgame.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.gameplay.StartupPhase;

public class LoadAndEditMap {
	GameMapGraph mapGraph = new GameMapGraph();
	HashMap<Country,Integer> visited=new HashMap<Country,Integer>();
	public String loadMap() throws Exception {
		
		System.out.println("\nChoose a map:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		Pattern pattern = Pattern.compile("[a-z, A-Z]+.[map]+");
		Matcher match = pattern.matcher(name.trim());
		while (!match.matches()) {
			System.out.println("\n Please enter a valid map ");
			name = br.readLine();
			match = pattern.matcher(name.trim());
		}
		// gets the current location of the working directory
		String workingDir = System.getProperty("user.dir");
		String fileName = name.trim();
		// file path for windows : \\src\\com\\riskgame\\maps\\
		// file path for mac: /src/com/riskgame/maps/
		String filepath = workingDir + "/src/com/riskgame/maps/" + fileName;
		String image_name = "";
		try {
			FileReader file = new FileReader(filepath);
			BufferedReader br_file = new BufferedReader(file);
			String str;
			str = br_file.readLine();
			str = str.replaceAll("\\[", "").replaceAll("\\]", "");
			Pattern tagData_pattern = Pattern.compile("[Map]+");
			Matcher tagData_match = tagData_pattern.matcher(str.trim());
			// format of the currents map loading which is Image = somthing.bmp
			Pattern image_pattern = Pattern.compile("[Image]+=+[a-z, A-Z]+.[bmp]+");
			Matcher image_match = image_pattern.matcher(str.trim());
			Pattern continents_pattern = Pattern.compile("[Continents]+");
			Matcher continent_match = continents_pattern.matcher(str.trim());
			Pattern territory_pattern = Pattern.compile("[Territories]+");
			Matcher territory_match = territory_pattern.matcher(str.trim());
			if (tagData_match.matches()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
//				HashMap<String,String> data=new HashMap<String,String>();
//				data=mapGraph.getTagData();
				while ((!continent_match.matches()) && (!str.trim().isEmpty())) {
//					String[] metadata=str.split("=");
					// finds the image name
//					data.put(metadata[0], metadata[1]);
					image_match = image_pattern.matcher(str.trim());
					if (image_match.matches()) {
						image_name = str.substring(6);
						
					}
					System.out.println(str + "\n");
					str = br_file.readLine();
					str = str.replaceAll("\\[", "").replaceAll("\\]", "");
					continent_match = continents_pattern.matcher(str.trim());
				}
//				mapGraph.setTagData(data);
				
			}
			while (str.trim().isEmpty()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
			}
			continent_match = continents_pattern.matcher(str.trim());
			if (continent_match.matches()) {
				//HashMap<String,Continent> hashmap=new HashMap<String,Continent>() ;
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
				while (!territory_match.matches() && (!str.trim().isEmpty())) {
					String[] cont = str.split("=");
					// checks the format and creates continent object with continent name and
					// control value
					if (cont.length == 2) {
						Continent continent = new Continent();
						continent.setName(cont[0]);
						continent.setControl_value(Integer.parseInt(cont[1]));
						//hashmap.put(cont[0], continent);
						HashMap<String,Continent> help_continent=new HashMap<String,Continent>();
						help_continent=mapGraph.getContinents();
						help_continent.put(cont[0],continent);
						mapGraph.setContinents(help_continent);
					} else {
						System.out.println("check the continent and control values");
						return loadMap();
					}
					
					System.out.println(str + "\n");
					str = br_file.readLine();
					str = str.replaceAll("\\[", "").replaceAll("\\]", "");
					territory_match = territory_pattern.matcher(str.trim());
				}
			}
			if (str.trim().isEmpty()) {
				while (!territory_match.matches()) {
					str = br_file.readLine();
					str = str.replaceAll("\\[", "").replaceAll("\\]", "");
					territory_match = territory_pattern.matcher(str.trim());
				}
			}
			Country key= new Country();
			if (territory_match.matches()) {
				
				while ((str = br_file.readLine()) != null) {
					//HashMap<Country,ArrayList<Country>> connectivity= new HashMap<Country,ArrayList<Country>>();
					Continent continent=new Continent();
					String[] ter = str.split(",");
					// checking the x-values and y-values and creates a country object
					if ((Integer.parseInt(ter[2]) < 400) && (Integer.parseInt(ter[1]) < 400)) {
						Country country = new Country();
						if (mapGraph.getCountrySet().get(ter[0]) != null) {
							country=mapGraph.getCountrySet().get(ter[0]);
						}
						else {
							country.setName(ter[0]);
						}
						visited.put(country, 0);
						country.setxValue(ter[1]);
						country.setyValue(ter[2]);
						country.setContinent(ter[3]);
						continent.getCountriesInContinent().add(country);
						mapGraph.getContinents().put(ter[3],continent);
						ArrayList<Country> adjacent = new ArrayList<Country>();
						HashMap<String, Country> country_name= new HashMap<String, Country>();
						//help_country
						HashMap<Country, ArrayList<Country>> help_country=new HashMap<Country, ArrayList<Country>>();
						// for putting the adjacent countries
						for (int i = 4; i < ter.length; i++) {
							Country adjac = new Country();
							ArrayList<Country> help = new ArrayList<Country>();
							if(mapGraph.getCountrySet().get(ter[i]) != null) {
								adjac=mapGraph.getCountrySet().get(ter[i]);
								help = adjac.getAdjacentCountries();
							}
							else {
								adjac.setName(ter[i]);
							}
							
							visited.put(adjac, 0);
							// if the country already has some other adjacent countries it will overwrite
							// the adjacent countries of that
//							if (adjac.getAdjacentCountries() != null) {
//								help = adjac.getAdjacentCountries();
//							}
//							help.add(country);
//							adjac.setAdjacentCountries(help);
							//adjacent=country.getAdjacentCountries();
							//country.getAdjacentCountries().add(adjac);
							//System.out.println("this is the adjacent"+adjacent);
//							if(adjacent !=null) {
								//adjacent.add(adjac);
//							}
//							else {
//								adjacent= new ArrayList<Country>();
//								adjacent.add(adjac);
//							}
//							if(country.getAdjacentCountries() != null) {
//								for (int k=0; k<country.getAdjacentCountries().size(); k++) {
//									adjacent.add(country.getAdjacentCountries().get(k));
//								}
//							}
							adjacent.add(adjac);
							
							if(help !=null) {
								if(!help.isEmpty()) {
									boolean find=false;
									for(int z=0; z<help.size();z++) {
										if (country.getName()==help.get(z).getName()) {
											find=true;
										}
									}
									if(find==false) {
										System.out.println("Invalid Map");
										mapGraph = new GameMapGraph();
										visited=new HashMap<Country,Integer>();
										return loadMap();
									}
								}
							}
//							if(help==null) {
//								System.out.println("Invalid Map");
//								mapGraph = new GameMapGraph();
//								visited=new HashMap<Country,Integer>();
//								loadMap();
//							}
								
//							help_country=mapGraph.getAdjacentCountries();
//							help_country.put(adjac,adjac.getAdjacentCountries());
//							mapGraph.setAdjacentCountries(help_country);
							country_name=mapGraph.getCountrySet();
							country_name.put(adjac.getName(), adjac);
							mapGraph.setCountrySet(country_name);
							
						}
						country.setAdjacentCountries(adjacent);
						//HashMap<Country, ArrayList<Country>> help_country=new HashMap<Country, ArrayList<Country>>();
						help_country=mapGraph.getAdjacentCountries();
						help_country.put(country, adjacent);
						mapGraph.setAdjacentCountries(help_country);
						//HashMap<String, Country> country_name= new HashMap<String, Country>();
						country_name=mapGraph.getCountrySet();
						country_name.put(ter[0], country);
						mapGraph.setCountrySet(country_name);
						//connectivity=help_country;
						key=country;
					} else {
						System.out.println("incorrect values for x-values and y-values");
						return loadMap();

					}
					HashMap<String, Country> count= new HashMap<String, Country>();
					count=mapGraph.getCountrySet();
					mapGraph.setCountOfCountries(count.size());	
					System.out.println(str + "\n");
					
				}
//				HashMap<String, Country> an=new HashMap<String,Country>();
//				an=mapGraph.getCountrySet();
				//System.out.println(mapGraph.getAdjacentCountries());
				
			}
			for(String s: mapGraph.getCountrySet().keySet()) {
				Country cunt=new Country();
				cunt=mapGraph.getCountrySet().get(s);
				
				if(cunt.getAdjacentCountries() ==null) {
					System.out.println("Invalid Map");
					mapGraph = new GameMapGraph();
					visited=new HashMap<Country,Integer>();
					return loadMap();
				}
			}
			DFS(mapGraph.getAdjacentCountries(),key);
			int counting=0;
			for (Country count_country: visited.keySet()) {
				counting=counting+visited.get(count_country);
			}
				
			
			
			if(counting<mapGraph.getCountOfCountries()) {
				System.out.println("Graph is not connected");
				mapGraph = new GameMapGraph();
				visited=new HashMap<Country,Integer>();
				return loadMap();
			}
			editMap(fileName);
			String image = mapImage(image_name);
			return image;

		} catch (IOException e) {
			System.out.println("File Not Found");
			return loadMap();
		}
	}

	public void editMap(String file) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to edit the map?(y/n)");
		String mapedit = br.readLine();
		String mapedition = mapedit.trim();
		while (mapedition != null) {
			if (mapedition.equals("y")) {
				edition(file);
				editMap(file);
				mapedition = "n";
			} else if (mapedition.equals("n")) {
				return;
			} else {
				System.out.println("Please enter a valid answer:(y/n)");
				mapedition = br.readLine().trim();
			}
		}
		loadMap();
	}

	public String mapImage(String image) throws Exception {
		System.out.println("Start or Exit?(start/exit)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine().trim();
		if (start.equals("start")) {
			start = null;
			if (image != null) {
				return image;
			} else
				System.out.println("No image found for the map");
		}
		while (start != null) {
			if (start.equals("exit"))
				System.exit(0);
			else {
				System.out.println("Please enter a valid answer(start/exit)");
				start = br.readLine().trim();
				StartupPhase startup = new StartupPhase();
				if (start.equalsIgnoreCase("start")) {
					startup.gamePlay(mapGraph);
				}
			}
		}
		return image;
	}

	public void edition(String file) throws IOException {
		System.out.println("\nChoose the below options to edit the map:");
		System.out.println("1. Edit map tag data:");
		System.out.println("2. Add continents:\n3. Delete a continent:");
		System.out.println("4. Add a country:\n5. Delete a country:");
		System.out.println("6. Add adjacency:\n7. Delete Adjacency:");
		System.out.println("8. Save the map and exit:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		try {
			option = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("\nPlease enter valid number");
			edition(file);
			return;
		}
		switch (option) {
		case 1:
			changeMetadata(file);
			break;

		case 2:
			setContinentDetails(file);
			break;

		case 3:
			deleteContinentDetails(file);

			break;

		case 4:
			addingCountry(file);
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
		default:
			System.out.println("Invalid option. Please choose the correct option.");
			edition(file);

		}
		
	}
	//ArrayList<String> mapTagData = new ArrayList<String>();
	public void changeMetadata(String file) throws IOException {
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
		//mapGraph.getTagData().put("image", name.trim());
		String image = "image=" + name.trim();
		
		System.out.println("Please specify yes or no for warn");
		String warnanswer = br.readLine();
		while(!((warnanswer.equals("yes"))||(warnanswer.equals("Yes")))) 
		{
			System.out.println("Please enter yes or no ");
			warnanswer = br.readLine();
		}
		//mapGraph.getTagData().put("warn", warnanswer.trim());
		String warn = "warn=" + warnanswer.trim();
		
		System.out.println("Please specify horizontal or vertical scroll");
		String scrollanswer = br.readLine();
		while(!((scrollanswer.equals("horizontal"))||(scrollanswer.equals("Horizontal"))))
		{
			System.out.println("Please enter valid response ");
			scrollanswer = br.readLine();
		}
		//mapGraph.getTagData().put("scroll", scrollanswer.trim());
		String scroll = "scroll=" + scrollanswer.trim();
		
		System.out.println("Please specify yes or no for wrap");
		String wrapanswer = br.readLine();
		while(!((wrapanswer.equals("no"))||(wrapanswer.equals("No"))))
		{
			System.out.println("Please enter yes or no ");
			wrapanswer = br.readLine();
		}
		//mapGraph.getTagData().put("wrap", wrapanswer.trim());
		String wrap = "wrap=" + wrapanswer.trim();
		
		System.out.println("Please enter the author name:");
		//mapGraph.getTagData().put("author", br.readLine().trim());
		String author = "author=" + br.readLine().trim();
		String workingDir = System.getProperty("user.dir");
		String fileName = file.trim();
		// file path for windows : \\src\\com\\riskgame\\maps\\
		// file path for mac: /src/com/riskgame/maps/
		String filepath = workingDir + "/src/com/riskgame/maps/" + file;
		File inputFile = new File(filepath);
		File tempFile = new File(workingDir + "/src/com/riskgame/maps/myTempFile.map");
		//BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		PrintWriter outputStream = new PrintWriter(tempFile);
		//outputStream.println(mapTagData[0]);	//write the image file
//		outputStream.println(mapTagData[1]);	//write the author
//		outputStream.flush();
//		outputStream.close();
		FileReader filereader = new FileReader(filepath);
		BufferedReader br_file = new BufferedReader(filereader);
		//BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		//BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String currentLine= br_file.readLine();
//		while(currentLine !=null) {
//			writer.write(currentLine);
			//for(String s:mapGraph.getTagData().keySet()) {
				//outputStream.println(s+"="+mapGraph.getTagData().get(s));
			//}
//		}
				outputStream.println("[Map]");
				outputStream.println(image);
				outputStream.println(warn);
				outputStream.println(scroll);
				outputStream.println(wrap);
				outputStream.println(author);
//		writer.write(image);
//		writer.write(warn);
//		writer.write(scroll);
//		writer.write(wrap);
//		writer.write(author);
//				while (currentLine.trim().isEmpty()) {
//					outputStream.println("\n");
//					currentLine = br_file.readLine();
//					
//				}
		Pattern tagData_pattern = Pattern.compile("[Map]+");
		currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
		Matcher tagData_match = tagData_pattern.matcher(currentLine.trim());
		if(tagData_match.matches()) {
			while(!currentLine.trim().isEmpty())
				currentLine = br_file.readLine();
		}
		while (currentLine.trim().isEmpty()) {
			outputStream.println();
			currentLine = br_file.readLine();
			
		}
		Pattern continents_pattern = Pattern.compile("[Continents]+");
		currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
		Matcher continent_match = continents_pattern.matcher(currentLine.trim());
		
		while(!continent_match.matches()) {
			System.out.println(currentLine);
			currentLine=br_file.readLine();
			currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
			continent_match = continents_pattern.matcher(currentLine.trim());	
		}
		
		outputStream.println("[Continent]");
		//writer.write("[Continent]");
		while((currentLine = br_file.readLine()) != null) {
			//String trimmedLine = currentLine.trim();
			outputStream.println(currentLine);
			//writer.write(currentLine );
		}
		outputStream.flush();
		outputStream.close();
		tempFile.renameTo(inputFile);
		
//		for(String s:mapGraph.getTagData().keySet())
//		{
//			writer.write(cbuf);
//		}
		//String author = "author=" + br.readLine().trim();
			
//		mapTagData.add(image);
//		mapTagData.add(author);
//		mapTagData.add(warn);		
//		mapTagData.add(wrap);
//		mapTagData.add(scroll);
//		
//		maptag.setMapTagData(mapTagData);		
		System.out.println("\nMaptagdata added");
	}
	public void setContinentDetails(String file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter continent details in below format:");
		System.out.println("Continent name=Control Value");
		Pattern pattern = Pattern.compile("[a-z, A-Z]+=[0-9]+");
		String continentName = br.readLine();
	    Matcher match = pattern.matcher(continentName.trim());
	    //Continent continent=new Continent();
	    while(!match.matches()) {
	    	System.out.println("Pleae enter continent details in below format:");
			System.out.println("Continent name=Control Value");
			continentName = br.readLine();
		    match = pattern.matcher(continentName.trim());
//	    	String[] str=continentName.split("=");
//	    	continent.getContinents().put(str[0],Integer.parseInt(str[1]));
	    }
	    if(match.matches()) {
	    	String workingDir = System.getProperty("user.dir");
			String fileName = file.trim();
	    	String filepath = workingDir + "/src/com/riskgame/maps/" + file;
			File inputFile = new File(filepath);
			File tempFile = new File(workingDir + "/src/com/riskgame/maps/myTempFile.map");
			PrintWriter outputStream = new PrintWriter(tempFile);
			FileReader filereader = new FileReader(filepath);
			BufferedReader br_file = new BufferedReader(filereader);
			String currentLine= br_file.readLine();
			String[] str=continentName.split("=");
			Pattern tagData_pattern = Pattern.compile("[Map]+");
			currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
			Matcher tagData_match = tagData_pattern.matcher(currentLine.trim());
			if(tagData_match.matches()) {
				while(!currentLine.trim().isEmpty()) {
					outputStream.println(currentLine);
					currentLine = br_file.readLine();
				}
			}
			while (currentLine.trim().isEmpty()) {
				outputStream.println();
				currentLine = br_file.readLine();
				
			}
			Pattern continents_pattern = Pattern.compile("[Continents]+");
			currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
			Matcher continent_match = continents_pattern.matcher(currentLine.trim());
			
			while(!continent_match.matches()) {
				System.out.println(currentLine);
				currentLine=br_file.readLine();
				currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
				continent_match = continents_pattern.matcher(currentLine.trim());	
			}
			
			outputStream.println("[Continent]");
			currentLine=br_file.readLine();
			if(mapGraph.getContinents().get(str[0]) != null) {
				String[] cont=currentLine.trim().split("=");
				while(!cont[0].equals(str[0])) {
					outputStream.println(currentLine);
					currentLine=br_file.readLine();
					cont=currentLine.trim().split("=");
					
				}
				if(cont[0].equals(str[0])) {
					outputStream.println(cont[0]+"="+str[1]);
					currentLine=br_file.readLine();
				}
			}
			else {
				Continent continent=new Continent();
				continent.setName(str[0]);
				continent.setControl_value(Integer.parseInt(str[1]));
				mapGraph.getContinents().put(str[0], continent);
				outputStream.println(continentName);
				outputStream.println(currentLine);
				currentLine = br_file.readLine();
			}
			while(currentLine != null) {
				//String trimmedLine = currentLine.trim();
				outputStream.println(currentLine);
				currentLine = br_file.readLine();
				//writer.write(currentLine );
			}
			outputStream.flush();
			outputStream.close();
			tempFile.renameTo(inputFile);
	    }
	}
	public void deleteContinentDetails(String file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the continent you want to delete:");
		String continentName = br.readLine();
		
		if(mapGraph.getContinents().get(continentName.trim()) ==null) {
			System.out.println("Continent doesn't exist");
		}
		else {
			Continent continent=new Continent();
			continent=mapGraph.getContinents().get(continentName.trim());
			if((continent.getCountriesInContinent() != null) && (!continent.getCountriesInContinent().isEmpty())) {
				//System.out.println("this is shit"+continent.getCountriesInContinent().get(0));
				System.out.println("Cannot delete continent");
			}
			 else{
			      continent.getContinents().remove(continentName.trim());
			      mapGraph.getContinents().remove(continentName);
			      System.out.println("The continent " + continentName.trim() + " is deleted from the map.");
			      String workingDir = System.getProperty("user.dir");
					String fileName = file.trim();
			    	String filepath = workingDir + "/src/com/riskgame/maps/" + file;
					File inputFile = new File(filepath);
					File tempFile = new File(workingDir + "/src/com/riskgame/maps/myTempFile.map");
					PrintWriter outputStream = new PrintWriter(tempFile);
					FileReader filereader = new FileReader(filepath);
					BufferedReader br_file = new BufferedReader(filereader);
					String currentLine= br_file.readLine();
					String[] str=continentName.split("=");
					Pattern tagData_pattern = Pattern.compile("[Map]+");
					currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
					Matcher tagData_match = tagData_pattern.matcher(currentLine.trim());
					if(tagData_match.matches()) {
						while(!currentLine.trim().isEmpty()) {
							outputStream.println(currentLine);
							currentLine = br_file.readLine();
						}
					}
					while (currentLine.trim().isEmpty()) {
						outputStream.println();
						currentLine = br_file.readLine();
						
					}
					Pattern continents_pattern = Pattern.compile("[Continents]+");
					currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
					Matcher continent_match = continents_pattern.matcher(currentLine.trim());
					
					while(!continent_match.matches()) {
						System.out.println(currentLine);
						currentLine=br_file.readLine();
						currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
						continent_match = continents_pattern.matcher(currentLine.trim());	
					}
					
					outputStream.println("[Continent]");
					currentLine=br_file.readLine();
					Pattern delete_pattern = Pattern.compile(continentName);
					Matcher delete_match = continents_pattern.matcher(currentLine.trim());
					String[] cont=currentLine.trim().split("=");
					while(!cont[0].equals(continentName.trim())&&(currentLine !=null)) {
						outputStream.println(currentLine);
						currentLine=br_file.readLine();
						cont=currentLine.trim().split("=");
						
					}
					if(cont[0].equals(continentName.trim())) {
						currentLine=br_file.readLine();
					}
					while(currentLine != null) {
						//String trimmedLine = currentLine.trim();
						outputStream.println(currentLine);
						currentLine = br_file.readLine();
						//writer.write(currentLine );
					}
					outputStream.flush();
					outputStream.close();
					tempFile.renameTo(inputFile);
					
			   }
		}
	}
	public void addingCountry(String file) throws IOException {
		System.out.println("Please enter the details of the countries in the below format:");
		System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
		Pattern pattern = Pattern.compile("[a-z, A-Z]+,+[0-9]+,+[0-9]+,[a-z, A-Z]+");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String countryName = br.readLine();
		String[] countryDetails =countryName.split(",") ;
		Country country=new Country();
		Continent continent=new Continent();
		if(mapGraph.getCountrySet().get(countryDetails[0]) != null) {
			country=mapGraph.getCountrySet().get(countryDetails[0]);
		}
		else {
			country.setName(countryDetails[0]);
		}
		country.setxValue(countryDetails[1]);
		country.setyValue(countryDetails[2]);
		country.setContinent(countryDetails[3]);
		if(mapGraph.getContinents().get(countryDetails[3])!=null) {
			continent=mapGraph.getContinents().get(countryDetails[3]);
		}
		else {
			continent.setName(countryDetails[3]);
		}
		continent.getCountriesInContinent().add(country);
		
		for(int i=4; i<countryDetails.length;i++) {
			country.getAdjacentCountries().add(mapGraph.getCountrySet().get(countryDetails[i]));
			mapGraph.getCountrySet().get(countryDetails[i]).getAdjacentCountries().add(country);
			mapGraph.getAdjacentCountries().put(mapGraph.getCountrySet().get(countryDetails), mapGraph.getCountrySet().get(countryDetails).getAdjacentCountries());
		}
		mapGraph.getAdjacentCountries().put(country,country.getAdjacentCountries());
		mapGraph.getCountrySet().put(country.getName(), country);
		mapGraph.setCountOfCountries(mapGraph.getCountrySet().size());
		for(Country visit:visited.keySet())
			visited.put(visit, 0);
		DFS(mapGraph.getAdjacentCountries(),country);
		
		String workingDir = System.getProperty("user.dir");
		String fileName = file.trim();
    	String filepath = workingDir + "/src/com/riskgame/maps/" + file;
		File inputFile = new File(filepath);
		File tempFile = new File(workingDir + "/src/com/riskgame/maps/myTempFile.map");
		PrintWriter outputStream = new PrintWriter(tempFile);
		FileReader filereader = new FileReader(filepath);
		BufferedReader br_file = new BufferedReader(filereader);
		String currentLine= br_file.readLine();
		//String[] str=continentName.split("=");
		Pattern tagData_pattern = Pattern.compile("[Map]+");
		currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
		Matcher tagData_match = tagData_pattern.matcher(currentLine.trim());
		if(tagData_match.matches()) {
			while(!currentLine.trim().isEmpty()) {
				outputStream.println(currentLine);
				currentLine = br_file.readLine();
			}
		}
		while (currentLine.trim().isEmpty()) {
			outputStream.println();
			currentLine = br_file.readLine();
		}
		Pattern territory_pattern = Pattern.compile("[Territory]+");
		currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
		Matcher territory_match = territory_pattern.matcher(currentLine.trim());
		
		while(!territory_match.matches()) {
			System.out.println(currentLine);
			currentLine=br_file.readLine();
			currentLine = currentLine.replaceAll("\\[", "").replaceAll("\\]", "");
			territory_match = territory_pattern.matcher(currentLine.trim());	
		}
		
		outputStream.println("[Territory]");
		currentLine=br_file.readLine();
		for(String s:mapGraph.getCountrySet().keySet()) {
			String adjacentCountries="";
			for(int i=0; i<mapGraph.getCountrySet().get(s).getAdjacentCountries().size();i++) {
				adjacentCountries+=(mapGraph.getCountrySet().get(s).getAdjacentCountries().get(i).getName());
			}
			outputStream.println(mapGraph.getCountrySet().get(s).getName()+','+mapGraph.getCountrySet().get(s).getxValue()+','+mapGraph.getCountrySet().get(s).getyValue()+','+adjacentCountries);
		}
//		Pattern delete_pattern = Pattern.compile(continentName);
//		Matcher delete_match = continents_pattern.matcher(currentLine.trim());
//		String[] cont=currentLine.trim().split("=");
//		while(!cont[0].equals(continentName.trim())&&(currentLine !=null)) {
//			outputStream.println(currentLine);
//			currentLine=br_file.readLine();
//			cont=currentLine.trim().split("=");
//			
//		}
//		if(cont[0].equals(continentName.trim())) {
//			currentLine=br_file.readLine();
//		}
//		while(currentLine != null) {
//			//String trimmedLine = currentLine.trim();
//			outputStream.println(currentLine);
//			currentLine = br_file.readLine();
//			//writer.write(currentLine );
//		}
		outputStream.flush();
		outputStream.close();
		tempFile.renameTo(inputFile);
		
		
	}
	public void DFS(HashMap<Country, ArrayList<Country>> adjacentCountries, Country country) {
		
		//System.out.println(country.getAdjacentCountries());
		visited.put(country, 1);
		ArrayList<Country> adjacent= new ArrayList<Country>();
		adjacent=adjacentCountries.get(country);
		for(int i=0 ; i<adjacent.size();i++) {
			if(visited.get(adjacent.get(i)) != 1) {
				//System.out.println(adjacent.get(i).getName());
				//HashMap<Country,ArrayList<Country>> next= new HashMap<Country,ArrayList<Country>>();
				//next.put(adjacent.get(i), adjacent.get(i).getAdjacentCountries());
				DFS(adjacentCountries,adjacent.get(i));
				}
			}
		
			
		
		
	}
}