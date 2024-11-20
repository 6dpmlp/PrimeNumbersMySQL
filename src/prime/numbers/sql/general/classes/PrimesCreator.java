package prime.numbers.sql.general.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class PrimesCreator {
	private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors();
	private static final int THRESHOLD = 100_000_000;
	private final int taskNum;
	private final long maxValue;
	private final long root;

	public PrimesCreator(long maxValue) {
		this.maxValue = maxValue;
		taskNum = maxValue > THRESHOLD ? 1000 : 100;
		root = (long) Math.sqrt(maxValue);
	}

	public long[] calculatePrimes() {
		long[] basePrimes = new BasePrimesCreator(root + 1).calculate();
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
		List<SubTask> subTasks = createSubTasks(basePrimes);
		List<Future<long[]>> futures = subTasks.stream().map(executor::submit).toList();
		executor.shutdown();
		return getPrimes(basePrimes, futures);
	}

	private List<SubTask> createSubTasks(long[] basePrimes) {
		List<SubTask> subTasks = new ArrayList<>();
		long range = (maxValue - root) / taskNum;
		for (int i = 0; i < taskNum; i++) {
			long start = root + 1 + i * range;
			long end = i == taskNum - 1 ? maxValue : root + 1 + (i + 1) * range;
			subTasks.add(new SubTask(start, end, basePrimes));
		}
		return subTasks;
	}

	private long[] getPrimes(long[] basePrimes, List<Future<long[]>> futures) {
		return LongStream.concat(LongStream.of(basePrimes), //
				futures.stream().map(this::getTask).flatMapToLong(LongStream::of)).toArray();
	}

	private long[] getTask(Future<long[]> primeRange) {
		try {
			return primeRange.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Check your code, should not be a problem here!");
		}
	}
}