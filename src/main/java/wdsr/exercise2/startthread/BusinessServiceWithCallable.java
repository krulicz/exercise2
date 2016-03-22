package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.List;

public class BusinessServiceWithCallable {
	private final ExecutorService executorService;
	private final NumericHelper helper;

	public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
		this.executorService = executorService;
		this.helper = helper;
	}

	/**
	 * Calculates a sum of 100 random numbers. Random numbers are returned by
	 * helper.nextRandom method. Each random number is calculated
	 * asynchronously.
	 * 
	 * @return sum of 100 random numbers.
	 */
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {
		int result = 0;
		// create a list to hold the Future object associated with Callable
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		// Create MyCallable instance
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int result = helper.nextRandom();
				return result;
			}
		};
		for (int i = 0; i < 100; i++) {
			// submit Callable tasks to be executed by thread pool
			Future<Integer> future = executorService.submit(callable);
			// add Future to the list, we can get return value using Future
			list.add(future);

		}
		for (Future<Integer> future : list) {
			result = result + future.get();
		}
		// Task:
		// 1. create 100 Callable objects that invoke helper.nextRandom in their
		// call() method.
		// 2. submit all Callable objects to executorService
		// (executorService.submit or executorService.invokeAll)
		// 3. sum up the results - each random number can be retrieved using
		// future.get() method.
		// 4. return the computed result.

		return result;
	}
}
