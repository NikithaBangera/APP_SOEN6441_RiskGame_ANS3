package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.HashMap;
import com.riskgame.controller.CardController;
import com.riskgame.model.Card;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import javax.swing.JTextField;

/**
 * This class aims to show the card view
 * 
 * @author Shresthi
 *
 */
public class CardView implements Observer {

	private JFrame frame;
	private JTextField infantryAssign;
	private JTextField cavalryAssign;
	private JTextField artileryAssign;

	/**
	 * Launch the application.
	 * 
	 * @param args-
	 *            arguments
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
	 * Card View Constructor
	 */
	public CardView() {

	}

	/**
	 * Create the application.
	 * 
	 * @param mapObj
	 *            - GameMapGraph object
	 * @param player
	 *            - current player
	 */
	public CardView(GameMapGraph mapObj, Player player) {
		// HashMap<String, Integer> mock = new HashMap<String, Integer>();
		// mock.put(Card.ARTILLERY, 1);
		// mock.put(Card.CAVALRY, 2);
		// mock.put(Card.INFANTRY, 1);
		// player.setPlayersCardList(mock);
		initialize(mapObj, player);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param mapObj
	 *            - GameMapGraph object
	 * @param player
	 *            - current player
	 */
	private void initialize(GameMapGraph mapObj, Player player) {
		String playerCardCount;
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(100, 100, 495, 396);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblInfantry = new JLabel("Infantry");
		lblInfantry.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblInfantry.setBounds(32, 72, 46, 29);
		frame.getContentPane().add(lblInfantry);

		JLabel lblCavalry = new JLabel("Cavalry");
		lblCavalry.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCavalry.setBounds(174, 70, 46, 33);
		frame.getContentPane().add(lblCavalry);

		JLabel lblArtillery = new JLabel("Artillery");
		lblArtillery.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblArtillery.setBounds(314, 71, 54, 30);
		frame.getContentPane().add(lblArtillery);

		playerCardCount = player.getPlayersCardList().get(Card.INFANTRY) != null
				? player.getPlayersCardList().get(Card.INFANTRY).toString()
				: "0";
		JLabel InfantryLabel = new JLabel();
		InfantryLabel.setText(playerCardCount);
		InfantryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		InfantryLabel.setBounds(90, 72, 26, 29);
		frame.getContentPane().add(InfantryLabel);

		playerCardCount = player.getPlayersCardList().get(Card.CAVALRY) != null
				? player.getPlayersCardList().get(Card.CAVALRY).toString()
				: "0";
		JLabel cavalryLabel = new JLabel();
		cavalryLabel.setText(playerCardCount);
		cavalryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cavalryLabel.setBounds(232, 72, 26, 29);
		frame.getContentPane().add(cavalryLabel);

		playerCardCount = player.getPlayersCardList().get(Card.ARTILLERY) != null
				? player.getPlayersCardList().get(Card.ARTILLERY).toString()
				: "0";
		JLabel artilleryLabel = new JLabel();
		artilleryLabel.setText(playerCardCount);
		artilleryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		artilleryLabel.setBounds(380, 72, 16, 25);
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
		if (!player.getPlayersCardList().isEmpty()) {

			int artilery = (player.getPlayersCardList().get(Card.ARTILLERY) == null ? 0
					: player.getPlayersCardList().get(Card.ARTILLERY));
			int cavalary = (player.getPlayersCardList().get(Card.CAVALRY) == null ? 0
					: player.getPlayersCardList().get(Card.CAVALRY));
			int infantry = (player.getPlayersCardList().get(Card.INFANTRY) == null ? 0
					: player.getPlayersCardList().get(Card.INFANTRY));

			int total = artilery + cavalary + infantry;
			/*
			 * if (total >= 3 && ((artilery>=1 && cavalary>=1 && infantry>=1) ||
			 * (artilery>=3 ||cavalary>=3 || infantry>=3))) { btnExchange.setEnabled(true);
			 * } else { btnExchange.setEnabled(false); }
			 */
			if (total >= 3) {
				if ((artilery >= 1 && cavalary >= 1 && infantry >= 1)) {
					infantryAssign.setEnabled(true);
					artileryAssign.setEnabled(true);
					cavalryAssign.setEnabled(true);
					btnExchange.setEnabled(true);
				} else if (artilery >= 3) {
					artileryAssign.setEnabled(true);
					btnExchange.setEnabled(true);

					infantryAssign.setEnabled(false);
					cavalryAssign.setEnabled(false);
				} else if (cavalary >= 3) {
					cavalryAssign.setEnabled(true);
					btnExchange.setEnabled(true);

					artileryAssign.setEnabled(false);
					infantryAssign.setEnabled(false);
				}

				else if (infantry >= 3) {
					infantryAssign.setEnabled(true);
					btnExchange.setEnabled(true);

					artileryAssign.setEnabled(false);
					cavalryAssign.setEnabled(false);
				} else {
					infantryAssign.setEnabled(false);
					btnExchange.setEnabled(false);

					artileryAssign.setEnabled(false);
					cavalryAssign.setEnabled(false);
				}

			}

			else {
				infantryAssign.setEnabled(false);
				btnExchange.setEnabled(false);

				artileryAssign.setEnabled(false);
				cavalryAssign.setEnabled(false);
			}
		} else {
			infantryAssign.setEnabled(false);
			btnExchange.setEnabled(false);

			artileryAssign.setEnabled(false);
			cavalryAssign.setEnabled(false);
		}
		btnExchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardController cardAction = new CardController();
				HashMap<String, Integer> cardsSelected = new HashMap<String, Integer>();

				if (artileryAssign.isEnabled()&&!artileryAssign.getText().trim().isEmpty()) {
					cardsSelected.put(Card.ARTILLERY, Integer.parseInt(artileryAssign.getText()));
				}
				if (infantryAssign.isEnabled()&&!infantryAssign.getText().trim().isEmpty()) {
					cardsSelected.put(Card.INFANTRY, Integer.parseInt(infantryAssign.getText()));
				}
				if (cavalryAssign.isEnabled()&&!cavalryAssign.getText().trim().isEmpty()) {
					cardsSelected.put(Card.CAVALRY, Integer.parseInt(cavalryAssign.getText()));
				}

				String message = cardAction.exchangeCards(cardsSelected, player);

				if (message.contains("Cannot")) {
					/*JLabel lblMessage = new JLabel("Error:" + message);
					lblMessage.setBounds(50, 339, 61, 16);
					frame.getContentPane().add(lblMessage);*/
					JOptionPane.showMessageDialog(null, message);
				} else if (message.contains("Success")) {
					frame.setVisible(false);
					JOptionPane.showMessageDialog(null, message);
				}

			}
		});
		btnExchange.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExchange.setBounds(85, 280, 115, 29);
		frame.getContentPane().add(btnExchange);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getPlayersCardList().isEmpty()) {
					frame.setVisible(false);
				} else {
					int artilery = (player.getPlayersCardList().get(Card.ARTILLERY) == null ? 0
							: player.getPlayersCardList().get(Card.ARTILLERY));
					int cavalary = (player.getPlayersCardList().get(Card.CAVALRY) == null ? 0
							: player.getPlayersCardList().get(Card.CAVALRY));
					int infantry = (player.getPlayersCardList().get(Card.INFANTRY) == null ? 0
							: player.getPlayersCardList().get(Card.INFANTRY));
					int total = artilery + cavalary + infantry;
					if (total >= 5) {
						JOptionPane.showMessageDialog(null, "Maximum card limit reached! Please Exchange the cards.");

					} else {
						frame.setVisible(false);
					}

				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExit.setBounds(270, 280, 115, 29);
		frame.getContentPane().add(btnExit);
		frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (frame != null) {
			frame.revalidate();
			frame.repaint();
		}
	}
}
