package prime.numbers.sql.solution1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import prime.numbers.sql.main.Query;
import prime.numbers.sql.main.QueryServicePoint;

public class QueryServicePointImpl implements QueryServicePoint {

	private static final QueryServicePointImpl INSTANCE = new QueryServicePointImpl();

	private QueryServicePointImpl() {

	}

	@Override
	public Queue<Query> getQueries() {
		return new LinkedList<>(List.of(new DeleteQuery(), new InsertQuery(), new ReadQuery()));
	}

	public static QueryServicePointImpl getInstance() {
		return INSTANCE;
	}
}
