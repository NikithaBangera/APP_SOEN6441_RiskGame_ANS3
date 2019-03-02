package com.riskgame.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.riskgame.common.Continent;
import com.riskgame.common.Country;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.MapTag;

public class ReadAndWriteMap {

	static String Delimiter = ",";
	GameMapGraph MapGraph;

	public void saveMap(GameMapGraph mapgraph) throws IOException {

		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "\\src\\com\\riskgame\\maps\\" + mapgraph.getFilename() + ".map");
		try {

			if (!file.exists()) {
				PrintWriter outputStream = new PrintWriter(file);

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

				outputStream.println("\n[Country]");
				for (Country country : mapgraph.getCountries()) {
					String detail = country.getName() + Delimiter + country.getxValue() + Delimiter
							+ country.getyValue() + Delimiter + country.getPartOfContinent().getContinentName();
					for (String adcountry : country.getAdjacentCountries()) {
						detail = detail.concat(Delimiter + adcountry);
					}
					outputStream.println(detail);
				}
				outputStream.close();
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void uploadMap(String fileName) throws IOException {

		if (fileName != null) {
			try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
				String inputText = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
				System.out.println(inputText);

			}
		}

	}
}