package prime.numbers.sql.solution1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class InsertQuery extends AbstractQuery {

	InsertQuery() {
		super("Insertion to table", "INSERT INTO prime_numbers (prime_numbers) VALUES (?)");
	}

	@Override
	public void execute(Connection connection, List<Integer> primes) throws SQLException {
		System.out.printf("%s%n", getLabel());
		connection.setAutoCommit(false);
		try (var ps = connection.prepareStatement(getPstmnt())) {
			for (int prime : primes) {
				ps.setInt(1, prime);
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
		}
		System.out.printf("Query has performed!%n%n");
	}
}
