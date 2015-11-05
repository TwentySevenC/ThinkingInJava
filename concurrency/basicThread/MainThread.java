package basicThread;

//:concurrency MainThread.java

/**
 * 此处的LiftOff只是作为一个类，调用其run（）方法而已，只有一个主线程
 * @author liujian
 *
 */

public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
		System.err.println("Wait for Runnable.run()..");
	}
}
