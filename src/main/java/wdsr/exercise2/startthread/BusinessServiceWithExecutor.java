package wdsr.exercise2.startthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BusinessServiceWithExecutor {
	private NumericHelper helper;
	private final ExecutorService executorService;
	
	public BusinessServiceWithExecutor(NumericHelper helper) {
		this.executorService = Executors.newFixedThreadPool(1);
		this.helper = helper;
	}

	/**
	 * Calculates Fibonacci number asynchronously and invokes the callback when result is available.
	 * This method returns immediately. 
	 * @param n Which Fibonacci number should be computed.
	 * @param callback Callback to be invoked when Fibonacci number is found.
	 */
	public void computeFibonacci(int n, FibonacciCallback callback) {
		Callable<Long> callable = new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				long value = helper.findFibonacciValue(n);
				callback.fibonacciComputed(value);
				return value;
			}
		};
		executorService.submit(callable);
		
		// TODO Task: execute the logic below using java.util.concurrent.ExecutorService
		// The ExecutorService should be declared as a field, not a local variable.
		
	}
}
