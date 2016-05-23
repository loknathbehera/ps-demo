package com.finovera.ebillApi.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddBillerFrame extends JFrame {

	private final JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public boolean addBillerPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AddBillerFrame frame = new AddBillerFrame();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
		return rootPaneCheckingEnabled;
	}

	/**
	 * Create the frame.
	 */
	public AddBillerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
