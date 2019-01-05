
public class Password {

	Entropy entropy;
	String randomPassword;
	String label;
	
	public Password()
	{
		this.label = "unknown";
		this.randomPassword = generatePassword();
		this.entropy = new Entropy(randomPassword);
	}
	public Password(String label)
	{
		this.label = label;
		this.randomPassword = generatePassword();
		this.entropy = new Entropy(randomPassword);
	}
	
	public Password(String label, String password)
	{
		this.label = label;
		this.randomPassword = password;
		this.entropy = new Entropy(randomPassword);
	}
	
	public double getEntropy()
	{
		return this.entropy.getBits();
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
	
	public String generatePassword()
	{
		String random = "";
		return random;
	}
}
