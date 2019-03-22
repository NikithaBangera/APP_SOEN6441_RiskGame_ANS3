package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import com.riskgame.model.GameMapGraph;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class RiskView extends JFrame implements Observer, MouseListener, ActionListener{

	private JFrame frame;
	private JTextField phaseTextField;
	private JTextField nameTextField;
	private JTextField armyTextField;

	public GameMapGraph getGameGraph() {
		return gameGraph;
	}

	public void setGameGraph(GameMapGraph gameGraph) {
		this.gameGraph = gameGraph;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getPhaseTextField() {
		return phaseTextField;
	}

	public void setPhaseTextField(JTextField phaseTextField) {
		this.phaseTextField = phaseTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public JTextField getArmyTextField() {
		return armyTextField;
	}

	public void setArmyTextField(JTextField armyTextField) {
		this.armyTextField = armyTextField;
	}
	
	GameMapGraph gameGraph = new GameMapGraph();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMapGraph gameGraph = new GameMapGraph();
					RiskView window = new RiskView();
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
	public RiskView() {
//		this.gameGraph = mapGraph;
//		System.out.println(mapGraph+"@@@@@");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		frame.getContentPane().setBackground(new Color(250, 235, 215));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnFortify.setBounds(212, 198, 96, 29);
		btnFortify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().setLayout(null);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(16, 171, 172, 90);
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(verticalBox);
		frame.getContentPane().add(btnFortify);
		
		JButton btnReinforcement = new JButton("Reinforcement");
		btnReinforcement.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnReinforcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReinforcement.setBounds(240, 171, 135, 29);
		frame.getContentPane().add(btnReinforcement);
		
		JButton btnEndturn = new JButton("EndTurn");
		btnEndturn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnEndturn.setBounds(260, 232, 96, 29);
		frame.getContentPane().add(btnEndturn);
		
		JButton btnEndPhase = new JButton("End Phase");
		btnEndPhase.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnEndPhase.setBounds(309, 198, 107, 29);
		frame.getContentPane().add(btnEndPhase);
		
		JLabel labelPhase = new JLabel("Phase:");
		labelPhase.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelPhase.setBounds(141, 6, 61, 16);
		frame.getContentPane().add(labelPhase);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblName.setBounds(18, 54, 61, 16);
		frame.getContentPane().add(lblName);
		
		phaseTextField = new JTextField();
		phaseTextField.setBackground(new Color(255, 255, 255));
		phaseTextField.setEditable(false);
		phaseTextField.setBounds(184, 1, 130, 26);
		frame.getContentPane().add(phaseTextField);
		phaseTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setBounds(58, 49, 84, 26);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblArmy = new JLabel("Army:");
		lblArmy.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblArmy.setBounds(16, 86, 61, 16);
		frame.getContentPane().add(lblArmy);
		
		armyTextField = new JTextField();
		armyTextField.setEditable(false);
		armyTextField.setBounds(58, 81, 84, 26);
		frame.getContentPane().add(armyTextField);
		armyTextField.setColumns(10);
		
		Box Countries = Box.createHorizontalBox();
		Countries.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Countries.setBounds(173, 54, 130, 105);
		frame.getContentPane().add(Countries);
		
		Box adjacentCountries = Box.createHorizontalBox();
		adjacentCountries.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		adjacentCountries.setBounds(309, 52, 124, 107);
		frame.getContentPane().add(adjacentCountries);
		
		JLabel lblCountries = new JLabel("Countries");
		lblCountries.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblCountries.setBounds(212, 39, 61, 16);
		frame.getContentPane().add(lblCountries);
		
		JLabel lblAdjacentCountries = new JLabel("Adjacent Countries");
		lblAdjacentCountries.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblAdjacentCountries.setBounds(326, 39, 107, 16);
		frame.getContentPane().add(lblAdjacentCountries);
		
		frame.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
