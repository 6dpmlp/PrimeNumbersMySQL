package prime.numbers.sql.general.classes;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PrimesCreator {
	private final int maxValue;
	private final List<Integer> primes;

	public PrimesCreator(int maxValue) {
		this.maxValue = maxValue;
		this.primes = calculatePrimes();
	}

	private List<Integer> calculatePrimes() {
		return Collections.synchronizedList(//
				IntStream.rangeClosed(2, maxValue)//
						.parallel()//
						.boxed()//
						.filter(this::isPrime)//
						.toList());
	}

	private boolean isPrime(Integer number) {
		if (number == 2) {
			return true;
		}
		if (number % 2 == 0) {
			return false;
		}
		int root = (int) Math.sqrt(number);
		for (int i = 3; i <= root; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
}
