package com.riskgame.action;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
public class SavingMapIntoFile {

	public void saveMapTag(String[] mapTagData) {
		String filepath= "/Users/s_hamlo_admin/Desktop/APP_SOEN6441_RiskGame_ANS3-SumeethaShiva/maps/"+mapTagData[2];
		
		try {
		File file = new File(filepath);
		file.getParentFile().mkdirs(); 
		file.createNewFile();
		PrintWriter outputStream = new PrintWriter(file);
		outputStream.println(mapTagData[0]);
		outputStream.println(mapTagData[1]);
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
