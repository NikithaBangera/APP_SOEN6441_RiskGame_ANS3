package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.riskgame.action.CreateEditMap;

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
				CreateEditMap createMap = new CreateEditMap();
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