package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import com.risk.services.MapEditor;
import com.risk.services.MapIO;
import com.risk.services.MapValidate;
import com.riskgame.action.CreateAndEditMap;

public class RiskMain extends JFrame {
	JButton createNewMapButton, loadExistingMapButton, exitMapButton;
	JLabel label1, label2;

	public RiskMain() {
		setLayout(new GridLayout(4, 4));
		createNewMapButton = new JButton("Create a new Map");
		createNewMapButton.setPreferredSize(new Dimension(60, 60));
		add(createNewMapButton);

		createNewMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateAndEditMap createMap = new CreateAndEditMap();
				try {
					setVisible(false);
					createMap.newMapCreation();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		loadExistingMapButton = new JButton("Load Existing Map");
		loadExistingMapButton.setPreferredSize(new Dimension(50, 50));
		add(loadExistingMapButton);
		
		loadExistingMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser;
				//Add load map functionality
				 int returnVal = fileChooser.showOpenDialog(this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            try {
			              // return the file path 
			            	String filename  = file.getAbsolutePath();
			            	System.out.println("File location: " + filename);
			            	ValidateMap validateMap = new ValidateMap();
			            	while(validateMap.validateMapFile(filename)) {
			            		mapreader= new Map(mapValidate);
			            		new MapEditor(mapreader.readFile()).editMapdata();
			            		
			            	}
			            	
			            } catch (Exception ex) {
			              System.out.println("problem accessing file"+file.getAbsolutePath());
			            }
			        } 
			        else {
			            System.out.println("File access cancelled by user.");
			        }  
				
			}
		});

		exitMapButton = new JButton("Exit");
		exitMapButton.setPreferredSize(new Dimension(50, 50));
		add(exitMapButton);

		exitMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void setUp() {
		RiskMain layout = new RiskMain();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setVisible(true);
		layout.pack();
		layout.setLocation(800, 400);
		layout.setSize(300, 300);
		layout.setTitle("Risk Game");
	}

	public static void main(String[] args) {
		setUp();
	}
}