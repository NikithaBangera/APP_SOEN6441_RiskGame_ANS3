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

import com.riskgame.model.GameMapGraph;
import com.riskgame.model.MapTag;
import com.riskgame.service.CreateAndEditMap;
import com.riskgame.service.ReadAndWriteMap;
import com.riskgame.service.StartupPhase;

public class RiskMain extends JFrame {
	JButton createNewMapButton, loadExistingMapButton, exitMapButton;
	JLabel label1, label2;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean uploadSuccessful = false;
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
				// Load map functionality
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select an image");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".map or .MAP", "map", "MAP");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {

					String fileName = jfc.getSelectedFile().getPath();
					setVisible(false);

					GameMapGraph loadMapGraph = new GameMapGraph();
					try {
						uploadSuccessful = loadMap.uploadMap(fileName);

						if (uploadSuccessful) {
							loadMapGraph = loadMap.getMapGraph();
							File f = new File(fileName);
							fileName = f.getName();
							fileName = fileName.substring(0, fileName.lastIndexOf("."));
							loadMapGraph.setFilename(fileName);
							isGoodToStartGame = createandeditmap.uploadMap(loadMapGraph);
							if (isGoodToStartGame)
								startGame();
						} else {
							System.out.println(ReadAndWriteMap.getError());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
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

	public static void setUp() throws Exception{
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
			GameMapGraph createMapGraph = new GameMapGraph();
			createMapGraph = createandeditmap.getMapGraph();
//			mockData(mapGraph);
			String choice = br.readLine().trim();
			while (choice.isEmpty()) {
				System.err.println("\nChoice cannot be blank. Please enter the correct choice below:");
				System.out.flush();
				choice = br.readLine().trim();
			}

			if (choice.equalsIgnoreCase("Yes")) {
				StartupPhase start = new StartupPhase();
//				System.out.println(createMapGraph);
				start.gamePlay(createMapGraph);
			} else {
				System.out.println("\nThank you!");
				System.exit(0);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		setUp();
	}
}