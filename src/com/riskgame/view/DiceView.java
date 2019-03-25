package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
public class DiceView implements Observer {

	private JFrame frame;
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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DiceView() {

	}

	/**
	 * Create the application.
	 * 
	 * @param gameMapGraph - GameMapGraph object
	 * @param defender     - the defender
	 * @param attacker     - the attacker
	 */
	public DiceView(GameMapGraph gameMapGraph, Country attacker, Country defender) {
		diceController = new DiceController();
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
		frame = new JFrame();

		frame.setBounds(600, 300, 571, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAttacker = new JLabel("Attacker");
		lblAttacker.setFont(new Font("Arial", Font.BOLD, 17));
		lblAttacker.setBounds(88, 13, 69, 20);
		frame.getContentPane().add(lblAttacker);

		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setFont(new Font("Arial", Font.BOLD, 17));
		lblDefender.setBounds(291, 13, 86, 20);
		frame.getContentPane().add(lblDefender);

		JRadioButton attackerDice1Radio = new JRadioButton("1");
		attackerDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice1Radio.setSelected(true);
		attackerDice1Radio.setBounds(44, 73, 56, 29);

		JRadioButton attackerDice2Radio = new JRadioButton("2");
		attackerDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice2Radio.setBounds(98, 73, 47, 29);

		JRadioButton attackerDice3Radio = new JRadioButton("3");
		attackerDice3Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		attackerDice3Radio.setBounds(150, 70, 47, 34);

		if (attackerCountry.getNoOfArmies() < 2) {
			frame.getContentPane().add(attackerDice1Radio);
		} else if (attackerCountry.getNoOfArmies() < 3) {
			frame.getContentPane().add(attackerDice1Radio);
			frame.getContentPane().add(attackerDice2Radio);
		} else {
			frame.getContentPane().add(attackerDice1Radio);
			frame.getContentPane().add(attackerDice2Radio);
			frame.getContentPane().add(attackerDice3Radio);
		}

		JRadioButton defenderDice1Radio = new JRadioButton("1");
		defenderDice1Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice1Radio.setSelected(true);
		defenderDice1Radio.setBounds(262, 73, 47, 29);

		JRadioButton defenderDice2Radio = new JRadioButton("2");
		defenderDice2Radio.setFont(new Font("Arial", Font.PLAIN, 17));
		defenderDice2Radio.setBounds(320, 73, 47, 29);

		if (defenderCountry.getNoOfArmies() < 2) {
			frame.getContentPane().add(defenderDice1Radio);
		} else {
			frame.getContentPane().add(defenderDice1Radio);
			frame.getContentPane().add(defenderDice2Radio);
		}
		// Group attacker radio buttons.
		ButtonGroup attackerGroup = new ButtonGroup();
		attackerGroup.add(attackerDice1Radio);
		attackerGroup.add(attackerDice2Radio);
		attackerGroup.add(attackerDice3Radio);

		// Group defender radio buttons.
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

		if (attackerDice3Radio.isSelected()) {
			frame.getContentPane().add(lblDice);
			frame.getContentPane().add(lblDice_1);
			frame.getContentPane().add(lblDice_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
		} else if (attackerDice2Radio.isSelected()) {
			frame.getContentPane().add(lblDice);
			frame.getContentPane().add(lblDice_1);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
		} else {
			frame.getContentPane().add(lblDice);
			frame.getContentPane().add(textField);
		}

		if (defenderDice2Radio.isSelected()) {
			if (!attackerDice3Radio.isSelected() || !attackerDice2Radio.isSelected()) {
				frame.getContentPane().add(lblDice_1);
			}
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
		} else {
			frame.getContentPane().add(textField_3);
		}

		messageField = new JTextField();
		messageField.setFont(new Font("Arial", Font.PLAIN, 17));
		messageField.setEnabled(false);
		messageField.setBounds(75, 331, 466, 26);
		messageField.setColumns(10);

		String message = "";

		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = "";
				attackerDiceCount = attackerDice3Radio.isSelected() ? 3
						: (attackerDice2Radio.isSelected() ? 2 : (attackerDice1Radio.isSelected() ? 1 : 0));
				defenderDiceCount = defenderDice2Radio.isSelected() ? 2 : (defenderDice1Radio.isSelected() ? 1 : 0);

				// DiceController diceController = new DiceController();
				String diceRollResult = diceController.startDiceRoll(attackerDiceCount, defenderDiceCount,
						attackerCountry, defenderCountry);
				int attackerLostCount = Integer.parseInt(diceRollResult.split(":")[0]);
				int defenderLostCount = Integer.parseInt(diceRollResult.split(":")[1]);

				if (attackerLostCount > 0) {
					message = "Attacker has lost " + attackerLostCount + " army(ies)";
				}
				if (defenderLostCount > 0) {
					message = message.length() > 0
							? (message + " ; " + "Defender has lost " + defenderLostCount + " army(ies)")
							: "Defender has lost " + defenderLostCount + " army(ies)";
				}

				messageField.setText(message);
			}
		});
		btnRollDice.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRollDice.setBounds(426, 71, 115, 29);
		frame.getContentPane().add(btnRollDice);

