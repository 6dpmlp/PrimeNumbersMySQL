package prime.numbers.sql.main;

import prime.numbers.sql.general.classes.PrimesCreator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;

import prime.numbers.sql.general.classes.GeneralInfo;
import prime.numbers.sql.general.classes.UserInput;
import prime.numbers.sql.solution1.QueryServicePointImpl;

public class Main {

	private static final Queue<Query> QUERIES = QueryServicePointImpl.getInstance().getQueries();

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		var genInfo = new GeneralInfo();
		genInfo.presentProgram();
		var userInput = new UserInput().askForUserInput();
		var primes = new PrimesCreator(userInput).getPrimes();
		try {
			Connection connection = getConnection();
			for (Query query : QUERIES) {
				query.execute(connection, primes);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		genInfo.sayGoodbye();
	}

	private Connection getConnection() throws SQLException, IOException {
		try (var is = getClass().getResourceAsStream("/connection.properties")) {
			if (is == null) {
				throw new IOException("Properties file not found!");
			}
			var properties = new Properties();
			properties.load(is);
			return DriverManager.getConnection(properties.getProperty("url"), properties);
		}
	}
}
