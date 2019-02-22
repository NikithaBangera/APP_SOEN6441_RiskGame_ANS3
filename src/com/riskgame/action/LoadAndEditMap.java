package com.riskgame.action;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoadAndEditMap {
	public void loadMap() throws Exception{
		System.out.println("\nChoose a map:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		Pattern pattern = Pattern.compile("[a-z, A-Z]+.[map]+");
		Matcher match = pattern.matcher(name.trim());	
		while(!match.matches())
		{
			System.out.println("\n Please enter a valid map ");
			name = br.readLine();
			match = pattern.matcher(name.trim());
		}
		
		String workingDir = System.getProperty("user.dir"); //gets the current location of the working directory
		String fileName =name.trim();
		String filepath= workingDir+"/maps/"+fileName;
		try {
			FileReader file=new FileReader(filepath) ;
			BufferedReader br_file = new BufferedReader(file);
			String str;
			String image_name="";
			while((str=br_file.readLine()) != null) {
				Pattern image_pattern = Pattern.compile("Image=");
				Matcher image_match = image_pattern.matcher(str.trim());
				if(image_match.matches()) {
					image_name=str.substring(7);
					
				}
				System.out.println(str+"\n");
			}
			System.out.println("Do you want to edit the map?(y/n)");
			BufferedReader editMap = new BufferedReader(new InputStreamReader(System.in));
			String mapedit=editMap.readLine();
			String mapedition=mapedit.trim();
			while (mapedition != null) {
				if(mapedition.equals("y")) {
					editMap();
				}
				if(mapedition.equals("n")){
					System.out.println("Start or Exit?(start/exit)");
					BufferedReader openImage = new BufferedReader(new InputStreamReader(System.in));
					String start=openImage.readLine().trim();
					while (start != null) {
						if(start.contentEquals("start") ) {
							if(image_name !=null) 
								loadImage(image_name);
							else
								System.out.println("No image found for the map");
						}
						else if(start.contentEquals("exit") )
							System.exit(0);
						else {
							System.out.println("Please enter a valid answer(start/exit)");
							start=openImage.readLine().trim();
						}
//						else {
//							if(start.contentEquals("start") ) {
//								if(image_name !=null) 
//									loadImage(image_name);
//								else
//									System.out.println("No image found for the map");
//							}
//							else if(start.contentEquals("exit") )
//								System.exit(0);
//						}
					}
				}
				else {
					System.out.println("Please enter a valid answer(y/n)");
					mapedition=editMap.readLine().trim();
				}
				
			
			}
			br_file.close() ;
		}
		catch(IOException e){
			System.out.println("File Not Found");
			
		}
		
	}
	public void loadImage(String image){
		JFrame jframe = new JFrame();
		JLabel background;
		String workingDir = System.getProperty("user.dir"); //gets the current location of the working directory
		String filepath= workingDir+"/images/"+image;
		ImageIcon map=new ImageIcon(filepath);
		jframe.setLayout(new FlowLayout());
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setSize(400, 400);
		jframe.setLocation(dim.width/2-jframe.getSize().width/2,dim.height/2-jframe.getSize().height/2);
		background = new JLabel("",map,JLabel.CENTER);
		background.setBounds(0,0,400,400);
		jframe.add(background);
		jframe.setVisible(true);		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void editMap(){
		
	}
	
}
