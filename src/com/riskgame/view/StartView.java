package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;

public class StartView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartView window = new StartView();
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
	public StartView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 235, 215));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayerDetails = new JLabel("Player Details");
		lblPlayerDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblPlayerDetails.setBounds(158, 16, 90, 30);
		frame.getContentPane().add(lblPlayerDetails);
		
		JLabel lblPlayerNumber = new JLabel("Number of players:");
		lblPlayerNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPlayerNumber.setBounds(40, 82, 104, 16);
		frame.getContentPane().add(lblPlayerNumber);
		
		textField = new JTextField();
		textField.setBounds(140, 76, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNameOfPlayers = new JLabel("Name of Players:");
		lblNameOfPlayers.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNameOfPlayers.setBounds(40, 131, 104, 16);
		frame.getContentPane().add(lblNameOfPlayers);
		
		JTextArea textAreaPlayerName = new JTextArea();
		textAreaPlayerName.setBounds(150, 114, 112, 116);
		frame.getContentPane().add(textAreaPlayerName);
	}
}
