package prime.numbers.sql.general.classes;

public class GeneralInfo {

	public static final long MAX_VALUE = 10_000_000_000l;

	public void presentProgram() {
		System.out.printf("""
				Welcome to the prime number generator program!

				First, it asks for a positive integer from the user, the largest number can be %,d.
				Then it will generate the prime numbers until this number using all available processors with multiple tasks.
				Then it creates an SQL connection and save the numbers into the prime_numbers table.
				Finally, in reverse order the program writes the numbers into a file.%n
				""", MAX_VALUE);
	}

	public void sayGoodbye() {
		System.out.printf("Thank you for using this program!%nNow you have the numbers listed in primes.txt.%nHave a nice day!%n");
	}

	public static long getMaxValue() {
		return MAX_VALUE;
	}
}
