package com.riskgame.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;
import javax.swing.DefaultComboBoxModel;

/**
 * StartGame view class which displays the view to select the number of players 
 * and also to give the names to players and choose the type of players
 * 
 * @author Nikitha
 * @author Shresthi
 *
 */
public class StartGameView extends JFrame{
	private JTextField textFieldPlayer1;
	private JTextField textFieldPlayer2;
	private JTextField textFieldPlayer3;
	private JTextField textFieldPlayer4;
	private JTextField textFieldPlayer5;
	private JTextField textFieldPlayer6;
	private JPanel rootPanel;
	private JPanel panelPlayerDetails;
	private int numberOfPlayers = 0;
	private Map<String, String> inputPlayerDetails = new HashMap<String, String>();
	String[] numPlayers = {"Select One","2","3","4","5","6"};
	String[] playerTypes = {"Select One","Human","Aggressive","Benevolent","Cheater","Random"};
	
	/**
	 * StartGameView constructor which displays the startgame view
	 * @param mapGraph - object of GameMapGraph
	 */
	public StartGameView(GameMapGraph mapGraph) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Start Game");
		setBounds(100, 100, 676, 442);
		getContentPane().setLayout(null);
		
		rootPanel = new JPanel();
		rootPanel.setBounds(0, 0, 658, 393);
		getContentPane().add(rootPanel);
		rootPanel.setLayout(null);
		panelPlayerDetails = new JPanel();
		panelPlayerDetails.setBounds(15, 94, 628, 283);
		rootPanel.add(panelPlayerDetails);
		panelPlayerDetails.setLayout(null);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players :");
		lblNumberOfPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNumberOfPlayers.setBounds(33, 28, 128, 27);
		rootPanel.add(lblNumberOfPlayers);
		
