package prime.numbers.sql.solution1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ReadQuery extends AbstractQuery {

	ReadQuery() {
		super("Reading data from SQL", "SELECT prime_numbers from prime_numbers");
	}

	@Override
	public void execute(Connection connection, List<Integer> primes) throws SQLException {
		System.out.printf("%s%n", getLabel());
		Deque<Integer> reversedPrimes = new ArrayDeque<>();
		try (var ps = connection.prepareStatement(getPstmnt()); var rs = ps.executeQuery()) {
			while (rs.next()) {
				int prime = rs.getInt("prime_numbers");
				reversedPrimes.addFirst(prime);
			}
		}
		printPrimes(reversedPrimes);
		System.out.printf("Query has performed!%n%n");
	}
}
