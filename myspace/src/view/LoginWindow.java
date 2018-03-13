package view;

import lib.FrameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import controller.LoginController;

public class LoginWindow extends FrameView {
	JTextField username;
	JPasswordField password;
	JLabel usernameLabel, passwordLabel, title,logo;
	JPanel controlsPanel, headingPanel, logoPanel;
	JButton loginButton, cancelButton;
	LoginController controller;

	public LoginWindow() {
		super("Login");
		this.setName("Login");
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		controller = new LoginController();
		initComponents();

		addComponents();
		this.pack();
		this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
		// this.setSize(350,300);
		//
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(245, 245, 245));

	}

	protected void initComponents() {
		username = new JTextField();
		password = new JPasswordField();
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		title = new JLabel("Login");
		loginButton = new JButton("Login");
		loginButton.addActionListener(controller);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(controller);
		controlsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) controlsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		controlsPanel.setBackground(new Color(245, 245, 245));
		// controlsPanel.setLayout(new BorderLayout());
		
		logoPanel = new JPanel();
		logoPanel.setLayout(new BorderLayout());
		logoPanel.setBackground(new Color(50, 50, 50));
		
		
		
		headingPanel = new JPanel();
		headingPanel.setLayout(new BorderLayout());
		headingPanel.setBackground(new Color(52, 73, 94));

		title.setBorder(BorderFactory.createEmptyBorder(25, 25, 0, 0));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Raleway Light", Font.PLAIN, 25));
		title.setVerticalTextPosition(SwingConstants.CENTER);
		
		logo = new JLabel("MySpace");
		logo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		logo.setFont(new Font("Raleway Light", Font.PLAIN, 18));
		logo.setVerticalTextPosition(SwingConstants.CENTER);
		logo.setForeground(Color.WHITE);
	}

	protected void addComponents() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 50, 100, 200, 50 };
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		getContentPane().setLayout(layout);
		if(!App.lockedFromApp)
			controlsPanel.add(cancelButton);
		controlsPanel.add(loginButton);
		logoPanel.add(logo,BorderLayout.EAST);
		headingPanel.add(title, BorderLayout.WEST);
		layoutConstraints.insets = new Insets(0, 0, 35, 0);
		layoutConstraints.ipady = 49;
		layoutConstraints.ipadx = 99;

		addToGridBag((JComponent) this.getContentPane(), headingPanel, layoutConstraints, 0, 0, 4, 1,
				GridBagConstraints.BOTH);
		headingPanel.setAlignmentX(CENTER_ALIGNMENT);
		layoutConstraints.insets = new Insets(0, 0, 0, 0);
		layoutConstraints.ipady = 0;
		layoutConstraints.ipadx = 70;
		addToGridBag((JComponent) this.getContentPane(), usernameLabel, layoutConstraints, 1, 1, 1, 1,
				GridBagConstraints.NONE);
		addToGridBag((JComponent) this.getContentPane(), username, layoutConstraints, 2, 1, 1, 1,
				GridBagConstraints.HORIZONTAL);
		addToGridBag((JComponent) this.getContentPane(), passwordLabel, layoutConstraints, 1, 2, 1, 1,
				GridBagConstraints.NONE);
		addToGridBag((JComponent) this.getContentPane(), password, layoutConstraints, 2, 2, 1, 1,
				GridBagConstraints.HORIZONTAL);
		layoutConstraints.ipadx = 0;
		addToGridBag((JComponent) this.getContentPane(), controlsPanel, layoutConstraints, 2, 3, 1, 1,
				GridBagConstraints.HORIZONTAL);
		//layoutConstraints.insets = new Insets(0, 0, 35, 0);
		layoutConstraints.ipadx = 0;
		addToGridBag((JComponent) this.getContentPane(), logoPanel, layoutConstraints, 0, 4, 4, 1,
				GridBagConstraints.HORIZONTAL);

	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public JComponent getLoginButton() {
		return loginButton;
	}

}
