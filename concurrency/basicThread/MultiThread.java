package concurrency.basicThread;

//concurrency MultiThread.java

/**
 * 包括主线程在内的10个线程是抢占式（抢占cpu资源）的，随机
 * @author liujian
 *
 */

public class MultiThread {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			new Thread(new LiftOff()).start();
		}
		
		System.out.println("Wait for threads started..");
	}
}
