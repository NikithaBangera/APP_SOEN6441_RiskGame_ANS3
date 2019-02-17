package com.riskgame.driver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.riskgame.action.CreateAndEditMap;
import com.riskgame.action.FunctionServiceImpl;

public class RiskMain {
	public static void main(String[] args) {
	//	FunctionServiceImpl fs  =  new FunctionServiceImpl();
		
		JFrame frame = new JFrame("MAP");
	    JPanel panel = new JPanel(new GridLayout(4,4,4,4));
	        JButton btn = new JButton(String.valueOf("Create New Map"));
	        btn.setPreferredSize(new Dimension(40, 40));
	        panel.add(btn);
	        btn.addActionListener(new ActionListener() {
			
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
	        
	        JButton btn1 = new JButton(String.valueOf("Load Existing Map"));
	        btn1.setPreferredSize(new Dimension(40, 40));
	        panel.add(btn1);
	        
	    
	    frame.setContentPane(panel);
	    frame.pack();
	    frame.setVisible(true); 
	}	
	
		
	}
}
