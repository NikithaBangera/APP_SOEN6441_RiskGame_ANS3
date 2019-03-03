package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.MapTag;

public class CreateAndEditMap {

	// private ReadandWriteMap saveMap;
	// create a new map
	private GameMapGraph mapGraph = new GameMapGraph();
	private String fileName;
	private ArrayList<Country> listOfCountries;
	private ArrayList<Continent> listOfContinents;
	private HashMap<String, Country> setOfCountries;
	BufferedReader br;
	boolean returnflag = false;

	// private String[] mapTagData = new String[3];

	public GameMapGraph getMapGraph() {
		return mapGraph;
	}

	public void setMapGraph(GameMapGraph mapGraph) {
		this.mapGraph = mapGraph;
	}
	
	public ArrayList<Continent> getListOfContinents() {
		return listOfContinents;
	}

	public void setListOfContinents(ArrayList<Continent> listOfContinents) {
		this.listOfContinents = listOfContinents;
	}
	
	public boolean newMapCreation() throws Exception {
		boolean exit = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!exit) {
			System.out.println("\nWelcome to Risk Game!");
			System.out.println("\nChoose the below options to create a new map\n");
			System.out.println("1. Enter Map name and Author name");
			System.out.println("2. Add the continents\n3. Delete a continent");
			System.out.println("4. Add the countries\n5. Delete a country");
			System.out.println("6. Add adjacency\n7. Delete Adjacency");
			System.out.println("8. Show the map's contents\n9. Save the map and exit");
			System.out.println("10. Exit without Saving the map");

			System.out.println("\nPlease enter your choice below:");
			Pattern pattern = Pattern.compile("[0-9]+");
			String option = br.readLine().trim();
			Matcher match = pattern.matcher(option.trim());
			while (!(match.matches())) {
				System.err.println("Please enter a valid option(number) from the game menu!");
				System.out.flush();
				option = br.readLine().trim();
				match = pattern.matcher(option.trim());
			}
//			while (option.isEmpty()) {
//				System.err.println("\nChoice cannot be blank. Please enter your choice below:");
//				System.out.flush();
//				option = br.readLine().trim();
//			}

			switch (Integer.parseInt(option)) {
			case 1:
				createMapTag();
				break;
			case 2:
				setContinentDetails();
				break;
			case 3:
				deleteContinent();
				break;
			case 4:
				setCountryDetails();
				break;
			case 5:
				deleteCountry();
				break;
			case 6:
				addAdjacency();
				break;
			case 7:
				removeAdjacency();
				break;
			case 8:
				printMap();
				break;
			case 9:
				Boolean saved = checkandSave();
				if (saved) {
					returnflag = true;
					exit = true;
				}
				break;
			case 10:
				System.out.println(
						"\nAll the entries made would be lost and not saved.Do you want to exit without saving?? - Yes/No ");
				String choice = br.readLine();
				if (choice.equalsIgnoreCase("yes"))
					exit = true;
				break;
			default:
				System.out.println("Invalid option. Please choose the correct option.");

			}
		}
		return returnflag;
	}

	public boolean uploadMap(GameMapGraph uploadedmapGraph) throws Exception {
		mapGraph = uploadedmapGraph;
		System.out.println("\nUploaded Map Details\n");
		printMap();
		boolean exit = false;
		while (!exit) {
			System.out.println("\nChoose the below options to edit the uploaded map\n");
			System.out.println("1. Enter Map name and Author name");
			System.out.println("2. Add the continents\n3. Delete a continent");
			System.out.println("4. Add the countries\n5. Delete a country");
			System.out.println("6. Add adjacency\n7. Delete Adjacency");
			System.out.println("8. Show the map's contents\n9. Save the map and exit");
			System.out.println("10. Exit without Saving the map");

			System.out.println("\nPlease enter your choice below:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Pattern pattern = Pattern.compile("[0-9]+");
			String option = br.readLine().trim();
			Matcher match = pattern.matcher(option.trim());
			while (!(match.matches())) {
				System.err.println("Please enter a valid option(number) from the game menu!");
				System.out.flush();
				option = br.readLine().trim();
				match = pattern.matcher(option.trim());
			}
//			while (option.isEmpty()) {
//				System.err.println("\nChoice cannot be blank. Please enter your choice below:");
//				System.out.flush();
//				option = br.readLine().trim();
//			}


			switch (Integer.parseInt(option)) {
			case 1:
				createMapTag();
				break;
			case 2:
				setContinentDetails();
				break;
			case 3:
				deleteContinent();
				break;
			case 4:
				setCountryDetails();
				break;
			case 5:
				deleteCountry();
				break;
			case 6:
				addAdjacency();
				break;
			case 7:
				removeAdjacency();
				break;
			case 8:
				printMap();
				break;
			case 9:
				Boolean saved = checkandSave();
				if (saved) {
					returnflag = true;
					exit = true;
				}
				break;
			case 10:
				System.out.println(
						"\nAll the entries made would be lost and not saved.Do you want to exit without saving?? - Yes/No ");
				String choice = br.readLine();
				if (choice.equalsIgnoreCase("yes"))
					exit = true;
				break;
			default:
				System.out.println("Invalid option. Please choose the correct option.");

			}
		}
		return returnflag;
	}

	public void createMapTag() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please Enter the image name of the map in below format:");
		System.out.println("(Imagename.bmp)\n");
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[_-]*.bmp");
		String name = br.readLine();
		Matcher match = pattern.matcher(name.trim());
		while (!match.matches()) {
			System.err.println("\nPlease enter valid image name.");
			System.out.flush();
			name = br.readLine();
			match = pattern.matcher(name.trim());
		}
		String image = name.trim();

		System.out.println("Please specify scroll is horizontal or vertical");
		String scroll = br.readLine().trim();
		while (!((scroll.equalsIgnoreCase("horizontal")) || (scroll.equalsIgnoreCase("vertical")) || scroll == null)) {
			System.err.println("Invalid/Blank value entered, please enter horizontal or vertical");
			System.out.flush();
			scroll = br.readLine().trim();
		}

		System.out.println("Please specify wrap is Yes or No");
		String wrap = br.readLine().trim();
		while (!((wrap.equalsIgnoreCase("Yes")) || (wrap.equalsIgnoreCase("No")) || wrap == null)) {
			System.err.println("Invalid/Blank value entered, please enter Yes or No");
			System.out.flush();
			wrap = br.readLine().trim();
		}

		System.out.println("Please enter the author name:");
		String author = br.readLine().trim();
		while (author.isEmpty()) {

			System.err.println("Sorry! The entered author name cannot be blank. Please enter again \n");
			System.out.flush();
			author = br.readLine().trim();

		}

		System.out.println("Please specify warn is Yes or No");
		String warn = br.readLine().trim();
		while (!((warn.equalsIgnoreCase("Yes")) || (warn.equalsIgnoreCase("No")) || warn == null)) {
			System.err.println("Invalid/Blank value entered, please enter Yes or No");
			System.out.flush();
			warn = br.readLine().trim();
		}

		MapTag mapTag = new MapTag(author, warn, image, wrap, scroll);
		mapGraph.setMapTag(mapTag);

		System.out.println("Map tag data Added successfully.");
	}

	public void setContinentDetails() throws Exception {
		listOfContinents = new ArrayList<>();
		String num;
		if (mapGraph.getContinents() != null) {
			listOfContinents = mapGraph.getContinents();
		}
		System.out.println("\nPlease enter the number of Continent:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = br.readLine();
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher match = pattern.matcher(num);
		while (!(match.matches())) {
			System.err.println("Please enter valid number of continents!");
			System.out.flush();
			num = br.readLine().trim();
			match = pattern.matcher(num.trim());
		}

		int numberOfContinents = Integer.parseInt(num);

		if (numberOfContinents > 0) {
			System.out.println("Please enter continent details in below format:");
			System.out.println("Continent name=Control Value");
			pattern = Pattern.compile("[a-zA-Z\\s]+=[0-9]+");

			for (int i = 0; i < numberOfContinents; i++) {
				String continentName = br.readLine();
				match = pattern.matcher(continentName.trim());
				if (match.matches()) {
					Continent continent = new Continent();
					int controlValue = Integer.parseInt(continentName.split("=")[1]);
					continentName = continentName.split("=")[0];
					if (alreadyDefinedContinent(continentName)) {
						System.out.println("Entered Continent already exists.\n Please enter new details");
						System.out.println("Continent name=Control Value");
						--i;
						continue;
					}
					continent.setName(continentName);
					continent.setControlValue(controlValue);
					listOfContinents.add(continent);
				} else {
					System.out.println(" Invalid continent details\n");
					System.out.println("PLease Enter continent details again");
					--i;
					continue;
				}
			}
			mapGraph.setContinents(listOfContinents);
			mapGraph.setCountOfContinents(numberOfContinents);
			System.out.println("\nContinent Added Successfully.\n");
		} else {
			System.out.println("Number of Continents should be greater than zero\n");
			setContinentDetails();
		}
	}

	public void setCountryDetails() throws Exception {
		listOfContinents = new ArrayList<>();
		listOfCountries = new ArrayList<>();
		int numberOfCountries = 0;
		int index = 0;
		boolean countryexist = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (mapGraph.getContinents() != null && !mapGraph.getContinents().isEmpty()) {
			listOfContinents = mapGraph.getContinents();
			if (mapGraph.getCountries() != null) {
				listOfCountries = mapGraph.getCountries();
			}
			boolean proceed = true;
			while (proceed) {
				System.out.println("\nPlease enter the number of Countries:");
				try {
					numberOfCountries = Integer.parseInt(br.readLine());
					proceed = false;
				} catch (NumberFormatException e) {
					System.out.println("\nPlease enter valid number");
					br = new BufferedReader(new InputStreamReader(System.in));
				}
			}
			if (numberOfCountries > 0) {
				System.out.println("Please enter the details of the countries in the below format:");
				System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
				Pattern pattern = Pattern.compile("[a-zA-Z\\s]+,[0-9]+,[0-9]+(,[a-zA-Z\\s]+)*");
				String[] continentAndCountryDetails = new String[numberOfCountries];

				for (int i = 0; i < numberOfCountries; i++) {
					String countryDetails = br.readLine();
					Matcher match = pattern.matcher(countryDetails.trim());
					if (match.matches()) {
						Country country = new Country();
						String[] input = countryDetails.split(",");
						if (alreadyDefinedContinent(input[3].trim())) {
							String details[] = countryDetails.split(",");
							continentAndCountryDetails[i] = countryDetails;
							country.setName(details[0]);
							country.setxValue(details[1]);
							country.setyValue(details[2]);
							Continent continent = new Continent();
							continent.setName(details[3]);
							listOfContinents.forEach(c -> {
								if (c.getContinentName().equalsIgnoreCase(details[3])) {
									continent.setControlValue(c.getControlValue());
								}
							});
							country.setPartOfContinent(continent);
							country.setContinent(details[3]);

							ArrayList<String> adjacentCountries = new ArrayList<>();
							for (Country availableCountry : listOfCountries) {
								if (availableCountry.getName().equalsIgnoreCase(details[0])) {
									index = listOfCountries.indexOf(availableCountry);
									listOfCountries.set(index, country);
									countryexist = true;
								}
							}
							if (!countryexist) {
								listOfCountries.add(country);
								index = listOfCountries.indexOf(country);
							}
							for (int j = 4; j < details.length; j++) {
								adjacentCountries.add(details[j]);
								checkandupdateAdjacentCountries(details[j], country.getName());
							}
							country.setAdjacentCountries(adjacentCountries);
							listOfCountries.set(index, country);

						} else {
							System.out.println(
									"\nContinent " + input[3].trim() + "is not available in current continents list");
							System.out.println("Please enter valid continent name while providing country details\n");
							--i;
							continue;
						}
					} else {
						System.out.println("\nInvalid pattern");
						System.out.println("Please enter details in valid pattern\n");
						--i;
						continue;
					}

				}
				mapGraph.setCountries(listOfCountries);
				mapGraph.setCountOfCountries(numberOfCountries);
				System.out.println("\nCountries Added Successfully.\n");

			} else {
				System.out.println("\nNumber of Countries should be greater than zero\n");
				setCountryDetails();
			}
		} else {
			System.out.println("\nContinents are not defined for map yet. Initalize Continents in Map first");
		}
	}

	public boolean alreadyDefined(String tempcontinentName) {
		try {
			String workingDir = System.getProperty("user.dir");
			File file = new File(workingDir + "\\src\\com\\riskgame\\maps\\" + fileName);

			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.split("=")[0];
				if (line.equals(tempcontinentName)) {
					return true;
				} else {
					continue;
				}
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", fileName);
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean alreadyDefinedContinent(String continentName) {
		boolean flag = false;
		if (listOfContinents != null) {
			for (Continent continent : listOfContinents) {
				if (continent.getContinentName().equalsIgnoreCase(continentName)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public void checkandupdateAdjacentCountries(String forcountry, String adcountry) {

		boolean flag = false;
		Country countryupdated = null;
		for (Country country : listOfCountries) {
			if (country.getName().equalsIgnoreCase(forcountry)) {
				if (!country.getAdjacentCountries().contains(adcountry)) {
					country.getAdjacentCountries().add(adcountry);
					countryupdated = country;

				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			// System.out.println("Country updated Successfully");
			// listOfCountries.add(index, countryupdated);
			countryupdated = new Country();
			ArrayList<String> adjacentCountry = new ArrayList<>();
			countryupdated.setName(forcountry);
			adjacentCountry.add(adcountry);
			countryupdated.setAdjacentCountries(adjacentCountry);
			listOfCountries.add(countryupdated);
			// System.out.println(listOfCountries.indexOf(countryupdated));
		}

		// return false;
	}

	public boolean isCountryInAdjacentCountryList(String[] input) {
		String country = input[0];

		for (int i = 4; i < input.length; i++) {
			if (country.equals(input[i]))
				return true;
			else
				continue;
		}
		return false;
	}

	public void deleteContinent() {
		boolean removed = false;
		// listOfContinents = new ArrayList<>();
		// listOfCountries = new ArrayList<>();
		listOfContinents = mapGraph.getContinents();
		listOfCountries = mapGraph.getCountries();
		ArrayList<String> deletedCountries = new ArrayList<>();

		if (listOfContinents != null && !listOfContinents.isEmpty()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the continent to be removed from the map:");
			String deleteContinent = new String();
			try {
				deleteContinent = br.readLine().trim();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (deleteContinent.isEmpty()) {
				System.out.println(
						"Sorry! The entered continent name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks) \n");
			}
			for (Continent continent : listOfContinents) {
				if (continent.getContinentName().equalsIgnoreCase(deleteContinent)) {
					listOfContinents.remove(continent);
					removed = true;
					break;
				}
			}
			if (listOfCountries != null && !listOfCountries.isEmpty()) {
				// for (Country country : listofCountries)
				Iterator<Country> CountryIT = listOfCountries.iterator();
				while (CountryIT.hasNext()) {
					Country country = CountryIT.next();
					if (country.getPartOfContinent() != null
							&& country.getPartOfContinent().getContinentName().equalsIgnoreCase(deleteContinent)) {
						deletedCountries.add(country.getName());
						// listofCountries.remove(country);
						CountryIT.remove();
					}

				}
				mapGraph.setCountries(listOfCountries);
				for (String name : deletedCountries) {
					if (!listOfCountries.isEmpty() && listOfCountries != null) {
						Iterator<Country> CountryIT1 = listOfCountries.iterator();
						// for (Country country : listofCountries)
						while (CountryIT1.hasNext()) {
							Country country = CountryIT1.next();
							ArrayList<String> adjacentCountries = country.getAdjacentCountries();
							// for (Country country : listofCountries)
							Iterator<String> adjacentIT = adjacentCountries.iterator();
							while (adjacentIT.hasNext()) {
								if (adjacentIT.next().equalsIgnoreCase(name)) {
									// adjacentCountries.remove(adjacentCountryName);
									adjacentIT.remove();
									// country.setAdjacentCountries(adjacentCountries);
									break;
								}
							}

							// listofCountries.add(country);

						}
					}
				}
				mapGraph.setCountries(listOfCountries);
				// System.out.println("\n " + deleteContinent + " deleted successfully");
			}
			mapGraph.setContinents(listOfContinents);
			System.out.println("\n " + deleteContinent + " deleted successfully");
			if (removed == false)
				System.out.println("Continent " + deleteContinent + " does not exist in the Map");

		} else {
			System.out.println(
					"No Continents are defined for map yet. To perform this opertaion map should have atleast one continent defined");
		}
	}

	public void deleteCountry() {
		boolean removed = false;
		// listOfCountries = new ArrayList<>();
		listOfCountries = mapGraph.getCountries();
		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String deleteCountry = new String();
			while (deleteCountry.isEmpty()) {
				System.out.println("Enter the country to be removed from the map:");

				try {
					deleteCountry = br.readLine().trim();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (deleteCountry.isEmpty())
					System.out.println(
							"Sorry! The entered continent name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks) \n");

			}
			// for (Country country : listofCountries) {
			Iterator<Country> CountryIT = listOfCountries.iterator();
			while (CountryIT.hasNext()) {
				Country country = CountryIT.next();
				if (country.getName().equalsIgnoreCase(deleteCountry)) {
					Iterator<Country> CountryIT1 = listOfCountries.iterator();
					// for (Country country : listofCountries)
					while (CountryIT1.hasNext()) {
						Country deletecountry = CountryIT1.next();
						// for (Country deletecountry : listofCountries) {
						// int index = listofCountries.indexOf(deletecountry);
						ArrayList<String> adjacentCountries = deletecountry.getAdjacentCountries();
						// for (String adjacentCountryName : adjacentCountries) {
						Iterator<String> adjacentIT = adjacentCountries.iterator();
						while (adjacentIT.hasNext()) {
							if (adjacentIT.next().equalsIgnoreCase(deleteCountry)) {
								adjacentIT.remove();
							}
						}

						// deletecountry.setAdjacentCountries(adjacentCountries);
						// listofCountries.add(deletecountry);
						// listofCountries.add(index, country);
					}
					CountryIT.remove();
					mapGraph.setCountries(listOfCountries);
					System.out.println("\n " + deleteCountry + " deleted successfully");
					removed = true;
					break;
				}

			}
			if (removed == false)
				System.out.println("Country " + deleteCountry + " does not exist in the Map");
			/*
			 * else { if(!listofCountries.isEmpty()) { for (Country country:
			 * listofCountries) { int index=listofCountries.indexOf(country);
			 * ArrayList<String> adjacentCountries = country.getAdjacentCountries();
			 * for(String adjacentCountryName :adjacentCountries) {
			 * if(adjacentCountryName.equalsIgnoreCase(deleteCountry)) {
			 * adjacentCountries.remove(adjacentCountryName); } }
			 * 
			 * country.setAdjacentCountries(adjacentCountries);
			 * listofCountries.add(country); //listofCountries.add(index, country);
			 * 
			 * } mapGraph.setCountries(listofCountries);
			 * 
			 * } }
			 */
		} else {
			System.out.println(
					"No Countries are defined for map yet. To perform this opertaion map should have atleast one country defined");
		}
	}

	public void addAdjacency() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Country> countrylist = mapGraph.getCountries();
		System.out.println("Enter provide name of two countries to be connected");
		System.out.println("Country 1:");
		String country1 = br.readLine().trim();
		System.out.println("Country 2:");
		String country2 = br.readLine().trim();
		boolean flag1 = false, flag2 = false;
		ArrayList<Country> checklist = new ArrayList<Country>();

		if (countrylist != null) {
			for (Country country : countrylist) {
				if (country.getName().equalsIgnoreCase(country1)) {
					flag1 = true;
					checklist.add(country);
				}
				if (country.getName().equalsIgnoreCase(country2)) {
					flag2 = true;
					checklist.add(country);
				}
			}

			if (flag1 && flag2) {
				ArrayList<String> adjacent1 = checklist.get(0).getAdjacentCountries();
				ArrayList<String> adjacent2 = checklist.get(1).getAdjacentCountries();
				if (adjacent1.contains(checklist.get(1).getName()) && adjacent2.contains(checklist.get(0).getName())) {
					System.out.println("Both the Countries provided are already adjacent countries");
				} else {
					if (!adjacent1.contains(checklist.get(1).getName())) {
						adjacent1.add(checklist.get(1).getName());
						checklist.get(0).setAdjacentCountries(adjacent1);
						// countrylist.add(checklist.get(0));

					}
					if (!adjacent2.contains(checklist.get(0).getName())) {
						adjacent2.add(checklist.get(0).getName());
						checklist.get(1).setAdjacentCountries(adjacent2);
						// countrylist.add(checklist.get(1));
					}
					System.out.println("Countries are linked and are now adjacent countries");
				}
			}
			if (flag1 == false)
				System.out.println("Invalid !! Country provided " + country1 + " is not avalable in Map");
			if (flag2 == false)
				System.out.println("Invalid !! Country provided " + country2 + " is not avalable in Map");
		}
	}

	public void removeAdjacency() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Country> countrylist = mapGraph.getCountries();
		System.out.println("Enter provide name of two countries to be connected");
		System.out.println("Country 1:");
		String country1 = br.readLine().trim();
		System.out.println("Country 2:");
		String country2 = br.readLine().trim();
		boolean flag1 = false, flag2 = false;
		ArrayList<Country> checklist = new ArrayList<Country>();

		if (countrylist != null) {
			for (Country country : countrylist) {
				if (country.getName().equalsIgnoreCase(country1)) {
					flag1 = true;
					checklist.add(country);
				}
				if (country.getName().equalsIgnoreCase(country2)) {
					flag2 = true;
					checklist.add(country);
				}
			}

			if (flag1 && flag2) {
				ArrayList<String> adjacent1 = checklist.get(0).getAdjacentCountries();
				ArrayList<String> adjacent2 = checklist.get(1).getAdjacentCountries();
				if (adjacent1.contains(checklist.get(1).getName()) && adjacent2.contains(checklist.get(0).getName())) {
					if (adjacent1.contains(checklist.get(1).getName())) {
						adjacent1.remove(checklist.get(1).getName());
						checklist.get(0).setAdjacentCountries(adjacent1);
//						countrylist.add(checklist.get(0));

					}
					if (adjacent2.contains(checklist.get(0).getName())) {
						adjacent2.remove(checklist.get(0).getName());
						checklist.get(1).setAdjacentCountries(adjacent2);
//						countrylist.add(checklist.get(1));
					}
					System.out.println("\nRemoved. Countries are no more linked or adjacent countries");

				} else {
					System.out.println("The Countries provided are already not adjacent to each other");
				}
			}
			if (flag1 == false)
				System.out.println("Invalid !! Country provided " + country1 + " is not avalable in Map");
			if (flag2 == false)
				System.out.println("Invalid !! Country provided " + country2 + " is not avalable in Map");

		}
	}

	public boolean checkandSave() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		listOfCountries = new ArrayList<Country>();
		listOfCountries = mapGraph.getCountries();
		listOfContinents = mapGraph.getContinents();
		ArrayList<String> availableContinents = new ArrayList<>();
		String error = new String();
		String aderror = new String(), conterror = new String(), adjacencyError = new String();
		ArrayList<String> adjacentCountries = new ArrayList<>();
		setOfCountries = new HashMap<>();
		// HashMap<String, ArrayList<String>> visited = new HashMap<String,
		// ArrayList<String>>();

		boolean flag = false, flag2 = true, flag3 = true, flag5 = true, flag6 = false;

		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			for (Country country : listOfCountries) {
				if (country.getxValue() == null && country.getyValue() == null
						&& country.getPartOfContinent() == null) {
					flag2 = false;
					error = error.concat("!! For " + country.getName()
							+ " X and Y offset Values and Continent is not defined.Country was created while adjacent country was being defined for another country.\n");
				}
				adjacentCountries = country.getAdjacentCountries();
				if (!adjacentCountries.isEmpty()) {
					// System.out.println(country);
					for (String name : adjacentCountries) {
						for (Country country2 : listOfCountries) {
							if (country2.getName().equals(name)) {
								if (country2.getAdjacentCountries().contains(country.getName())) {
									flag = true;
									break;
								} else {
									flag = false;
								}
							} else
								flag = false;
//							if (country2.getPartOfContinent().getContinentName()
//									.equalsIgnoreCase((country.getPartOfContinent().getContinentName()))) {
//								flag6 = true;
//								break;
//							}
						}
//						if (flag6)
//							break;
						if (flag == false) {
							aderror = aderror.concat("!! " + country.getName() + " and " + name
									+ " are not defined properly as adjacent countries on " + name + " end.\n");
						}
					}
//					if (flag6)
//						break;
//					else {
//						adjacencyError = "!! Countries of the two defined continents are not adjacent. This is not a connected graph.\n";
//					}
				} else {
					// System.out.println("false"+country);
					flag5 = false;
					aderror = error.concat("!! " + country.getName()
							+ " does not have any Adjacents Countries. Should have atleast one adjacent country to play the game \n");
				}

			}

		}
		if (listOfContinents.size() < 2) {
			flag3 = false;
			conterror = "!! Minimum number of continents should be two to play the game. PLease add one more country and respective countries.\n";
		}

		listOfCountries.forEach(country -> {
			if (!availableContinents.contains(country.getName())) {
				if (country.getPartOfContinent() != null)
					availableContinents.add(country.getPartOfContinent().getContinentName());
			}
		});

		for (Continent continent : listOfContinents) {
			boolean flag4 = true;
			for (String continentname : availableContinents) {
				if (continent.getContinentName().equalsIgnoreCase(continentname)) {
					flag4 = false;
				}
			}
			if (flag4 == true) {
				flag = false;
				conterror = conterror.concat("!! " + continent.getContinentName()
						+ " does not have any defined Country. Should have atleast one country.\n");
			}
		}

		if (flag == true && flag2 == true && flag3 == true && flag5 == true) {
			if (Thread.currentThread().getStackTrace()[2].getMethodName().equalsIgnoreCase("uploadMap")) {
				System.out.println("\nDo you want to rename the file ? Yes or No ");
				String option = br.readLine();
				if (option.equalsIgnoreCase("yes")) {
					System.out.println("\nPlease enter the new file name to save map file:");
					fileName = br.readLine();

					while (fileName.isEmpty()) {
						System.out.println(
								"Sorry! The entered file name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks).\nPlease enter the file name to save map file:\n");
						fileName = br.readLine();
					}
				} else {
					fileName = mapGraph.getFilename();
				}
			} else {
				System.out.println("\nPlease enter the file name to save map file:");
				fileName = br.readLine();

				while (fileName.isEmpty()) {
					System.out.println(
							"Sorry! The entered file name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks).\nPlease enter the file name to save map file:\n");
					fileName = br.readLine();
				}
			}

			listOfCountries.forEach(country -> {
				setOfCountries.put(country.getName(), country);
				mapGraph.setCountrySet(setOfCountries);
			});

			mapGraph.setFilename(fileName);
			ReadAndWriteMap save = new ReadAndWriteMap();
			save.saveMap(mapGraph);
			System.out.println("Map saved into " + fileName + ".map file");
			return true;

		}

		else {
			System.err.println(
					"Below are the error present in Map.Entry Please resolve all the below issues before saving the Map.\n");
			error = conterror.concat(error).concat(aderror);
			System.err.println(error);
			System.out.flush();
			return false;
		}

	}

	public void printMap() {
		System.out.println(
				"\n##-------------------------------------------------------------------------------------------------------##\n");
		System.out.println("Map Meta Data:");
		if (mapGraph.getMapTag() == null)
			System.out.println("No MetaDta Declared for Map Yet");
		else
			System.out.println(mapGraph.getMapTag());
		System.out.println("\nContinents:");
		if (mapGraph.getContinents() != null && !mapGraph.getContinents().isEmpty()) {
			System.out.println("Number of Continents: " + mapGraph.getContinents().size());
			System.out.println("\nContinent Details:");
			for (int i = 0; i < mapGraph.getContinents().size(); i++) {
				Continent continent = mapGraph.getContinents().get(i);
				System.out.println((i + 1) + ". " + continent.getContinentName() + " , " + continent.getControlValue());
			}
		} else {
			System.out.println("No Continent Defined for Map\n");
		}
		System.out.println("\nCountries:");
		if (mapGraph.getCountries() != null && !mapGraph.getCountries().isEmpty()) {
			System.out.println("\nNumber of Countries: " + mapGraph.getCountries().size());
			System.out.println("\nCountry Details:");
			for (int i = 0; i < mapGraph.getCountries().size(); i++) {
				Country country = mapGraph.getCountries().get(i);

				String print = (i + 1) + ". " + country.getName() + "," + country.getxValue() + ","
						+ country.getyValue() + ",";
				if (country.getPartOfContinent() != null) {
					print = print.concat(
							country.getPartOfContinent().getContinentName() + "," + country.getAdjacentCountries());
				} else
					print = print.concat("<Not Defined>," + country.getAdjacentCountries());

				System.out.println(print);
			}
		} else {
			System.out.println("No Country Defined for Map\n");
		}
		System.out.println(
				"\n##-------------------------------------------------------------------------------------------------------##");
	}

}
