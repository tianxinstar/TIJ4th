package concurrency;

//: concurrency/DaemonsDontRunFinally.java
// Daemon threads don't run the finally clause
import java.util.concurrent.*;
import static net.mindview.util.Print.*;

class ADaemon implements Runnable {
	public void run() {
		try {
			print("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			print("Exiting via InterruptedException");
		} finally {
			print("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);// 如果设置true，main执行完，后台进程就将结束，finally不会执行
		t.start();

		/*
		 * ExecutorService exec = Executors.newCachedThreadPool();
		 * exec.execute(t); exec.shutdown();
		 */
		// exec 保证线程走完
	}
}
/*
 * Output: Starting ADaemon
 */// :~
