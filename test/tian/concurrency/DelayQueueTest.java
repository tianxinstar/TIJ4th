/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.concurrency;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static net.mindview.util.Print.print;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月14日 上午10:22:00 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
class DelayTask implements Runnable, Delayed {
	private final long trigger;
	private String name;

	public DelayTask(int delayInMilliseconds, String name) {
		trigger = System.nanoTime() + NANOSECONDS.convert(delayInMilliseconds, MILLISECONDS);
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "_" + this);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayTask that = (DelayTask) o;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DelayTask [trigger=" + trigger + ", name=" + name + "]";
	}

}

class DelayTaskConsumer implements Runnable {
	private DelayQueue<DelayTask> queue = null;

	public DelayTaskConsumer(DelayQueue<DelayTask> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				DelayTask dt = queue.take(); // Run task with the current thread
				dt.run();
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		print("Finished DelayedTaskConsumer");

	}

}

public class DelayQueueTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayTask> queue = new DelayQueue<DelayTask>();
		for (int i = 0; i < 20; i++) {
			int name = i + 200;
			queue.put(new DelayTask(name, "延迟" + name));
		}
		// for (int i = 0; i < 1; i++) {
		exec.execute(new DelayTaskConsumer(queue));
		// }
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
