package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class CardView implements Observer{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frame.setBounds(100, 100, 495, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInfantry = new JLabel("Infantry");
		lblInfantry.setFont(new Font("Arial", Font.PLAIN, 17));
		lblInfantry.setBounds(32, 72, 69, 29);
		frame.getContentPane().add(lblInfantry);
		
		JLabel lblCavalry = new JLabel("Cavalry");
		lblCavalry.setFont(new Font("Arial", Font.PLAIN, 17));
		lblCavalry.setBounds(174, 70, 69, 33);
		frame.getContentPane().add(lblCavalry);
		
		JLabel lblArtillery = new JLabel("Artillery");
		lblArtillery.setFont(new Font("Arial", Font.PLAIN, 17));
		lblArtillery.setBounds(314, 71, 67, 30);
		frame.getContentPane().add(lblArtillery);
		
		JButton btnExchange = new JButton("Exchange");
		btnExchange.setFont(new Font("Arial", Font.PLAIN, 17));
		btnExchange.setBounds(96, 280, 115, 29);
		frame.getContentPane().add(btnExchange);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 17));
		btnExit.setBounds(285, 280, 115, 29);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pending: Condition to not allow user to exit card view if card count = 5
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
		
		JLabel label = new JLabel("0");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(116, 72, 26, 29);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("0");
		label_1.setFont(new Font("Arial", Font.PLAIN, 17));
		label_1.setBounds(257, 72, 16, 29);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("0");
		label_2.setFont(new Font("Arial", Font.PLAIN, 17));
		label_2.setBounds(406, 74, 16, 25);
		frame.getContentPane().add(label_2);
		
		JLabel lblAvailableDiceFor = new JLabel("Available Dice for Player");
		lblAvailableDiceFor.setFont(new Font("Arial", Font.BOLD, 17));
		lblAvailableDiceFor.setBounds(144, 16, 197, 40);
		frame.getContentPane().add(lblAvailableDiceFor);
		
		JLabel lblSelectDiceTo = new JLabel("Select Dice to exchange");
		lblSelectDiceTo.setFont(new Font("Arial", Font.BOLD, 17));
		lblSelectDiceTo.setBounds(144, 145, 189, 29);
		frame.getContentPane().add(lblSelectDiceTo);
		
		JLabel lblInfantry_1 = new JLabel("Infantry");
		lblInfantry_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblInfantry_1.setBounds(32, 208, 69, 20);
		frame.getContentPane().add(lblInfantry_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setBounds(111, 205, 31, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCavalry_1 = new JLabel("Cavalry");
		lblCavalry_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblCavalry_1.setBounds(174, 208, 59, 20);
		frame.getContentPane().add(lblCavalry_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(242, 205, 31, 26);
		frame.getContentPane().add(textField_1);
		
		JLabel lblArtillery_1 = new JLabel("Artillery");
		lblArtillery_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblArtillery_1.setBounds(314, 208, 67, 20);
		frame.getContentPane().add(lblArtillery_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_2.setColumns(10);
		textField_2.setBounds(391, 205, 31, 26);
		frame.getContentPane().add(textField_2);
	}

	@Override
	public void update(Observable o, Object arg) {
		frame.revalidate();
		frame.repaint();
	}
}
