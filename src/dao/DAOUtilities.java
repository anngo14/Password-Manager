package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DAOUtilities {

	/*private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "password";
	private static final String URL = "jdbc:postgresql://localhost:5432/passwordManager";*/
	private static final String DB_USER = "admin";
	private static final String DB_PASSWORD = "Powermacg5";
	private static final String DB_URL = "jdbc:mysql://totemdb-1.cykewri9xfhv.us-east-2.rds.amazonaws.com/passwordManager";
	private static Connection connection;

	static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			/*try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}*/
			 connection = establishDataSource().getConnection();
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			 connection = establishDataSource().getConnection();
		}
		return connection;
	}
	public static MysqlDataSource establishDataSource()
	{
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL(DB_URL);
		ds.setUser(DB_USER);
		ds.setPassword(DB_PASSWORD);
		return ds;
	}
}
