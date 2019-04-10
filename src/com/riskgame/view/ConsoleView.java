package com.riskgame.view;

import java.awt.Font;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.riskgame.controller.TextAreaOutputStream;
import javax.swing.ScrollPaneConstants;

/**
 * View to display the status of the BOT players
 * @author Nikitha
 *
 */
public class ConsoleView extends JFrame {
	
	/**
	 * Constructor of the ConsoleView
	 */
	public ConsoleView() {
		getContentPane().setLayout(null);
		setBounds(300,100,431,663);
		setTitle("ConsoleOutput");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		textArea.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 409, 607);
		getContentPane().add(scrollPane);
		
		TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
		         textArea);
		
		 System.setOut(new PrintStream(taOutputStream));
		setVisible(true);
	}
}
