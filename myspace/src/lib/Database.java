package lib;
import java.sql.*;

public class Database {
	Connection connection;
	Statement statement;
	ResultSet result;
	int lastResultCount;
	
	public Database() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myspace","root","");
	}
	
	public void execute(String query) throws SQLException {
		lastResultCount = 0;
		
		statement = connection.createStatement();
		result = statement.executeQuery(query);
		
		if(result.last()) {
			lastResultCount = result.getRow();
			result.beforeFirst();
		}
		
	}
	
	public void executeUpdate(String query) throws SQLException {
		statement = connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public ResultSet fetchResult() {
		return result;
	}
	
	public int getRowCount() {
		return lastResultCount;
	}
}

