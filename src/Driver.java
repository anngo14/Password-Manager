import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		while(!input.equals("START"))
		{
			System.out.print("Enter START to begin: ");
			input = scanner.next();
			input = input.toUpperCase();
		}
		while(!input.equals("END"))
		{
			System.out.print("Waiting for a command: ");
			input = scanner.next();
			input = input.toUpperCase();
			switch(input)
			{
				case "GENERATE":
					Password generator = new Password();
					String random = generator.getRandomPassword();
					Entropy check = new Entropy(random);

					System.out.println("Generated Password: " + random);
					System.out.println("Entropy of Generated Password: " + check.getBits());
					break;
				case "CHECK":
					System.out.print("Enter a password to check Entropy: ");
					input = scanner.next();
					Entropy checker = new Entropy(input);
					System.out.println("Entropy of Password: " + checker.getBits());
					break;
				default:
					break;
			}
		}
		System.out.println("Program Terminated");
		

	}

}
