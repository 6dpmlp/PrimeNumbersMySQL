package prime.numbers.sql.solution1;

import java.util.Deque;

import prime.numbers.sql.general.classes.PrimesWriter;
import prime.numbers.sql.main.Query;

abstract class AbstractQuery implements Query {

	private final String label;
	private final String pstmnt;

	AbstractQuery(String label, String pstmnt) {
		this.label = label;
		this.pstmnt = pstmnt;
	}

	void printPrimes(Deque<Integer> reversedPrimes) {
		new PrimesWriter(reversedPrimes).writeToFile();
	}

	String getLabel() {
		return label;
	}

	String getPstmnt() {
		return pstmnt;
	}
}
