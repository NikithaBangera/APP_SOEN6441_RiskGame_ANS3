package com.riskgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.riskgame.controller.CardController;
import com.riskgame.controller.PlayerController;
import com.riskgame.controller.RoundRobinController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;
import com.riskgame.model.PlayerDomination;

/**
 * This class aims to create the player's view
 * 
 * @author Nikitha
 * @author Shiva
 *
 */
public class PlayerView implements Observer {

	private JFrame frmRiskGame;
	private JPanel rootPanel;

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
	RoundRobinController roundRobin;
	private String playerCountryDetails;
	private String playerAdjCountryDetails;
	private int conquerCount = 0;

	/**
	 * Launch the application.
	 * 
	 * @param args - arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMapGraph mapgraph = new GameMapGraph();
					PlayerView window = new PlayerView(mapgraph);
					// window.frmGameplay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * PlayerView Constructor
	 */
	public PlayerView() {

	}

	/**
	 * PlayerView Constructor with parameter.
	 * 
	 * @param inputMapGraph - GameMapGraph object
	 */
	public PlayerView(GameMapGraph inputMapGraph) {
		try {
			playerController = new PlayerController();
			nextPlayerNumber = 0;
			totalNumberOfPlayers = inputMapGraph.getPlayers().size();
			roundRobin = new RoundRobinController(inputMapGraph.getPlayers());

			frmRiskGame = new JFrame();
			frmRiskGame.setTitle("Risk Game");
			frmRiskGame.setBounds(100, 100, 883, 568);
			frmRiskGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmRiskGame.getContentPane().setLayout(null);

			rootPanel = new JPanel();
			rootPanel.setBounds(0, 0, 861, 496);
			frmRiskGame.getContentPane().add(rootPanel);
			initialize(inputMapGraph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param mapGraph - GameMapGraph object
	 */
	private void initialize(GameMapGraph mapGraph) {

		boolean isFortificationComplete = false;

		if (validateGameCompletion(mapGraph)) {

			if (roundRobin == null) {
				roundRobin = new RoundRobinController(mapGraph.getPlayers());
			}

			if (!mapGraph.isRefreshFrame()) {
				nextPlayerNumber++;
				selectedAdjacentCountry = "";
				selectedCountry = "";
				player = roundRobin.nextTurn();
				while (player.isPlayerLostGame()) {
					player = roundRobin.nextTurn();
				}
				player.setFirstReinforcement(true);
				player.setCompleteAttack(false);
				conquerCount = player.getConquerCountry();
			}

			if (playerController.isPlaceArmiesComplete(mapGraph)
					&& mapGraph.getGamePhase().equalsIgnoreCase("Place Armies")) {
				mapGraph.setGamePhase("Reinforcement");
				mapGraph.setRefreshFrame(false);
				rootPanel.removeAll();
				rootPanel.revalidate();
				rootPanel.repaint();
				initialize(mapGraph);
			}

			if (mapGraph.getGamePhase().equalsIgnoreCase("Place Armies") && player.isEndPlaceArmies()) {
				mapGraph.setRefreshFrame(false);
				rootPanel.removeAll();
				rootPanel.revalidate();
				rootPanel.repaint();
				initialize(mapGraph);
			}

			if (mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement") && player.isFirstReinforcement()) {
				player.setFirstReinforcement(false);
				startReinforcement(mapGraph, player);
				CardView cardView = new CardView(mapGraph, player);
			}

			rootPanel.setLayout(null);

			JPanel playerInformation = new JPanel();
			playerInformation.setBounds(15, 16, 179, 108);
			rootPanel.add(playerInformation);
			playerInformation.setLayout(null);

			JLabel lblPlayerName = new JLabel("Player Name");
			lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayerName.setBounds(0, 0, 179, 26);
			lblPlayerName.setFont(new Font("Calibri", Font.BOLD, 16));
			playerInformation.add(lblPlayerName);

			textFieldPlayerName = new JTextField();
			textFieldPlayerName.setFont(new Font("Calibri", Font.PLAIN, 16));
			textFieldPlayerName.setBounds(0, 25, 164, 26);
			playerInformation.add(textFieldPlayerName);
			textFieldPlayerName.setColumns(10);
			textFieldPlayerName.setText(player.getName());
			textFieldPlayerName.setEnabled(false);

			JLabel lblArmies = new JLabel("Armies");
			lblArmies.setFont(new Font("Calibri", Font.BOLD, 16));
			lblArmies.setHorizontalAlignment(SwingConstants.CENTER);
			lblArmies.setBounds(0, 56, 179, 20);
			playerInformation.add(lblArmies);

			textFieldArmies = new JTextField();
			textFieldArmies.setFont(new Font("Calibri", Font.PLAIN, 16));
			textFieldArmies.setBounds(0, 81, 164, 26);
			playerInformation.add(textFieldArmies);
			textFieldArmies.setColumns(10);
			textFieldArmies.setText(Integer.toString(player.getArmyCount()));
			textFieldArmies.setEnabled(false);

			DefaultListModel<String> playerCountries = new DefaultListModel<String>();
			for (String playerCountry : player.getPlayerCountryNames()) {
				playerCountries.addElement(playerCountry);
			}
			JList<String> listPlayerCountryList = new JList<String>(playerCountries);
			listPlayerCountryList.setBounds(0, 26, 211, 170);
			listPlayerCountryList.setSelectionForeground(Color.LIGHT_GRAY);
			listPlayerCountryList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			listPlayerCountryList.setFont(new Font("Calibri", Font.PLAIN, 16));
			listPlayerCountryList.setVisibleRowCount(20);
			listPlayerCountryList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			listPlayerCountryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JLabel lblSelectedCountries = new JLabel("Player Countries");
			lblSelectedCountries.setBounds(0, 0, 208, 23);
			lblSelectedCountries.setHorizontalAlignment(SwingConstants.CENTER);

			lblSelectedCountries.setFont(new Font("Calibri", Font.PLAIN, 14));

			JPanel panelPlayerCountries = new JPanel();
			panelPlayerCountries.setBounds(206, 16, 208, 196);
			rootPanel.add(panelPlayerCountries);
			panelPlayerCountries.setLayout(null);
			panelPlayerCountries.add(lblSelectedCountries);

			JScrollPane scrollPanePlayerCountries = new JScrollPane(listPlayerCountryList);
			scrollPanePlayerCountries.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanePlayerCountries.setBounds(0, 23, 208, 173);
			panelPlayerCountries.add(scrollPanePlayerCountries);

			JPanel panelAdjacentCountries = new JPanel();
			panelAdjacentCountries.setBounds(608, 16, 238, 196);
			rootPanel.add(panelAdjacentCountries);
			panelAdjacentCountries.setBackground(UIManager.getColor("Button.background"));
			panelAdjacentCountries.setLayout(null);

			JLabel lblAdjacentCountry = new JLabel("Adjacent Countries");
			lblAdjacentCountry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblAdjacentCountry.setBounds(0, 5, 238, 15);
			lblAdjacentCountry.setHorizontalAlignment(SwingConstants.CENTER);
			panelAdjacentCountries.add(lblAdjacentCountry);
			lblAdjacentCountry.setFont(new Font("Calibri", Font.PLAIN, 14));

			JPanel panelPhaseName = new JPanel();
			panelPhaseName.setBounds(15, 129, 179, 83);
			rootPanel.add(panelPhaseName);
			panelPhaseName.setLayout(null);

			JLabel lblPhaseName = new JLabel("Phase Name");
			lblPhaseName.setBounds(0, 0, 179, 20);
			lblPhaseName.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhaseName.setFont(new Font("Calibri", Font.BOLD, 16));
			panelPhaseName.add(lblPhaseName);

			textFieldPhaseName = new JTextField(mapGraph.getGamePhase());
			textFieldPhaseName.setFont(new Font("Calibri", Font.PLAIN, 16));
			textFieldPhaseName.setBounds(0, 25, 164, 26);
			panelPhaseName.add(textFieldPhaseName);
			textFieldPhaseName.setColumns(10);
			textFieldPhaseName.setEnabled(false);
			listPlayerCountryList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting()) {
						selectedCountry = listPlayerCountryList.getSelectedValue().toString();
						selectedCountryObject = player.getSelectedCountry(selectedCountry);
					}
					setPlayerCountryDetails(
							selectedCountry + " has " + selectedCountryObject.getNoOfArmies() + " armies.");
					setPlayerAdjCountryDetails("");
					rootPanel.removeAll();
					rootPanel.revalidate();
					rootPanel.repaint();
					refreshFrame(mapGraph);
				}
			});

			if (selectedCountryObject != null) {
				DefaultListModel<String> adjCountryList = new DefaultListModel<String>();
				for (String adjCountry : selectedCountryObject.getAdjacentCountries()) {
					adjCountryList.addElement(adjCountry);
				}
				JList<String> listAdjacentCountries = new JList<String>(adjCountryList);
				listAdjacentCountries.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				listAdjacentCountries.setFont(new Font("Calibri", Font.PLAIN, 16));
				listAdjacentCountries.setVisibleRowCount(20);
				listAdjacentCountries.setBounds(0, 23, 238, 173);
				listAdjacentCountries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listAdjacentCountries.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						setSelectedAdjacentCountry(listAdjacentCountries.getSelectedValue().toString());
						Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph,
								getSelectedAdjacentCountry());
						Player adjCountryPlayer = playerController.getPlayerForCountry(mapGraph,
								getSelectedAdjacentCountry());
						setPlayerAdjCountryDetails(
								getSelectedAdjacentCountry() + " belongs to " + adjCountryPlayer.getName() + " - has "
										+ selectedAdjCountryObject.getNoOfArmies() + " armies.");
						rootPanel.removeAll();
						rootPanel.revalidate();
						rootPanel.repaint();
						refreshFrame(mapGraph);
					}
				});

				JScrollPane scrollPanePlayerAdjacentCountries = new JScrollPane(listAdjacentCountries);
				scrollPanePlayerAdjacentCountries
						.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPanePlayerAdjacentCountries.setBounds(0, 25, 238, 171);
				panelAdjacentCountries.add(scrollPanePlayerAdjacentCountries);
			}

			JButton btnPlaceArmy = new JButton("Place Army");
			btnPlaceArmy.setEnabled(false);
			if (mapGraph.getGamePhase().equalsIgnoreCase("Place Armies")) {
				btnPlaceArmy.setEnabled(true);
			}
			btnPlaceArmy.setBounds(426, 16, 165, 29);
			btnPlaceArmy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PlayerController playerController = new PlayerController();
					if (selectedCountry != null && selectedCountry.length() > 0) {
						playerController.armiesAssignedToCountries(mapGraph, selectedCountry, 1);

						if (nextPlayerNumber == totalNumberOfPlayers) {
							roundRobin = null;
							nextPlayerNumber = 0;
						}

						mapGraph.setRefreshFrame(false);
						player.setFirstReinforcement(true);

						listPlayerCountryList.removeAll();
						panelPlayerCountries.removeAll();
						rootPanel.removeAll();
						rootPanel.revalidate();
						rootPanel.repaint();
						initialize(mapGraph);
					} else {
						JOptionPane.showMessageDialog(new JFrame("Error"), "Please select a country to place an Army");
					}
				}
			});
			btnPlaceArmy.setFont(new Font("Calibri", Font.PLAIN, 16));
			rootPanel.add(btnPlaceArmy);

			JButton btnReinforcement = new JButton("Reinforcement");
			btnReinforcement.setBounds(426, 46, 165, 29);
			btnReinforcement.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnReinforcement.setEnabled(false);
			if (mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement")) {
				btnReinforcement.setEnabled(true);
			}
			btnReinforcement.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectedCountry != null && selectedCountry.length() > 0) {
						JFrame reinforceArmy = new JFrame("Reinforce Armies");
						String armyReinforce = JOptionPane.showInputDialog(reinforceArmy,
								"Enter the number of armies to be reinforced:");
						if (armyReinforce != null) {
							playerController.armiesAssignedToCountries(mapGraph, selectedCountry,
									Integer.parseInt(armyReinforce));
							rootPanel.removeAll();
							rootPanel.revalidate();
							rootPanel.repaint();
							refreshFrame(mapGraph);
						} else {
							reinforceArmy.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a country to reinforce armies");
					}
				}
			});
			rootPanel.add(btnReinforcement);

			JButton btnAttack = new JButton("Attack");
			btnAttack.setBounds(426, 76, 165, 29);
			btnAttack.setFont(new Font("Calibri", Font.PLAIN, 16));
			rootPanel.add(btnAttack);
			btnAttack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PlayerController playerController = new PlayerController();
					Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph,
							getSelectedAdjacentCountry());
					if (selectedCountryObject != null && selectedAdjCountryObject != null) {
						playerController.attackPhase(mapGraph, selectedCountryObject, selectedAdjCountryObject);
					} else {
						JOptionPane.showMessageDialog(null, "Please select the attacker and defender countries.");
					}
					rootPanel.removeAll();
					rootPanel.revalidate();
					rootPanel.repaint();
					refreshFrame(mapGraph);

				}
			});

			JButton btnCompleteAttack = new JButton("All Out");
			btnCompleteAttack.setBounds(426, 106, 165, 29);
			btnCompleteAttack.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnCompleteAttack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PlayerController playerController = new PlayerController();
					Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph,
							getSelectedAdjacentCountry());
					if (selectedCountryObject != null && selectedAdjCountryObject != null) {
						playerController.allOutAttack(mapGraph, selectedCountryObject, selectedAdjCountryObject);
						mapGraph.setGamePhase("Fortification");
					} else {
						JOptionPane.showMessageDialog(null, "Please select the countries for the All-Out mode");
					}
					rootPanel.removeAll();
					rootPanel.revalidate();
					rootPanel.repaint();
					refreshFrame(mapGraph);
				}
			});
			rootPanel.add(btnCompleteAttack);

			btnAttack.setEnabled(false);
			btnCompleteAttack.setEnabled(false);

			if (player.getArmyCount() == 0 && mapGraph.getGamePhase().equalsIgnoreCase("Reinforcement")) {
				btnReinforcement.setEnabled(false);
				btnAttack.setEnabled(true);
				btnCompleteAttack.setEnabled(true);
				mapGraph.setGamePhase("Attack");
			} else if (mapGraph.getGamePhase().equalsIgnoreCase("Attack")) {
				btnReinforcement.setEnabled(false);
				btnAttack.setEnabled(true);
				btnCompleteAttack.setEnabled(true);
			}

			JButton btnFortify = new JButton("Fortify");
			btnFortify.setBounds(426, 136, 165, 29);
			btnFortify.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnFortify.putClientProperty("isFortificationComplete", isFortificationComplete);
			btnFortify.setEnabled(false);
			if (mapGraph.getGamePhase().equalsIgnoreCase("Fortification")) {
				btnFortify.setEnabled(true);
			}
			btnFortify.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					boolean playerFound = false;
					boolean isAdjCountry = false;
					boolean isFortificationComplete = (boolean) ((JButton) (e.getSource()))
							.getClientProperty("isFortificationComplete");
					Country selectedAdjCountryObject = playerController.getAdjacentCountry(mapGraph,
							getSelectedAdjacentCountry());
					JFrame fortifyArmy = new JFrame();
					String armiesCount = JOptionPane.showInputDialog(fortifyArmy,
							"Enter the number of armies you want to move:");
					if (armiesCount != null) {
						if (selectedCountryObject != null && selectedAdjCountryObject != null) {
							if (Integer.parseInt(armiesCount) >= selectedCountryObject.getNoOfArmies()) {
								JOptionPane.showMessageDialog(null,
										"Can move only " + (selectedCountryObject.getNoOfArmies() - 1) + " armies.");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Please select the countries for army fortification");
						}

						for (Player player : mapGraph.getPlayers()) {
							for (Country country : player.getMyCountries()) {
								if (country.getName().equalsIgnoreCase(selectedCountryObject.getName())) {
									playerFound = true;
									break;
								}
							}
							if (playerFound) {
								for (Country country : player.getMyCountries()) {
									if (country.getName().equalsIgnoreCase(selectedAdjCountryObject.getName())) {
										playerController.moveArmies(selectedCountryObject, selectedAdjCountryObject,
												Integer.parseInt(armiesCount));
										isAdjCountry = true;
										isFortificationComplete = true;
										mapGraph.setGamePhase("Reinforcement");
										break;
									}
								}
								break;
							}
						}

						if (!isAdjCountry) {
							JOptionPane.showMessageDialog(null, player.getName() + " does not own this country");
						}
						((JButton) (e.getSource())).putClientProperty("isFortificationComplete",
								isFortificationComplete);

						if (isFortificationComplete) {
							mapGraph.setRefreshFrame(false);
							rootPanel.removeAll();
							rootPanel.revalidate();
							rootPanel.repaint();
							initialize(mapGraph);
						}
					} else {
						fortifyArmy.dispose();
					}
				}

			});
			rootPanel.add(btnFortify);

			JCheckBox chckbxCompleteAttack = new JCheckBox("Complete Attack");
			chckbxCompleteAttack.setBounds(0, 57, 179, 26);
			chckbxCompleteAttack.setFont(new Font("Calibri", Font.BOLD, 16));
			if (mapGraph.getGamePhase().equalsIgnoreCase("Attack")
					|| mapGraph.getGamePhase().equalsIgnoreCase("Fortification")) {
				panelPhaseName.add(chckbxCompleteAttack);
			}
			if (mapGraph.getGamePhase().equalsIgnoreCase("Fortification")) {
				chckbxCompleteAttack.setEnabled(false);
			}
			chckbxCompleteAttack.setSelected(player.isCompleteAttack());
			chckbxCompleteAttack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (chckbxCompleteAttack.isSelected()) {
						player.setCompleteAttack(true);
						mapGraph.setGamePhase("Fortification");
					} else {
						player.setCompleteAttack(false);
						mapGraph.setGamePhase("Attack");
					}
					mapGraph.setRefreshFrame(true);
					rootPanel.removeAll();
					rootPanel.revalidate();
					rootPanel.repaint();
					initialize(mapGraph);
				}
			});

			JButton btnEndTurn = new JButton("End Turn");
			btnEndTurn.setBounds(426, 166, 165, 29);
			btnEndTurn.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnEndTurn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (mapGraph.getGamePhase().equalsIgnoreCase("Place Armies")) {
						playerController.getCurrentPlayer(mapGraph, player.getName()).setEndPlaceArmies(true);
					}

					if (playerController.isPlaceArmiesComplete(mapGraph)) {
						mapGraph.setGamePhase("Reinforcement");
					}

					if (nextPlayerNumber == totalNumberOfPlayers) {
						roundRobin = null;
						nextPlayerNumber = 0;
					}
					// allocateCard
					if (player.getConquerCountry() > conquerCount) {
						CardController cardAction = new CardController();
						cardAction.allocateCardToPlayer(player);

					}
					mapGraph.setRefreshFrame(false);
					rootPanel.removeAll();
					rootPanel.revalidate();
					rootPanel.repaint();
					initialize(mapGraph);
				}
			});
			rootPanel.add(btnEndTurn);

			// world domination panel

			JPanel worldDomination = new JPanel();
			worldDomination.setBounds(15, 228, 495, 268);
			rootPanel.add(worldDomination);

			JPanel panelMapOccupied = new JPanel();
			panelMapOccupied.setBounds(0, 31, 295, 237);

			DefaultPieDataset dataset = new DefaultPieDataset();
			PlayerDomination playerDomination = new PlayerDomination();
			Iterator<Entry<String, Double>> iterator = playerDomination.dominationPercentage(mapGraph).entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Double> nextItem = iterator.next();
				dataset.setValue(nextItem.getKey(), nextItem.getValue());
			}

			JFreeChart chart = ChartFactory.createPieChart("% Map Occupied", dataset, true, true, false);
			PiePlot p = (PiePlot) chart.getPlot();
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBounds(0, 0, 296, 232);
			chartPanel.setPreferredSize(new Dimension(panelMapOccupied.getWidth(), panelMapOccupied.getHeight()));

			panelMapOccupied.removeAll();
			panelMapOccupied.validate();
			panelMapOccupied.repaint();
			worldDomination.setLayout(null);
			panelMapOccupied.setLayout(null);
			chartPanel.setLayout(null);
			panelMapOccupied.add(chartPanel);
			worldDomination.add(panelMapOccupied);

			// Start : PlayerDetails TreeMap

			JPanel panel = new JPanel();
			panel.setBounds(300, 31, 195, 237);
			worldDomination.add(panel);

			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			panel.setLayout(new BorderLayout(0, 0));

			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Player Details");
			DefaultMutableTreeNode playerBranch;
			DefaultMutableTreeNode continentsBranch;
			DefaultMutableTreeNode continent;
			DefaultMutableTreeNode countries;
			DefaultMutableTreeNode country;

			for (Player player : mapGraph.getPlayers()) {
				playerBranch = new DefaultMutableTreeNode(player.getName());
				root.add(playerBranch);
				continentsBranch = new DefaultMutableTreeNode("Continents");
				countries = new DefaultMutableTreeNode("Country");
				playerBranch.add(continentsBranch);
				playerBranch.add(countries);
				for (String playerCountry : player.getPlayerCountryNames()) {
					Country countryForArmyCount = player.getSelectedCountry(playerCountry);
					country = new DefaultMutableTreeNode(playerCountry + " : " + countryForArmyCount.getNoOfArmies());
					countries.add(country);
				}
			}

			JTree playerTree = new JTree(root);
			playerTree.setVisibleRowCount(12);
			playerTree.setToggleClickCount(1);
			playerTree.setAutoscrolls(true);
			playerTree.setFont(new Font("Calibri", Font.PLAIN, 16));

			DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) playerTree.getCellRenderer();
			Icon closedIcon = new ImageIcon("");
			Icon openIcon = new ImageIcon("");
			Icon leafIcon = new ImageIcon("");
			renderer.setClosedIcon(closedIcon);
			renderer.setOpenIcon(openIcon);
			renderer.setLeafIcon(leafIcon);
			playerTree.setEnabled(true);

			JScrollPane scrollPane = new JScrollPane(playerTree);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panel.add(scrollPane);

			JLabel lblWorldDomination = new JLabel("World Domination");
			lblWorldDomination.setBounds(0, 0, 495, 30);
			lblWorldDomination.setBackground(Color.WHITE);
			lblWorldDomination.setFont(new Font("Calibri", Font.BOLD, 24));
			lblWorldDomination.setHorizontalAlignment(SwingConstants.CENTER);
			worldDomination.add(lblWorldDomination);

			JPanel panelMapDetails = new JPanel();
			panelMapDetails.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panelMapDetails.setBounds(514, 228, 332, 268);
			rootPanel.add(panelMapDetails);
			panelMapDetails.setLayout(null);

			JLabel lblMapDetails = new JLabel("Map Details");
			lblMapDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			lblMapDetails.setHorizontalAlignment(SwingConstants.CENTER);
			lblMapDetails.setBounds(0, 0, 332, 30);
			panelMapDetails.add(lblMapDetails);

			JLabel lblPlayerCountryDetails = new JLabel(getPlayerCountryDetails());
			lblPlayerCountryDetails.setBounds(15, 74, 313, 70);
			panelMapDetails.add(lblPlayerCountryDetails);
			lblPlayerCountryDetails.setBackground(Color.BLACK);

			JLabel lblPlayerAdjCountryDetails = new JLabel(getPlayerAdjCountryDetails());
			lblPlayerAdjCountryDetails.setBounds(15, 147, 313, 70);
			panelMapDetails.add(lblPlayerAdjCountryDetails);
			lblPlayerAdjCountryDetails.setBackground(Color.BLACK);

			frmRiskGame.setVisible(true);
		} else {
			for (Player player : mapGraph.getPlayers()) {
				if (!player.isPlayerLostGame()) {
					JOptionPane.showMessageDialog(null, player.getName() + " has won the game!!");
					System.exit(0);
				}
			}

		}
	}

	private boolean validateGameCompletion(GameMapGraph mapGraph) {
		int i = 0;
		for (Player player : mapGraph.getPlayers()) {
			if (!player.isPlayerLostGame()) {
				i++;
			}
		}
		if (i > 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method starts the initial reinforcement phase
	 * 
	 * @param mapGraph - The GameMapGraph object
	 * @param player   - The Player object
	 */
	public void startReinforcement(GameMapGraph mapGraph, Player player) {
		int reinforcementArmies = playerController.reinforcementPhase(player, mapGraph);
		player.setArmyCount(player.getArmyCount() + reinforcementArmies);
	}

	/**
	 * This method is to update the observers
	 */
	@Override
	public void update(Observable o, Object arg) {
		for (Window window : Window.getWindows()) {
			if (window instanceof JFrame) {
				if (((JFrame) window).getTitle().equalsIgnoreCase("Risk Game")) {
					frmRiskGame = (JFrame) window;
				}
			}
		}

		if (frmRiskGame != null) {
			frmRiskGame.revalidate();
			frmRiskGame.repaint();
		}
	}

	/**
	 * Method to get the selected country
	 * 
	 * @return selectedCountry - the player's country
	 */
	public String getSelectedCountry() {
		return selectedCountry;
	}

	/**
	 * Methdo to set the selected country
	 * 
	 * @param selectedCountry - the selected country
	 */
	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	/**
	 * Method to get the selected adjacent country
	 * 
	 * @return selectedAdjacentCountry - the country
	 */
	public String getSelectedAdjacentCountry() {
		return selectedAdjacentCountry;
	}

	/**
	 * Method to set the selected adjacent country
	 * 
	 * @param selectedAdjacentCountry - Sets the selected adjacent country
	 */
	public void setSelectedAdjacentCountry(String selectedAdjacentCountry) {
		this.selectedAdjacentCountry = selectedAdjacentCountry;
	}

	/**
	 * Method to get the player's country details
	 * 
	 * @return playerCountryDetails - the country details of player
	 */
	public String getPlayerCountryDetails() {
		return playerCountryDetails;
	}

	/**
	 * Method to set the player's country details
	 * 
	 * @param playerCountryDetails - the country details of player
	 */
	public void setPlayerCountryDetails(String playerCountryDetails) {
		this.playerCountryDetails = playerCountryDetails;
	}

	/**
	 * Method to get the player's adjacent country details
	 * 
	 * @return playerAdjCountryDetails - the adjacent country details
	 */
	public String getPlayerAdjCountryDetails() {
		return playerAdjCountryDetails;
	}

	/**
	 * Method to get the player's adjacent country details
	 * 
	 * @param playerAdjCountryDetails - the adjacent country details
	 */
	public void setPlayerAdjCountryDetails(String playerAdjCountryDetails) {
		this.playerAdjCountryDetails = playerAdjCountryDetails;
	}

	/**
	 * This method refreshes the frame
	 * 
	 * @param mapGraph - The GameMapGraph object
	 */
	private void refreshFrame(GameMapGraph mapGraph) {
		mapGraph.setRefreshFrame(true);
		initialize(mapGraph);
	}
}