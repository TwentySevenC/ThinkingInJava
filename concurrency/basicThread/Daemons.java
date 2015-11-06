package concurrency.basicThread;


import java.util.concurrent.TimeUnit;

//:concurrency/Daemons.java

//Daemon threads spawn other daemon threads
//后台线程所创立的线程会自动成为后台线程

class Daemon implements Runnable{
	Thread [] threads = new Thread[10];
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			threads[i] = new Thread(new DaemoSpawn());
			System.out.println("DaemonSpawn " + i + " started.");
		}
		
		for(int i = 0; i < 10; i++){
			System.out.println(threads[i] + " is Daemon: " + threads[i].isDaemon());
		}
	}
	
}

class DaemoSpawn implements Runnable{

	@Override
	public void run() {
		while(true){
			Thread.yield();
		}
	}
	
}


public class Daemons {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Daemon());
		t.setDaemon(true);
		t.start();
		System.out.println("Daemon started.");
		System.out.println("Daemon Thread is Daemon: " + t.isDaemon());
		
		TimeUnit.MILLISECONDS.sleep(0);
	}

}
