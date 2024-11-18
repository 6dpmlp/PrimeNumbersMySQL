package prime.numbers.sql.general.classes;

import java.util.stream.LongStream;

record BasePrimesCreator(long limit) {

	long[] calculate() {
		return LongStream.concat(LongStream.of(2l, 3L), //
				LongStream.iterate(5L, l -> l < limit, l -> l + 2).filter(this::isPrime)//
		).toArray();
	}

	private boolean isPrime(long number) {
		for (long i = 3L; i * i < number; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
