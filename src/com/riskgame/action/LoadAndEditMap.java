package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.gameplay.StartupPhase;

public class LoadAndEditMap {

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
		//file path for windows
		String filepath = workingDir + "\\src\\com\\riskgame\\maps\\" + fileName;
		String image_name = "";
		try {
			FileReader file = new FileReader(filepath);
			BufferedReader br_file = new BufferedReader(file);
			String str;
			str = br_file.readLine();
			str = str.replaceAll("\\[", "").replaceAll("\\]", "");
			Pattern tagData_pattern = Pattern.compile("[Map]+");
			Matcher tagData_match = tagData_pattern.matcher(str.trim());

			Pattern image_pattern = Pattern.compile("[Image]+ +=+ +[a-z, A-Z]+.[bmp]+");
			Matcher image_match = image_pattern.matcher(str.trim());
			Pattern continents_pattern = Pattern.compile("[Continents]+");
			Matcher continent_match = continents_pattern.matcher(str.trim());
			Pattern territory_pattern = Pattern.compile("[Territories]+");
			Matcher territory_match = territory_pattern.matcher(str.trim());
			if (tagData_match.matches()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
				while ((!continent_match.matches()) && (!str.trim().isEmpty())) {
					image_match = image_pattern.matcher(str.trim());
					if (image_match.matches()) {
						image_name = str.substring(8);
					}
					System.out.println(str + "\n");
					str = br_file.readLine();
					str = str.replaceAll("\\[", "").replaceAll("\\]", "");
					continent_match = continents_pattern.matcher(str.trim());
				}
			}
			while (str.trim().isEmpty()) {
				// while(!continent_match.matches()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
				// continent_match = continents_pattern.matcher(str.trim());
				// }
			}
			continent_match = continents_pattern.matcher(str.trim());
			if (continent_match.matches()) {
				str = br_file.readLine();
				str = str.replaceAll("\\[", "").replaceAll("\\]", "");
				while (!territory_match.matches() && (!str.trim().isEmpty())) {

					String[] cont = str.split("=");
					if (cont.length == 2) {
						Continent continent = new Continent();
						continent.setName(cont[0]);
						// System.out.println(cont.length + "\n");
						continent.setControl_value(Integer.parseInt(cont[1]));
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
			if (territory_match.matches()) {
				while ((str = br_file.readLine()) != null) {
					String[] ter = str.split(",");
					if ((Integer.parseInt(ter[2]) < 400) && (Integer.parseInt(ter[1]) < 400)) {
						Country country = new Country();
						country.setName(ter[0]);
						country.setxValue(ter[1]);
						country.setyValue(ter[2]);
						country.setContinent(ter[3]);
						ArrayList<Country> adjacent = new ArrayList();
						for (int i = 4; i < ter.length; i++) {
							Country adjac = new Country();
							adjac.setName(ter[i]);
							ArrayList<Country> help = new ArrayList();
							if (adjac.getAdjacentCountries() != null) {
								help = adjac.getAdjacentCountries();
//								help.add(country);
							}

							help.add(country);
							adjac.setAdjacentCountries(help);

							adjacent.add(adjac);
						}
						System.out.print(adjacent);
						country.setAdjacentCountries(adjacent);
					} else {
						System.out.println("check the x-values and y-values");
						return loadMap();

					}
					// System.out.println("this is adjacents"+country.getAdjacentCountries() +
					// "\n");
					System.out.println(str + "\n");
				}
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
					// startup
					startup.gamePlay();
					startup.allocationOfCountry();
					startup.allocationOfArmyToPlayers();
					startup.allocationOfArmyToCountriesInitially();
					startup.allocationOfArmyToCountries_Balance();
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

	public boolean territoryverification(String territory) {
		return false;

	}
}