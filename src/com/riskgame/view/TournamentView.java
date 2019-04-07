package com.riskgame.view;

import javax.swing.JFrame;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TournamentView extends JFrame{
	private JTextField textFieldTurnsPerGame;
	private JTextField textFieldPlayer1;
	private JTextField textFieldPlayer2;
	private JTextField textFieldPlayer3;
	private JTextField textFieldPlayer4;
	private JPanel rootPanel;
	private JPanel panelOptions;
	private JPanel panelPlayerDetails;
	private int numberOfPlayers = 0;
	private Map<String, String> inputPlayerDetails = new HashMap<String, String>();
	String[] numPlayers = {"Select One","2","3","4"};
	String[] numMaps = {"Select One","1","2","3","4","5"};
	String[] numGames = {"Select One","1","2","3","4","5"};
	String[] playerTypes = {"Select One","Aggressive","Benevolent","Cheater","Random"};

	public TournamentView(GameMapGraph gameMapGraph) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tournament");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 773, 524);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panelOptions = new JPanel();
		panelOptions.setBounds(12, 0, 749, 174);
		panel.add(panelOptions);
		panelOptions.setLayout(null);
		
		JLabel lblNumberOfMaps = new JLabel("Number of Maps:");
		lblNumberOfMaps.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNumberOfMaps.setBounds(12, 13, 120, 26);
		panelOptions.add(lblNumberOfMaps);
		
		JLabel lblNewLabel = new JLabel("Number of Players:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(12, 52, 120, 26);
		panelOptions.add(lblNewLabel);
		
		JLabel lblNumberOfGames = new JLabel("Number of Games:");
		lblNumberOfGames.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNumberOfGames.setBounds(12, 92, 120, 26);
		panelOptions.add(lblNumberOfGames);
		
		JComboBox comboBoxMaps = new JComboBox(numMaps);
		comboBoxMaps.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxMaps.setBounds(145, 14, 112, 22);
		panelOptions.add(comboBoxMaps);
		
		JComboBox comboBoxPlayers = new JComboBox(numPlayers);
		comboBoxPlayers.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxPlayers.setBounds(144, 53, 112, 22);
		panelOptions.add(comboBoxPlayers);
		
		JComboBox comboBoxGames = new JComboBox(numGames);
		comboBoxGames.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBoxGames.setBounds(144, 93, 112, 22);
		panelOptions.add(comboBoxGames);
		
		JLabel lblNumberOfTurns = new JLabel("Number of Turns:");
		lblNumberOfTurns.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNumberOfTurns.setBounds(301, 13, 120, 26);
		panelOptions.add(lblNumberOfTurns);
		
		textFieldTurnsPerGame = new JTextField();
		textFieldTurnsPerGame.setBounds(417, 14, 116, 22);
		panelOptions.add(textFieldTurnsPerGame);
		textFieldTurnsPerGame.setColumns(10);
				
		panelPlayerDetails = new JPanel();
		panelPlayerDetails.setBounds(12, 175, 749, 349);
		panel.add(panelPlayerDetails);
		initialize(gameMapGraph);
		setVisible(true);
	}
	
	public void initialize(GameMapGraph mapGraph) {
		mapGraph.getInputPlayerDetails().clear();
		
		JLabel lblPlayerType = new JLabel("Player Type");
		lblPlayerType.setBounds(242, 69, 68, 17);
		lblPlayerType.setFont(new Font("Calibri", Font.BOLD, 14));
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(118, 69, 73, 17);
		lblPlayerName.setFont(new Font("Calibri", Font.BOLD, 14));
		
		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setBounds(35, 113, 46, 17);
		lblPlayer1.setFont(new Font("Calibri", Font.BOLD, 14));
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(96, 109, 116, 22);
		textFieldPlayer1.setColumns(10);
		
		JComboBox comboBoxPlayer1 = new JComboBox(playerTypes);
		comboBoxPlayer1.setBounds(232, 109, 89, 22);
		
		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setBounds(35, 155, 46, 17);
		lblPlayer2.setFont(new Font("Calibri", Font.BOLD, 14));
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(96, 151, 116, 22);
		textFieldPlayer2.setColumns(10);
		
		JComboBox comboBoxPlayer2 = new JComboBox(playerTypes);
		comboBoxPlayer2.setBounds(232, 151, 89, 22);
		
		JLabel lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setBounds(35, 197, 46, 17);
		lblPlayer3.setFont(new Font("Calibri", Font.BOLD, 14));
		
		textFieldPlayer3 = new JTextField();
		textFieldPlayer3.setBounds(96, 193, 116, 22);
		textFieldPlayer3.setColumns(10);
		
		JComboBox comboBoxPlayer3 = new JComboBox(playerTypes);
		comboBoxPlayer3.setBounds(232, 193, 89, 22);
		
		JLabel lblPlayer4 = new JLabel("Player 4");
		lblPlayer4.setBounds(35, 236, 46, 17);
		lblPlayer4.setFont(new Font("Calibri", Font.BOLD, 14));
		
		textFieldPlayer4 = new JTextField();
		textFieldPlayer4.setBounds(96, 232, 116, 22);
		textFieldPlayer4.setColumns(10);
		
		JComboBox comboBoxPlayer4 = new JComboBox(playerTypes);
		comboBoxPlayer4.setBounds(232, 232, 89, 22);

		switch (numberOfPlayers) {
		case 2: panelPlayerDetails.add(lblPlayerType);
				panelPlayerDetails.add(lblPlayerName);
				panelPlayerDetails.add(textFieldPlayer1);
				panelPlayerDetails.add(comboBoxPlayer1);
				panelPlayerDetails.add(lblPlayer1);
				panelPlayerDetails.add(textFieldPlayer2);
				panelPlayerDetails.add(comboBoxPlayer2);
				panelPlayerDetails.add(lblPlayer2);
			break;
			
		case 3: panelPlayerDetails.add(lblPlayerType);
				panelPlayerDetails.add(lblPlayerName);
				panelPlayerDetails.add(textFieldPlayer1);
				panelPlayerDetails.add(comboBoxPlayer1);
				panelPlayerDetails.add(lblPlayer1);
				panelPlayerDetails.add(textFieldPlayer2);
				panelPlayerDetails.add(comboBoxPlayer2);
				panelPlayerDetails.add(lblPlayer2);
				panelPlayerDetails.add(textFieldPlayer3);
				panelPlayerDetails.add(comboBoxPlayer3);
				panelPlayerDetails.add(lblPlayer3);
			break;	
			
		case 4: panelPlayerDetails.add(lblPlayerType);
				panelPlayerDetails.add(lblPlayerName);
				panelPlayerDetails.add(textFieldPlayer1);
				panelPlayerDetails.add(comboBoxPlayer1);
				panelPlayerDetails.add(lblPlayer1);
				panelPlayerDetails.add(textFieldPlayer2);
				panelPlayerDetails.add(comboBoxPlayer2);
				panelPlayerDetails.add(lblPlayer2);
				panelPlayerDetails.add(textFieldPlayer3);
				panelPlayerDetails.add(comboBoxPlayer3);
				panelPlayerDetails.add(lblPlayer3);
				panelPlayerDetails.add(textFieldPlayer4);
				panelPlayerDetails.add(comboBoxPlayer4);
				panelPlayerDetails.add(lblPlayer4);
			break;	
			
	
		default:
			break;
		}
		panelPlayerDetails.setLayout(null);
		panelPlayerDetails.add(lblPlayerType);
		panelPlayerDetails.add(lblPlayerName);
		panelPlayerDetails.add(textFieldPlayer1);
		panelPlayerDetails.add(comboBoxPlayer1);
		panelPlayerDetails.add(lblPlayer1);
		panelPlayerDetails.add(textFieldPlayer2);
		panelPlayerDetails.add(comboBoxPlayer2);
		panelPlayerDetails.add(lblPlayer2);
		panelPlayerDetails.add(textFieldPlayer3);
		panelPlayerDetails.add(comboBoxPlayer3);
		panelPlayerDetails.add(lblPlayer3);
		panelPlayerDetails.add(textFieldPlayer4);
		panelPlayerDetails.add(comboBoxPlayer4);
		panelPlayerDetails.add(lblPlayer4);
		
		JPanel panel = new JPanel();
		panel.setBounds(354, 0, 395, 349);
		panelPlayerDetails.add(panel);
		
		JLabel lblPlayerDetails = new JLabel("Player Details");
		lblPlayerDetails.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPlayerDetails.setBounds(110, 13, 145, 16);
		panelPlayerDetails.add(lblPlayerDetails);
		
		JButton btnStartGame = new JButton("Play");
		btnStartGame.setFont(new Font("Calibri", Font.BOLD, 14));
		btnStartGame.setBounds(619, 52, 118, 25);
		panelOptions.add(btnStartGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnExit.setBounds(619, 92, 118, 25);
		panelOptions.add(btnExit);

	
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int exit = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to Exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION);
				if(exit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnStartGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mapGraph.setGameTurns(textFieldTurnsPerGame.getText());
					PlayerController playerController = new PlayerController();
					switch (numberOfPlayers) {
					case 2: mapGraph.getInputPlayerDetails().put("1", textFieldPlayer1.getText()+","+comboBoxPlayer1.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("2", textFieldPlayer2.getText()+","+comboBoxPlayer2.getSelectedItem());
						break;
						
					case 3: mapGraph.getInputPlayerDetails().put("1", textFieldPlayer1.getText()+","+comboBoxPlayer1.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("2", textFieldPlayer2.getText()+","+comboBoxPlayer2.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("3", textFieldPlayer3.getText()+","+comboBoxPlayer3.getSelectedItem());
						break;	
						
					case 4: mapGraph.getInputPlayerDetails().put("1", textFieldPlayer1.getText()+","+comboBoxPlayer1.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("2", textFieldPlayer2.getText()+","+comboBoxPlayer2.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("3", textFieldPlayer3.getText()+","+comboBoxPlayer3.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("4", textFieldPlayer4.getText()+","+comboBoxPlayer4.getSelectedItem());
						break;	
						
					default:
						break;
					}
					
					if(validateGameTurnsOptions(mapGraph)) {
						if(!validatePlayerDetails(mapGraph)) {
							setVisible(false);
							playerController.gamePlay(mapGraph);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please Enter the details for all Players");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter the number of game turns between 10 and 50.");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public boolean validateGameTurnsOptions(GameMapGraph mapGraph) {
		boolean isValid = false;
		if(mapGraph.getGameTurns() != null && mapGraph.getGameTurns().length() > 0
				&& Integer.parseInt(mapGraph.getGameTurns()) >= 10 && Integer.parseInt(mapGraph.getGameTurns()) <= 50) {
			isValid = true;
		}
		return isValid;
	}
	
	public boolean validatePlayerDetails(GameMapGraph mapGraph) {
		boolean missingData = false;
		Iterator<Entry<String, String>> playerDetailsIT = mapGraph.getInputPlayerDetails().entrySet().iterator();
		while(playerDetailsIT.hasNext()) {
			Entry<String, String> inputPlayer = playerDetailsIT.next();
			if((inputPlayer.getValue().split(",")[0]).length() == 0 || (inputPlayer.getValue().split(",")[1]).equalsIgnoreCase("Select One")) {
				missingData = true;
				break;
			}
		}
		return missingData;
	}
}
