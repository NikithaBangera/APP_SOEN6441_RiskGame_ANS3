package com.riskgame.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.riskgame.controller.PlayerController;
import com.riskgame.controller.RoundRobinScheduler;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

public class PlayerView implements Observer {

	private JFrame frame;
	PlayerController playerController; 
	/** The players. */
	private Player player;
	private String selectedCountry;
	private String selectedAdjacentCountry;
	private Country selectedCountryObject;
	private JTextField textFieldPlayerName;
	private JTextField textFieldArmies;
	private JTextField textFieldPhaseName;
	private boolean nextPlayer = false;
	private int nextPlayerNumber;
	private int totalNumberOfPlayers = 0;
	RoundRobinScheduler roundRobin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMapGraph mapgraph = new GameMapGraph();
					PlayerView window = new PlayerView(mapgraph);
					//window.frmGameplay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerView(GameMapGraph inputMapGraph) {
		try {
			playerController = new PlayerController();
			nextPlayerNumber = 0;
			totalNumberOfPlayers = inputMapGraph.getPlayers().size();
			roundRobin = new RoundRobinScheduler(inputMapGraph.getPlayers());
			
			frame = new JFrame();
			frame.setBounds(100, 100, 883, 568);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
				
			initialize(inputMapGraph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @param roundRobin 
	 */
	private void initialize(GameMapGraph mapGraph) {
		boolean isFortificationComplete = false;
		
		if(roundRobin == null) {
			roundRobin = new RoundRobinScheduler(mapGraph.getPlayers());
		}
		nextPlayerNumber++;
		Player player = roundRobin.nextTurn();
		startReinforcement(mapGraph, player);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 38, 165, 108);
		frame.getContentPane().add(panel_1);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPlayerName);
		
		textFieldPlayerName = new JTextField();
		panel_1.add(textFieldPlayerName);
		textFieldPlayerName.setColumns(10);
		
		JLabel lblArmies = new JLabel("Armies");
		panel_1.add(lblArmies);
		
		textFieldArmies = new JTextField();
		panel_1.add(textFieldArmies);
		textFieldArmies.setColumns(10);
		
		JPanel panelPlayerCountries = new JPanel();
		panelPlayerCountries.setBounds(195, 38, 196, 188);
		frame.getContentPane().add(panelPlayerCountries);
		
		//dummy values
		String[] countries = {"India", "Montreal", "Nepal"};
		
		JList listPlayerCountryList = new JList(player.getPlayerCountryNames().toArray());
		listPlayerCountryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelPlayerCountries.add(listPlayerCountryList);
		listPlayerCountryList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					setSelectedCountry(listPlayerCountryList.getSelectedValue().toString());
					selectedCountryObject = player.getSelectedCountry(getSelectedCountry());
				}
			}
		});
		
		JPanel panelAdjacentCountries = new JPanel();
		panelAdjacentCountries.setBounds(626, 38, 218, 188);
		frame.getContentPane().add(panelAdjacentCountries);
		
		JList listAdjacentCountries = new JList(selectedCountryObject.getAdjacentCountries().toArray());
		listAdjacentCountries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelAdjacentCountries.add(listAdjacentCountries);
		listAdjacentCountries.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setSelectedAdjacentCountry(listAdjacentCountries.getSelectedValue().toString());
			}
		});
		
		
		JButton btnPlaceArmy = new JButton("Place Army");
		btnPlaceArmy.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPlaceArmy.setBounds(426, 38, 165, 29);
		frame.getContentPane().add(btnPlaceArmy);
		
		JButton btnReinforcement = new JButton("Reinforcement");
		btnReinforcement.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReinforcement.setBounds(426, 65, 165, 29);
		btnReinforcement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//check for the number of cards
				
				
				JFrame reinforceArmy = new JFrame("Reinforce Armies");
				String armyReinforce = JOptionPane.showInputDialog(reinforceArmy, "Enter the number of armies to be reinforced:");
				player.armiesAssignedToCountries(selectedCountryObject, Integer.parseInt(armyReinforce));
				frame.revalidate();
				frame.repaint();
			}
		});
		frame.getContentPane().add(btnReinforcement);
		
		if(player.getArmyCount() == 0) {
			btnReinforcement.setEnabled(false);
		}
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAttack.setBounds(426, 90, 165, 29);
		frame.getContentPane().add(btnAttack);
		btnAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerController playerController = new PlayerController();
				Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph, getSelectedAdjacentCountry());
				if(selectedCountryObject != null && selectedAdjCountryObject != null) {
					playerController.attackPhase(mapGraph,selectedCountryObject, selectedAdjCountryObject);
				}
			}
		});
		
		JButton btnCompleteAttack = new JButton("All Out");
		btnCompleteAttack.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCompleteAttack.setBounds(426, 117, 165, 29);
		btnCompleteAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerController playerController = new PlayerController();
				Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph, getSelectedAdjacentCountry());
				if(selectedCountryObject != null && selectedAdjCountryObject != null) {
					playerController.allOutAttack(mapGraph, selectedCountryObject, selectedAdjCountryObject);
				}
				
			}
		});
		
		frame.getContentPane().add(btnCompleteAttack);
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFortify.setBounds(426, 143, 165, 29);
		
		btnFortify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean playerFound = false;
				boolean isAdjCountry = false;
				Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph, getSelectedAdjacentCountry());
				JFrame fortifyArmy = new JFrame();
				String armiesCount=JOptionPane.showInputDialog(fortifyArmy,"Enter the number of armies you want to move:");
				for(Player player : mapGraph.getPlayers()) {
					for(Country country : player.getMyCountries()) {
						if(country.getName().equalsIgnoreCase(selectedCountryObject.getName())) {
							playerFound = true;
							break;
						}
					}
					if(playerFound) {
						for(Country country: player.getMyCountries()) {
							if(country.getName().equalsIgnoreCase(selectedAdjCountryObject.getName())) {
								playerController.moveArmies(selectedCountryObject, selectedAdjCountryObject, Integer.parseInt(armiesCount));
								isAdjCountry = true;
								break;
							}
						}
					}
				}
				if(!isAdjCountry)
				{
					JOptionPane.showMessageDialog(null,lblPlayerName.getText()+" does not own this country");
				}
			}
		});
		frame.getContentPane().add(btnFortify);
		
		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEndTurn.setBounds(426, 171, 165, 29);
		btnEndTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nextPlayerNumber == totalNumberOfPlayers) {
					roundRobin = null;
					nextPlayerNumber  = 0;
				}
				initialize(mapGraph);
			}
		});
		frame.getContentPane().add(btnEndTurn);
		
		btnAttack.setEnabled(false);
		btnCompleteAttack.setEnabled(false);
		btnFortify.setEnabled(false);
		
		if(player.getArmyCount() == 0) {
			btnAttack.setEnabled(true);
			btnCompleteAttack.setEnabled(true);
		}
		
		JLabel lblAdjacentCountry = new JLabel("Adjacent Country");
		lblAdjacentCountry.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdjacentCountry.setBounds(684, 12, 107, 20);
		frame.getContentPane().add(lblAdjacentCountry);
		
		JLabel lblSelectedCountries = new JLabel("Player Countries");
		lblSelectedCountries.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectedCountries.setBounds(240, 4, 152, 37);
		frame.getContentPane().add(lblSelectedCountries);
		
		JButton btnCards = new JButton("Cards");
		btnCards.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCards.setBounds(426, 197, 165, 29);
		frame.getContentPane().add(btnCards);
		
		JPanel panelPhaseName = new JPanel();
		panelPhaseName.setBounds(15, 146, 165, 83);
		frame.getContentPane().add(panelPhaseName);
		
		JLabel lblPhaseName = new JLabel("Phase Name");
		lblPhaseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelPhaseName.add(lblPhaseName);
		
		textFieldPhaseName = new JTextField();
		textFieldPhaseName.setEnabled(false);
		panelPhaseName.add(textFieldPhaseName);
		textFieldPhaseName.setColumns(10);
		
		JLabel lblWorldDomination = new JLabel("  World Domination");
		lblWorldDomination.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWorldDomination.setBounds(119, 259, 273, 37);
		frame.getContentPane().add(lblWorldDomination);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(1);
		progressBar.setToolTipText("");
		progressBar.setBounds(15, 259, 376, 233);
		frame.getContentPane().add(progressBar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(436, 259, 408, 233);
		frame.getContentPane().add(panel_3);
		
		JLabel lblContinentName = new JLabel("Continent Name");
		panel_3.add(lblContinentName);
	
	}
	
	public void startReinforcement(GameMapGraph mapGraph, Player player) {
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		

	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public String getSelectedAdjacentCountry() {
		return selectedAdjacentCountry;
	}

	public void setSelectedAdjacentCountry(String selectedAdjacentCountry) {
		this.selectedAdjacentCountry = selectedAdjacentCountry;
	}

}
