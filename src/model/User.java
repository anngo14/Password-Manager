package model;

public class User {

	private String userName;
	private String password;
	
	public User()
	{
		userName = "";
		password = "";
	}
	public String getUser()
	{
		return this.userName;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setUser(String name)
	{
		userName = name;
	}
	public void setPassword(String pass)
	{
		password = pass;
	}
}
