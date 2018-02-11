package concurrency;

import java.util.concurrent.atomic.AtomicInteger;
//: concurrency/SimpleMicroBenchmark.java
// The dangers of microbenchmarking.
import java.util.concurrent.locks.*;

abstract class Incrementable {
	protected long counter = 0;

	public abstract void increment();
}

class SynchronizingTest extends Incrementable {
	public synchronized void increment() {
		++counter;
	}
}

class LockingTest extends Incrementable {
	private Lock lock = new ReentrantLock();

	public void increment() {
		lock.lock();
		try {
			++counter;
		} finally {
			lock.unlock();
		}
	}
}

class AtomicingTest extends Incrementable {
	private static AtomicInteger i = new AtomicInteger();

	public void increment() {
		i.getAndIncrement();
	}
}

public class SimpleMicroBenchmark {
	static long test(Incrementable incr) {
		long start = System.nanoTime();
		for (long i = 0; i < 10000000L; i++)
			incr.increment();
		return System.nanoTime() - start;
	}

	public static void main(String[] args) {
		long synchTime = test(new SynchronizingTest());
		long lockTime = test(new LockingTest());
		long atoMicTime = test(new AtomicingTest());
		System.out.printf("synchronized: %1$10d\n", synchTime);
		System.out.printf("Lock:         %1$10d\n", lockTime);
		System.out.printf("Atomic:       %1$10d\n", atoMicTime);
		System.out.printf("Lock/synchronized = %1$.3f", (double) lockTime / (double) synchTime);
	}
}
/*
 * Output: (75% match) synchronized: 244919117 Lock: 939098964 Lock/synchronized
 * = 3.834
 */// :~
