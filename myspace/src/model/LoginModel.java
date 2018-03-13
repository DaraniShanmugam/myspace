package model;

import java.sql.SQLException;

import view.App;

public class LoginModel {
	public boolean validate(String username, String password) throws SQLException {
		String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
		App.database.execute(query);

		return (App.database.getRowCount() != 0) ? true : false;
	}

}
