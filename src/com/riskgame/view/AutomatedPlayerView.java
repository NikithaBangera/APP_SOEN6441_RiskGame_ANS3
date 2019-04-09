package com.riskgame.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.riskgame.controller.PlayerController;
import com.riskgame.model.GameMapGraph;

import javax.swing.JLabel;
import java.awt.Font;

/**
 * Automated player view class is invoked when in the
 * single game mode only strategy players are chosen
 * @author Nikitha
 * @author Shiva
 *
 */
public class AutomatedPlayerView extends JFrame{
	
	JPanel panel;
	
	/**
	 * Method to invoke the automated player view for the
	 * game to be automatically played
	 * @param mapGraph - object of GameMapGraph
	 * @throws Exception - exception to be caught
	 */
	public AutomatedPlayerView(GameMapGraph mapGraph) throws Exception {
		setTitle("Risk Game");
		getContentPane().setLayout(null);
		setBounds(100, 100, 712, 259);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 694, 212);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		initialize(mapGraph);
	}

	/**
	 * Method which contains details of the view
	 * @param mapGraph - object of GameMapGraph
	 * @throws Exception - exception to be caught
	 */
	private void initialize(GameMapGraph mapGraph) throws Exception {

		JLabel lblNoHuman = new JLabel("No Human player selected");
		lblNoHuman.setEnabled(false);
		lblNoHuman.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNoHuman.setBounds(214, 25, 270, 26);
		panel.add(lblNoHuman);
		
		String gameStatus = "Automated Game in progress...";
		
		if(mapGraph.getGameResult() != null && mapGraph.getGameResult().length() > 0) {
			gameStatus = mapGraph.getGameResult();
		}
		JLabel lblAutomatedGameIn = new JLabel(gameStatus);
		lblAutomatedGameIn.setFont(new Font("Calibri", Font.BOLD, 28));
		lblAutomatedGameIn.setBounds(157, 86, 394, 36);
		panel.add(lblAutomatedGameIn);
		
		setVisible(true);
		
	}
}
