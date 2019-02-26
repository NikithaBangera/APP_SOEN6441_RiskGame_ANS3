package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

import com.riskgame.action.CreateAndEditMap;
import com.riskgame.gameplay.StartupPhase;


public class RiskMain extends JFrame {
	JButton createNewMapButton, loadExistingMapButton, exitMapButton;
	JLabel label1, label2;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static boolean isGoodToStartGame = false;

	public RiskMain() {
		setLayout(new GridLayout(4, 4));
		createNewMapButton = new JButton("Create a new Map");
		createNewMapButton.setPreferredSize(new Dimension(60, 60));
		add(createNewMapButton);

		createNewMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateAndEditMap createandeditmap = new CreateAndEditMap();
				try {
					setVisible(false);
					createandeditmap.newMapCreation();
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
				// Add load map functionality
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
		startGame();
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
	
	private void startGame() {
		
		
		if (isGoodToStartGame) {
			System.out.println("Do you want to start the game? (Yes or No)");
			try {
				String choice = br.readLine();
				if(choice.equalsIgnoreCase("Yes")) {
					StartupPhase start = new StartupPhase();
					start.gamePlay();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		setUp();
	}
}
