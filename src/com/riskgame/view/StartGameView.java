package com.riskgame.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;

import javax.swing.JButton;

public class StartGameView extends JFrame{
	private JTextField textFieldPlayer1;
	private JTextField textFieldPlayer2;
	private JTextField textFieldPlayer3;
	private JTextField textFieldPlayer4;
	private JTextField textFieldPlayer5;
	private JTextField textFieldPlayer6;
	private JPanel rootPanel;
	private int numberOfPlayers = 0;
	private Map<String, String> inputPlayerDetails = new HashMap<String, String>();
	
	
	public StartGameView(GameMapGraph mapGraph) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Start Game");
		setBounds(100, 100, 676, 442);
		getContentPane().setLayout(null);
		
		rootPanel = new JPanel();
		rootPanel.setBounds(0, 0, 658, 393);
		getContentPane().add(rootPanel);
		rootPanel.setLayout(null);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players :");
		lblNumberOfPlayers.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNumberOfPlayers.setBounds(33, 28, 128, 27);
		rootPanel.add(lblNumberOfPlayers);
		
		String[] numPlayers = {"Select One","2","3","4","5","6"};
		JComboBox comboBox = new JComboBox(numPlayers);
		comboBox.setBounds(153, 29, 94, 22);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				numberOfPlayers = Integer.parseInt((String) comboBox.getSelectedItem());
				rootPanel.removeAll();
				rootPanel.revalidate();
				rootPanel.repaint();
				initialize(mapGraph);
			}
		});
	//	initialize(mapGraph);
		rootPanel.add(comboBox);
		setVisible(true);
	}
	
	public void initialize(GameMapGraph mapGraph) {
		
		mapGraph.getInputPlayerDetails().clear();
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayerName.setBounds(225, 122, 103, 16);
		rootPanel.add(lblPlayerName);
		
		JLabel lblPlayerType = new JLabel("Player Type");
		lblPlayerType.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayerType.setBounds(391, 122, 68, 16);
		rootPanel.add(lblPlayerType);
		
		textFieldPlayer1 = new JTextField();
		textFieldPlayer1.setBounds(212, 154, 116, 22);
		textFieldPlayer1.setColumns(10);
		
		textFieldPlayer2 = new JTextField();
		textFieldPlayer2.setBounds(212, 189, 116, 22);
		textFieldPlayer2.setColumns(10);
		
		textFieldPlayer3 = new JTextField();
		textFieldPlayer3.setBounds(212, 224, 116, 22);
		textFieldPlayer3.setColumns(10);
		
		textFieldPlayer4 = new JTextField();
		textFieldPlayer4.setBounds(212, 259, 116, 22);
		textFieldPlayer4.setColumns(10);
		
		textFieldPlayer5 = new JTextField();
		textFieldPlayer5.setBounds(212, 297, 116, 22);
		textFieldPlayer5.setColumns(10);
		
		textFieldPlayer6 = new JTextField();
		textFieldPlayer6.setBounds(212, 332, 116, 22);
		textFieldPlayer6.setColumns(10);
		
		String[] playerTypes = {"Select One","Human","Aggressive","Benevolent","Cheater","Random"};
		
		JComboBox comboBoxPlayer1 = new JComboBox(playerTypes);
		comboBoxPlayer1.setBounds(355, 154, 128, 22);
		
		JComboBox comboBoxPlayer2 = new JComboBox(playerTypes);
		comboBoxPlayer2.setBounds(355, 189, 128, 22);
		
		JComboBox comboBoxPlayer3 = new JComboBox(playerTypes);
		comboBoxPlayer3.setBounds(355, 224, 128, 22);
		
		JComboBox comboBoxPlayer4 = new JComboBox(playerTypes);
		comboBoxPlayer4.setBounds(355, 259, 128, 22);
		
		JComboBox comboBoxPlayer5 = new JComboBox(playerTypes);
		comboBoxPlayer5.setBounds(355, 297, 128, 22);
		
		JComboBox comboBoxPlayer6 = new JComboBox(playerTypes);
		comboBoxPlayer6.setBounds(355, 332, 128, 22);
		
		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer1.setBounds(105, 157, 56, 16);
		
		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer2.setBounds(105, 192, 56, 16);
		
		JLabel lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer3.setBounds(105, 227, 56, 16);
		
		JLabel lblPlayer4 = new JLabel("Player 4");
		lblPlayer4.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer4.setBounds(105, 262, 56, 16);
		
		JLabel lblPlayer5 = new JLabel("Player 5");
		lblPlayer5.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer5.setBounds(105, 300, 56, 16);
		
		JLabel lblPlayer6 = new JLabel("Player 6");
		lblPlayer6.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlayer6.setBounds(105, 335, 56, 16);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnStartGame.setBounds(517, 29, 115, 25);
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
					setVisible(false);
					playerController.gamePlay(mapGraph);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		rootPanel.add(btnStartGame);
		
		JButton btnExitGame = new JButton("Exit");
		btnExitGame.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnExitGame.setBounds(517, 66, 115, 25);
		rootPanel.add(btnExitGame);

		switch (numberOfPlayers) {
		case 2: rootPanel.add(textFieldPlayer1);
				rootPanel.add(comboBoxPlayer1);
				rootPanel.add(lblPlayer1);
				rootPanel.add(textFieldPlayer2);
				rootPanel.add(comboBoxPlayer2);
				rootPanel.add(lblPlayer2);
			break;
			
		case 3: rootPanel.add(textFieldPlayer1);
				rootPanel.add(comboBoxPlayer1);
				rootPanel.add(lblPlayer1);
				rootPanel.add(textFieldPlayer2);
				rootPanel.add(comboBoxPlayer2);
				rootPanel.add(lblPlayer2);
				rootPanel.add(textFieldPlayer3);
				rootPanel.add(comboBoxPlayer3);
				rootPanel.add(lblPlayer3);
			break;	
			
		case 4: rootPanel.add(textFieldPlayer1);
				rootPanel.add(comboBoxPlayer1);
				rootPanel.add(lblPlayer1);
				rootPanel.add(textFieldPlayer2);
				rootPanel.add(comboBoxPlayer2);
				rootPanel.add(lblPlayer2);
				rootPanel.add(textFieldPlayer3);
				rootPanel.add(comboBoxPlayer3);
				rootPanel.add(lblPlayer3);
				rootPanel.add(textFieldPlayer4);
				rootPanel.add(comboBoxPlayer4);
				rootPanel.add(lblPlayer4);
			break;	
			
		case 5: rootPanel.add(textFieldPlayer1);
				rootPanel.add(comboBoxPlayer1);
				rootPanel.add(lblPlayer1);
				rootPanel.add(textFieldPlayer2);
				rootPanel.add(comboBoxPlayer2);
				rootPanel.add(lblPlayer2);
				rootPanel.add(textFieldPlayer3);
				rootPanel.add(comboBoxPlayer3);
				rootPanel.add(lblPlayer3);
				rootPanel.add(textFieldPlayer4);
				rootPanel.add(comboBoxPlayer4);
				rootPanel.add(lblPlayer4);
				rootPanel.add(textFieldPlayer5);
				rootPanel.add(comboBoxPlayer5);
				rootPanel.add(lblPlayer5);
			break;
			
		case 6: rootPanel.add(textFieldPlayer1);
				rootPanel.add(comboBoxPlayer1);
				rootPanel.add(lblPlayer1);
				rootPanel.add(textFieldPlayer2);
				rootPanel.add(comboBoxPlayer2);
				rootPanel.add(lblPlayer2);
				rootPanel.add(textFieldPlayer3);
				rootPanel.add(comboBoxPlayer3);
				rootPanel.add(lblPlayer3);
				rootPanel.add(textFieldPlayer4);
				rootPanel.add(comboBoxPlayer4);
				rootPanel.add(lblPlayer4);
				rootPanel.add(textFieldPlayer5);
				rootPanel.add(comboBoxPlayer5);
				rootPanel.add(lblPlayer5);
				rootPanel.add(textFieldPlayer6);
				rootPanel.add(comboBoxPlayer6);
				rootPanel.add(lblPlayer6);
			break;
		default:
			break;
		}
			
	
	}
}
