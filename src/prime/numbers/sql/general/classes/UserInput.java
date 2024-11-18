package prime.numbers.sql.general.classes;

import java.util.Scanner;

public class UserInput {

	public static final long MAX_VALUE = GeneralInfo.getMaxValue();

	public long askForUserInput() {
		long userInput = Long.MIN_VALUE;
		try (var scn = new Scanner(System.in);) {
			do {
				System.out.printf("Please enter a positive integer not bigger than %,d: ", MAX_VALUE);
				try {
					userInput = Long.parseLong(scn.nextLine().strip());
				} catch (NumberFormatException nfe) {
				}
			} while (userInput < 0 || userInput > MAX_VALUE);
			return userInput;
		}
	}
}
