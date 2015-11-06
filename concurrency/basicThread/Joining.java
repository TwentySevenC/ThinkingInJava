package concurrency.basicThread;

import java.util.concurrent.TimeUnit;

//:concurrency/Joining.java

//加入一个线程

class Sleeper extends Thread{
	private long duration;
	public Sleeper(String name, long duration) {
		super(name);
		this.duration = duration;
		start();
	}
	
	
	public void run(){
		try {
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted," +  "is interrupted: " + isInterrupted());
			return ;
		}
		
		System.out.println(getName() + "was awake.");
	}
	
}


class Joiner extends Thread{
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper){
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrupted.");
		}
		
		System.out.println(getName() + " joined completed.");
	}
}



public class Joining {
	
	public static void main(String[] args) {
		Sleeper
		sleepy = new Sleeper("Sleepy", 1500),
		grumpy = new Sleeper("Grumpy", 1500);
	
	@SuppressWarnings("unused")
	Joiner
		dopey = new Joiner("Dopey", sleepy),
		doc = new Joiner("Doc", grumpy);
	
	
	grumpy.interrupt();
	}

	
}
