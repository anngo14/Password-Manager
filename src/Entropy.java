
public class Entropy {
	
	double bits;

	public Entropy()
	{
		bits = 0.0;
	}
	
	public Entropy(String password)
	{
		bits = calculateBits(password);
	}
	
	public double getBits()
	{
		return this.bits;
	}
	
	public double calculateBits(String password)
	{
		double entropyBits = 0.0;
		return entropyBits;
	}
}
