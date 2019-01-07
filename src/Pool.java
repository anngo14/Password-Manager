import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pool {

	private final int lowerMin = 97;
	private final int lowerMax = 122;
	private final int upperMin = 65;
	private final int upperMax = 90;
	private final int specialMin = 33;
	private final int specialMax = 47;
	
	private ArrayList<Character> lower = new ArrayList<Character>();
	private ArrayList<Character> upper = new ArrayList<Character>();
	private ArrayList<Character> special = new ArrayList<Character>();
	private ArrayList<Character> number = new ArrayList<Character>();
	private ArrayList<String> combinedPool = new ArrayList<String>();
	
	public Pool()
	{
		initializeLower();
		initializeUpper();
		initializeSpecial();
		initializeNumber();
		combinePool();
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
	
	public ArrayList<Character> getNumber()
	{
		return this.number;
	}
	
	public ArrayList<String> getCombined()
	{
		return this.combinedPool;
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
		List<Character> numeric = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
		number.addAll(numeric);	
	}
	
	public void combinePool()
	{
		ArrayList<Character> charPool = new ArrayList<Character>();
		charPool.addAll(lower);
		charPool.addAll(upper);
		charPool.addAll(special);
		charPool.addAll(number);

		for(Character c: charPool)
		{
			combinedPool.add(c.toString());
		}
		
	}
}
