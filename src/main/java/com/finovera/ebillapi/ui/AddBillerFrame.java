package com.finovera.ebillapi.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.finovera.ebillapi.auth.AuthInfo;

public class AddBillerFrame extends JFrame {

	private JPanel contentPane;
	AuthInfo authInfo;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
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
	}

	public AddBillerFrame(final AuthInfo authInfo) throws HeadlessException {
		super();
		this.authInfo = authInfo;
	}

	/**
	 * Create the frame.
	 */
	public AddBillerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final JLabel lblNewLabel = new JLabel((String) authInfo.cacheData.get("userId"));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
