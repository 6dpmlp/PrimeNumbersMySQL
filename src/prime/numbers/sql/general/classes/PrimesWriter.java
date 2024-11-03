package prime.numbers.sql.general.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class PrimesWriter {
	private static final File FILE = new File("D:\\Development\\Workspace\\PrimeNumbersMySQL\\primes.txt");
	private final List<Integer> reversedPrimes;

	public PrimesWriter(List<Integer> reversedPrimes) {
		this.reversedPrimes = reversedPrimes;
		Collections.reverse(reversedPrimes);
	}

	// utilisation of PrintWriter as it is not prone to IOException
	public void writeToFile() {
		try (var printWriter = new PrintWriter(FILE)) {
			printWriter.printf("There are %d prime numbers until %,d, which are the following ones:%n", reversedPrimes.size(),
					GeneralInfo.getMaxValue());
			for (Integer prime : reversedPrimes) {
				printWriter.printf("      %,d%n", prime);
			}
		} catch (FileNotFoundException fne) {
			System.out.println("Please check your filesystem!");
		}
	}
}
