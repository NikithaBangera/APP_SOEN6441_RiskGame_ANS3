package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class CardView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardView window = new CardView();
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
	public CardView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(100, 100, 495, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInfantry = new JLabel("Infantry");
		lblInfantry.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblInfantry.setBounds(32, 72, 69, 29);
		frame.getContentPane().add(lblInfantry);
		
		JLabel lblCavalry = new JLabel("Cavalry");
		lblCavalry.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCavalry.setBounds(174, 70, 69, 33);
		frame.getContentPane().add(lblCavalry);
		
		JLabel lblArtillery = new JLabel("Artillery");
		lblArtillery.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblArtillery.setBounds(314, 71, 67, 30);
		frame.getContentPane().add(lblArtillery);
		
		JButton btnExchange = new JButton("Exchange");
		btnExchange.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExchange.setBounds(85, 280, 115, 29);
		frame.getContentPane().add(btnExchange);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setBounds(270, 280, 115, 29);
		frame.getContentPane().add(btnExit);
		
		JLabel label = new JLabel("0");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(116, 72, 26, 29);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("0");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_1.setBounds(257, 72, 16, 29);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("0");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_2.setBounds(406, 74, 16, 25);
		frame.getContentPane().add(label_2);
		
		JLabel lblAvailableCardFor = new JLabel("Available Card for Player");
		lblAvailableCardFor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAvailableCardFor.setBounds(144, 16, 197, 40);
		frame.getContentPane().add(lblAvailableCardFor);
		
		JLabel lblSelectCardTo = new JLabel("Select Card to exchange");
		lblSelectCardTo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectCardTo.setBounds(144, 145, 189, 29);
		frame.getContentPane().add(lblSelectCardTo);
		
		JRadioButton rdbtnInfantary = new JRadioButton("Infantary");
		rdbtnInfantary.setBounds(31, 206, 141, 23);
		frame.getContentPane().add(rdbtnInfantary);
		
		JRadioButton rdbtnCavillary = new JRadioButton("Cavillary");
		rdbtnCavillary.setBounds(144, 206, 141, 23);
		frame.getContentPane().add(rdbtnCavillary);
		
		JRadioButton rdbtnArtillery = new JRadioButton("Artillery");
		rdbtnArtillery.setBounds(270, 206, 141, 23);
		frame.getContentPane().add(rdbtnArtillery);
	}
}
