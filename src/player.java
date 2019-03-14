import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.Color;

public class player {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					player window = new player();
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
	public player() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.controlHighlight);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 669, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("player");
		lblNewLabel.setBounds(59, 28, 59, 42);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblArmy = new JLabel("army");
		lblArmy.setBounds(333, 42, 61, 16);
		lblArmy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		frame.getContentPane().add(lblArmy);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBounds(6, 84, 657, 275);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCountry = new JButton("country");
		btnCountry.setBounds(18, 22, 117, 29);
		panel.add(btnCountry);
		
		JLabel lblArmies = new JLabel("armies");
		lblArmies.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblArmies.setForeground(new Color(0, 0, 255));
		lblArmies.setBackground(new Color(0, 0, 255));
		lblArmies.setBounds(132, 22, 52, 29);
		panel.add(lblArmies);
		
		JToggleButton tglbtnReinforcement = new JToggleButton("Reinforcement");
		tglbtnReinforcement.setBackground(new Color(0, 0, 0));
		tglbtnReinforcement.setBounds(23, 229, 161, 29);
		panel.add(tglbtnReinforcement);
		
		JToggleButton tglbtnAttack = new JToggleButton("Attack");
		tglbtnAttack.setBackground(new Color(204, 255, 51));
		tglbtnAttack.setBounds(179, 229, 161, 29);
		panel.add(tglbtnAttack);
		
		JToggleButton tglbtnFortification = new JToggleButton("fortification");
		tglbtnFortification.setBackground(new Color(51, 102, 51));
		tglbtnFortification.setBounds(337, 229, 161, 29);
		panel.add(tglbtnFortification);
		
		JToggleButton tglbtnEndTurn = new JToggleButton("End Turn");
		tglbtnEndTurn.setBounds(490, 229, 161, 29);
		panel.add(tglbtnEndTurn);
		
		JButton btnCountry_1 = new JButton("country");
		btnCountry_1.setBounds(18, 63, 117, 29);
		panel.add(btnCountry_1);
		
		JLabel lblArmies_1 = new JLabel("armies");
		lblArmies_1.setForeground(new Color(0, 0, 255));
		lblArmies_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblArmies_1.setBounds(132, 68, 61, 16);
		panel.add(lblArmies_1);
		
		JButton button = new JButton("country");
		button.setBounds(18, 100, 117, 29);
		panel.add(button);
		
		JLabel label = new JLabel("armies");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Helvetica", Font.PLAIN, 13));
		label.setBounds(132, 104, 61, 16);
		panel.add(label);
		if(tglbtnAttack.isSelected()) {
			tglbtnReinforcement.setSelected(false);
			tglbtnReinforcement.setEnabled(false);
		}
		if(tglbtnFortification.isSelected()) {
			tglbtnReinforcement.setSelected(false);
			tglbtnAttack.setSelected(false);
			tglbtnReinforcement.setEnabled(false);
			tglbtnAttack.setEnabled(false);
		}
		
			
	}

}
