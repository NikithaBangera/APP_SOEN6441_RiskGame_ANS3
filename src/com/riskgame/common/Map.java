package com.riskgame.common;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import com.riskgame.action.CreateAndEditMap;
import com.riskgame.action.LoadAndEditMap;

public class  Map 
{
		public void mainFrame()
		{
			JFrame jframe = new JFrame();
			JButton createMapBtn = new JButton("Create Map");
			JButton loadMapBtn = new JButton("Load Existing Map");
						
			jframe.add(createMapBtn);
			jframe.add(loadMapBtn);
			
			jframe.setLayout(new FlowLayout());
			Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
			jframe.setSize(400, 400);
			jframe.setLocation(dim.width/2-jframe.getSize().width/2,dim.height/2-jframe.getSize().height/2);
			jframe.setVisible(true);		
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			createMapBtn.addActionListener(e -> {
					jframe.setVisible(false);
				    CreateAndEditMap createandeditmap = new CreateAndEditMap();
				try {
						createandeditmap.newMapCreation();
					} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			loadMapBtn.addActionListener(e -> {
				jframe.setVisible(false);
				LoadAndEditMap loadandeditmap = new LoadAndEditMap();
				try {
						loadandeditmap.loadMap();
					} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		}			
}
