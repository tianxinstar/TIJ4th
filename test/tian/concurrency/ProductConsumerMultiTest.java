/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月13日 下午3:20:27 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */

class Car {
	private static AtomicInteger serialNo = new AtomicInteger();
	private String id;

	public Car() {
		id = String.valueOf(serialNo.getAndIncrement());
	}

	public String getId() {
		return "CAR-" + id;
	}
}

class Producter implements Runnable {// 一个生产者

	private int carMaxSize = 30;
	private BlockingQueue<Car> productQueue;

	public Producter(BlockingQueue<Car> productQueue, int carMaxSize) {
		this.productQueue = productQueue;
		this.carMaxSize = carMaxSize;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted() && productQueue.size() < carMaxSize) {
				Car car = new Car();
				productQueue.add(car);
				System.out.println("car is product: " + car.getId() + " 库存= " + productQueue.size());
				TimeUnit.MILLISECONDS.sleep(100);// 0.2秒生成一辆车
			}
			System.out.println("product car " + carMaxSize + ", stop");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {// 多个消费者
	private BlockingQueue<Car> productQueue;

	public Consumer(BlockingQueue<Car> productQueue) {
		this.productQueue = productQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted() && productQueue.size() > 0) {
				System.out.println("car is Consumer: " + productQueue.take().getId() + " 还剩几辆= " + productQueue.size()
						+ " " + Thread.currentThread().getName());
				TimeUnit.MILLISECONDS.sleep(200);// 1.2秒消费一辆车
			}
			System.out.println("consume car is 0, stop");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ProductConsumerMultiTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Car> productQueue = new LinkedBlockingQueue<Car>();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Producter(productQueue, 23));
		// for (int i = 0; i < 3; i++) {
		exec.execute(new Consumer(productQueue));
		// }
		exec.shutdown();
	}
}
