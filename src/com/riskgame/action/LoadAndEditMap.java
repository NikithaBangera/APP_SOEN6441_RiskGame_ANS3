package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			Pattern tagData_pattern = Pattern.compile("[map]+");
			Matcher tagData_match = tagData_pattern.matcher(str.trim());
			// format of the currents map loading which is Image = somthing.bmp
			Pattern image_pattern = Pattern.compile("[image]+=+[a-z, A-Z]+.[bmp]+");
			Matcher image_match = image_pattern.matcher(str.trim());
			Pattern continents_pattern = Pattern.compile("[continents]+");
			Matcher continent_match = continents_pattern.matcher(str.trim());
			Pattern territory_pattern = Pattern.compile("[territories]+");
			Matcher territory_match = territory_pattern.matcher(str.trim());
			if (tagData_match.matches()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
				while ((!continent_match.matches()) && (!str.trim().isEmpty())) {
					// finds the image name
					image_match = image_pattern.matcher(str.trim());
					if (image_match.matches()) {
						image_name = str.substring(6);
					}
					System.out.println(str + "\n");
					str = br_file.readLine();
					str = str.replaceAll("\\[", "").replaceAll("\\]", "");
					continent_match = continents_pattern.matcher(str.trim());
				}
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
					
					String[] ter = str.split(",");
					// checking the x-values and y-values and creates a country object
					if ((Integer.parseInt(ter[2]) < 400) && (Integer.parseInt(ter[1]) < 400)) {
						Country country = new Country();
						visited.put(country, 0);
						if (mapGraph.getCountrySet().get(ter[0]) != null) {
							country=mapGraph.getCountrySet().get(ter[0]);
						}
						else {
							country.setName(ter[0]);
						}
						country.setxValue(ter[1]);
						country.setyValue(ter[2]);
						country.setContinent(ter[3]);
						ArrayList<Country> adjacent = new ArrayList<Country>();
						HashMap<String, Country> country_name= new HashMap<String, Country>();
						//help_country
						HashMap<Country, ArrayList<Country>> help_country=new HashMap<Country, ArrayList<Country>>();
						// for putting the adjacent countries
						for (int i = 4; i < ter.length; i++) {
							Country adjac = new Country();
							visited.put(adjac, 0);
							ArrayList<Country> help = new ArrayList<Country>();
							if(mapGraph.getCountrySet().get(ter[i]) != null) {
								adjac=mapGraph.getCountrySet().get(ter[i]);
								help = adjac.getAdjacentCountries();
							}
							else {
								adjac.setName(ter[i]);
							}
							
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
							
							
							if(!help.isEmpty()) {
								boolean find=false;
								for(int z=0; z<help.size();z++) {
									if (country==help.get(z)) {
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
			editMap(filepath);
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
				edition();
				editMap(file);
				mapedition = "n";
			} else if (mapedition.equals("n")) {
				return;
			} else {
				System.out.println("Please enter a valid answer:(y/n)");
				mapedition = br.readLine().trim();
			}
		}
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

	public void edition() throws IOException {
		System.out.println("\nChoose the below options to edit the map:");
		System.out.println("1. edit Map name or Author name:");
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
			edition();
			return;
		}
		switch (option) {
		case 1:
			changeMetadata();
			break;

		case 2:
			System.out.println("Enter the number of Continents:");
			int numberOfContinents = Integer.parseInt(br.readLine());
			HashMap<String, Integer> continentDetails = new HashMap<String, Integer>();
			System.out.println("Enter the continent details in below format:");
			System.out.println("Continent name = Control Value");
			for (int i = 0; i < numberOfContinents; i++) {
				String continent = br.readLine();
				String[] split = continent.split("=");
				continentDetails.put(split[0], Integer.parseInt(split[1]));
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
		default:
			System.out.println("Invalid option. Please choose the correct option.");
			edition();

		}
	}

	public void changeMetadata() {

	}
	
	public void DFS(HashMap<Country, ArrayList<Country>> adjacentCountries, Country country) {
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