		frame.getContentPane().add(messageField);

		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 14));
		btnContinue.setBounds(426, 113, 115, 25);
		btnContinue.setEnabled(false);

		if (attackerCountry.getNoOfArmies() > 1 && defenderCountry.getNoOfArmies() > 0) {
			btnContinue.setEnabled(true);
		}

		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialize(gameMapGraph, attackerCountry, defenderCountry);
			}
		});

		frame.getContentPane().add(btnContinue);

		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEndTurn.setBounds(426, 151, 115, 25);
		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				// System.exit(0);
			}
		});
		frame.getContentPane().add(btnEndTurn);

		JButton btnMoveArmies = new JButton("MoveArmies");
		btnMoveArmies.setFont(new Font("Arial", Font.PLAIN, 14));
		btnMoveArmies.setBounds(426, 189, 115, 25);
		btnMoveArmies.setEnabled(false);

		if (defenderCountry.getNoOfArmies() == 0) {
			btnMoveArmies.setEnabled(true);
		}

		btnMoveArmies.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerController playerController = new PlayerController();
				JFrame moveArmyFrame = new JFrame("Move Armies");
				String armiesToMove = JOptionPane.showInputDialog(moveArmyFrame,
						"Enter the number of armies to be moved:");
				Player attacker = diceController.getPlayerForCountry(gameMapGraph, attackerCountry.getName());
				Player defender = diceController.getPlayerForCountry(gameMapGraph, defenderCountry.getName());
				diceController.moveArmies(Integer.parseInt(armiesToMove), attackerCountry, defenderCountry,
						gameMapGraph);
				if (defender.getMyCountries().size() == 1) {
					attacker.getPlayersCardList().putAll(defender.getPlayersCardList());
					JOptionPane.showMessageDialog(null, "Player " + defender.getName() + " has lost the game!!");
				}

			}
		});

		frame.getContentPane().add(btnMoveArmies);

		JLabel lblAttackerCountry = new JLabel(attackerCountry.getName());
		lblAttackerCountry.setBounds(31, 40, 104, 16);
		frame.getContentPane().add(lblAttackerCountry);

		JLabel lblAttackerArmies = new JLabel(Integer.toString(attackerCountry.getNoOfArmies()));
		lblAttackerArmies.setBounds(156, 40, 56, 16);
		frame.getContentPane().add(lblAttackerArmies);

		JLabel lblDefenderCountry = new JLabel(defenderCountry.getName());
		lblDefenderCountry.setBounds(253, 40, 106, 16);
		frame.getContentPane().add(lblDefenderCountry);

		JLabel lblDefenderArmies = new JLabel(Integer.toString(defenderCountry.getNoOfArmies()));
		lblDefenderArmies.setBounds(371, 40, 56, 16);
		frame.getContentPane().add(lblDefenderArmies);

		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Arial", Font.BOLD, 17));
		lblResult.setBounds(12, 337, 69, 16);
		frame.getContentPane().add(lblResult);

		if (attackerCountry.getNoOfArmies() == 1) {
			message = "Armies exhausted!! Cannot attack anymore!! End your turn.";
			btnRollDice.setEnabled(false);
			btnMoveArmies.setEnabled(false);
			btnContinue.setEnabled(false);
		}
		messageField.setText(message);

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
