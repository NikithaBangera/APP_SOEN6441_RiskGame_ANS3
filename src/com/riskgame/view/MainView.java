package com.riskgame.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.riskgame.controller.CreateMapController;
import com.riskgame.controller.PlayerController;
import com.riskgame.controller.LoadMapController;
//import com.riskgame.driver.RiskMain;
import com.riskgame.model.GameMapGraph;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainView extends JFrame {
	
	/**
	 * Buttons are created for creating a new map, for loading an existing map and
	 * also for exiting.
	 */
	JButton createNewMapButton, loadExistingMapButton, exitMapButton;

	JLabel label1, label2;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean uploadSuccessful = false;
	public static boolean isGoodToStartGame = false;

	/**
	 * createandeditmap a CreateAndEditMap object
	 */
	public static CreateMapController createandeditmap = new CreateMapController();

	/**
	 * loadMap a ReadAndWriteMap object
	 */
	public static LoadMapController loadMap = new LoadMapController();

	/**
	 * RiskMain constructor contains the action to be performed on the click create
	 * map, load map and exit buttons. When the user clicks on create map button,
	 * then the newMapCreation method of CreateAndEditMap class is called. When the
	 * user clicks on load map button, then the uploadMap method of ReadAndWriteMap
	 * class is called. Exit button exits the user from the game.
	 * 
	 */

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView layout = new MainView();
					layout.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					layout.frame.setVisible(true);
					layout.frame.pack();
					layout.frame.setLocation(800, 400);
					layout.frame.setSize(320, 300);
					layout.frame.setTitle("Risk Game");
//					layout.frame.setVisible(true);
//					layout.frame.setTitle("Risk Game");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton createNewMapButton = new JButton("Create Map");
		createNewMapButton.setForeground(Color.WHITE);
		createNewMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
		createNewMapButton.setBackground(Color.BLACK);
		createNewMapButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		createNewMapButton.setBounds(68, 25, 171, 38);
		frame.getContentPane().add(createNewMapButton);
		
		JButton loadExistingMapButton = new JButton("Load/Edit Map");
		loadExistingMapButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		loadExistingMapButton.setForeground(Color.WHITE);
		loadExistingMapButton.setBackground(Color.BLACK);
		loadExistingMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load map functionality
				boolean exit = false;
				while (!exit) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setDialogTitle("Select an Map File");
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
								else
									System.out.println(" \n Thank You !! ");
								System.exit(0);
							} else {
								System.out.println(LoadMapController.getError());
								System.out.println(
										"\nPlease rectify all the above mentioned issues and upload the file again");
								System.out.println("\n Do you want to upload the correct file again? :Yes/ No ");
								boolean uploadagain = true;
								while (uploadagain) {
									String input = br.readLine();
									if (input.equalsIgnoreCase("yes")) {
										exit = false;
										uploadagain = false;
										setVisible(true);
										break;
									} else if (input.equalsIgnoreCase("no")) {
										exit = true;
										uploadagain = false;
										System.out.println(" Thank You !!");
									} else {
										System.out.println("\nInvalid !!. Enter correct option - Yes/No ");
										uploadagain = true;
									}

								}

							}
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				System.exit(0);
			}
		});
		loadExistingMapButton.setBounds(68, 98, 171, 38);
		frame.getContentPane().add(loadExistingMapButton);
		
		JButton exitMapButton = new JButton("Exit");
		exitMapButton.setForeground(Color.WHITE);
		exitMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		exitMapButton.setBackground(Color.BLACK);
		exitMapButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		exitMapButton.setBounds(68, 175, 171, 38);
		frame.getContentPane().add(exitMapButton);
	}
	
	/**
	 * This method sets up a layout for the risk game by displaying the create a
	 * map, load a map and exit buttons respectively using Java Swing framework.
	 * 
	 * @throws Exception - ClassNotFoundException
	 */
//	public static void setUp() throws Exception {
//		MainView layout = new MainView();
//		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		layout.setVisible(true);
//		layout.pack();
//		layout.setLocation(800, 400);
//		layout.setSize(300, 300);
//		layout.setTitle("Risk Game");
//
//	}

	/**
	 * This is the method where the gameplay begins after the creation of new map or
	 * loading of existing map.
	 * 
	 * @throws Exception - NoSuchMethodException
	 */
	private void startGame() throws Exception {
		System.out.println("Do you want to start the game? (Yes or No)");
		try {
			GameMapGraph createMapGraph = new GameMapGraph();
			createMapGraph = createandeditmap.getMapGraph();
			String choice = br.readLine().trim();
			while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No") || choice == null)) {
				System.err.println("\nPlease enter the choice as either Yes or No:");
				System.out.flush();
				choice = br.readLine().trim();
			}

			if (choice.equalsIgnoreCase("Yes")) {
//				StartupPhase start = new StartupPhase();
//				start.gamePlay(createMapGraph);
				PlayerController play = new PlayerController();
				play.gamePlay(createMapGraph);
			} else {
				System.out.println("\nThank you!");
				System.exit(0);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the main method which launches the entire Risk Game.
	 * 
	 * @param args main arguments
	 * @throws Exception - NoSuchMethodException
	 */
//	public static void main(String[] args) throws Exception {
//		setUp();
//	}
	
}


//package com.riskgame.view;
//
//public class MainView {
//
//}
