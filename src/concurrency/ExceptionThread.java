package concurrency;

//: concurrency/ExceptionThread.java
// {ThrowsException}
import java.util.concurrent.*;

public class ExceptionThread implements Runnable {
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		// 1.
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
		// 2.
		Thread t = new Thread(new ExceptionThread());
		t.start();
	}
} /// :~
