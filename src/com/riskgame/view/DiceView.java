package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.riskgame.controller.DiceController;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiceView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	int attackerDiceCount = 0;
	int defenderDiceCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceView window = new DiceView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DiceView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 571, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAttacker = new JLabel("Attacker");
		lblAttacker.setFont(new Font("Arial", Font.BOLD, 17));
		lblAttacker.setBounds(87, 33, 69, 20);
		frame.getContentPane().add(lblAttacker);
		
		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setFont(new Font("Arial", Font.BOLD, 17));
		lblDefender.setBounds(281, 33, 86, 20);
		frame.getContentPane().add(lblDefender);
		
		JRadioButton attackerDice1Radio = new JRadioButton("1");
		attackerDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice1Radio.setSelected(true);
		attackerDice1Radio.setBounds(44, 73, 56, 29);
		frame.getContentPane().add(attackerDice1Radio);
		
		JRadioButton attackerDice2Radio = new JRadioButton("2");
		attackerDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice2Radio.setBounds(98, 73, 47, 29);
		frame.getContentPane().add(attackerDice2Radio);
		
		JRadioButton attackerDice3Radio = new JRadioButton("3");
		attackerDice3Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice3Radio.setBounds(150, 70, 47, 34);
		frame.getContentPane().add(attackerDice3Radio);
		
		JRadioButton defenderDice1Radio = new JRadioButton("1");
		defenderDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice1Radio.setSelected(true);
		defenderDice1Radio.setBounds(262, 73, 47, 29);
		frame.getContentPane().add(defenderDice1Radio);
		
		JRadioButton defenderDice2Radio = new JRadioButton("2");
		defenderDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice2Radio.setBounds(320, 73, 47, 29);
		frame.getContentPane().add(defenderDice2Radio);
		
		JLabel lblDice = new JLabel("Dice1");
		lblDice.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice.setBounds(15, 135, 47, 20);
		frame.getContentPane().add(lblDice);
		
		JLabel lblDice_1 = new JLabel("Dice2");
		lblDice_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice_1.setBounds(15, 174, 47, 20);
		frame.getContentPane().add(lblDice_1);
		
		JLabel lblDice_2 = new JLabel("Dice3");
		lblDice_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDice_2.setBounds(15, 210, 47, 20);
		frame.getContentPane().add(lblDice_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setBounds(68, 132, 146, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_1.setBounds(68, 171, 146, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_2.setBounds(68, 210, 146, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_3.setBounds(260, 132, 146, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_4.setBounds(260, 171, 146, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		//Group attacker radio buttons.
	    ButtonGroup attackerGroup = new ButtonGroup();
	    attackerGroup.add(attackerDice1Radio);
	    attackerGroup.add(attackerDice2Radio);
	    attackerGroup.add(attackerDice3Radio);
	    
		//Group defender radio buttons.
	    ButtonGroup defenderGroup = new ButtonGroup();
	    defenderGroup.add(defenderDice1Radio);
	    defenderGroup.add(defenderDice2Radio);
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(attackerDice1Radio.isSelected() && defenderDice1Radio.isSelected()) {
					defenderDiceCount = 1;
					attackerDiceCount = 1;
				}
				else if(attackerDice2Radio.isSelected() && defenderDice2Radio.isSelected()) {
					defenderDiceCount = 2;
					attackerDiceCount = 2;
				}
				else if(attackerDice3Radio.isSelected() && defenderDice2Radio.isSelected()) {
					defenderDiceCount = 2;
					attackerDiceCount = 3;
				}
				else if(attackerDice3Radio.isSelected() && defenderDice1Radio.isSelected()) {
					defenderDiceCount = 1;
					attackerDiceCount = 3;
				}
				else if(attackerDice1Radio.isSelected() && defenderDice2Radio.isSelected()) {
					defenderDiceCount = 2;
					attackerDiceCount = 1;
				}
				else if(attackerDice2Radio.isSelected() && defenderDice1Radio.isSelected()) {
					defenderDiceCount = 1;
					attackerDiceCount = 2;
				}
				DiceController diceController = new DiceController();
				diceController.startDiceRoll(attackerDiceCount, defenderDiceCount);
			}
		});
		btnRollDice.setFont(new Font("Arial", Font.PLAIN, 17));
		btnRollDice.setBounds(401, 73, 115, 29);
		frame.getContentPane().add(btnRollDice);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_5.setEnabled(false);
		textField_5.setBounds(68, 292, 370, 26);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
	}
	
	
}
