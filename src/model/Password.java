package model;
import java.util.ArrayList;

public class Password {

	private ArrayList<String> combinedPool = new ArrayList<String>();
	
	Double entropy;
	String randomPassword;
	String label;
	Pool pool;
	
	final int passwordLength = 32;
	final int poolSize = 94;
	
	public Password()
	{
		this.label = "unknown";
		this.randomPassword = "";
		this.entropy = 0.0;
		this.pool = new Pool();
		combinedPool = pool.getCombined();
		
		this.randomPassword = generatePassword();
		cleansePassword();
		
	}
	public Password(String label)
	{
		this.label = label;
		this.randomPassword = "";
		this.entropy = 0.0;
		this.pool = new Pool();
		combinedPool = pool.getCombined();

		this.randomPassword = generatePassword();
		cleansePassword();
	}
	
	public Password(String label, String password)
	{
		this.label = label;
		this.randomPassword = password;
		this.entropy = 0.0;
		this.pool = new Pool();
		combinedPool = pool.getCombined();

		cleansePassword();
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
	
	public String generatePassword()
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
}
