package com.finovera.ebillapi.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.AddBillerServices;
import com.finovera.platformServices.data.Biller;
import com.finovera.platformServices.data.CredentialFieldData;
import com.finovera.platformServices.data.CredentialFieldInfo;
import com.finovera.platformServices.request.ebill.AddBillerLoginRequest;

public class AddBillerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3012305622049707248L;

	private final JTextField immuitableIdtextField;
	JPanel credsPanel;
	JTextPane billertextPane;
	final JButton addBillerbtn;

	AuthInfo authInfo;

	AddBillerServices addBillerService;

	/**
	 * Create the frame.
	 */
	public AddBillerUI(final AuthInfo authinfo) {

		this.authInfo = authinfo;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		final JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final JLabel immuitableIdLbl = new JLabel("ImmuitableId :");

		immuitableIdtextField = new JTextField();
		immuitableIdtextField.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				addBillerService = new AddBillerServices(authinfo);
				final Biller biller = addBillerService.getBillerInfo(e.getActionCommand());
				createCredPanel(biller);

				credsPanel.setVisible(true);

				billertextPane.setText(getBillerText(biller));
				billertextPane.setVisible(true);

				addBillerbtn.setVisible(true);

			}
		});
		immuitableIdtextField.setToolTipText("Please Enter the Immuitable Id of the biller which you need to add");
		immuitableIdtextField.setHorizontalAlignment(SwingConstants.LEFT);
		immuitableIdtextField.setColumns(10);

		credsPanel = new JPanel();
		credsPanel.setBackground(UIManager.getColor("Button.shadow"));

		billertextPane = new JTextPane();
		billertextPane.setEditable(false);

		addBillerbtn = new JButton("Add Biller");
		addBillerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				final String userId = "ebillApiTestUser_54365e38800b4732b43152642ed7a757";
				addBillerService.postRequestToAddBillerLogin(createRequestToAddBillerLogin(), userId);

			}

		});
		addBillerbtn.setVisible(false);
		final GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(immuitableIdLbl, GroupLayout.PREFERRED_SIZE, 76,
																				GroupLayout.PREFERRED_SIZE).addGap(18)
																		.addComponent(immuitableIdtextField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(30)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(Alignment.LEADING)
																						.addComponent(addBillerbtn, GroupLayout.PREFERRED_SIZE, 142,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(credsPanel, GroupLayout.DEFAULT_SIZE,
																												187, Short.MAX_VALUE)
																										.addGap(51)
																										.addComponent(billertextPane, GroupLayout.DEFAULT_SIZE,
																												141, Short.MAX_VALUE))))).addGap(29)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addGap(12)
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(immuitableIdLbl)
										.addComponent(immuitableIdtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(
								gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_contentPane.createSequentialGroup().addComponent(credsPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
														.addGap(27).addComponent(addBillerbtn, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
														.addGap(21))
										.addGroup(
												gl_contentPane.createSequentialGroup()
														.addComponent(billertextPane, GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE).addGap(95)))));

		credsPanel.setVisible(false);
		billertextPane.setVisible(false);

		contentPane.setLayout(gl_contentPane);

	}

	protected void createCredPanel(final Biller biller) {

		int i = 0;

		credsPanel.removeAll();

		for (final CredentialFieldInfo credentialFieldInfo : biller.getCredentialFieldInfoList()) {

			// final JPanel credPanel = new JPanel();
			// credPanel.setBounds(38, 11, 306, 36);

			System.out.println(credentialFieldInfo.getName());

			final JLabel credlbl = new JLabel(credentialFieldInfo.getName() + "lblFiled");
			credlbl.setText(credentialFieldInfo.getName());
			credlbl.setBounds(10, 11, 61, 14);
			credlbl.setHorizontalAlignment(SwingConstants.LEFT);
			// credPanel.add(credlbl);

			final JTextField credTextField = new JTextField();
			credTextField.setName(credentialFieldInfo.getName() + "_txtFiled");
			credTextField.setBounds(81, 8, 215, 20);
			credTextField.setColumns(10);
			// credPanel.add(credTextField);

			// credPanel.setAlignmentX(i * 2);
			// credPanel.setAlignmentY(i * 2);

			credsPanel.add(credlbl);
			credsPanel.add(credTextField);

			i++;
		}
	}

	public String getBillerText(final Biller biller) {

		final StringBuilder sb = new StringBuilder();

		sb.append("\nImmuitable Id : " + biller.getImmutableId() + "\n");
		sb.append("\nDisplay Name :  " + biller.getDisplayName() + "\n");
		sb.append("\nCategory : " + biller.getCategory() + "\n");
		sb.append("\nWebsite URL : " + biller.getWebsiteURL() + "\n");

		sb.append("\nIs Internal : " + biller.isInternal() + "\n");

		sb.append("\n\n-----------------Credential Filed Info-----------------------------------------------\n\n");
		for (final CredentialFieldInfo credentialFieldInfo : biller.getCredentialFieldInfoList()) {
			sb.append("\n cred Def Name  -: " + credentialFieldInfo.getName() + "      Order of Display -: " + credentialFieldInfo.getOrderOfDisplay()
					+ "           isMasked   -: " + credentialFieldInfo.isMasked() + "\n");
		}

		sb.append("\n--------------------------------------------------------------------------------------\n\n");

		return sb.toString();
	}

	private AddBillerLoginRequest createRequestToAddBillerLogin() {

		final AddBillerLoginRequest req = new AddBillerLoginRequest();
		req.setBillerImmutableId(immuitableIdtextField.getText());

		final List<String> lblFileds = new ArrayList<String>();
		final List<String> txtFileds = new ArrayList<String>();

		final List<CredentialFieldData> cfldList = new ArrayList<CredentialFieldData>();

		for (final Component component : credsPanel.getComponents()) {

			final String name, value;
			if (component.getClass().equals(JTextField.class)) {
				System.out.println(((JTextField) component).getText());
				value = ((JTextField) component).getText();
				txtFileds.add(value);
			} else if (component.getClass().equals(JLabel.class)) {
				System.out.println(((JLabel) component).getText());
				name = ((JLabel) component).getText();
				lblFileds.add(name);
			}
		}

		for (int i = 0; i < txtFileds.size(); i++) {
			final CredentialFieldData credentialFieldData = new CredentialFieldData();

			credentialFieldData.setName(lblFileds.get(i));
			credentialFieldData.setStringValue(txtFileds.get(i));
			cfldList.add(credentialFieldData);

		}

		req.setCredentials(cfldList.toArray(new CredentialFieldData[cfldList.size()]));

		return req;

	}
}
