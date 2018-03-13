package view;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.LoginController;
import lib.*;

public class App {
	public static FrameView view,lock=null;
	public static Database database;
	public static boolean lockedFromApp = false;
	public static String sessionPass = LoginController.SHA1("pass");

	public static void main(String[] args) {
		try {
			// Set System L&F
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}

			}
			/*
			 * UIManager.setLookAndFeel(
			 * UIManager.getSystemLookAndFeelClassName());
			 */
		} catch (Exception e) {
			// handle exception
		}

		try {
			database = new Database();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

		lock = new LoginWindow();

	}

}
