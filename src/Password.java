import java.util.ArrayList;

public class Password {

	private ArrayList<Character> lower = new ArrayList<Character>();
	private ArrayList<Character> upper = new ArrayList<Character>();
	private ArrayList<Character> special = new ArrayList<Character>();
	private ArrayList<Integer> number = new ArrayList<Integer>();
	private ArrayList<String> combinedPool = new ArrayList<String>();
	
	Entropy entropy;
	String randomPassword;
	String label;
	
	final int passwordLength = 32;
	final int poolSize = 94;
	
	public Password()
	{
		this.label = "unknown";
		this.randomPassword = "";
		this.entropy = new Entropy();
		
		lower = entropy.getLower();
		upper = entropy.getUpper();
		special = entropy.getSpecial();
		number = entropy.getNumber();
		combinePool();
		
		this.randomPassword = generatePassword();
	}
	public Password(String label)
	{
		this.label = label;
		this.randomPassword = "";
		this.entropy = new Entropy();
		
		lower = entropy.getLower();
		upper = entropy.getUpper();
		special = entropy.getSpecial();
		number = entropy.getNumber();
		combinePool();
		
		this.randomPassword = generatePassword();
	}
	
	public Password(String label, String password)
	{
		this.label = label;
		this.randomPassword = password;
		this.entropy = new Entropy(randomPassword);
		
		lower = entropy.getLower();
		upper = entropy.getUpper();
		special = entropy.getSpecial();
		number = entropy.getNumber();
		combinePool();
		
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
		String charToString = combinedPool.get((int) (Math.random() * ((poolSize) + 1)));
		return charToString;
	}
	
	public void combinePool()
	{
		ArrayList<Character> charPool = new ArrayList<Character>();
		charPool.addAll(lower);
		charPool.addAll(upper);
		charPool.addAll(special);

		for(Character c: charPool)
		{
			combinedPool.add(c.toString());
		}
		for(Integer i: number)
		{
			combinedPool.add(i.toString());
		}
		
	}
}
