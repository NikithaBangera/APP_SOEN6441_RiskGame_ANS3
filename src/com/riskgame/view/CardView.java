package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import com.riskgame.controller.CardController;
import com.riskgame.model.Card;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class CardView {

	private JFrame frame;
	private JTextField infantryAssign;
	private JTextField cavalryAssign;
	private JTextField artileryAssign;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMapGraph mapGraph = new GameMapGraph();
					Player currentPlayer = new Player();
					CardView window = new CardView(mapGraph, currentPlayer);
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
	public CardView(GameMapGraph mapObj, Player player) {
		initialize(mapObj, player);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(GameMapGraph mapObj, Player player) {
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

		JLabel InfantryLabel = new JLabel();
		InfantryLabel.setText(player.getPlayersCardList().get(Card.INFANTRY).toString());
		InfantryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		InfantryLabel.setBounds(116, 72, 26, 29);
		frame.getContentPane().add(InfantryLabel);

		JLabel cavalryLabel = new JLabel();
		InfantryLabel.setText(player.getPlayersCardList().get(Card.CAVALRY).toString());
		cavalryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cavalryLabel.setBounds(257, 72, 16, 29);
		frame.getContentPane().add(cavalryLabel);

		JLabel artilleryLabel = new JLabel();
		InfantryLabel.setText(player.getPlayersCardList().get(Card.ARTILLERY).toString());
		artilleryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		artilleryLabel.setBounds(406, 74, 16, 25);
		frame.getContentPane().add(artilleryLabel);

		JLabel lblAvailableCardFor = new JLabel("Available Card for Player");
		lblAvailableCardFor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAvailableCardFor.setBounds(144, 16, 197, 40);
		frame.getContentPane().add(lblAvailableCardFor);

		JLabel lblSelectCardTo = new JLabel("Select Card to exchange");
		lblSelectCardTo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectCardTo.setBounds(144, 145, 189, 29);
		frame.getContentPane().add(lblSelectCardTo);

		JLabel lblInfrantryAssign = new JLabel("Infantry");
		lblInfrantryAssign.setBounds(40, 235, 61, 16);
		frame.getContentPane().add(lblInfrantryAssign);

		infantryAssign = new JTextField();
		infantryAssign.setBounds(99, 230, 71, 26);
		frame.getContentPane().add(infantryAssign);
		infantryAssign.setColumns(10);

		JLabel lblCavalryAssign = new JLabel("Cavalry");
		lblCavalryAssign.setBounds(182, 235, 61, 16);
		frame.getContentPane().add(lblCavalryAssign);

		cavalryAssign = new JTextField();
		cavalryAssign.setBounds(234, 230, 67, 26);
		frame.getContentPane().add(cavalryAssign);
		cavalryAssign.setColumns(10);

		JLabel lblArtileryAssign = new JLabel("Artilery");
		lblArtileryAssign.setBounds(320, 235, 61, 16);
		frame.getContentPane().add(lblArtileryAssign);

		artileryAssign = new JTextField();
		artileryAssign.setBounds(371, 230, 79, 26);
		frame.getContentPane().add(artileryAssign);
		artileryAssign.setColumns(10);

		JButton btnExchange = new JButton("Exchange");
		btnExchange.setEnabled(true);
		btnExchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardController cardAction = new CardController();
				HashMap<String, Integer> cardsSelected = new HashMap<String, Integer>();
				if (player.getPlayersCardList().size() < 3) {
					btnExchange.setEnabled(false);
				}
				if (artileryAssign.getText() != null) {
					cardsSelected.put(Card.ARTILLERY, Integer.parseInt(artileryAssign.getText()));
				}
				if (infantryAssign.getText() != null) {
					cardsSelected.put(Card.INFANTRY, Integer.parseInt(artileryAssign.getText()));
				}
				if (cavalryAssign.getText() != null) {
					cardsSelected.put(Card.CAVALRY, Integer.parseInt(artileryAssign.getText()));
				}

				String message = cardAction.exchangeCards(cardsSelected, player);

				if (message.contains("Cannot")) {
					JLabel lblMessage = new JLabel("Error:" + message);
					lblMessage.setBounds(50, 339, 61, 16);
					frame.getContentPane().add(lblMessage);
				}

			}
		});
		btnExchange.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExchange.setBounds(85, 280, 115, 29);
		frame.getContentPane().add(btnExchange);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// to be implemented

				if (player.getPlayersCardList().size() >= 5) {
					JOptionPane.showMessageDialog(null, "Maximum card limit reached! Please Exchange the cards.");

				} else {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setBounds(270, 280, 115, 29);
		frame.getContentPane().add(btnExit);

	}
}
