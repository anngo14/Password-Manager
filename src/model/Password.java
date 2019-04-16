package model;
import java.util.ArrayList;

public class Password {

	private ArrayList<String> combinedPool = new ArrayList<String>();
	
	Double entropy;
	String randomPassword;
	String label;
	String link;
	int passwordId;
	Pool pool;
	
	final int defaultLength = 32;
	final int poolSize = 94;
	
	public Password()
	{
		
		this.label = "UNKNOWN";
		this.randomPassword = "";
		this.entropy = 0.0;
		this.pool = new Pool();
		this.passwordId = 0;
		this.link = "N/A";
		combinedPool = pool.getCombined();
		
		this.randomPassword = generatePassword(defaultLength);
		cleansePassword();
		
	}
	public Password(String label)
	{
		this.label = label;
		this.randomPassword = "";
		this.entropy = 0.0;
		this.pool = new Pool();
		combinedPool = pool.getCombined();
		this.link = "N/A";
		this.randomPassword = generatePassword(defaultLength);
		cleansePassword();
	}
	
	public Password(String label, String password)
	{
		this.label = label;
		this.randomPassword = password;
		this.entropy = 0.0;
		this.pool = new Pool();
		combinedPool = pool.getCombined();
		this.link = "N/A";
		cleansePassword();
	}
	
	public int getId()
	{
		return this.passwordId;
	}
	
	public double getEntropy()
	{
		return this.entropy;
	}
	
	public String getRandomPassword()
	{
		return this.randomPassword;
	}
	
	public String getLabel()
	{
		return this.label;
	}
	public String getLink()
	{
		return this.link;
	}
	public int getPasswordId()
	{
		return this.passwordId;
	}
	public void setId(int passId)
	{
		this.passwordId = passId;
	}
	
	public void setRandomPassword(String password)
	{
		this.randomPassword = password;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setEntropy(double bits)
	{
		this.entropy = bits;
	}
	public void setLink(String newLink)
	{
		this.link = newLink;
	}
	public void setPasswordId(int id)
	{
		this.passwordId = id;
	}
	public String generatePassword(int passwordLength)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < passwordLength; i++)
		{
			list.add(randomize());
		}
		String random = list.toString();
		return random;
	}
	
	public String randomize()
	{
		int randomIndex = (int) (Math.random() * (poolSize));
		String charToString = combinedPool.get(randomIndex);
		return charToString;
	}
		
	public void cleansePassword()
	{
		this.randomPassword = this.randomPassword.replaceAll("\\,\\s", "");
		this.randomPassword = this.randomPassword.substring(1, this.randomPassword.length() - 1);
	}
	public String toString()
	{
		String output = "ID: " + this.passwordId + "\nLabel: " + this.label + "\nPassword: " + this.randomPassword + "\nEntropy: " + this.entropy + "\nLink: " + this.link;
		return output;
	}
}
