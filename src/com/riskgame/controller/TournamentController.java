package com.riskgame.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.model.TournamentMapGraph;
import com.riskgame.strategy.Aggressive;
import com.riskgame.strategy.Benevolent;
import com.riskgame.strategy.Cheater;
import com.riskgame.strategy.PlayerStrategy;
import com.riskgame.strategy.RandomPlayer;
import com.riskgame.view.ConsoleView;

/**
 * Tournament class contains all the methods to start the tournament mode,
 * load the maps for the games based the number of maps selected by the user,
 * save the objects of the GameMapGraph into different games, start the
 * automated tournament game and finally display the tournament result of the
 * differnt games
 * 
 * @author Nikitha
 * @author Anusha
 *
 */
public class TournamentController {

	/**
	 * Object of LoadMapController class
	 */
	public LoadMapController loadMap = new LoadMapController();
	
	/**
	 * Object of CreateMapController class
	 */
	public CreateMapController createandeditmap = new CreateMapController();
	
	/**
	 * boolean variable to check if upload map is successful
	 */
	boolean uploadSuccessful = false;
	
	/**
	 * boolean variable to check if good to start game
	 */
	public static boolean isGoodToStartGame = false;
	
	/**
	 * Game key variable
	 */
	private String gameKey;
	
	/**
	 * hashmap for keeping track of number of maps for different games
	 */
	private Map<Integer, GameMapGraph> initialTournamentMaps = new TreeMap<Integer, GameMapGraph>(); 
	
	/**
	 * number of players count
	 */
	private int countOfthePlayers = 0;
	
	/**
	 * object of PlayerController object
	 */
	PlayerController playerController = new PlayerController();
	
	/**
	 * This method is used to start the tournament mode by invoking the place armies function
	 * and also the different phase methods based on the different player strategies
	 * 
	 * @param tournamentMapGraph - an object containing the details of the tournament game
	 */
	public void playTournament(TournamentMapGraph tournamentMapGraph) {
		playerController.setCountOfthePlayers(tournamentMapGraph.getNumberOfPlayers());
		loadTournamentMaps(tournamentMapGraph);
		populateTournamentMapGraphs(tournamentMapGraph);
		
		ConsoleView consoleView = new ConsoleView();
		
		Iterator<Entry<String, GameMapGraph>> tournamentIt = tournamentMapGraph.getTournamentMapGraphs().entrySet().iterator();
		int gameNumber = 0;
		while(tournamentIt.hasNext()) {
			gameNumber++;
			boolean gameComplete = false;
			Entry<String, GameMapGraph> nextGame = tournamentIt.next();
			GameMapGraph gameMapGraph = nextGame.getValue();
			int gameTurns = gameMapGraph.getGameTurns();
			
			tournamentPlaceArmies(gameMapGraph);
			
			while(gameTurns > 0) {
				
				if(validateGameCompletion(gameMapGraph)) {
					for(Player player : gameMapGraph.getPlayers()) {
						if(!player.isPlayerLostGame()) {
							invokePlayerStrategy(gameMapGraph, player);
						}
					}
					gameTurns--;
				}
				else {
					gameComplete = true;
					break;
				}
			}
			if(gameTurns == 0) {
				gameMapGraph.setGameResult("Draw");
			}
			else if(gameComplete) {
				Player winner = null;
				for(Player player: gameMapGraph.getPlayers()) {
					if(player.getMyCountries().size() >= 1) {
						winner = player;
					}
				}
				gameMapGraph.setGameResult(winner.getPlayerType());
			}
			tournamentMapGraph.getTournamentResult().put(nextGame.getKey(), gameMapGraph.getGameResult());
		}
	}

