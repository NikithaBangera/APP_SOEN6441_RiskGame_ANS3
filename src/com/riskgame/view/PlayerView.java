package com.riskgame.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import com.riskgame.controller.PlayerController;
import com.riskgame.model.Country;
import com.riskgame.model.GameMapGraph;
import com.riskgame.model.Player;

import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class PlayerView implements Observer {

	private JFrame frame;
	PlayerController play = new PlayerController();
	GameMapGraph mapGraph = new GameMapGraph();
	/** The players. */
	private ArrayList<Player> players;
	private String selectedCountry;
	private String selectedAdjacentCountry;
	private Country selectedCountryObject;
	

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerView window = new PlayerView(new PlayerController().getPlayersList().get(0));
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
//	public PlayerView(GameMapGraph mapGraph, ArrayList<Player> players) {
//		this.mapGraph = mapGraph;
//		this.players = players;
//		
//	}

	public PlayerView(Player player) {
		try {
			initialize(player);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Player player) {
	frame = new JFrame();
	frame.setBounds(100, 100, 883, 568);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(15, 38, 165, 108);
	frame.getContentPane().add(panel_1);
	
	JLabel lblPlayerName = new JLabel("Player Name");
	lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
	panel_1.add(lblPlayerName);
	
	JLabel lblNumberOfArmies_1 = new JLabel("Number of Armies");
	lblNumberOfArmies_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	panel_1.add(lblNumberOfArmies_1);
	
	
	JButton btnPlaceArmy = new JButton("Place Army");
	btnPlaceArmy.setFont(new Font("Arial", Font.PLAIN, 12));
	btnPlaceArmy.setBounds(426, 23, 165, 29);
	frame.getContentPane().add(btnPlaceArmy);
	
	JButton btnAttack = new JButton("Attack");
	btnAttack.setFont(new Font("Arial", Font.PLAIN, 12));
	btnAttack.setBounds(426, 78, 165, 29);
	frame.getContentPane().add(btnAttack);
	btnAttack.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerController playerController = new PlayerController();
			Country selectedAdjCountryObject = playerController.getDefenderCountry(getSelectedAdjacentCountry());
			if(selectedCountryObject != null && selectedAdjCountryObject != null) {
				playerController.attackPhase(selectedCountryObject, selectedAdjCountryObject);
			}
		}
	});
	
	JButton btnCompleteAttack = new JButton("All Out");
	btnCompleteAttack.setFont(new Font("Arial", Font.PLAIN, 12));
	btnCompleteAttack.setBounds(426, 105, 165, 29);
	btnCompleteAttack.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerController playerController = new PlayerController();
			Country selectedAdjCountryObject = playerController.getDefenderCountry(getSelectedAdjacentCountry());
			if(selectedCountryObject != null && selectedAdjCountryObject != null) {
				playerController.allOutAttack(selectedCountryObject, selectedAdjCountryObject);
			}
			
		}
	});
	
	frame.getContentPane().add(btnCompleteAttack);
	
	JButton btnFortify = new JButton("Fortify");
	btnFortify.setFont(new Font("Arial", Font.PLAIN, 12));
	btnFortify.setBounds(426, 131, 165, 29);
	
	frame.getContentPane().add(btnFortify);
	
	JButton btnEndTurn = new JButton("End Turn");
	btnEndTurn.setFont(new Font("Arial", Font.PLAIN, 12));
	btnEndTurn.setBounds(426, 159, 165, 29);
	frame.getContentPane().add(btnEndTurn);
	
	JButton btnEndPhase = new JButton("End Phase");
	btnEndPhase.setFont(new Font("Arial", Font.PLAIN, 12));
	btnEndPhase.setBounds(426, 185, 165, 29);
	frame.getContentPane().add(btnEndPhase);
	
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
	btnCards.setBounds(426, 212, 165, 29);
	frame.getContentPane().add(btnCards);
	
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
			//	JOptionPane.showMessageDialog(null, getSelectedCountry());
			}
		}
	});
	
	
	JPanel panel_2 = new JPanel();
	panel_2.setBounds(15, 146, 165, 83);
	frame.getContentPane().add(panel_2);
	
	JLabel lblNumberOfArmies = new JLabel("Phase Name");
	lblNumberOfArmies.setFont(new Font("Tahoma", Font.PLAIN, 12));
	panel_2.add(lblNumberOfArmies);
	
	JLabel lblWorldDomination = new JLabel("  World Domination");
	lblWorldDomination.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblWorldDomination.setBounds(119, 259, 273, 37);
	frame.getContentPane().add(lblWorldDomination);
	
	JPanel world_domination = new JPanel();
	world_domination.setBounds(6, 259, 386, 233);
	frame.getContentPane().add(world_domination);
	world_domination.setLayout(new BorderLayout(0, 0));
	
	DefaultPieDataset dataset = new DefaultPieDataset();
	for(int i=0; i<play.getCountOfthePlayers();i++) {
		Player playerdetail=play.getPlayersList().get(i);
		dataset.setValue(playerdetail.getName(),playerdetail.getMyCountries().size());
	}
	JFreeChart chart=ChartFactory.createPieChart("World Domination", dataset,true,true,true);
	PiePlot p=(PiePlot)chart.getPlot();
//	p.setForegroundAlpha(alpha);
	ChartPanel CP = new ChartPanel(chart);
	world_domination.removeAll();
	world_domination.setLayout(new BorderLayout(0, 0));
	world_domination.validate();
	world_domination.setLayout(new BorderLayout(0, 0));
	world_domination.add(CP);
	
	JButton btnReinforcement = new JButton("Reinforcement");
	btnReinforcement.setFont(new Font("Arial", Font.PLAIN, 12));
	btnReinforcement.setBounds(426, 50, 165, 29);
	frame.getContentPane().add(btnReinforcement);
	
	JPanel panel_3 = new JPanel();
	panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
	panel_3.setBounds(436, 259, 408, 233);
	frame.getContentPane().add(panel_3);
	
	JLabel lblContinentName = new JLabel("Continent Name");
	panel_3.add(lblContinentName);
	
	JPanel panelAdjacentCountries = new JPanel();
	panelAdjacentCountries.setBounds(626, 38, 218, 188);
	frame.getContentPane().add(panelAdjacentCountries);

	
	JList listAdjacentCountries = new JList(selectedCountryObject.getAdjacentCountries().toArray());
	listAdjacentCountries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	panelAdjacentCountries.add(listAdjacentCountries);
	btnFortify.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String countrytofortify=(String) listAdjacentCountries.getSelectedValue();
			String armiesCount;
			if(mapGraph.getCountrySet().get(countrytofortify).getPlayer()== lblPlayerName.getText()) {
				armiesCount=JOptionPane.showInputDialog("enter the nuber of armies you want to move");
				play.moveArmies(mapGraph.getCountrySet().get(listPlayerCountryList.getSelectedValue()), mapGraph.getCountrySet().get(countrytofortify), Integer.parseInt(armiesCount));
			}
			else {
				JOptionPane.showMessageDialog(null,lblPlayerName.getText()+" does not own this country");
			}
		}
	});
	listAdjacentCountries.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			setSelectedAdjacentCountry(listAdjacentCountries.getSelectedValue().toString());
		}
	});
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//frmGameplay.setVisible(true);

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
