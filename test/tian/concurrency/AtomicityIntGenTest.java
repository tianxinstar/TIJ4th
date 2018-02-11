/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月12日 下午2:13:08 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class AtomicityIntGenTest implements Runnable {

	private static final AtomicInteger i = new AtomicInteger(0);

	public int increase() {
		return i.addAndGet(1);
	}

	public void run() {
		int i = increase();
		print(i);
	}

	public static void print(int i) {
		System.out.println("thread " + Thread.currentThread().getName() + " " + i);
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++)
			exec.execute(new AtomicityIntGenTest());
		exec.shutdown();
	}
}
