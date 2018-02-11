/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月13日 下午6:00:32 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */

class Worker extends Thread {
	String workerName;
	int workTime;
	CountDownLatch latch;

	public Worker(String workerName, int workTime, CountDownLatch latch) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}

	public void run() {
		try {
			System.out
					.println("Worker " + workerName + " do work begin at " + CountDownLatchTest.sdf.format(new Date()));
			doWork();// 工作了
			System.out.println(
					"Worker " + workerName + " do work complete at " + CountDownLatchTest.sdf.format(new Date()));
		} finally {
			latch.countDown();// 工人完成工作，计数器减一
		}

	}

	private void doWork() {
		try {
			Thread.sleep(workTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class CountDownLatchTest {
	public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
		Worker worker1 = new Worker("1", 5000, latch);
		Worker worker2 = new Worker("2", 8000, latch);
		worker1.start();//
		worker2.start();//
		latch.await();// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
	}

}
