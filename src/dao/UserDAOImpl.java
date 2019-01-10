package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAOImpl implements UserDAO{

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection connection = null;
		Statement stmt = null;
		
		try
		{
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			String sql = "SELECT * FROM \"Users\"";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				User u = new User();
				u.setUser(rs.getString("userName"));
				u.setPassword(rs.getString("userPassword"));
				userList.add(u);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void registerUser(User userToSave) {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO \"Users\" VALUES (?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, userToSave.getUser());
			stmt.setString(2, userToSave.getPassword());
			
			success = stmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void resetUser(User userToReset) {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE \"Users\" SET \"userPassword\" = ? WHERE \"userName\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, userToReset.getPassword());
			stmt.setString(2, userToReset.getUser());
			
			success = stmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean checkUser(User userToCheck) {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT \"userPassword\" FROM \"Users\" WHERE \"userName\" = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,  userToCheck.getUser());
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				String pass = rs.getString("userPassword");
				if(userToCheck.getPassword().equals(pass))
				{
					return true;
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
