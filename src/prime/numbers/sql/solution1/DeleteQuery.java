package prime.numbers.sql.solution1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class DeleteQuery extends AbstractQuery {

	DeleteQuery() {
		super("Deletion of table", "DELETE from prime_numbers");
	}

	@Override
	public void execute(Connection connection, List<Integer> primes) throws SQLException {
		System.out.printf("%n%s%n", getLabel());
		try (var ps = connection.prepareStatement(getPstmnt())) {
			ps.executeUpdate();
		}
		System.out.printf("Query has performed!%n%n");
	}
}