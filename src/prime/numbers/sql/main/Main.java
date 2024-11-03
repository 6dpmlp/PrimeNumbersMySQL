package prime.numbers.sql.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import prime.numbers.sql.general.classes.GeneralInfo;
import prime.numbers.sql.general.classes.PrimesCreator;
import prime.numbers.sql.general.classes.PrimesWriter;
import prime.numbers.sql.general.classes.UserInput;

public class Main {

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
			var operations = new SQLOperations(primes, connection);
			List<Integer> primesRead = operations.readTable();
			new PrimesWriter(primesRead).writeToFile();
			genInfo.sayGoodbye();
		} catch (SQLException se) {
			System.out.println("There is an issue with the SQL commands!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
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
