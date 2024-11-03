package prime.numbers.sql.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SQLOperations {
	private final List<Integer> primes;
	private final Connection connection;

	SQLOperations(List<Integer> primes, Connection connection) {
		this.primes = primes;
		this.connection = connection;
	}

	List<Integer> readTable() throws SQLException {
		deleteTable();
		insertTable();
		List<Integer> primesFromTable = new ArrayList<>();
		System.out.printf("%s%n", "Reading from table");
		try (var ps = connection.prepareStatement("SELECT prime_numbers from prime_numbers"); var rs = ps.executeQuery()) {
			primesFromTable = new ArrayList<>();
			while (rs.next()) {
				int prime = rs.getInt("prime_numbers");
				primesFromTable.add(prime);
			}
		}
		System.out.printf("Reading has been performed!%n%n");
		return primesFromTable;
	}

	private void deleteTable() throws SQLException {
		System.out.printf("%n%s%n", "Deletion of table");
		try (var ps = connection.prepareStatement("DELETE from prime_numbers")) {
			ps.executeUpdate();
		}
		System.out.printf("Deletion has been performed!%n%n");
	}

	private void insertTable() throws SQLException {
		System.out.printf("%s%n", "Insertion to table");
		connection.setAutoCommit(false);
		try (var ps = connection.prepareStatement("INSERT INTO prime_numbers (prime_numbers) VALUES (?)")) {
			for (int prime : primes) {
				ps.setInt(1, prime);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
		}
		System.out.printf("Insertion has been performed!%n%n");
	}
}
