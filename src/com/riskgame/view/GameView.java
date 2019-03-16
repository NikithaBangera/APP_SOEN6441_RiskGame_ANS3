package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class GameView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView window = new GameView();
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
	public GameView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(-32, -45, 883, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 38, 165, 108);
		frame.getContentPane().add(panel_1);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPlayerName);
		
		JLabel lblNumberOfArmies_1 = new JLabel("Number of Armies");
		lblNumberOfArmies_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblNumberOfArmies_1);
		
		
		JButton btnNewButton = new JButton("Place Army");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(426, 38, 165, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		
		JButton btnCompleteAttack = new JButton("Complete Attack");
		btnCompleteAttack.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCompleteAttack.setBounds(426, 91, 165, 29);
		frame.getContentPane().add(btnCompleteAttack);
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFortify.setBounds(426, 117, 165, 29);
		frame.getContentPane().add(btnFortify);
		
		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEndTurn.setBounds(426, 146, 165, 29);
		frame.getContentPane().add(btnEndTurn);
		
		JButton btnCards = new JButton("End Phase");
		btnCards.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCards.setBounds(426, 173, 165, 29);
		frame.getContentPane().add(btnCards);
		
		textField = new JTextField();
		textField.setBounds(630, 38, 214, 191);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAdjacentCountry = new JLabel("Adjacent Country");
		lblAdjacentCountry.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdjacentCountry.setBounds(684, 12, 107, 20);
		frame.getContentPane().add(lblAdjacentCountry);
		
		JLabel lblSelectedCountries = new JLabel("Selected Country");
		lblSelectedCountries.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectedCountries.setBounds(240, 4, 152, 37);
		frame.getContentPane().add(lblSelectedCountries);
		
		JButton btnCards_1 = new JButton("Cards");
		btnCards_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCards_1.setBounds(426, 200, 165, 29);
		frame.getContentPane().add(btnCards_1);
		
		JButton btnCountry = new JButton("Country 1");
		btnCountry.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry.setBounds(195, 38, 196, 29);
		frame.getContentPane().add(btnCountry);
		
		JButton btnCountyr = new JButton("Country 2");
		btnCountyr.setBackground(UIManager.getColor("Button.background"));
		btnCountyr.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountyr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCountyr.setBounds(195, 65, 196, 29);
		frame.getContentPane().add(btnCountyr);
		
		JButton btnCountry_1 = new JButton("Country 3");
		btnCountry_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry_1.setBounds(195, 91, 196, 29);
		frame.getContentPane().add(btnCountry_1);
		
		JButton btnCountry_2 = new JButton("Country 4");
		btnCountry_2.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry_2.setBounds(195, 117, 196, 29);
		frame.getContentPane().add(btnCountry_2);
		
		JButton btnCountry_3 = new JButton("Country 5");
		btnCountry_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCountry_3.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry_3.setBounds(195, 146, 196, 29);
		frame.getContentPane().add(btnCountry_3);
		
		JButton btnCountry_4 = new JButton("Country 6");
		btnCountry_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCountry_4.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry_4.setBounds(195, 173, 196, 29);
		frame.getContentPane().add(btnCountry_4);
		
		JButton btnCountry_5 = new JButton("Country 7");
		btnCountry_5.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCountry_5.setBounds(195, 200, 197, 29);
		frame.getContentPane().add(btnCountry_5);
		
		JPanel panel = new JPanel();
		panel.setBounds(195, 38, 196, 188);
		frame.getContentPane().add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 146, 165, 83);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNumberOfArmies = new JLabel("Phase Name");
		lblNumberOfArmies.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(lblNumberOfArmies);
		
		JLabel lblWorldDomination = new JLabel("  World Domination");
		lblWorldDomination.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWorldDomination.setBounds(119, 259, 273, 37);
		frame.getContentPane().add(lblWorldDomination);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(1);
		progressBar.setToolTipText("");
		progressBar.setBounds(15, 259, 376, 233);
		frame.getContentPane().add(progressBar);
		
		JButton btnReinforcement = new JButton("Reinforcement");
		btnReinforcement.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReinforcement.setBounds(426, 65, 165, 29);
		frame.getContentPane().add(btnReinforcement);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(436, 259, 408, 233);
		frame.getContentPane().add(panel_3);
		
		JLabel lblContinentName = new JLabel("Continent Name");
		panel_3.add(lblContinentName);
	}
}
