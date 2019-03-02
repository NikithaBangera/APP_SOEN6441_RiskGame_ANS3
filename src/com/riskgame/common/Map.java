package com.riskgame.common;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

import com.riskgame.action.CreateAndEditMap;
import com.riskgame.action.LoadAndEditMap;

import javax.imageio.ImageIO;
public class  Map 
{
		public void mainFrame()
		{
			JFrame jframe = new JFrame();
			JButton createMapBtn = new JButton("Create Map");
			JButton loadMapBtn = new JButton("Load Existing Map");	
			jframe.add(createMapBtn);
			jframe.add(loadMapBtn);
			//putting the size and the location of the jframe
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
						String image=loadandeditmap.loadMap();
						if(image !=null) {
							JLabel background;
							String workingDir = System.getProperty("user.dir"); 
							//file path for mac: /src/com/riskgame/images/
							// file path for windows : \\src\\com\\riskgame\\images\\
							String filepath= workingDir+"/src/com/riskgame/images/"+image;
							BufferedImage image_br = ImageIO.read(new File(filepath));
							ImageIcon img=new ImageIcon(image_br);
							background = new JLabel(img);
							jframe.setContentPane(background);
							jframe.setVisible(true);
						}
					}
				catch (IOException e2) {
					System.out.println("Image not found");
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		}			
}