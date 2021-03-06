package com.riskgame.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.riskgame.model.Continent;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.MapTag;

/**
 * This class aims to load and existing map in the game and performs various
 * validation to check if its a valid map.Post loading user can also edit a map.
 * The values are saved in the form of an object and then passed to the called
 * function to perform user asked operation.
 * 
 * @author Shresthi Garg
 *
 */
public class LoadMapController {

	static String Delimiter = ",";
	private GameMapGraph mapGraph;
	private static String error, adjancencyerror;
	private MapTag mapTag;
	private ArrayList<Continent> listOfContinent;
	private ArrayList<Country> listOfCountries;

	public GameMapGraph getMapGraph() {
		return mapGraph;
	}

	public void setMapGraph(GameMapGraph mapGraph) {
		this.mapGraph = mapGraph;
	}

	public static String getError() {
		return error;
	}

	public static void setError(String error) {
		LoadMapController.error = error;
	}

	/**
	 * This method aims to save the map which was edited post loading into the game.
	 * It also checks if the user wishes to rename the file and performs various
	 * validation with respect to it.
	 * 
	 * @param mapgraph    - GameMapGraph object containing the details of the loaded map.
	 * @param oldFileName - The file name of the loaded map.
	 * @throws IOException - throws input output
	 */
	public void saveMap(GameMapGraph mapgraph, String oldFileName) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "/resources/maps/" + mapgraph.getFilename() + ".map");
		File oldFile = new File(workingDir + "/resources/maps/" + oldFileName + ".map");
		PrintWriter outputStream;
		String newFileName;
		// Upload
		if (!oldFileName.isEmpty()) {
			if (!oldFileName.equalsIgnoreCase(mapgraph.getFilename())) {
				while (file.exists()) {
					System.out.println(
							"\nFile with same name already exist. Please provide another file name to save map file:");
					newFileName = br.readLine();

					while (newFileName.isEmpty()) {
						System.out.println(
								"Sorry! File name cannot be blank.Provided contains only whitespace (ie. spaces, tabs or line breaks).\nPlease enter valid file name to save map file:\n");
						newFileName = br.readLine();
					}
					file = new File(workingDir + "/resources/maps/" + newFileName + ".map");
				}

				oldFile.renameTo(file);
				outputStream = new PrintWriter(file);
			} else {
				oldFile.renameTo(file);
				outputStream = new PrintWriter(file);
			}
		}
		// Create
		else {
			outputStream = new PrintWriter(file);
		}

		// Writing Map Meta data file
		MapTag maptag = mapgraph.getMapTag();
		outputStream.println("[Map]");
		outputStream.println("Author=" + maptag.getAuthorName());
		outputStream.println("Image Name=" + maptag.getImageName());
		outputStream.println("Warn=" + maptag.getWarn());
		outputStream.println("Scroll=" + maptag.getScroll());
		outputStream.println("Wrap=" + maptag.getWrap());

		// Writing Continents into file
		outputStream.println("\n[Continent]");
		for (Continent continent : mapgraph.getContinents()) {
			outputStream.println(continent.getContinentName() + "=" + continent.getControlValue());
		}

		// Writing Countries into file
		outputStream.println("\n[Country]");
		for (Country country : mapgraph.getCountries()) {
			String detail = country.getName() + Delimiter + country.getxValue() + Delimiter + country.getyValue()
					+ Delimiter + country.getPartOfContinent().getContinentName();
			for (String adcountry : country.getAdjacentCountries()) {
				detail = detail.concat(Delimiter + adcountry);
			}
			outputStream.println(detail);
		}

		outputStream.close();
		System.out.println("Map saved into " + file.getName() + " file");

	}

	/**
	 * This method is called when the user wishes to play game by uploading a map.
	 * It performs validation and then stores the details of the valid file in the
	 * form of GameMapGraph object.
	 * 
	 * @param fileName - The file which was uploaded.
	 * @return flag - To determine if the returned content is valid.
	 * @throws IOException -throws input output
	 */
	public boolean uploadMap(String fileName) throws IOException {

		BufferedReader read = new BufferedReader(new FileReader(fileName));
		String inputText = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
		String invalidtagerror = new String();
		error = new String();
		if (!inputText.trim().isEmpty()) {
			mapGraph = new GameMapGraph();
			Pattern p = Pattern.compile("\\n\\n");
			String[] result = p.split(inputText);
			ArrayList<String> visitedtag = new ArrayList<String>();
			boolean invalidtag = true, duplicatedata = true, validatemetadata = true, validatecontinentdata = true,
					validatecountrydata = true;

			for (String tagdetails : result) {
				String tag = tagdetails.split("\\n")[0].trim();
				if (tag.equalsIgnoreCase("[Map]") || tag.equalsIgnoreCase("[Continent]")
						|| tag.equalsIgnoreCase("[Country]")) {
					if (tag.equalsIgnoreCase("[Map]")) {
						if (!visitedtag.contains(tag)) {
							if (validatemetadata(tagdetails)) {
								mapGraph.setMapTag(mapTag);
								visitedtag.add(tag);
							} else
								validatemetadata = false;
						} else {
							error = error.concat("!! Duplicate Entry for [Map] Tag Found.\n");
							duplicatedata = false;
						}

					} else if (tag.equalsIgnoreCase("[Continent]")) {
						if (!visitedtag.contains(tag)) {
							if (validatecontinents(tagdetails)) {
								mapGraph.setContinents(listOfContinent);
								visitedtag.add(tag);
							} else
								validatecontinentdata = false;
						} else {
							error = error.concat("!! Duplicate Entry for [Continent] Tag Found.\n");
							validatecontinentdata = false;
						}

					} else if (tag.equalsIgnoreCase("[Country]")) {
						if (!visitedtag.contains(tag)) {
							if (validatecountries(tagdetails)) {
								mapGraph.setCountries(listOfCountries);
								visitedtag.add(tag);
							} else
								validatecountrydata = false;
						} else {
							error = error.concat("!! Duplicate Entry for [Country] Tag Found.\n");
							validatecountrydata = false;
						}
					}
				} else {
					invalidtagerror = invalidtagerror.concat("!! Invalid " + tag + " found.\n");
					invalidtag = false;
				}
			}

			if (invalidtag == true && duplicatedata == true && validatemetadata == true && validatecontinentdata == true
					&& validatecountrydata == true) {
				if (checkcountryadjancy()) {
					read.close();
					return true;
				} else {
					error = "** Uploaded Map have below provided error. Please resolve below errors and try uploading again.\n\n";
					error = error.concat(adjancencyerror);
					read.close();
					return false;
				}

			} else {

				error = invalidtagerror.concat(error);
				error = "** Uploaded Map have below provided error. Please resolve below errors and try uploading again.\n"
						+ error;
				read.close();
				return false;
			}
		} else {
			error = "File Provided is Empty.\n";
			read.close();
			return false;
		}

	}

	/**
	 * This method aims validate list of continents available in the uploaded file
	 * and checks if they are in correct format.
	 * 
	 * @param tagData Value of the continent tag
	 * @return flag of type boolean determines if the entered continents are valid
	 */
	public boolean validatecontinents(String tagData) {

		listOfContinent = new ArrayList<Continent>();
		HashMap<String, String> visitedcontinent = new HashMap<String, String>();
		boolean duplicatedata = true, formatdata = true;
		String formaterror = new String();
		String Duplicateerror = new String();
		String[] metaData = tagData.split("\n");
		if (metaData.length != 1) {
			for (int i = 1; i < metaData.length; i++) {
				String data = metaData[i].trim().toUpperCase();
				Pattern pattern = Pattern.compile("[a-zA-Z\\s]+=[0-9]+");
				if (!data.trim().isEmpty()) {
					Continent continent = new Continent();
					Matcher match = pattern.matcher(data.trim());
					if (!match.matches()) {
						formaterror = formaterror.concat("!! Invalid Continent Details. " + data.trim()
								+ " is not defined in required format. Format required is: Field<Name>=Value<Control Value in digits >.\n");
						formatdata = false;
					} else {
						if (visitedcontinent.containsKey(data.split("=")[0])) {
							Duplicateerror = Duplicateerror.concat(
									"!! " + data.split("=")[0] + " is already defined. Duplicate Entry Found as "
											+ visitedcontinent.get(data.split("=")[0]) + " and " + data + ".\n");
							duplicatedata = false;
						} else {
							String field = data.split("=")[0];
							int value = Integer.parseInt(data.split("=")[1]);
							continent.setName(field);
							continent.setControlValue(value);
							listOfContinent.add(continent);
							visitedcontinent.put(field, data);
						}
					}
				}
			}
			if (duplicatedata && formatdata) {
				return true;
			} else {
				error = error.concat(formaterror).concat(Duplicateerror);
				return false;
			}
		} else {
			error = error.concat("!! No Continents defined under Countinent tag.\n");
			return false;
		}
	}

	/**
	 * This method aims validate list of countries available in the uploaded file
	 * and checks if they are in correct format.
	 * 
	 * @param tagData Value of the countries tag
	 * @return flag of type boolean determines if the entered countries are valid
	 */
	public boolean validatecountries(String tagData) {

		listOfCountries = new ArrayList<Country>();
		HashMap<String, String> visited = new HashMap<String, String>();
		boolean duplicatedata = true, formatdata = true, adjacentdata = true, continentdata = true;
		ArrayList<String> adjacentcountries;
		String formaterror = new String(), Duplicateerror = new String(), continenterror = new String(),
				adjacencyerror = new String();
		String[] countryData = tagData.split("\\n");
		for (int i = 1; i < countryData.length; i++) {
			String data = countryData[i].trim().toUpperCase();
			Pattern pattern = Pattern.compile("[a-zA-Z\\s]+,[0-9]+,[0-9]+,[a-zA-Z\\s]+(,[a-zA-Z\\s]+)*");
			if (!data.trim().isEmpty()) {
				Matcher match = pattern.matcher(data.trim());
				if (!match.matches()) {
					formaterror = formaterror.concat(
							"!! Invalid Country Details. " + data.trim() + " is not defined in required format.\n");
					formatdata = false;
					continue;
				}

				else {
					if (visited.containsKey(data.split(",")[0])) {
						Duplicateerror = Duplicateerror
								.concat("!! " + data.split(",")[0] + " is already defined. Duplicate Entry Found as "
										+ visited.get(data.split("=")[0]) + " and " + data + ".\n");
						duplicatedata = false;
						continue;
					} else {
						Country country = new Country();
						String[] countrydetail = data.split(",");
						String name = countrydetail[0];
						String xOffset = countrydetail[1];
						String yOffset = countrydetail[2];

						boolean continentAvailable = false;
						if (listOfContinent != null && !listOfContinent.isEmpty()) {
							Continent newcontinent;

							for (Continent continent : listOfContinent) {
								if (continent.getContinentName().equalsIgnoreCase(data.split(",")[3])) {
									newcontinent = continent;
									country.setPartOfContinent(newcontinent);
									continentAvailable = true;
									continentdata = true;
								}

							}
						} else {
							continenterror = continenterror.concat(
									"!! No Valid continents available. Declare Valid Continents before declaring the Countries.\n");
							continentdata = false;
							break;
						}
						if (!continentAvailable) {
							continenterror = continenterror.concat("!! " + data.split(",")[3]
									+ " is not defined as Continent. Declare " + data.split(",")[3]
									+ " as a continent first for declaring '" + name + "' as a country\n");
							continentdata = false;
							continue;
						}
						adjacentcountries = new ArrayList<>();
						if (countrydetail.length > 4) {
							for (int j = 4; j < countrydetail.length; j++) {
								adjacentcountries.add(countrydetail[j]);
							}
						} else {
							adjacencyerror = adjacencyerror
									.concat("!! No Adjacent Country defined for " + name + ".\n");
							adjacentdata = false;
							continue;
						}
						country.setName(name);
						country.setxValue(xOffset);
						country.setyValue(yOffset);
						country.setAdjacentCountries(adjacentcountries);
						listOfCountries.add(country);
						visited.put(name, data);
					}
				}
			}

		}

		if (duplicatedata == true && formatdata == true && adjacentdata == true && continentdata == true) {
			return true;
		} else {
			error = error.concat(formaterror).concat(Duplicateerror).concat(continenterror).concat(adjacencyerror);
			return false;
		}
	}

	/**
	 * This method aims validate list of map tag data available in the uploaded file
	 * and checks if they are in correct format.
	 * 
	 * @param tagData Value of the map tag data
	 * @return flag of type boolean determines if the entered map tags are valid
	 */
	public boolean validatemetadata(String tagData) {

		boolean checkdata = true, duplicatedata = true, formatdata = true;
		String formaterror = "", validateerror = "", Duplicateerror = "";
		mapTag = new MapTag();
		error = new String();
		HashMap<String, String> visited = new HashMap<String, String>();
		String[] metaData = tagData.split("\\n");
		for (int i = 1; i < metaData.length; i++) {
			String data = metaData[i].trim().toUpperCase();
			Pattern pattern = Pattern.compile("[a-zA-Z\\s]+=[a-zA-Z\\s\\.]+");
			if (!data.trim().isEmpty()) {
				Matcher match = pattern.matcher(data.trim());
				if (!match.matches()) {
					formaterror = formaterror.concat("!! Invalid Metadata. " + data.trim()
							+ " is not defined in required format. Format required is: Field=Value.\n");
					formatdata = false;
				} else {
					if (visited.containsKey(data.split("=")[0])) {
						Duplicateerror = Duplicateerror
								.concat("!! " + data.split("=")[0] + " is already defined. Duplicate Entry Found as "
										+ visited.get(data.split("=")[0]) + " and " + data + ".\n");
						duplicatedata = false;
					} else {
						String field = data.split("=")[0];
						String value = data.split("=")[1];
						if (field.equalsIgnoreCase("Author")) {
							Pattern check = Pattern.compile("[a-zA-Z\\s]+");
							if (check.matcher(value).matches()) {
								mapTag.setAuthorName(data.split("=")[1]);
								visited.put(field, data);
							} else {
								validateerror = validateerror.concat("!! Author Name is not Defined properly\n");
								checkdata = false;
							}
						} else if (field.equalsIgnoreCase("Image Name")) {
							Pattern check = Pattern.compile("[a-zA-Z0-9]+[_-]*.BMP");
							if (check.matcher(value).matches()) {
								mapTag.setImageName(data.split("=")[1]);
								visited.put(field, data);
							} else {
								validateerror = validateerror.concat(
										"!! Image Name is not Defined properly in proper format. required format- 'imagename.bmp'\n");
								checkdata = false;
							}

						} else if (field.equalsIgnoreCase("Warn")) {

							if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("No")) {
								mapTag.setWarn(value);
								visited.put(field, data);
							} else {
								validateerror = validateerror.concat(
										"!! Warn is not Defined properly, Values should be either Yes or No.\n");
								checkdata = false;
							}

						} else if (field.equalsIgnoreCase("Wrap")) {
							if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("No")) {
								mapTag.setWrap(value);
								visited.put(field, data);
							} else {
								validateerror = validateerror.concat(
										"!! Wrap is not Defined properly, Values should be either Yes or No.\n");
								checkdata = false;
							}

						} else if (field.equalsIgnoreCase("Scroll")) {
							if (value.equalsIgnoreCase("Horizontal") || value.equalsIgnoreCase("Vertical")) {
								mapTag.setScroll(value);
								visited.put(field, data);
							} else {
								validateerror = validateerror.concat(
										"!! Scroll is not Defined properly, Values should be either Horizontal or Vertical.\n");
								checkdata = false;
							}
						} else {
							validateerror = validateerror.concat("!! " + data + " is not a Valid Metadata.\n");
							checkdata = false;
						}
					}
				}
			}
		}
		if (duplicatedata == true && checkdata == true && formatdata == true) {
			return true;
		} else {
			error = error.concat(validateerror).concat(Duplicateerror).concat(formaterror);
			return false;
		}
	}

	/**
	 * This method aims to check adjacency between countries in the uploaded file
	 * and checks if they are in correct format.
	 * 
	 * @return flag of type boolean determines if the entered countries are properly
	 *         defined as adjacent.
	 * @throws IOException - throws input output
	 */
	public boolean checkcountryadjancy() throws IOException {

		String aderror = new String(), conterror = new String(), connectedError = new String();
		ArrayList<String> adjacentCountries = new ArrayList<>();
		ArrayList<String> availableContinent = new ArrayList<>();
		ArrayList<String> connectivity = new ArrayList<>();

		boolean flag = false, continentFlag = true, adjacencyFlag = true, connectedFlag = true;

		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			for (Country country : listOfCountries) {

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

								} else
									flag = false;
							} else
								flag = false;
						}
						if (!flag) {
							adjacencyFlag = false;
							aderror = aderror.concat("!! " + country.getName() + " and " + name
									+ " are not defined properly as adjacent countries on " + name + " end.\n");
						}
					}
				} else {
					adjacencyFlag = false;
					aderror = aderror.concat("!! " + country.getName()
							+ " does not have any Adjacents Countries. Should have atleast one adjacent country to play the game \n");
				}
			}
		}
		if (listOfContinent.size() < 2) {
			continentFlag = false;
			conterror = "!! Minimum number of continents should be two to play the games. PLease add one more continent and respective countries.\n";
		}
		if (listOfCountries != null && !listOfCountries.isEmpty()) {
			listOfCountries.forEach(country -> {
				if (!availableContinent.contains(country.getName())) {
					availableContinent.add(country.getPartOfContinent().getContinentName());
				}
			});
		}

		for (Continent continent : listOfContinent) {
			boolean flag4 = true;
			for (String continentname : availableContinent) {
				if (continent.getContinentName().equalsIgnoreCase(continentname)) {
					flag4 = false;
				}
			}
			if (flag4) {
				continentFlag = false;
				conterror = conterror.concat("!! " + continent.getContinentName()
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
						+ " are not connected to any of the countries of the other " + (listOfContinent.size() - 1)
						+ " available continents.\n");
			}
		}

		if (flag && continentFlag && adjacencyFlag && connectedFlag) {
			return true;

		}

		else {

			adjancencyerror = "\n".concat(conterror).concat(aderror);
			if (!connectedFlag) {
				connectedError = "\n!! Map Graph is not Connected - \n".concat(connectedError);
				adjancencyerror = adjancencyerror.concat(connectedError);
			}
			return false;
		}

	}
}
