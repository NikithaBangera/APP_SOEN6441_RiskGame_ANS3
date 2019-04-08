package com.riskgame.controller;

import java.io.File;
import java.io.IOException;
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

public class TournamentController {

	public LoadMapController loadMap = new LoadMapController();
	public CreateMapController createandeditmap = new CreateMapController();
	boolean uploadSuccessful = false;
	public static boolean isGoodToStartGame = false;
	private String gameKey;
	private Map<Integer, GameMapGraph> initialTournamentMaps = new TreeMap<Integer, GameMapGraph>(); 
	private int countOfthePlayers = 0;
	PlayerController playerController = new PlayerController();
	
	public void playTournament(TournamentMapGraph tournamentMapGraph) {
		
		loadTournamentMaps(tournamentMapGraph);
		populateTournamentMapGraphs(tournamentMapGraph);
		
		Iterator<Entry<String, GameMapGraph>> tournamentIt = tournamentMapGraph.getTournamentMapGraphs().entrySet().iterator();
		int gameNumber = 0;
		while(tournamentIt.hasNext()) {
			gameNumber++;
			boolean gameComplete = false;
			Entry<String, GameMapGraph> nextGame = tournamentIt.next();
			GameMapGraph gameMapGraph = nextGame.getValue();
			int gameTurns = gameMapGraph.getGameTurns();
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
			
			while(gameTurns > 0) {
				
				if(validateGameCompletion(gameMapGraph)) {
					for(Player player : gameMapGraph.getPlayers()) {
						invokePlayerStrategy(gameMapGraph, player);
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
			
			tournamentMapGraph.setTournamentResult(tournamentMapGraph.getTournamentResult().concat(nextGame.getKey()+" : "+gameMapGraph.getGameResult()+"\n"));
		}
		
	}

	

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
						isGoodToStartGame = createandeditmap.uploadMap(loadMapGraph);
						if(isGoodToStartGame) {
							initialTournamentMaps.put(i, loadMapGraph);
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
	
	private void populateTournamentMapGraphs(TournamentMapGraph tournamentMapGraph) {
		
		for(int j=1;j<=tournamentMapGraph.getNumberOfGames();j++) {
			for(int k=1;k<=tournamentMapGraph.getNumberOfMaps();k++) {
				gameKey = "G"+j+"M"+k;
				initialTournamentMaps.get(k).setGameTurns(tournamentMapGraph.getGameTurns());
				countOfthePlayers = tournamentMapGraph.getInputPlayerDetails().size();
				Iterator<Entry<String, String>> inputPlayerIt = tournamentMapGraph.getInputPlayerDetails().entrySet().iterator();
				while(inputPlayerIt.hasNext()) {
					Entry<String, String> inputPlayer = inputPlayerIt.next();
					Player riskPlayer = new Player();
					riskPlayer.setName(inputPlayer.getValue().split(",")[0]);
					riskPlayer.setPlayerType(inputPlayer.getValue().split(",")[1]);
					riskPlayer.setConquerCountry(0);
					riskPlayer.setFirstReinforcement(true);
					initialTournamentMaps.get(k).getPlayers().add(riskPlayer);
					playerController.allocationOfCountry(initialTournamentMaps.get(k));
					playerController.allocationOfArmyToPlayers(initialTournamentMaps.get(k));
					playerController.allocationOfArmyToCountriesInitially(initialTournamentMaps.get(k));
					
				}
				tournamentMapGraph.getTournamentMapGraphs().put(gameKey, initialTournamentMaps.get(k));
			}
		}
	}
	
	private boolean invokePlayerStrategy(GameMapGraph mapGraph, Player currentPlayer) {
		boolean strategyComplete = false;
		
		PlayerStrategy playerStrategy = null;
		
		playerStrategy =  currentPlayer.getPlayerType().equalsIgnoreCase("Aggressive") ? new Aggressive()
				 : (currentPlayer.getPlayerType().equalsIgnoreCase("Benevolent") ? new Benevolent()
						 : (currentPlayer.getPlayerType().equalsIgnoreCase("Cheater") ? new Cheater()
								 :(currentPlayer.getPlayerType().equalsIgnoreCase("Random") ? new RandomPlayer()
										 : null)));

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
		return strategyComplete;
	}
	
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