	/**
	 * Method which calls the placeArmies function of the players based
	 * on the player strategies
	 * 
	 * @param gameMapGraph - object of GameMapGraph class
	 */
	public void tournamentPlaceArmies(GameMapGraph gameMapGraph) {
		
		while(!playerController.isPlaceArmiesComplete(gameMapGraph)) {
			for(Player currentPlayer : gameMapGraph.getPlayers()) {
				PlayerStrategy playerStrategy = null;
				
				playerStrategy =  currentPlayer.getPlayerType().equalsIgnoreCase("Aggressive") ? new Aggressive()
						 : (currentPlayer.getPlayerType().equalsIgnoreCase("Benevolent") ? new Benevolent()
								 : (currentPlayer.getPlayerType().equalsIgnoreCase("Cheater") ? new Cheater()
										 :(currentPlayer.getPlayerType().equalsIgnoreCase("Random") ? new RandomPlayer()
												 : null)));
				
				playerStrategy.placeArmies(gameMapGraph, currentPlayer, null);
			}
		}
	}

	/**
	 * Method to the load the maps chosen by the user for different games
	 * 
	 * @param tournamentMapGraph - an object containing the details of the tournament game
	 */
	private void loadTournamentMaps(TournamentMapGraph tournamentMapGraph) {
		int i = 0;
		int numberOfMaps = tournamentMapGraph.getNumberOfMaps();
		while(numberOfMaps > 0) {
			i++;
			numberOfMaps--;
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Select an Map File");
			jfc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".map or .MAP", "map", "MAP");
			jfc.addChoosableFileFilter(filter);

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {

				String fileName = jfc.getSelectedFile().getPath();

				GameMapGraph loadMapGraph = new GameMapGraph();
				try {
					uploadSuccessful = loadMap.uploadMap(fileName);

					if (uploadSuccessful) {
						loadMapGraph = loadMap.getMapGraph();
						File f = new File(fileName);
						fileName = f.getName();
						fileName = fileName.substring(0, fileName.lastIndexOf("."));
						loadMapGraph.setFilename(fileName);
						loadMapGraph.setGameType("Tournament");
						isGoodToStartGame = createandeditmap.uploadMap(loadMapGraph);
						if(isGoodToStartGame) {
							initialTournamentMaps.put(i, loadMapGraph);
							saveTournamentMaps(i, loadMapGraph);
						}
					}
					
				}catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Saving the tournament map objects for different games
	 * @param i - number of games
	 * @param loadMapGraph - object of the GameMapGraph
	 */
	private void saveTournamentMaps(int i, GameMapGraph loadMapGraph) {
		try {
			File saveFile = new File(System.getProperty("user.dir")+"/resources/TournamentMaps/"+i+".txt");
			FileOutputStream fileOutput;
			if(saveFile.createNewFile()) {
				fileOutput = new FileOutputStream(saveFile);
				ObjectOutputStream save = new ObjectOutputStream(fileOutput);
				save.writeObject(loadMapGraph);
				save.close();
				fileOutput.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to populate the maps for diferent games into the tournamentMapGraph
	 * object which contains all the details of the tournament
	 * @param tournamentMapGraph - object of the TournamentMapGraph
	 */
	private void populateTournamentMapGraphs(TournamentMapGraph tournamentMapGraph) {
		try {
			for(int j=1;j<=tournamentMapGraph.getNumberOfGames();j++) {
				for(int k=1;k<=tournamentMapGraph.getNumberOfMaps();k++) {
					FileInputStream fi = new FileInputStream(new File(System.getProperty("user.dir")+"/resources/TournamentMaps/"+k+".txt"));
					ObjectInputStream oi = new ObjectInputStream(fi);
					GameMapGraph mapGraph = (GameMapGraph) oi.readObject();
					oi.close();
					gameKey = "G"+j+"M"+k;
					mapGraph.setGameTurns(tournamentMapGraph.getGameTurns());
					countOfthePlayers = tournamentMapGraph.getInputPlayerDetails().size();
					Iterator<Entry<String, String>> inputPlayerIt = tournamentMapGraph.getInputPlayerDetails().entrySet().iterator();
					while(inputPlayerIt.hasNext()) {
						Entry<String, String> inputPlayer = inputPlayerIt.next();
						Player riskPlayer = new Player();
						riskPlayer.setName(inputPlayer.getValue().split(",")[0]);
						riskPlayer.setPlayerType(inputPlayer.getValue().split(",")[1]);
						riskPlayer.setConquerCountry(0);
						riskPlayer.setFirstReinforcement(true);
						mapGraph.getPlayers().add(riskPlayer);
					}
					playerController.allocationOfCountry(mapGraph);
					playerController.allocationOfArmyToPlayers(mapGraph);
					playerController.allocationOfArmyToCountriesInitially(mapGraph);
					mapGraph.setGameType("Tournament");
					tournamentMapGraph.getTournamentMapGraphs().put(gameKey, mapGraph);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method the methods of the different players based on the 
	 * type of the player during that player's turn
	 * @param mapGraph - object of the GameMapGraph
	 * @param currentPlayer - current player playing the game
	 * @return strategyComplete - status of the strategy completion
	 */
	private boolean invokePlayerStrategy(GameMapGraph mapGraph, Player currentPlayer) {
		boolean strategyComplete = false;
		
		PlayerStrategy playerStrategy = null;
		
		playerStrategy =  currentPlayer.getPlayerType().equalsIgnoreCase("Aggressive") ? new Aggressive()
				 : (currentPlayer.getPlayerType().equalsIgnoreCase("Benevolent") ? new Benevolent()
						 : (currentPlayer.getPlayerType().equalsIgnoreCase("Cheater") ? new Cheater()
								 :(currentPlayer.getPlayerType().equalsIgnoreCase("Random") ? new RandomPlayer()
										 : null)));

		System.out.println("Player "+currentPlayer.getName()+"("+currentPlayer.getPlayerType()+") has started playing");
		switch (currentPlayer.getPlayerType()) {
		
		case "Aggressive":
				playerStrategy.reinforcementPhase(currentPlayer, mapGraph, null, 0);
				playerStrategy.attackPhase(mapGraph, currentPlayer, null, null);
				playerStrategy.fortificationPhase(mapGraph, currentPlayer, null, null, 0);
			break;
			
		case "Benevolent":
				playerStrategy.reinforcementPhase(currentPlayer, mapGraph, null, 0);
				playerStrategy.fortificationPhase(mapGraph, currentPlayer, null, null, 0);
			break;
		
		case "Random":
				playerStrategy.reinforcementPhase(currentPlayer, mapGraph, null, 0);
				int numberOfAttacks = new Random().nextInt(currentPlayer.getMyCountries().size()) + 1;
				while(numberOfAttacks > 0) {
					playerStrategy.attackPhase(mapGraph, currentPlayer, null, null);
					numberOfAttacks--;
				}
				playerStrategy.fortificationPhase(mapGraph, currentPlayer, null, null, 0);
			break;
			
		case "Cheater":
				playerStrategy.reinforcementPhase(currentPlayer, mapGraph, null, 0);
				playerStrategy.attackPhase(mapGraph, currentPlayer, null, null);
				playerStrategy.fortificationPhase(mapGraph, currentPlayer, null, null, 0);
			break;
		default:
			break;
		}
		System.out.println(currentPlayer.getName()+"("+currentPlayer.getPlayerType()+") player's turn ended.");
		return strategyComplete;
	}
	
	/**
	 * Method to check whether the game is complete
	 * @param mapGraph - object of the GameMapGraph
	 * @return true - return true or false
	 */
	private boolean validateGameCompletion(GameMapGraph mapGraph) {
		int i = 0;
		for (Player player : mapGraph.getPlayers()) {
			if (!player.isPlayerLostGame()) {
				i++;
			}
		}
		if (i > 1) {
			return true;
		}
		return false;
	}
}
