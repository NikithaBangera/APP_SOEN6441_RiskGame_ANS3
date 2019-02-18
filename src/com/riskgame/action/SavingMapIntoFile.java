package com.riskgame.action;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SavingMapIntoFile {

	public void saveMapTag(String[] mapTagData) {
		String fileName=mapTagData[2];
		try {
		PrintWriter outputStream = new PrintWriter(fileName);
		outputStream.println(mapTagData[0]);
		outputStream.println(mapTagData[1]);
		outputStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
