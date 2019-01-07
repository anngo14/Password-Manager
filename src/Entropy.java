import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entropy {
	
	
	private final int lowerPool = 26;
	private final int specialPool = 32;
	private final int numberPool = 10;
	
	private ArrayList<Character> lower = new ArrayList<Character>();
	private ArrayList<Character> upper = new ArrayList<Character>();
	private ArrayList<Character> special = new ArrayList<Character>();
	private ArrayList<Character> number = new ArrayList<Character>();
	private ArrayList<Character> passwordList = new ArrayList<Character>();
	double bits;
	Pool pool;
	
	public Entropy()
	{	
		pool = new Pool();
		bits = 0.0;
		lower = pool.getLower();
		upper = pool.getUpper();
		special = pool.getSpecial();
		number = pool.getNumber();
	}
	
	public Entropy(String password)
	{
		pool = new Pool();
		lower = pool.getLower();
		upper = pool.getUpper();
		special = pool.getSpecial();
		number = pool.getNumber();
		passwordList = convertToList(password);
		bits = calculateBits(password);
	}
	
	public double getBits()
	{
		return this.bits;
	}
	
	public void setBits(double bits)
	{
		this.bits = bits;
	}
	public double calculateBits(String password)
	{
		passwordList = convertToList(password);
		int pool = determinePool(password);
		double entropyBits = 0.0;
		
		entropyBits = Math.log(Math.pow(pool, password.length())) / Math.log(2);
		return entropyBits;
	}
	
	public int determinePool(String password)
	{
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
