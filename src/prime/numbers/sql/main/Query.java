package prime.numbers.sql.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Query {

	void execute(Connection connection, List<Integer> primes) throws SQLException;
}
