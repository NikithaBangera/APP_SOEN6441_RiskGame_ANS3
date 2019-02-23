package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.riskgame.action.CreateAndEditMap;
import com.riskgame.action.FunctionServiceImpl;

public class RiskMain extends JFrame {
	JButton button1, button2;
	JLabel label1, label2;

	public RiskMain() {
		setLayout(new GridLayout(4, 4));
		// JFrame frame = new JFrame();
		button1 = new JButton("Create a new Map");
		button1.setPreferredSize(new Dimension(60, 60));
		add(button1);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateAndEditMap createMap = new CreateAndEditMap();
				try {
					createMap.newMapCreation();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		button2 = new JButton("Load Existing Map");
		button2.setPreferredSize(new Dimension(50, 50));
		add(button2);

		// frame.add(panel);
		// frame.setSize(400, 400);

	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		RiskMain layout = new RiskMain();
		layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout.setVisible(true);
		layout.pack();
		layout.move(800, 400);
		layout.setSize(300, 300);
		;
		layout.setTitle("Risk Game");
		// FunctionServiceImpl fs = new FunctionServiceImpl();

	}
}