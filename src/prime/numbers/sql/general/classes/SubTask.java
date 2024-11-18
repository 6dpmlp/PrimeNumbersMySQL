package prime.numbers.sql.general.classes;

import java.util.concurrent.Callable;
import java.util.stream.LongStream;

record SubTask(long start, long finish, long[] basePrimes) implements Callable<long[]> {
	@Override
	public long[] call() {
		return LongStream.iterate(start, l -> l < finish, l -> l + 2)//
				.filter(this::isPrime)//
				.toArray();
	}

	private boolean isPrime(long number) {
		for (long prime : basePrimes) {
			if (prime * prime > number) {
				break;
			}
			if (number % prime == 0) {
				return false;
			}
		}
		return true;
	}
}
