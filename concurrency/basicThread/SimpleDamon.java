package concurrency.basicThread;

import java.util.concurrent.TimeUnit;

//concurrency/SimpleDaemon.java

//Daemon Thread can't prevent the program from stopping


public class SimpleDamon implements Runnable {
	
	private int id;
	
	public SimpleDamon(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public String toString(){
		return Thread.currentThread() + ":" + id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			
			System.out.println(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++){
			Thread thread = new Thread(new SimpleDamon(i));
			thread.setDaemon(true);
			thread.start();
		}
		
		System.out.println("DaemonThread started..");
		
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
