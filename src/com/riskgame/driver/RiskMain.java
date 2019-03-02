package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.riskgame.action.CreateAndEditMap;
import com.riskgame.action.ReadAndWriteMap;
import com.riskgame.common.GameMapGraph;
import com.riskgame.common.MapTag;
import com.riskgame.gameplay.StartupPhase;

public class RiskMain extends JFrame {
	JButton createNewMapButton, loadExistingMapButton, exitMapButton;
	JLabel label1, label2;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static boolean isGoodToStartGame = false;
	public static CreateAndEditMap createandeditmap = new CreateAndEditMap();
	public static ReadAndWriteMap loadMap = new ReadAndWriteMap();

	public RiskMain() throws Exception {
		setLayout(new GridLayout(4, 4));
		createNewMapButton = new JButton("Create a new Map");
		createNewMapButton.setPreferredSize(new Dimension(60, 60));
		add(createNewMapButton);

		createNewMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					setVisible(false);
					isGoodToStartGame = createandeditmap.newMapCreation();
					if (isGoodToStartGame) {
						startGame();
					}
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
				// load map functionality
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select a map file");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".map or .MAP", "map", "MAP");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(jfc.getSelectedFile().getPath());
					String fileName = jfc.getSelectedFile().getPath();
					setVisible(false);
					/*
					 * try { // loadMap.uploadMap(fileName); } catch (IOException e2) {
					 * e2.printStackTrace(); }
					 */
					System.out.println("load" + isGoodToStartGame);
					if (isGoodToStartGame) {
						try {
							startGame();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
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

	public static void setUp() throws Exception {
		RiskMain layout = new RiskMain();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setVisible(true);
		layout.pack();
		layout.setLocation(800, 400);
		layout.setSize(300, 300);
		layout.setTitle("Risk Game");

	}

	private void startGame() throws Exception {
		System.out.println("Do you want to start the game? (Yes or No)");
		try {
			GameMapGraph mapGraph = new GameMapGraph();
			mapGraph = createandeditmap.getMapGraph();
//			mockData(mapGraph);
			
			String choice = br.readLine();
			if (choice.equalsIgnoreCase("Yes")) {
				StartupPhase start = new StartupPhase();
				System.out.println(mapGraph);
				start.gamePlay(mapGraph);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mockData(GameMapGraph mapGraph) {
		MapTag mapTag = new MapTag();
		mapTag.setAuthorName("shresthi");
		mapTag.setWarn("yes");
		mapTag.setImageName("world.bmp");
		mapTag.setWrap("yes");
		mapTag.setScroll("horizontal");
		mapGraph.setMapTag(mapTag);
		
	}

	public static void main(String[] args) throws Exception {
		setUp();
	}
}
