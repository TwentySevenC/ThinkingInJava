package concurrency.shareLimitedResource;

//:concurrency/SerialNumberGenerator.java
//A serial number generator class 

public class SerialNumberGenerator {
	private static volatile int serial = 0;
	
	public synchronized static int nextSerialNumber(){
		return ++serial;
	}
}
