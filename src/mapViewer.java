import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class mapViewer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mapViewer window = new mapViewer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mapViewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Reinforce");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Attack");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Fortify");
		panel.add(btnNewButton);
		
		JButton btnEndTurn = new JButton("End Turn");
		panel.add(btnEndTurn);
		
		JLabel lblNewLabel = new JLabel("Army Count");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNewLabel, BorderLayout.EAST);
	}

}
