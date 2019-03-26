package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.experimental.theories.FromDataPoints;

import com.riskgame.controller.DiceController;
import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

/**
 * This class aims to show the dice view
 * 
 * @author Nikitha
 *
 */
public class DiceView implements Observer{

	private JFrame frmDiceView;
	private JPanel diceRootPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField messageField;
	int attackerDiceCount = 0;
	int defenderDiceCount = 0;
	private DiceController diceController;

	/**
	 * Launch the application.
	 * 
	 * @param args- arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceView window = new DiceView(new GameMapGraph(), new Country(), new Country());
					window.frmDiceView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * DiceView Constructor
	 */
	public DiceView() {
		
	}
	
	/**
	 * DiceView Constructor with parameters.
	 * 
	 * @param gameMapGraph - GameMapGraph object
	 * @param defender     - the defender
	 * @param attacker     - the attacker
	 */
	public DiceView(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		diceController = new DiceController();
		frmDiceView = new JFrame();
		frmDiceView.setTitle("Dice View");
		frmDiceView.setBounds(600, 300, 571, 442);
		frmDiceView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDiceView.getContentPane().setLayout(null);
		
		diceRootPanel = new JPanel();
		diceRootPanel.setBounds(0, 0, 553, 395);
		diceRootPanel.setLayout(null);
		frmDiceView.getContentPane().add(diceRootPanel);
		
		initialize(gameMapGraph, attacker, defender);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param gameMapGraph - GameMapGraph object
	 * @param defender     - the defender
	 * @param attacker     - the attacker
	 */
	private void initialize(GameMapGraph gameMapGraph, Country attackerCountry, Country defenderCountry) {
		JLabel lblAttacker = new JLabel("Attacker");
		lblAttacker.setFont(new Font("Calibri", Font.BOLD, 17));
		lblAttacker.setBounds(74, 8, 66, 20);
		diceRootPanel.add(lblAttacker);
		
		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setFont(new Font("Calibri", Font.BOLD, 17));
		lblDefender.setBounds(281, 8, 71, 20);
		diceRootPanel.add(lblDefender);
		
		JRadioButton attackerDice1Radio = new JRadioButton("1");
		attackerDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice1Radio.setActionCommand("1");
		attackerDice1Radio.setBounds(44, 73, 56, 29);
		
		JRadioButton attackerDice2Radio = new JRadioButton("2");
		attackerDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice2Radio.setBounds(98, 73, 47, 29);
		attackerDice2Radio.setActionCommand("2");
		
		JRadioButton attackerDice3Radio = new JRadioButton("3");
		attackerDice3Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice3Radio.setBounds(150, 70, 47, 34);
		attackerDice3Radio.setActionCommand("3");
		
		if(attackerCountry.getNoOfArmies() < 2) {
			diceRootPanel.add(attackerDice1Radio);
		}
		else if(attackerCountry.getNoOfArmies() < 3) {
			diceRootPanel.add(attackerDice1Radio);
			diceRootPanel.add(attackerDice2Radio);
		}
		else {
			diceRootPanel.add(attackerDice1Radio);
			diceRootPanel.add(attackerDice2Radio);
			diceRootPanel.add(attackerDice3Radio);
		}
		
		JRadioButton defenderDice1Radio = new JRadioButton("1");
		defenderDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice1Radio.setBounds(262, 73, 47, 29);
		defenderDice1Radio.setActionCommand("1");
		
		
		JRadioButton defenderDice2Radio = new JRadioButton("2");
		defenderDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice2Radio.setBounds(320, 73, 47, 29);
		defenderDice2Radio.setActionCommand("2");
		
		if(defenderCountry.getNoOfArmies() < 2) {
			diceRootPanel.add(defenderDice1Radio);
		}
		else {
			diceRootPanel.add(defenderDice1Radio);
			diceRootPanel.add(defenderDice2Radio);
		}
		//Group attacker radio buttons.
	    ButtonGroup attackerGroup = new ButtonGroup();
	    attackerGroup.add(attackerDice1Radio);
	    attackerGroup.add(attackerDice2Radio);
	    attackerGroup.add(attackerDice3Radio);
	    
		//Group defender radio buttons.
	    ButtonGroup defenderGroup = new ButtonGroup();
	    defenderGroup.add(defenderDice1Radio);
	    defenderGroup.add(defenderDice2Radio);
		
		JLabel lblDice = new JLabel("Dice1");
		lblDice.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice.setBounds(15, 135, 47, 20);
		
		JLabel lblDice_1 = new JLabel("Dice2");
		lblDice_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice_1.setBounds(15, 174, 47, 20);
		
		JLabel lblDice_2 = new JLabel("Dice3");
		lblDice_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice_2.setBounds(15, 210, 47, 20);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setBounds(68, 132, 146, 26);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_1.setBounds(68, 171, 146, 26);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_2.setBounds(68, 210, 146, 26);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_3.setBounds(260, 132, 146, 26);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_4.setBounds(260, 171, 146, 26);
		textField_4.setColumns(10);
		
		if(attackerDiceCount == 3) {
			diceRootPanel.add(lblDice);
			diceRootPanel.add(lblDice_1);
			diceRootPanel.add(lblDice_2);
			textField.setText(attackerCountry.getDiceValues().get(0).toString());
			textField_1.setText(attackerCountry.getDiceValues().get(1).toString());
			textField_2.setText(attackerCountry.getDiceValues().get(2).toString());
			diceRootPanel.add(textField);
			diceRootPanel.add(textField_1);
			diceRootPanel.add(textField_2);
		}
		else if(attackerDiceCount == 2) {
			diceRootPanel.add(lblDice);
			diceRootPanel.add(lblDice_1);
			textField.setText(attackerCountry.getDiceValues().get(0).toString());
			textField_1.setText(attackerCountry.getDiceValues().get(1).toString());
			diceRootPanel.add(textField);
			diceRootPanel.add(textField_1);
		}
		else {
			diceRootPanel.add(lblDice);
			if(attackerCountry.getDiceValues() != null && attackerCountry.getDiceValues().size() > 0) {
				textField.setText(attackerCountry.getDiceValues().get(0).toString());
			}
			diceRootPanel.add(textField);
		}
		
		if(defenderDiceCount == 2) {
			if(!attackerDice3Radio.isSelected() || !attackerDice2Radio.isSelected()) {
				diceRootPanel.add(lblDice_1);
			}
			textField_3.setText(defenderCountry.getDiceValues().get(0).toString());
			textField_4.setText(defenderCountry.getDiceValues().get(1).toString());
			diceRootPanel.add(textField_3);
			diceRootPanel.add(textField_4);
		}
		else {
			if(defenderCountry.getDiceValues() != null && defenderCountry.getDiceValues().size() > 0) {
				textField_3.setText(defenderCountry.getDiceValues().get(0).toString());
			}
			diceRootPanel.add(textField_3);
		}
		
		messageField = new JTextField();
		messageField.setEnabled(false);
		messageField.setFont(new Font("Arial", Font.PLAIN, 17));
		messageField.setBounds(102, 333, 439, 26);
		messageField.setColumns(10);
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.setEnabled(false);
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = "";
				attackerDiceCount = Integer.parseInt(attackerGroup.getSelection().getActionCommand());
				defenderDiceCount = Integer.parseInt(defenderGroup.getSelection().getActionCommand());
				
				
			//	DiceController diceController = new DiceController();
				String diceRollResult = diceController.startDiceRoll(attackerDiceCount, defenderDiceCount, attackerCountry, defenderCountry);
				int attackerLostCount = Integer.parseInt(diceRollResult.split(":")[0]);
				int defenderLostCount = Integer.parseInt(diceRollResult.split(":")[1]);
				
				if(attackerLostCount > 0) {
					message = "Attacker has lost "+attackerLostCount+" army(ies)";
				}
				if(defenderLostCount > 0) {
					message = message.length() > 0 ? (message+" ; "+"Defender has lost "+defenderLostCount+" army(ies)") : "Defender has lost "+defenderLostCount+" army(ies)";
				}
				
				gameMapGraph.setDiceViewMessage(message);
				diceRootPanel.removeAll();
				diceRootPanel.revalidate();
				diceRootPanel.repaint();
				initialize(gameMapGraph, attackerCountry, defenderCountry);
			}
		});
		btnRollDice.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRollDice.setBounds(429, 41, 112, 25);
		diceRootPanel.add(btnRollDice);
		messageField.setText(gameMapGraph.getDiceViewMessage());
		diceRootPanel.add(messageField);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 14));
		btnContinue.setBounds(429, 71, 112, 25);
		btnContinue.setEnabled(false);
		
		if(attackerCountry.getNoOfArmies() > 1 && defenderCountry.getNoOfArmies() > 0) {
			btnRollDice.setEnabled(true);
			btnContinue.setEnabled(true);
		}
		
		btnContinue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				attackerCountry.getDiceValues().clear();
				defenderCountry.getDiceValues().clear();
				attackerGroup.clearSelection();
				defenderGroup.clearSelection();
				attackerDiceCount = 0;
				defenderDiceCount = 0;
				gameMapGraph.setDiceViewMessage("");
				diceRootPanel.removeAll();
				diceRootPanel.revalidate();
				diceRootPanel.repaint();
				initialize(gameMapGraph, attackerCountry, defenderCountry);
			}
		});
		diceRootPanel.add(btnContinue);
		
		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEndTurn.setBounds(429, 101, 112, 25);
		btnEndTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmDiceView.dispose();
			}
		});
		diceRootPanel.add(btnEndTurn);
		
		
		JButton btnMoveArmies = new JButton("MoveArmies");
		btnMoveArmies.setFont(new Font("Arial", Font.PLAIN, 14));
		btnMoveArmies.setBounds(429, 131, 112, 25);
		btnMoveArmies.setEnabled(false);
		
		if(defenderCountry.getNoOfArmies() == 0) {
			btnMoveArmies.setEnabled(true);
		}
		
		btnMoveArmies.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerController playerController = new PlayerController();
				JFrame moveArmyFrame = new JFrame("Move Armies");
				String armiesToMove = JOptionPane.showInputDialog(moveArmyFrame, "Enter the number of armies to be moved:");
				if(armiesToMove != null) {
					Player attacker = diceController.getPlayerForCountry(gameMapGraph, attackerCountry.getName());
					Player defender = diceController.getPlayerForCountry(gameMapGraph, defenderCountry.getName());
					boolean moveComplete = diceController.moveArmies(Integer.parseInt(armiesToMove), attackerCountry, defenderCountry, gameMapGraph);
					if(moveComplete) {
						if(defender.getMyCountries().size() == 1) {
							attacker.getPlayersCardList().putAll(defender.getPlayersCardList());
							JOptionPane.showMessageDialog(null, "Player "+defender.getName()+" has lost the game!!");
						}
						JOptionPane.showMessageDialog(null, armiesToMove+" armies moved to "+defenderCountry.getName());
						frmDiceView.dispose();
					}
					else {
						moveArmyFrame.dispose();
					}
				}
				else {
					moveArmyFrame.dispose();
				}
			}
		});
		
		diceRootPanel.add(btnMoveArmies);
		
		JLabel lblAttackerCountry = new JLabel(attackerCountry.getName());
		lblAttackerCountry.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAttackerCountry.setBounds(34, 41, 89, 16);
		diceRootPanel.add(lblAttackerCountry);
		
		JLabel lblAttackerArmies = new JLabel(Integer.toString(attackerCountry.getNoOfArmies()));
		lblAttackerArmies.setBounds(154, 41, 19, 16);
		diceRootPanel.add(lblAttackerArmies);
		
		JLabel lblDefenderCountry = new JLabel(defenderCountry.getName());
		lblDefenderCountry.setBounds(238, 41, 65, 16);
		diceRootPanel.add(lblDefenderCountry);
		
		JLabel lblDefenderArmies = new JLabel(Integer.toString(defenderCountry.getNoOfArmies()));
		lblDefenderArmies.setBounds(350, 41, 31, 16);
		diceRootPanel.add(lblDefenderArmies);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Arial", Font.BOLD, 17));
		lblResult.setBounds(34, 336, 56, 20);
		diceRootPanel.add(lblResult);
		
		if(attackerCountry.getNoOfArmies() == 1) {
			gameMapGraph.setDiceViewMessage("Armies exhausted!! Cannot attack anymore!! End your turn.");
			btnRollDice.setEnabled(false);
			btnMoveArmies.setEnabled(false);
			btnContinue.setEnabled(false);
		}
		
		frmDiceView.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(frmDiceView != null) {
			frmDiceView.revalidate();
			frmDiceView.repaint();
		}
	}
}