		JComboBox comboBoxNoOfPlayers = new JComboBox(numPlayers);
		comboBoxNoOfPlayers.setModel(new DefaultComboBoxModel(new String[] {"Select One", "2", "3", "4", "5", "6"}));
		comboBoxNoOfPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBoxNoOfPlayers.setBounds(171, 31, 128, 22);
		rootPanel.add(comboBoxNoOfPlayers);
		comboBoxNoOfPlayers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				if(((String) comboBox.getSelectedItem()).equalsIgnoreCase("Select One")) {
					numberOfPlayers = 0;
				}
				else {
					numberOfPlayers = Integer.parseInt((String) comboBox.getSelectedItem());
				}
				panelPlayerDetails.removeAll();
				panelPlayerDetails.revalidate();
				panelPlayerDetails.repaint();
				initialize(mapGraph);
			}
		});
	
		//initialize(mapGraph);
		setVisible(true);
	}
	
	/**
	 * The method which contains all the buttons, labels, combo boxes
	 * required  for the start game view
	 *  
	 * @param mapGraph - object of the GameMapGraph
	 */
	public void initialize(GameMapGraph mapGraph) {
		
		
		mapGraph.getInputPlayerDetails().clear();
		
		JLabel lblPlayerType = new JLabel("Player Type");
		lblPlayerType.setBounds(373, 16, 100, 17);
		lblPlayerType.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(210, 16, 103, 16);
		lblPlayerName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setBounds(112, 42, 56, 16);
		lblPlayer1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(194, 42, 116, 22);
		textFieldPlayer1.setColumns(10);
		
		JComboBox comboBoxPlayer1 = new JComboBox(playerTypes);
		comboBoxPlayer1.setBounds(344, 42, 128, 22);
		
		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setBounds(112, 80, 56, 16);
		lblPlayer2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(194, 81, 116, 22);
		textFieldPlayer2.setColumns(10);
		
		JComboBox comboBoxPlayer2 = new JComboBox(playerTypes);
		comboBoxPlayer2.setBounds(344, 80, 128, 22);
		
		JLabel lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setBounds(112, 118, 56, 16);
		lblPlayer3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer3 = new JTextField();
		textFieldPlayer3.setBounds(194, 118, 116, 22);
		textFieldPlayer3.setColumns(10);
		
		JComboBox comboBoxPlayer3 = new JComboBox(playerTypes);
		comboBoxPlayer3.setBounds(344, 118, 128, 22);
		
		JLabel lblPlayer4 = new JLabel("Player 4");
		lblPlayer4.setBounds(112, 156, 56, 16);
		lblPlayer4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer4 = new JTextField();
		textFieldPlayer4.setBounds(194, 156, 116, 22);
		textFieldPlayer4.setColumns(10);
		
		JComboBox comboBoxPlayer4 = new JComboBox(playerTypes);
		comboBoxPlayer4.setBounds(344, 156, 128, 22);
		
		JLabel lblPlayer5 = new JLabel("Player 5");
		lblPlayer5.setBounds(112, 194, 56, 16);
		lblPlayer5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer5 = new JTextField();
		textFieldPlayer5.setBounds(194, 194, 116, 22);
		textFieldPlayer5.setColumns(10);
		
		JComboBox comboBoxPlayer5 = new JComboBox(playerTypes);
		comboBoxPlayer5.setBounds(344, 194, 128, 22);
		
		JLabel lblPlayer6 = new JLabel("Player 6");
		lblPlayer6.setBounds(112, 232, 56, 16);
		lblPlayer6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		textFieldPlayer6 = new JTextField();
		textFieldPlayer6.setBounds(194, 232, 116, 22);
		textFieldPlayer6.setColumns(10);
		
		JComboBox comboBoxPlayer6 = new JComboBox(playerTypes);
		comboBoxPlayer6.setBounds(344, 232, 128, 22);

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
			
		case 5: panelPlayerDetails.add(lblPlayerType);
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
				panelPlayerDetails.add(textFieldPlayer5);
				panelPlayerDetails.add(comboBoxPlayer5);
				panelPlayerDetails.add(lblPlayer5);
			break;
			
		case 6: panelPlayerDetails.add(lblPlayerType);
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
				panelPlayerDetails.add(textFieldPlayer5);
				panelPlayerDetails.add(comboBoxPlayer5);
				panelPlayerDetails.add(lblPlayer5);
				panelPlayerDetails.add(textFieldPlayer6);
				panelPlayerDetails.add(comboBoxPlayer6);
				panelPlayerDetails.add(lblPlayer6);
			break;
		default:
			break;
		}
		
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(498, 57, 115, 25);
		if(numberOfPlayers > 0) {
			panelPlayerDetails.add(btnStartGame);
		}
		btnStartGame.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.setBounds(498, 93, 115, 25);
		if(numberOfPlayers > 0) {
			panelPlayerDetails.add(btnExitGame);
		}
		btnExitGame.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnExitGame.addActionListener(new ActionListener() {
			
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
						
					case 5: mapGraph.getInputPlayerDetails().put("1", textFieldPlayer1.getText()+","+comboBoxPlayer1.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("2", textFieldPlayer2.getText()+","+comboBoxPlayer2.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("3", textFieldPlayer3.getText()+","+comboBoxPlayer3.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("4", textFieldPlayer4.getText()+","+comboBoxPlayer4.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("5", textFieldPlayer5.getText()+","+comboBoxPlayer5.getSelectedItem());
						break;
						
					case 6: mapGraph.getInputPlayerDetails().put("1", textFieldPlayer1.getText()+","+comboBoxPlayer1.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("2", textFieldPlayer2.getText()+","+comboBoxPlayer2.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("3", textFieldPlayer3.getText()+","+comboBoxPlayer3.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("4", textFieldPlayer4.getText()+","+comboBoxPlayer4.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("5", textFieldPlayer5.getText()+","+comboBoxPlayer5.getSelectedItem());
							mapGraph.getInputPlayerDetails().put("6", textFieldPlayer6.getText()+","+comboBoxPlayer6.getSelectedItem());
						break;
					default:
						break;
					}
					if(!validatePlayerDetails(mapGraph)) {
						setVisible(false);
						if(automatedPlay(mapGraph)) {
							mapGraph.setGameType("Tournament");
							AutomatedPlayerView automatedPlayerView = new AutomatedPlayerView(mapGraph);
						}
						playerController.gamePlay(mapGraph);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Enter the details for all Players");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Method to validate the player details
	 * @param mapGraph - object of the GameMapGraph
	 * @return missingData - boolean variable
	 */
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
	
	/**
	 * Method to check if play needs to be authomated when human 
	 * player is not chosen
	 * @param mapGraph - object of GameMapGraph
	 * @return automatedPlay - boolean variable
	 */
	public boolean automatedPlay(GameMapGraph mapGraph) {
		boolean automatedPlay = true;
		int i = 0;
		for(Map.Entry<String, String> inputPlayer : mapGraph.getInputPlayerDetails().entrySet()) {
			if(inputPlayer.getValue().split(",")[1].equalsIgnoreCase("Human")) {
				i++;
				break;
			}
		}
		if(i > 0 || mapGraph.getInputPlayerDetails().size() == 0) {
			automatedPlay = false;
		}
		return automatedPlay;
	}
}
