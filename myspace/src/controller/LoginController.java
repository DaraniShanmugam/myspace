package controller;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JComponent;

import model.LoginModel;


public class LoginController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Actions for Login button
		if (e.getSource() == ((LoginWindow) App.lock).getLoginButton()) {
			LoginWindow window = (LoginWindow) App.lock;
			String username = window.getUsername(); // Get entered username
			String password = window.getPassword(); // Get entered password
			LoginModel loginModel = new LoginModel(); // Create LoginModel
			
			// Test if the username - password combination exists
			try {
				if (loginModel.validate(username, SHA1(password))) {
					App.lock.dispose(); // Close Login screen
					// If the app is newly opened then create a new Dashboard or show existing Dashboard
					if(App.lockedFromApp)
						App.view.setVisible(true);
					else					
						App.view = new DashboardView();
					
					
				} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else {
			// Close the login screen if Cancel button is pressed
			App.lock.dispose();
		}

	}
	// Function to generate SHA1 hash for password
	public static String SHA1(String message) {
		MessageDigest sha1 = null;
		try {
			sha1 = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] result = sha1.digest(message.getBytes());
		StringBuffer digest = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			digest.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return digest.toString();
	}
}
