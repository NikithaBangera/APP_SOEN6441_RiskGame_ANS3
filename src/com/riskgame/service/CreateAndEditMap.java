package com.riskgame.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.MapTag;

/**
 * This class aims to create the new map by taking user inputs and also checks
 * the validation of various fields before saving the map. The values are saved
 * in the form of an object and then passed to the called function to perform
 * user asked operation.
 * 
 * @author Shresthi Garg
 *
 */
public class CreateAndEditMap {

	// create a new map
	private GameMapGraph mapGraph = new GameMapGraph();
	private String fileName;
	private ArrayList<Country> listOfCountries;
	private ArrayList<Continent> listOfContinents;
	private HashMap<String, Country> setOfCountries;
	BufferedReader br;
	boolean returnflag = false;

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

	/**
	 * This method is called when the user prompts to create a map. It takes values
	 * from user for Map tag data, continents, country, adjacency and lets user
	 * delete/add the same. Also, lets user view the contents added.
	 * 
	 * @return returnflag of type boolean whose value decides whether the user is
	 *         good to start the start game.
	 * @throws Exception - Input output Exception
	 */
	public boolean newMapCreation() throws Exception {
		boolean exit = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!exit) {
			System.out.println("\nWelcome to Risk Game!");
			System.out.println("\nChoose the below options to create a new map\n");
			System.out.println("1. Enter Map tag data.");
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
				System.out.println("Please enter a valid option(number) from the game menu!");

				option = br.readLine().trim();
				match = pattern.matcher(option.trim());
			}

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
				if (choice.equalsIgnoreCase("yes")) {
					exit = true;
					System.exit(0);
				}
				break;
			default:
				System.out.println("Invalid option. Please choose the correct option.");

			}
		}
		return returnflag;
	}

	/**
	 * This method is called when the user prompts to load a map. It lets user to
	 * edit the map and save the map and continue further with the game if the ap is
	 * valid.
	 * 
	 * @param uploadedmapGraph - It is an object of GameMapGraph class and contains
	 *                         the data retrieved from the loaded file.
	 * @return returnflag of type boolean which decides whether user can start the
	 *         same.
	 * @throws Exception - Input output Exception
	 */
	public boolean uploadMap(GameMapGraph uploadedmapGraph) throws Exception {
		mapGraph = uploadedmapGraph;
		System.out.println("\nUploaded Map Details\n");
		printMap();
		boolean exit = false;
		while (!exit) {
			System.out.println("\nChoose the below options to edit the uploaded map\n");
			System.out.println("1. Enter Map tag data.");
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
				System.out.println("Please enter a valid option(number) from the game menu!");

				option = br.readLine().trim();
				match = pattern.matcher(option.trim());
			}

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
				if (choice.equalsIgnoreCase("yes")) {
					exit = true;
					System.exit(0);
				}
				break;
			default:
				System.out.println("Invalid option. Please choose the correct option.");

			}
		}
		return returnflag;
	}

	/**
	 * This method aims to create map tag data. It prompts the user to enter the
	 * fields related to map tag and also performs various validation with respect
	 * to the map tag. Post validating it stores the details of the map tag in the
	 * GameMapGraph object It returns to the parent function for creating a map.
	 * 
	 * @throws Exception - Input Output Exception
	 */
	public void createMapTag() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please Enter the image name of the map in below format:");
		System.out.println("(Imagename.bmp)\n");
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[_-]*.bmp");
		String name = br.readLine().trim();
		Matcher match = pattern.matcher(name.trim());
		while (!match.matches()) {
			System.out.println("\nPlease enter valid image name.");

			name = br.readLine().trim();
			match = pattern.matcher(name.trim());
		}
		String image = name.trim();

		System.out.println("Please specify scroll is horizontal or vertical");
		String scroll = br.readLine().trim();
		while (!((scroll.equalsIgnoreCase("horizontal")) || (scroll.equalsIgnoreCase("vertical")) || scroll == null)) {
			System.out.println("Invalid/Blank value entered, please enter horizontal or vertical");

			scroll = br.readLine().trim();
		}

		System.out.println("Please specify wrap is Yes or No");
		String wrap = br.readLine().trim();
		while (!((wrap.equalsIgnoreCase("Yes")) || (wrap.equalsIgnoreCase("No")) || wrap == null)) {
			System.out.println("Invalid/Blank value entered, please enter Yes or No");

			wrap = br.readLine().trim();
		}

		System.out.println("Please enter the author name:");
		String author = br.readLine().trim();
		while (author.isEmpty()) {

			System.out.println("Sorry! The entered author name cannot be blank. Please enter again \n");

			author = br.readLine().trim();

		}

		System.out.println("Please specify warn is Yes or No");
		String warn = br.readLine().trim();
		while (!((warn.equalsIgnoreCase("Yes")) || (warn.equalsIgnoreCase("No")) || warn == null)) {
			System.out.println("Invalid/Blank value entered, please enter Yes or No");

			warn = br.readLine().trim();
		}

		MapTag mapTag = new MapTag(author.toUpperCase(), warn.toUpperCase(), image.toUpperCase(), wrap.toUpperCase(),
				scroll.toUpperCase());
		mapGraph.setMapTag(mapTag);

		System.out.println("Map tag data Added successfully.");
	}

	/**
	 * This method aims to create continent details. It prompts the user to enter
	 * the fields related to continent and also performs various validation with
	 * respect to the continent. Post validating it stores the details of the
	 * continent in the GameMapGraph object It returns to the parent function for
	 * creating a map.
	 * 
	 * @throws Exception - Input output Exception
	 */
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
			System.out.println("Invalid input.Please enter valid number of continents!");

			num = br.readLine().trim();
			match = pattern.matcher(num.trim());
		}

		int numberOfContinents = Integer.parseInt(num);

		if (numberOfContinents > 0) {
			System.out.println("Please enter continent details in below format:");
			System.out.println("Continent name=Control Value");
			pattern = Pattern.compile("[a-zA-Z\\s]+=[0-9]+");

			for (int i = 0; i < numberOfContinents; i++) {
				String continentName = br.readLine().trim().toUpperCase();
				match = pattern.matcher(continentName.trim());
				if (match.matches()) {
					Continent continent = new Continent();
					int controlValue = Integer.parseInt(continentName.split("=")[1]);
					continentName = continentName.split("=")[0];
					if (alreadyDefinedContinent(continentName)) {
						System.out.println("Entered Continent already exists.\n Please enter new details :");
						--i;
						continue;
					}
					continent.setName(continentName);
					continent.setControlValue(controlValue);
					listOfContinents.add(continent);
				} else {
					System.out.println(" Invalid continent details\n");
					System.out.println("PLease Enter continent details again:");
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

	/**
	 * This method aims to create country details. It prompts the user to enter the
	 * fields related to country and also performs various validation with respect
	 * to the country. Post validating it stores the details of the country in the
	 * GameMapGraph object It returns to the parent function for creating a map.
	 * 
	 * @throws Exception - Input output Exception
	 */
	public void setCountryDetails() throws Exception {
		listOfContinents = new ArrayList<>();
		listOfCountries = new ArrayList<>();
		int numberOfCountries = 0;
		int index = 0;

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
					System.out.println("\nInvalid Input provided.");

					System.out.println("\nPlease enter valid number of Countries:");
					br = new BufferedReader(new InputStreamReader(System.in));
				}
			}
			if (numberOfCountries > 0) {
				System.out.println("Please enter the details of the countries in the below format:");
				System.out.println("Country Name, X-axis, Y-axis, Continent Name, Adjacent countries separated by ,");
				Pattern pattern = Pattern.compile("[a-zA-Z\\s]+,[0-9]+,[0-9]+(,[a-zA-Z\\s]+)*");
				String[] continentAndCountryDetails = new String[numberOfCountries];

				for (int i = 0; i < numberOfCountries; i++) {
					String countryDetails = br.readLine().trim().toUpperCase();
					Matcher match = pattern.matcher(countryDetails.trim());
					if (match.matches()) {

						Country country = new Country();
						boolean countryexist = false;
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
									break;
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
									"\nContinent " + input[3].trim() + " is not available in current continents list");
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

	/**
	 * This method aims to check whether the continent is already defined by the
	 * user.
	 * 
	 * @param continentName - The continent name entered by the user.
	 * @return flag of type boolean which tells the continent is already defined or
	 *         not.
	 */
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

	/**
	 * This method checks and updates the adjacency between two countries.
	 * 
	 * @param forcountry - The country which has defined an adjacent country.
	 * @param adcountry  - The country which has been defined adjacent.
	 */
	public void checkandupdateAdjacentCountries(String forcountry, String adcountry) {

		boolean flag = false;
		Country countryupdated;
		for (Country country : listOfCountries) {
			if (country.getName().equalsIgnoreCase(forcountry)) {
				if (!country.getAdjacentCountries().contains(adcountry)) {
					country.getAdjacentCountries().add(adcountry);
				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			countryupdated = new Country();
			ArrayList<String> adjacentCountry = new ArrayList<>();
			countryupdated.setName(forcountry);
			adjacentCountry.add(adcountry);
			countryupdated.setAdjacentCountries(adjacentCountry);
			listOfCountries.add(countryupdated);
		}

	}

	/**
	 * 
	 * This method aims to delete a continent. It prompts the user to enter the
	 * continent to be deleted and also performs various validation with respect to
	 * the country. Post validating it stores the details of the country in the
	 * GameMapGraph object It returns to the parent function for creating a map.
	 * 
	 */
	public void deleteContinent() {
		boolean removed = false;
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
				e.printStackTrace();
			}
			while (deleteContinent.isEmpty()) {
				System.out.println(
						"\nSorry! The entered continent name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks) \n");
			}
			for (Continent continent : listOfContinents) {
				if (continent.getContinentName().equalsIgnoreCase(deleteContinent)) {
					listOfContinents.remove(continent);
					removed = true;
					break;
				}
			}
			if (listOfCountries != null && !listOfCountries.isEmpty()) {
				Iterator<Country> CountryIT = listOfCountries.iterator();
				while (CountryIT.hasNext()) {
					Country country = CountryIT.next();
					if (country.getPartOfContinent() != null
							&& country.getPartOfContinent().getContinentName().equalsIgnoreCase(deleteContinent)) {
						deletedCountries.add(country.getName());

						CountryIT.remove();
					}

				}
				mapGraph.setCountries(listOfCountries);
				for (String name : deletedCountries) {
					if (!listOfCountries.isEmpty() && listOfCountries != null) {
						Iterator<Country> CountryIT1 = listOfCountries.iterator();
						while (CountryIT1.hasNext()) {
							Country country = CountryIT1.next();
							ArrayList<String> adjacentCountries = country.getAdjacentCountries();
							Iterator<String> adjacentIT = adjacentCountries.iterator();
							while (adjacentIT.hasNext()) {
								if (adjacentIT.next().equalsIgnoreCase(name)) {
									adjacentIT.remove();
									break;
								}
							}
						}
					}
				}
				mapGraph.setCountries(listOfCountries);
			}

			if (!removed)
				System.out.println("Continent " + deleteContinent + " does not exist in the Map");
			else {
				mapGraph.setContinents(listOfContinents);
				System.out.println("\n " + deleteContinent + " deleted successfully");
			}

		} else {
			System.out.println(
					"No Continents are defined for map yet. To perform this opertaion map should have atleast one continent defined");
		}
	}

	/**
	 * 
	 * This method aims to delete a country. It prompts the user to enter the
	 * country to be deleted and also performs various validation with respect to
	 * the country. Post validating it stores the details of the country in the
	 * GameMapGraph object It returns to the parent function for creating a map.
	 * 
	 */
	public void deleteCountry() {
		boolean removed = false;
		listOfCountries = mapGraph.getCountries();
		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String deleteCountry = new String();
			while (deleteCountry.isEmpty()) {
				System.out.println("Enter the country to be removed from the map:");

				try {
					deleteCountry = br.readLine().trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (deleteCountry.isEmpty())
					System.out.println(
							"Sorry! The entered continent name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks) \n");

			}

			Iterator<Country> CountryIT = listOfCountries.iterator();

			while (CountryIT.hasNext()) {
				Country country = CountryIT.next();
				if (country.getName().equalsIgnoreCase(deleteCountry)) {
					Iterator<Country> CountryIT1 = listOfCountries.iterator();

					while (CountryIT1.hasNext()) {
						Country deletecountry = CountryIT1.next();
						ArrayList<String> adjacentCountries = deletecountry.getAdjacentCountries();
						Iterator<String> adjacentIT = adjacentCountries.iterator();

						while (adjacentIT.hasNext()) {
							if (adjacentIT.next().equalsIgnoreCase(deleteCountry)) {
								adjacentIT.remove();
							}
						}
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
		} else {
			System.out.println(
					"No Countries are defined for map yet. To perform this opertaion map should have atleast one country defined");
		}
	}

	/**
	 * This method aims to add adjacency between two countries. It prompts the user
	 * to enter the the countries they wish to be adjacent and also performs various
	 * validation with respect to the country. Post validating it stores the details
	 * of the country in the GameMapGraph object It returns to the parent function
	 * for creating a map.
	 * 
	 * @throws IOException - Input output Exception
	 */
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
		if (!country1.equalsIgnoreCase(country2)) {
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
					if (adjacent1.contains(checklist.get(1).getName())
							&& adjacent2.contains(checklist.get(0).getName())) {
						System.out.println("\nBoth the Countries provided are already adjacent countries");
					} else {
						if (!adjacent1.contains(checklist.get(1).getName())) {
							adjacent1.add(checklist.get(1).getName());
							checklist.get(0).setAdjacentCountries(adjacent1);

						}
						if (!adjacent2.contains(checklist.get(0).getName())) {
							adjacent2.add(checklist.get(0).getName());
							checklist.get(1).setAdjacentCountries(adjacent2);
						}
						System.out.println("\n\nCountries are linked and are now adjacent countries");
					}
				}
				if (!flag1)
					System.out.println("\nInvalid !! Country provided " + country1 + " is not avalable in Map");
				if (!flag2)
					System.out.println("\nInvalid !! Country provided " + country2 + " is not avalable in Map");
			} else {
				System.out.println(
						"\nNo Countries are defined in the Map yet. Countries should be defined first to perform this operation");
			}
		} else {
			System.out.println(
					"\nInvalid !! Both the countries provided are same.Adjacencies are for two different countries.");
		}

	}

	/**
	 * This method aims to remove adjacency between two countries. It prompts the
	 * user to enter the the countries they wish to not be adjacent and also
	 * performs various validation with respect to the country. Post validating it
	 * stores the details of the country in the GameMapGraph object It returns to
	 * the parent function for creating a map.
	 * 
	 * @throws IOException - Input output Exception
	 */
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

		if (!country1.equalsIgnoreCase(country2)) {
			if (countrylist != null && !countrylist.isEmpty()) {
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
					if (adjacent1.contains(checklist.get(1).getName())
							&& adjacent2.contains(checklist.get(0).getName())) {
						if (adjacent1.contains(checklist.get(1).getName())) {
							adjacent1.remove(checklist.get(1).getName());
							checklist.get(0).setAdjacentCountries(adjacent1);

						}
						if (adjacent2.contains(checklist.get(0).getName())) {
							adjacent2.remove(checklist.get(0).getName());
							checklist.get(1).setAdjacentCountries(adjacent2);
						}
						System.out.println("\nRemoved. Countries are no more linked or adjacent countries");

					} else {
						System.out.println("\nThe Countries provided are already not adjacent to each other");
					}
				}
				if (!flag1)
					System.out.println("\nInvalid !! Country provided " + country1 + " is not avalable in Map");
				if (!flag2)
					System.out.println("\nInvalid !! Country provided " + country2 + " is not avalable in Map");

			} else {
				System.out.println(
						"\nNo Countries are defined in the Map yet. Countries should be defined to perform this operation");
			}
		} else {
			System.out.println(
					"\nInvalid !! Both the countries provided are same.Adjacencies are for two different countries.");
		}
	}

	/**
	 * This method aims to perform various validation with respect to the saving a
	 * map info entered by user during create to the map. Post validating it throws
	 * the error for the user to resolve and returns the control back to the calling
	 * method.
	 * 
	 * @return flag- Boolean type, which determines whether we can allow the player
	 *         to go ahead and play the game.
	 * @throws IOException - Input output Exception
	 */
	public boolean checkandSave() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		listOfCountries = mapGraph.getCountries();
		listOfContinents = mapGraph.getContinents();
		ArrayList<String> availableContinents = new ArrayList<>();
		String error = new String(), nullError = new String();
		String adjacencyError = new String(), continentError = new String(), countryError = new String(),
				connectedError = new String(), mapTagError = new String();
		ArrayList<String> adjacentCountries = new ArrayList<>();
		setOfCountries = new HashMap<>();
		ArrayList<String> connectivity = new ArrayList<>();

		boolean flag = false, countrydataFlag = true, continentFlag = true, adjacencyFlag = true, mapTagFlag = true,
				connectedFlag = true;

		if (mapGraph.getMapTag() == null) {
			mapTagFlag = false;
			mapTagError = "!! No map tag data defined for the map.\n";
		}

		if (listOfCountries != null && !listOfCountries.isEmpty()) {

			for (Country country : listOfCountries) {
				if (country.getxValue() == null && country.getyValue() == null
						&& country.getPartOfContinent() == null) {
					countrydataFlag = false;
					countryError = countryError.concat("!! For " + country.getName()
							+ " X and Y offset Values and Continent is not defined.Country was created while adjacent country was being defined for another country.\n");
				}
				adjacentCountries = country.getAdjacentCountries();
				if (!adjacentCountries.isEmpty()) {
					for (String name : adjacentCountries) {
						for (Country country2 : listOfCountries) {
							if (country2.getName().equals(name)) {
								if (country2.getAdjacentCountries().contains(country.getName())) {
									flag = true;

									// To check for Connectivity of the Graph adding the connected continents in the
									// list
									if (country.getPartOfContinent() != null && country2.getPartOfContinent() != null) {
										if (!country2.getPartOfContinent().getContinentName()
												.equals(country.getPartOfContinent().getContinentName())) {
											connectivity.add(country2.getPartOfContinent().getContinentName());
											connectivity.add(country.getPartOfContinent().getContinentName());
										}
									}

									break;

								} else {
									flag = false;
								}
							} else
								flag = false;
						}
						if (!flag) {
							adjacencyFlag = false;
							adjacencyError = adjacencyError.concat("!! " + country.getName() + " and " + name
									+ " are not defined properly as adjacent countries on " + name + " end.\n");
						}
					}
				} else {
					adjacencyFlag = false;
					adjacencyError = adjacencyError.concat("!! " + country.getName()
							+ " does not have any Adjacents Countries. Should have atleast one adjacent country to play the game \n");
				}
			}
		} else {
			nullError = "!! Not a single Country is defined in the map.Please define atleast one country for each continents.\n";
			flag = false;
		}

		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			listOfCountries.forEach(country -> {
				if (!availableContinents.contains(country.getName())) {
					if (country.getPartOfContinent() != null)
						availableContinents.add(country.getPartOfContinent().getContinentName());
				}
			});
		}
		if (listOfContinents != null) {
			if (listOfContinents.size() < 2) {
				continentFlag = false;
				continentError = "!! Minimum number of continents should be two to play the game. PLease add one more country and respective countries.\n";
			}

			for (Continent continent : listOfContinents) {
				boolean flag4 = true;
				for (String continentname : availableContinents) {
					if (continent.getContinentName().equalsIgnoreCase(continentname)) {
						flag4 = false;
					}
				}
				if (flag4) {
					continentFlag = false;
					continentError = continentError.concat("!! " + continent.getContinentName()
							+ " does not have any defined Country. Should have atleast one country.\n");
				}
				flag4 = true;
				for (String connectedContinent : connectivity) {
					if (connectedContinent.equalsIgnoreCase(continent.getContinentName())) {
						flag4 = false;
					}
				}
				if (flag4) {
					connectedFlag = false;
					connectedError = connectedError.concat("  # Countries from " + continent.getContinentName()
							+ " are not connected to any of the countries of the other " + (listOfContinents.size() - 1)
							+ " available continents.\n");
				}

			}

		} else {
			flag = false;
			nullError = nullError.concat(
					"!! Not a Single continent is defined in the map.Please define minimum of two continents. \n");
		}

		if (flag && countrydataFlag && continentFlag && adjacencyFlag /* && flag7 */ && mapTagFlag) {
			if (connectedFlag) {
				String oldFileName = new String();
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
						oldFileName = mapGraph.getFilename();
					} else {
						fileName = mapGraph.getFilename();
						oldFileName = mapGraph.getFilename();
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
				save.saveMap(mapGraph, oldFileName);
				return true;

			} else {

				connectedError = "\n!! Map Graph is not Connected - \n".concat(connectedError)
						.concat("Please resolve above connectivity issue\n");
				error = mapTagError.concat(nullError).concat(continentError).concat(countryError).concat(adjacencyError)
						.concat(connectedError);
				System.out.println(error);
				return false;
			}
		}

		else {
			System.out.println(
					"Below are the error present in Map.Entry Please resolve all the below issues before saving the Map.\n");
			error = mapTagError.concat(nullError).concat(continentError).concat(countryError).concat(adjacencyError);
			System.out.println(error);
			return false;
		}

	}

	/**
	 * 
	 * This method aims to print the content of the map over the console.
	 * 
	 */
	public void printMap() {
		System.out.println(
				"\n------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"\n------------------------------------------------------------------------------------------------------------");
		System.out.println("Map Meta Data:");
		if (mapGraph.getMapTag() == null)
			System.out.println("No Map Tag data Declared for Map Yet");
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
				"\n------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"\n------------------------------------------------------------------------------------------------------------");
	}

}
