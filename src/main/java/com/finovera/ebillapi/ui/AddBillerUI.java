package com.finovera.ebillapi.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.AddBillerServices;
import com.finovera.platformServices.data.Biller;
import com.finovera.platformServices.data.CredentialFieldInfo;

public class AddBillerUI extends JFrame {

	private JPanel contentPane;
	private JTextField immuitableIdtextField;
	private JTextField credTextField;

	AuthInfo authInfo;

	AddBillerServices addBillerService;

	/**
	 * Create the frame.
	 */
	public AddBillerUI(final AuthInfo authinfo) {

		this.authInfo = authinfo;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel immuitableIdLbl = new JLabel("ImmuitableId :");

		immuitableIdtextField = new JTextField();
		immuitableIdtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBillerService = new AddBillerServices(authinfo);
				Biller biller = addBillerService.getBillerInfo(e.getActionCommand());
				createCredPanel(biller);
			}
		});
		immuitableIdtextField.setToolTipText("Please Enter the Immuitable Id of the biller which you need to add");
		immuitableIdtextField.setHorizontalAlignment(SwingConstants.LEFT);
		immuitableIdtextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
								.addComponent(immuitableIdLbl, GroupLayout.PREFERRED_SIZE, 76,
										GroupLayout.PREFERRED_SIZE)
								.addGap(43)
								.addComponent(immuitableIdtextField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addGap(87)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(immuitableIdLbl))
				.addComponent(immuitableIdtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE));
		contentPane.setLayout(gl_contentPane);

	}

	protected void createCredPanel(Biller biller2) {

		JPanel credPanel = new JPanel();
		// sl_contentPane.putConstraint(SpringLayout.NORTH, credPanel, 46,
		// SpringLayout.NORTH, contentPane);
		// sl_contentPane.putConstraint(SpringLayout.SOUTH, credPanel, -1,
		// SpringLayout.SOUTH, contentPane);
		// sl_contentPane.putConstraint(SpringLayout.SOUTH,
		// immuitableIdtextField, -6, SpringLayout.NORTH, credPanel);
		// sl_contentPane.putConstraint(SpringLayout.WEST, credPanel, 97,
		// SpringLayout.WEST, contentPane);
		// sl_contentPane.putConstraint(SpringLayout.EAST, credPanel, 4,
		// SpringLayout.EAST, contentPane);
		// contentPane.add(credPanel);
		credPanel.setLayout(null);

		CredentialFieldInfo[] credentialFieldInfo = biller2.getCredentialFieldInfoList();

		JLabel credDefNameLbl = new JLabel("");
		credDefNameLbl.setBounds(10, 52, 46, 14);
		credPanel.add(credDefNameLbl);

		credTextField = new JTextField();
		credTextField.setBounds(100, 49, 157, 20);
		credPanel.add(credTextField);
		credTextField.setColumns(10);

	}
}
