package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Password;
import model.User;

public class PasswordsDAOImpl implements PasswordsDAO{

	@Override
	public ArrayList<Password> getAllPasswords(User user) {
		ArrayList<Password> passwordList = new ArrayList<Password>();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try
		{
			connection = DAOUtilities.getConnection();
			
			String sql = "SELECT * FROM \"Passwords\" WHERE \"userName\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUser());
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Password p = new Password();
				p.setEntropy(rs.getDouble("entropy"));
				p.setLabel(rs.getString("label"));
				p.setRandomPassword(rs.getString("password"));
				p.setId(rs.getInt("passwordId"));
				
				passwordList.add(p);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return passwordList;
	}

	@Override
	public void savePassword(Password passwordToSave, User user) {
		Password save = passwordToSave;
		String userName = user.getUser();
		Connection connection = null;
		Statement statement = null;
		PreparedStatement stmt = null;
		int numberOfRows = 0;
		int success = 0;
		
		try
		{
			connection = DAOUtilities.getConnection();
			statement = connection.createStatement();

			String sql = "INSERT INTO \"Passwords\" VALUES (?,?,?,?,?)";
			String sql1 = "SELECT COUNT(*) FROM \"Passwords\"";
			
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next())
			{
				numberOfRows = rs.getInt("count");
			}
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numberOfRows+1);
			stmt.setString(2, passwordToSave.getLabel());
			stmt.setString(3, passwordToSave.getRandomPassword());
			stmt.setDouble(4, passwordToSave.getEntropy());
			stmt.setString(5, user.getUser());
			
			success = stmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
