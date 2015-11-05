package basicThread;

import java.util.concurrent.TimeUnit;

//: concurrency/DaemonDontFinally.javaß
//Daemon thread don't run the finally clause

class SimpleDaemon implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Daemon started..");
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("Interrupted");
		}finally {
			System.out.println("It always run in finally?");
		}
	}
	
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) {
		Thread thread = new Thread(new SimpleDaemon());
		thread.setDaemon(true);
		thread.start();
	}
}
