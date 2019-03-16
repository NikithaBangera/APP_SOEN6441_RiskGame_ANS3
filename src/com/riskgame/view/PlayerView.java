package com.riskgame.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

public class PlayerView {

	private JFrame frame;
	PlayerController play = new PlayerController();
	GameMapGraph mapGraph = new GameMapGraph();
	/** The players. */
	private ArrayList<Player> players;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("11111111111111");
					PlayerView window = new PlayerView(null, null);
					System.out.println("@@@@@@@@@@@@@@@@@");
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
	public PlayerView(GameMapGraph mapGraph, ArrayList<Player> players) {
		this.mapGraph = mapGraph;
		this.players = players;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("textHighlight"));
		frame.setBounds(100, 100, 723, 638);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Player");
		lblNewLabel.setBounds(75, 15, 59, 42);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblArmy = new JLabel("Army:");
		lblArmy.setBounds(474, 28, 61, 16);
		lblArmy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		frame.getContentPane().add(lblArmy);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBounds(0, 59, 687, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnCountry = new JButton("country");

		btnCountry.setBounds(18, 22, 117, 29);
		panel.add(btnCountry);

		JLabel lblArmies = new JLabel("1");
		lblArmies.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblArmies.setForeground(new Color(0, 0, 255));
		lblArmies.setBackground(Color.WHITE);
		lblArmies.setBounds(147, 22, 31, 29);
		panel.add(lblArmies);

		/**
		 * when clicked on the country, the number of
		 * 
		 * armies assigned to it will be changed
		 **/

		btnCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String armies = lblArmies.getText();
				int numberofarmies = Integer.parseInt(armies);
				numberofarmies++;
				armies = String.valueOf(numberofarmies);
				lblArmies.setText(armies);

			}
		});

		JToggleButton tglbtnReinforcement = new JToggleButton("Reinforcement");
		tglbtnReinforcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		tglbtnReinforcement.setBackground(Color.WHITE);
		tglbtnReinforcement.setBounds(23, 193, 161, 29);
		panel.add(tglbtnReinforcement);

		JToggleButton tglbtnAttack = new JToggleButton("Attack");
		tglbtnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnReinforcement.setSelected(false);
				tglbtnReinforcement.setEnabled(false);
			}
		});

		tglbtnAttack.setBackground(Color.WHITE);
		tglbtnAttack.setBounds(177, 193, 161, 29);
		panel.add(tglbtnAttack);

		JToggleButton tglbtnFortification = new JToggleButton("fortification");
		tglbtnFortification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnReinforcement.setSelected(false);
				tglbtnReinforcement.setEnabled(false);
				tglbtnAttack.setSelected(false);
				tglbtnAttack.setEnabled(false);
			}
		});
		tglbtnFortification.setBackground(Color.WHITE);
		tglbtnFortification.setBounds(339, 193, 161, 29);
		panel.add(tglbtnFortification);

		JToggleButton tglbtnEndTurn = new JToggleButton("End Turn");
		tglbtnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnReinforcement.setSelected(false);
				tglbtnReinforcement.setEnabled(false);
				tglbtnAttack.setSelected(false);
				tglbtnAttack.setEnabled(false);
				tglbtnFortification.setSelected(false);
				tglbtnFortification.setEnabled(false);

			}
		});
		tglbtnEndTurn.setBounds(491, 193, 161, 29);
		panel.add(tglbtnEndTurn);

		JPanel DominationPanel = new JPanel();
		DominationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		DominationPanel.setBounds(10, 304, 676, 245);
		frame.getContentPane().add(DominationPanel);
		DominationPanel.setLayout(null);

		JLabel lblDomination = new JLabel("Domination");
		lblDomination.setFont(new Font("Arial", Font.BOLD, 18));
		lblDomination.setBounds(284, 0, 119, 36);
		DominationPanel.add(lblDomination);

		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Arial", Font.BOLD, 12));
		lblPlayerName.setBounds(15, 52, 99, 26);
		DominationPanel.add(lblPlayerName);

		JLabel label = new JLabel("%");
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBounds(154, 52, 30, 26);
		DominationPanel.add(label);

		JLabel lblArmies_1 = new JLabel("Armies #");
		lblArmies_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblArmies_1.setBounds(215, 54, 73, 23);
		DominationPanel.add(lblArmies_1);

		JLabel lblContinents = new JLabel("Continents");
		lblContinents.setFont(new Font("Arial", Font.BOLD, 12));
		lblContinents.setBounds(319, 52, 99, 26);
		DominationPanel.add(lblContinents);

		JLabel lblPlayer = new JLabel("Player1");
		lblPlayer.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlayer.setBounds(25, 101, 69, 20);
		DominationPanel.add(lblPlayer);

		JLabel label_1 = new JLabel("50");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(151, 101, 37, 20);
		DominationPanel.add(label_1);

		JLabel label_2 = new JLabel("6");
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(225, 101, 37, 20);
		DominationPanel.add(label_2);

		JLabel lblNewLabel_1 = new JLabel("Asia");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(329, 101, 55, 20);
		DominationPanel.add(lblNewLabel_1);
		frame.setVisible(true);

	}
}
