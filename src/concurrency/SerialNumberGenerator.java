package concurrency;

//: concurrency/SerialNumberGenerator.java

public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++; // Not thread-safe
	}
	
	public synchronized static int nextSerialNumberSafe() {
		return serialNumber++; // thread-safe
	}
} /// :~
