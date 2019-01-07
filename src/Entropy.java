import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entropy {
	
	private final int lowerMin = 97;
	private final int lowerMax = 122;
	private final int upperMin = 65;
	private final int upperMax = 90;
	private final int specialMin = 33;
	private final int specialMax = 47;
	private final int lowerPool = 26;
	private final int specialPool = 32;
	private final int numberPool = 10;
	
	double bits;
	private ArrayList<Character> lower = new ArrayList<Character>();
	private ArrayList<Character> upper = new ArrayList<Character>();
	private ArrayList<Character> special = new ArrayList<Character>();
	private ArrayList<Integer> number = new ArrayList<Integer>();

	public Entropy()
	{
		initializeLower();
		initializeUpper();
		initializeSpecial();
		initializeNumber();
		bits = 0.0;
	}
	
	public Entropy(String password)
	{
		initializeLower();
		initializeUpper();
		initializeSpecial();
		initializeNumber();
		bits = calculateBits(password);
	}
	
	public double getBits()
	{
		return this.bits;
	}
	
	public ArrayList<Character> getLower()
	{
		return this.lower;
	}
	
	public ArrayList<Character> getUpper()
	{
		return this.upper;
	}
	
	public ArrayList<Character> getSpecial()
	{
		return this.special;
	}
	
	public ArrayList<Integer> getNumber()
	{
		return this.number;
	}
	
	public void initializeLower()
	{
		for(int i = lowerMin; i <= lowerMax; i++)
		{
			lower.add((char) i);
		}
	}
	
	public void initializeUpper()
	{
		for(int i = upperMin; i <= upperMax; i++)
		{
			upper.add((char) i);
		}
	}
	
	public void initializeSpecial()
	{
		List<Character> specialChar = Arrays.asList(':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '-', '`', '{', '|', '}', '~');
		for(int i = specialMin; i <= specialMax; i++)
		{
			special.add((char) i);
		}
		special.addAll(specialChar);
	}
	
	public void initializeNumber()
	{
		for(int i = 0; i <= 9; i++)
		{
			number.add(i);
		}
	}
	
	public double calculateBits(String password)
	{
		int pool = determinePool(password);
		double entropyBits = 0.0;
		
		entropyBits = Math.log(Math.pow(pool, password.length())) / Math.log(2);
		return entropyBits;
	}
	
	public int determinePool(String password)
	{
		ArrayList<Character> passwordList = convertToList(password);
		int poolOfChar = 0;
		while(passwordList.size() > 0)
		{
			poolOfChar += addPool(passwordList);
		}
		return poolOfChar;
	}
	
	public int addPool(ArrayList<Character> passwordList)
	{
		int initial = 0;
		if(lower.contains(passwordList.get(0)))
		{
			initial = lowerPool;
			passwordList.removeAll(lower);
		}
		else if(upper.contains(passwordList.get(0)))
		{
			initial = lowerPool;
			passwordList.removeAll(upper);
		}
		else if(special.contains(passwordList.get(0)))
		{
			initial = specialPool;
			passwordList.removeAll(special);
		}
		else 
		{
			initial = numberPool;
			passwordList.removeAll(number);
		}
		
		return initial;
	}
	
	public ArrayList<Character> convertToList(String password)
	{
		ArrayList<Character> list = new ArrayList<Character>();
		for(char c: password.toCharArray())
		{
			list.add(c);
		}
		return list;
	}
}
