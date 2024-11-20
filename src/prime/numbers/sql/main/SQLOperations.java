package prime.numbers.sql.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SQLOperations {
	private final long[] primes;
	private final Connection connection;

	SQLOperations(long[] primes, Connection connection) {
		this.primes = primes;
		this.connection = connection;
	}

	long[] readTable() throws SQLException {
		deleteTable();
		insertTable();
		System.out.printf("%s%n", "Reading from table is in progress...");
		List<Long> primesFromTable = new ArrayList<>();
		try (var ps = connection.prepareStatement("SELECT prime_numbers from prime_numbers"); var rs = ps.executeQuery()) {
			while (rs.next()) {
				Long prime = rs.getLong("prime_numbers");
				primesFromTable.add(prime);
			}
		}
		System.out.printf("Reading has been performed!%n%n");
		return primesFromTable.stream().mapToLong(Long::valueOf).toArray();
	}

	private void deleteTable() throws SQLException {
		System.out.printf("%n%s%n", "Deletion of table is in progress...");
		try (var ps = connection.prepareStatement("DELETE from prime_numbers WHERE TRUE")) {
			ps.executeUpdate();
		}
		System.out.printf("Deletion has been performed!%n%n");
	}

	private void insertTable() throws SQLException {
		System.out.printf("%s%n", "Insertion to table is in progress...");
		connection.setAutoCommit(false);
		final int batchSize = 10_000;
		int count = 1;
		try (var ps = connection.prepareStatement("INSERT INTO prime_numbers (prime_numbers) VALUES (?)")) {
			for (Long prime : primes) {
				ps.setLong(1, prime);
				ps.addBatch();
				if (count++ % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			connection.commit();
		}
		connection.setAutoCommit(true);
		System.out.printf("Insertion has been performed!%n%n");
	}
}
