package com.riskgame.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.riskgame.controller.DiceController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;

public class NewDiceView {
	private JFrame frame;
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
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDiceView window = new NewDiceView(new GameMapGraph(), new Country(), new Country());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewDiceView() {
		
	}
	
	/**
	 * Create the application.
	 * @param gameMapGraph 
	 * @param defender 
	 * @param attacker 
	 */
	public NewDiceView(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		diceController = new DiceController();
		frame = new JFrame();
		frame.setBounds(600, 300, 571, 442);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		diceRootPanel = new JPanel();
		diceRootPanel.setBounds(0, 0, 565, 430);
		frame.getContentPane().add(diceRootPanel);
		
		initialize(gameMapGraph, attacker, defender);
	}
	
	private void initialize(GameMapGraph gameMapGraph, Country attackerCountry, Country defenderCountry) {
		diceRootPanel.setLayout(null);
		JLabel lblAttacker = new JLabel("Attacker");
		lblAttacker.setFont(new Font("Arial", Font.BOLD, 17));
		lblAttacker.setBounds(92, 5, 66, 20);
		diceRootPanel.add(lblAttacker);
		
		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setFont(new Font("Arial", Font.BOLD, 17));
		lblDefender.setBounds(260, 5, 71, 20);
		diceRootPanel.add(lblDefender);
	}
}
