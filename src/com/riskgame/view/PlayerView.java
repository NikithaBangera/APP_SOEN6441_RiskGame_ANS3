package com.riskgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.riskgame.controller.PlayerController;
import com.riskgame.controller.RoundRobinScheduler;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.model.PlayerDomination;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

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
	private boolean isAttackNotPossible = true;
	RoundRobinScheduler roundRobin;
	private String playerCountryDetails;
	private String playerAdjCountryDetails;

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

	public PlayerView() {
		
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
		if(mapGraph.getGamePhase().equalsIgnoreCase("Place Armies") && player.isEndPlaceArmies()) {
			initialize(mapGraph);
		}
		
		if(mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement")) {
			startReinforcement(mapGraph, player);
			CardView cardView = new CardView(mapGraph, player);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 38, 165, 108);
		frame.getContentPane().add(panel_1);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPlayerName);
		
		textFieldPlayerName = new JTextField();
		panel_1.add(textFieldPlayerName);
		textFieldPlayerName.setColumns(10);
		textFieldPlayerName.setText(player.getName());
		
		JLabel lblArmies = new JLabel("Armies");
		panel_1.add(lblArmies);
		
		textFieldArmies = new JTextField();
		panel_1.add(textFieldArmies);
		textFieldArmies.setColumns(10);
		textFieldArmies.setText(Integer.toString(player.getArmyCount()));
		
		JPanel panelPlayerCountries = new JPanel();
		panelPlayerCountries.setBounds(195, 38, 196, 188);
		frame.getContentPane().add(panelPlayerCountries);
		
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
				setPlayerCountryDetails(getSelectedCountry()+" has "+selectedCountryObject.getNoOfArmies()+" armies.");
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
				Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph, getSelectedAdjacentCountry());
				Player adjCountryPlayer = playerController.getPlayerForCountry(mapGraph, getSelectedAdjacentCountry());
				setPlayerAdjCountryDetails(getSelectedAdjacentCountry()+" belongs to "+adjCountryPlayer.getName()+" - has "+selectedAdjCountryObject.getNoOfArmies()+" armies.");
			}
		});
		
		
		JButton btnPlaceArmy = new JButton("Place Army");
		btnPlaceArmy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayerController playerController = new PlayerController();
                playerController.allocationOfRemainingArmyToCountries(selectedCountryObject, player);
                
                if(nextPlayerNumber == totalNumberOfPlayers) {
					roundRobin = null;
					nextPlayerNumber  = 0;
					mapGraph.setGamePhase("Reinforcement");
					btnPlaceArmy.setEnabled(false);
				}
				initialize(mapGraph);
			}
		});
		btnPlaceArmy.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPlaceArmy.setBounds(426, 38, 165, 29);
		frame.getContentPane().add(btnPlaceArmy);
		
		JButton btnReinforcement = new JButton("Reinforcement");
		btnReinforcement.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReinforcement.setBounds(426, 65, 165, 29);
		btnReinforcement.setEnabled(false);
		if(mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement")) {
			btnReinforcement.setEnabled(true);
		}
		btnReinforcement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame reinforceArmy = new JFrame("Reinforce Armies");
				String armyReinforce = JOptionPane.showInputDialog(reinforceArmy, "Enter the number of armies to be reinforced:");
				player.armiesAssignedToCountries(selectedCountryObject, Integer.parseInt(armyReinforce));
				frame.revalidate();
				frame.repaint();
			}
		});
		frame.getContentPane().add(btnReinforcement);
		
		
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
				frame.revalidate();
				frame.repaint();
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
					mapGraph.setGamePhase("Fortification");
				}
				
				frame.revalidate();
				frame.repaint();
			}
		});
		frame.getContentPane().add(btnCompleteAttack);
		
		if(player.getArmyCount() == 0) {
			btnReinforcement.setEnabled(false);
			btnAttack.setEnabled(true);
			btnCompleteAttack.setEnabled(true);
			if(mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement")) {
				mapGraph.setGamePhase("Attack");
			}
		}
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFortify.setBounds(426, 143, 165, 29);
		btnFortify.putClientProperty("isFortificationComplete", isFortificationComplete);
		btnFortify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean playerFound = false;
				boolean isAdjCountry = false;
				boolean isFortificationComplete = (boolean)((JButton)(e.getSource())).getClientProperty("isFortificationComplete");
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
								isFortificationComplete = true;
								mapGraph.setGamePhase("Reinforcement");
								break;
							}
						}
					}
				}
				if(!isAdjCountry)
				{
					JOptionPane.showMessageDialog(null, lblPlayerName.getText()+" does not own this country");
				}
				((JButton)(e.getSource())).putClientProperty("isFortificationComplete", isFortificationComplete);
			}
		});
		frame.getContentPane().add(btnFortify);
		if(isFortificationComplete) {
			initialize(mapGraph);
		}
		
		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEndTurn.setBounds(426, 171, 165, 29);
		btnEndTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mapGraph.getGamePhase().equalsIgnoreCase("Place Armies")) {
					playerController.getCurrentPlayer(mapGraph, player.getName()).setEndPlaceArmies(true);
				}
				
				if(playerController.isPlaceArmiesComplete(mapGraph)) {
					mapGraph.setGamePhase("Reinforcement");
				}
				
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
		
		JLabel lblAdjacentCountry = new JLabel("Adjacent Countries");
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
		btnCards.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardView cardView = new CardView(mapGraph, player);
			}
		});
		frame.getContentPane().add(btnCards);
		
		
		JPanel panelPhaseName = new JPanel();
		panelPhaseName.setBounds(15, 146, 165, 83);
		frame.getContentPane().add(panelPhaseName);
		
		JLabel lblPhaseName = new JLabel("Phase Name");
		lblPhaseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelPhaseName.add(lblPhaseName);
		
		textFieldPhaseName = new JTextField(mapGraph.getGamePhase());
		textFieldPhaseName.setEnabled(false);
		panelPhaseName.add(textFieldPhaseName);
		textFieldPhaseName.setColumns(10);
		
		JCheckBox chckbxCompleteAttack = new JCheckBox("Complete Attack");
		if(mapGraph.getGamePhase().equalsIgnoreCase("Attack")) {
			panelPhaseName.add(chckbxCompleteAttack);
		}
		chckbxCompleteAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxCompleteAttack.isSelected()) {
					btnFortify.setEnabled(true);
				}
			}
		});
		
		
		//world domination panel
		JPanel worldDomination = new JPanel();
		worldDomination.setBounds(15, 259, 386, 233);
		frame.getContentPane().add(worldDomination);
		worldDomination.setLayout(null);
		
		JPanel panelMapOccupied = new JPanel();
		panelMapOccupied.setBounds(0, 31, 195, 202);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		PlayerDomination playerDomination = new PlayerDomination();
		Iterator<Entry<String, Integer>> iterator = playerDomination.dominationPercentage(mapGraph).entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Integer> nextItem = iterator.next();
			dataset.setValue(nextItem.getKey(), nextItem.getValue());
		}
		
		JFreeChart chart=ChartFactory.createPieChart("% Map Occupied", dataset, true, true, true);
		PiePlot p=(PiePlot)chart.getPlot();
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 195, 202);
		chartPanel.setPreferredSize(new Dimension(panelMapOccupied.getWidth(), panelMapOccupied.getHeight()));
		
		panelMapOccupied.removeAll();
		panelMapOccupied.validate();
		panelMapOccupied.setLayout(null);
		panelMapOccupied.add(chartPanel);
		chartPanel.setLayout(null);
		worldDomination.add(panelMapOccupied);
		
		JPanel panel = new JPanel();
		panel.setBounds(196, 31, 190, 202);
		worldDomination.add(panel);
		
		JLabel lblWorldDomination = new JLabel("World Domination");
		lblWorldDomination.setBackground(Color.WHITE);
		lblWorldDomination.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblWorldDomination.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorldDomination.setBounds(0, 0, 386, 30);
		worldDomination.add(lblWorldDomination);
		
		JPanel panelMapDetails = new JPanel();
		panelMapDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMapDetails.setBounds(436, 259, 408, 233);
		frame.getContentPane().add(panelMapDetails);
		panelMapDetails.setLayout(null);
		
		JPanel panelPlayerCountryDetails = new JPanel();
		panelPlayerCountryDetails.setBounds(12, 34, 384, 70);
		panelMapDetails.add(panelPlayerCountryDetails);
		panelPlayerCountryDetails.setLayout(null);
		
		JLabel lblPlayerCountryDetails = new JLabel(getPlayerCountryDetails());
		lblPlayerCountryDetails.setBounds(0, 13, 384, 44);
		lblPlayerCountryDetails.setVerticalAlignment(SwingConstants.TOP);
		panelPlayerCountryDetails.add(lblPlayerCountryDetails);
		
		JLabel lblContinentName = new JLabel("Map Details");
		lblContinentName.setBounds(178, 6, 66, 16);
		panelMapDetails.add(lblContinentName);
		
		JPanel panelPlayerAdjCountryDetails = new JPanel();
		panelPlayerAdjCountryDetails.setBounds(12, 117, 384, 70);
		panelMapDetails.add(panelPlayerAdjCountryDetails);
		panelPlayerAdjCountryDetails.setLayout(null);
		
		
		
		JLabel lblPlayerAdjCountryDetails = new JLabel(getPlayerAdjCountryDetails());
		lblPlayerAdjCountryDetails.setBounds(0, 13, 384, 44);
		panelPlayerAdjCountryDetails.add(lblPlayerAdjCountryDetails);
		lblPlayerAdjCountryDetails.setVerticalAlignment(SwingConstants.TOP);
	
	}
	
	public void startReinforcement(GameMapGraph mapGraph, Player player) {
		//Pending: implement opening card view for reinforcement if number of cards > 3 
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		frame.revalidate();
		frame.repaint();
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

	public String getPlayerCountryDetails() {
		return playerCountryDetails;
	}

	public void setPlayerCountryDetails(String playerCountryDetails) {
		this.playerCountryDetails = playerCountryDetails;
	}

	public String getPlayerAdjCountryDetails() {
		return playerAdjCountryDetails;
	}

	public void setPlayerAdjCountryDetails(String playerAdjCountryDetails) {
		this.playerAdjCountryDetails = playerAdjCountryDetails;
	}
}