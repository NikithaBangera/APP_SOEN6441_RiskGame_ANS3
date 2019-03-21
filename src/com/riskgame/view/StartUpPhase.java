package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class StartUpPhase {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCountryArmies;
	private JTextField textField_2;
	private JLabel lblEnterNumberOf;
	private JLabel lblAmies;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblStartupPhase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUpPhase window = new StartUpPhase();
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
	public StartUpPhase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 553, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPlayer.setBounds(33, 105, 79, 33);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblArmies = new JLabel("Armies:");
		lblArmies.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblArmies.setBounds(305, 105, 91, 33);
		frame.getContentPane().add(lblArmies);
		
		textField = new JTextField();
		textField.setText("aa ");
		textField.setEditable(false);
		textField.setBounds(126, 109, 134, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("aa ");
		textField_1.setEditable(false);
		textField_1.setBounds(408, 108, 59, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblCountryArmies = new JLabel("Country: ");
		lblCountryArmies.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblCountryArmies.setBounds(33, 192, 102, 33);
		frame.getContentPane().add(lblCountryArmies);
		
		textField_2 = new JTextField();
		textField_2.setText("aa ");
		textField_2.setEditable(false);
		textField_2.setBounds(147, 197, 315, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblEnterNumberOf = new JLabel("Armies you want to assign");
		lblEnterNumberOf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblEnterNumberOf.setBounds(37, 286, 175, 33);
		frame.getContentPane().add(lblEnterNumberOf);
		
		lblAmies = new JLabel("Amies: ");
		lblAmies.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAmies.setBounds(33, 245, 102, 28);
		frame.getContentPane().add(lblAmies);
		
		textField_3 = new JTextField();
		textField_3.setText("aa ");
		textField_3.setEditable(false);
		textField_3.setBounds(149, 243, 315, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(224, 288, 240, 28);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnOkay.setBounds(187, 349, 97, 25);
		frame.getContentPane().add(btnOkay);
		
		lblStartupPhase = new JLabel("  Start-Up Phase");
		lblStartupPhase.setForeground(Color.BLUE);
		lblStartupPhase.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblStartupPhase.setBounds(111, 24, 255, 48);
		frame.getContentPane().add(lblStartupPhase);
	}
}
