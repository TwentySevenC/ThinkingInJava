package concurrency.basicThread;

//concurrency/BasicThread.java
//Thread start() 

public class BasicThreads {
	public static void main(String[] args) {
		Thread thread = new Thread(new LiftOff());
		thread.start();
		System.out.println("Wait for LiftOff run..");
	}
}
