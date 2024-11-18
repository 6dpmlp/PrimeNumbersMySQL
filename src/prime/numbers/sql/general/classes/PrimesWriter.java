package prime.numbers.sql.general.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PrimesWriter {
	private static final File FILE = new File("primes.txt");
	private final long[] primes;
	private final long userInput;

	public PrimesWriter(long[] primes, long userInput) {
		this.primes = reverse(primes);
		this.userInput = userInput;
	}

	private long[] reverse(long[] primes) {
		List<Long> longList = LongStream.of(primes).mapToObj(i -> i).collect(Collectors.toList());
		Collections.reverse(longList);
		return longList.stream().mapToLong(Long::valueOf).toArray();
	}

	// utilisation of PrintWriter as it is not prone to IOException
	public void writeToFile() {
		try (var printWriter = new PrintWriter(FILE)) {
			printWriter.printf("There are %d prime numbers until %,d, which are the following ones:%n", primes.length, userInput);
			for (long prime : primes) {
				printWriter.printf("      %,d%n", prime);
			}
		} catch (FileNotFoundException fne) {
			System.out.println("Please check your filesystem!");
		}
	}
}
