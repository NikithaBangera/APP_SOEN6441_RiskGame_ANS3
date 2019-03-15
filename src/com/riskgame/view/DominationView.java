package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class DominationView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DominationView window = new DominationView();
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
	public DominationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Arial", Font.BOLD, 17));
		lblPlayerName.setBounds(35, 66, 107, 27);
		frame.getContentPane().add(lblPlayerName);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Arial", Font.BOLD, 17));
		label.setBounds(180, 66, 24, 27);
		frame.getContentPane().add(label);
		
		JLabel lblPlayer = new JLabel("Player1");
		lblPlayer.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPlayer.setBounds(52, 109, 69, 20);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player2");
		lblPlayer_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPlayer_1.setBounds(52, 158, 69, 20);
		frame.getContentPane().add(lblPlayer_1);
		
		JLabel label_1 = new JLabel("50");
		label_1.setFont(new Font("Arial", Font.PLAIN, 17));
		label_1.setBounds(180, 109, 32, 20);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("50");
		label_2.setFont(new Font("Arial", Font.PLAIN, 17));
		label_2.setBounds(180, 158, 32, 20);
		frame.getContentPane().add(label_2);
		
		JLabel lblDomination = new JLabel("Domination");
		lblDomination.setFont(new Font("Arial", Font.BOLD, 17));
		lblDomination.setBounds(277, 0, 113, 27);
		frame.getContentPane().add(lblDomination);
		
		JLabel lblNumberOfArmies = new JLabel("Number of Armies");
		lblNumberOfArmies.setFont(new Font("Arial", Font.BOLD, 17));
		lblNumberOfArmies.setBounds(269, 66, 153, 27);
		frame.getContentPane().add(lblNumberOfArmies);
		
		JLabel lblContinents = new JLabel("Continents");
		lblContinents.setFont(new Font("Arial", Font.BOLD, 17));
		lblContinents.setBounds(478, 62, 88, 34);
		frame.getContentPane().add(lblContinents);
		
		JLabel label_3 = new JLabel("6");
		label_3.setFont(new Font("Arial", Font.PLAIN, 17));
		label_3.setBounds(326, 109, 24, 20);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("6");
		label_4.setFont(new Font("Arial", Font.PLAIN, 17));
		label_4.setBounds(326, 158, 24, 20);
		frame.getContentPane().add(label_4);
		
		JLabel lblAsia = new JLabel("Asia");
		lblAsia.setFont(new Font("Arial", Font.PLAIN, 17));
		lblAsia.setBounds(497, 109, 69, 20);
		frame.getContentPane().add(lblAsia);
	}

}
