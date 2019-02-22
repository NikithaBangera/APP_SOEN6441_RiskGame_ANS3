package com.riskgame.action;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
public class SavingMapIntoFile {

	public void saveMapTag(String[] mapTagData) {
		String workingDir = System.getProperty("user.dir"); //gets the current location of the working directory
		String filepath= workingDir+"/maps/"+mapTagData[2];	//path of the map that we want to create
		try {
		File file = new File(filepath);
		file.getParentFile().mkdirs(); 
		file.createNewFile();
		PrintWriter outputStream = new PrintWriter(file);
		outputStream.println(mapTagData[0]);	//write the image file
		outputStream.println(mapTagData[1]);	//write the author
		outputStream.flush();
		outputStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
