import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		start();
		
	}
	public static void start()
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while(!input.equals("START"))
		{
			System.out.print("Enter START to begin: ");
			input = scanner.next();
			input = input.toUpperCase();
		}
		
		while(!input.equals("GUEST") && !input.equals("USER"))
		{
			System.out.print("\nUSER or GUEST: ");
			input = scanner.next();
			input = input.toUpperCase();
		}
		if(input.equals("GUEST"))
		{
			guestUtility(scanner, input);
		}
		else if(input.equals("USER"))
		{
			userUtility(scanner, input);
		}
	}
	public static void guestUtility(Scanner scanner, String input)
	{
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

					System.out.println("Label : " + generator.getLabel());
					System.out.println("Generated Password: " + random);
					System.out.printf("Entropy of Generated Password: %.2f\n", check.getBits());
					System.out.println();
					break;
				case "CHECK":
					System.out.print("Enter a password to check Entropy: ");
					input = scanner.next();
					Entropy checker = new Entropy(input);
					System.out.printf("Entropy of Password: %.2f\n", checker.getBits());
					System.out.println();
					break;
				default:
					break;
			}
		}
		System.out.println("Program Terminated");
	}
	public static void userUtility(Scanner scanner, String input)
	{
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
					System.out.print("Enter a Label for the Password: ");
					input = scanner.next();
					generator.setLabel(input);
					
					System.out.println("Label : " + generator.getLabel());
					System.out.println("Generated Password: " + random);
					System.out.printf("Entropy of Generated Password: %.2f\n", check.getBits());
					System.out.println();
					break;
				case "CHECK":
					System.out.print("Enter a password to check Entropy: ");
					input = scanner.next();
					Entropy checker = new Entropy(input);
					System.out.printf("Entropy of Password: %.2f\n", checker.getBits());
					System.out.println();
					break;
				default:
					break;
			}
		}
		System.out.println("Program Terminated");
	}

}
