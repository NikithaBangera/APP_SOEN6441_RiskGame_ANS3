package com.riskgame.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SavingMapIntoFile {

	private String[] Data;

	public void saveMapTag(String[] mapTagData, String fileName, String tag) throws IOException {
		int l=mapTagData.length;
		this.Data = mapTagData;
		String tags = tag;
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "\\src\\com\\riskgame\\" + fileName);
		try {
		
			if(!file.exists()) 
			{	@SuppressWarnings("resource")
				PrintWriter outputStream = new PrintWriter(file);
				outputStream.println(tags);
		    	//PrintWriter outputStream = new PrintWriter(new FileWriter(file,true));
				for (int i=0; i<l;i++) {
				outputStream.println(Data[i]); }
				outputStream.close();
			}
			else {
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				br.write("\n\n\n\n\n" + tags);
				br.newLine();
				for (int i=0; i<l;i++) 
				{
					br.write(Data[i]);
					br.newLine(); }
				br.close();
				fr.close();
			}
			}
			
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
