package prime.numbers.sql.general.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Deque;

public class PrimesWriter {
	private static final File FILE = new File("D:\\Development\\Workspace\\PrimeNumbersMySQL\\primes.txt");
	private Deque<Integer> reversedPrimes;

	public PrimesWriter(Deque<Integer> reversedPrimes) {
		this.reversedPrimes = reversedPrimes;
	}

	public void writeToFile() {
		try (var printWriter = new PrintWriter(FILE)) {
			int spaceLength = reversedPrimes.size() / 10;
			printWriter.printf("%6s","Prime numbers:%n");
			for (Integer prime : reversedPrimes) {
				printWriter.printf("%6d%n", prime);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Please check your filesystem!");
		}
	}
}
