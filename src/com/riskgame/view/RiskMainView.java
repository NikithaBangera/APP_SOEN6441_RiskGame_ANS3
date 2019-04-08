package com.riskgame.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.riskgame.controller.CreateMapController;
import com.riskgame.controller.LoadMapController;
import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;

/**
 * RiskMain class launches the Risk Game and provided options for the users to
 * create a new map or load an existing map in-order to begin the game.
 * 
 * @author Shresthi
 * @author Shiva
 *
 */
public class RiskMainView extends JFrame {

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
	private JButton buttonResumeGame;
	private JButton btnStartTournament;

	/**
	 * RiskMain constructor contains the action to be performed on the click create
	 * map, load map and exit buttons. When the user clicks on create map button,
	 * then the newMapCreation method of CreateAndEditMap class is called. When the
	 * user clicks on load map button, then the uploadMap method of ReadAndWriteMap
	 * class is called. Exit button exits the user from the game.
	 * 
	 */
	public RiskMainView() {
		setTitle("Risk Game");
		getContentPane().setLayout(new GridLayout(5, 3));
		createNewMapButton = new JButton("Create a new Map");
		createNewMapButton.setPreferredSize(new Dimension(60, 60));
		getContentPane().add(createNewMapButton);

		createNewMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					setVisible(false);
					isGoodToStartGame = createandeditmap.newMapCreation();
					if (isGoodToStartGame) {
						//startGame();
						GameMapGraph createMapGraph = new GameMapGraph();
						createMapGraph = createandeditmap.getMapGraph();
						StartGameView startGame = new StartGameView(createMapGraph);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		loadExistingMapButton = new JButton("Load Existing Map");
		loadExistingMapButton.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(loadExistingMapButton);

		loadExistingMapButton.addActionListener(new ActionListener() {

			@Override
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
								if (isGoodToStartGame) {
									//startGame();
									StartGameView startGame = new StartGameView(loadMapGraph);
									exit = true;
								}
									
								else
									System.out.println(" \n Thank You !! ");
								//System.exit(0);
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
				//System.exit(0);
			}
		});
		
		buttonResumeGame = new JButton("Resume Saved Game");
		buttonResumeGame.setPreferredSize(new Dimension(50, 50));
		buttonResumeGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setDialogTitle("Select an Map File");
					jfc.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt or .TXT", "txt", "TXT");
					jfc.addChoosableFileFilter(filter);
	
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
	
						String fileName = jfc.getSelectedFile().getPath();
						setVisible(false);
						
						FileInputStream fi = new FileInputStream(new File(fileName));
						ObjectInputStream oi = new ObjectInputStream(fi);
						
						GameMapGraph mapGraph = (GameMapGraph) oi.readObject();
						PlayerView playerView = new PlayerView(mapGraph);
						
						
						fi.close();
						oi.close();
					}
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		getContentPane().add(buttonResumeGame);
		
		btnStartTournament = new JButton("Start Tournament");
		btnStartTournament.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(btnStartTournament);
		btnStartTournament.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TournamentView tournamentView = new TournamentView();
			}
		});
		
		exitMapButton = new JButton("Exit");
		exitMapButton.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(exitMapButton);

		exitMapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	/**
	 * This method sets up a layout for the risk game by displaying the create a
	 * map, load a map and exit buttons respectively using Java Swing framework.
	 * 
	 * @throws Exception - ClassNotFoundException
	 */
	public static void setUp() throws Exception {
		RiskMainView layout = new RiskMainView();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setVisible(true);
		layout.pack();
		layout.setLocation(800, 400);
		layout.setSize(300, 300);
		layout.setTitle("Risk Game");

	}

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
	public static void main(String[] args) throws Exception {
		setUp();
	}
}
