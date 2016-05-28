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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.AddBillerServices;
import com.finovera.platformServices.data.Biller;
import com.finovera.platformServices.data.CredentialFieldInfo;

public class AddBillerUI extends JFrame {

	private JPanel contentPane;
	private JTextField immuitableIdtextField;
	JPanel credsPanel;

	AuthInfo authInfo;

	AddBillerServices addBillerService;
	private JTextField credTextField;

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

				credsPanel.setVisible(true);
			}
		});
		immuitableIdtextField.setToolTipText("Please Enter the Immuitable Id of the biller which you need to add");
		immuitableIdtextField.setHorizontalAlignment(SwingConstants.LEFT);
		immuitableIdtextField.setColumns(10);

		credsPanel = new JPanel();
		credsPanel.setBackground(UIManager.getColor("Button.shadow"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
										.addComponent(immuitableIdLbl, GroupLayout.PREFERRED_SIZE, 76,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(immuitableIdtextField, GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(30).addComponent(credsPanel,
								GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)))
						.addGap(29)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(immuitableIdLbl)
								.addComponent(immuitableIdtextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(32).addComponent(credsPanel, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addGap(22)));
		credsPanel.setLayout(null);
		credsPanel.setVisible(false);

		contentPane.setLayout(gl_contentPane);

	}

	protected void createCredPanel(Biller biller) {

		for (CredentialFieldInfo credentialFieldInfo : biller.getCredentialFieldInfoList()) {

			JPanel credPanel = new JPanel();
			credPanel.setBounds(38, 11, 306, 36);
			credsPanel.add(credPanel);
			credPanel.setLayout(null);

			JLabel credlbl = new JLabel(credentialFieldInfo.getName());
			credlbl.setText(credentialFieldInfo.getName());
			credlbl.setBounds(10, 11, 61, 14);
			credlbl.setHorizontalAlignment(SwingConstants.LEFT);
			credPanel.add(credlbl);

			credTextField = new JTextField();
			credTextField.setName(credentialFieldInfo.getName() + "_txtFiled");
			credTextField.setBounds(81, 8, 215, 20);
			credPanel.add(credTextField);
			credTextField.setColumns(10);

			credsPanel.add(credPanel);

		}
	}
}
