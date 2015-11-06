package concurrency.tasksCooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//:concurrency/WaxOMatic.java


class Car {
	private boolean waxOn = false;
	
	public synchronized void waxed(){
		waxOn = true;
//		notifyAll();
		notify();
	}
	
	public synchronized void buffed(){
		waxOn = false;
//		notifyAll();
		notify();
	}
	
	public synchronized void waitingForWaxing() throws InterruptedException{
		while(waxOn == false){
//			System.out.println("waxing wait");
			wait();
		}
			
	}
	
	public synchronized void waitingForBuffing() throws InterruptedException{
		while(waxOn == true){
//			System.out.println("buffing wait.");
			wait();
		}
			
	}
}


class WaxOn implements Runnable{
	
	private Car c;
	public WaxOn(Car c) {
		this.c = c;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println(" WaxOn ");
				TimeUnit.MILLISECONDS.sleep(200);
				c.waxed();
				c.waitingForBuffing();
			}
		}catch(InterruptedException e){
			System.out.println("WO exiting via InterruptingException.");
		}
		System.out.println("Exiting WaxOn run().");
	}
	
}

class WaxOff implements Runnable{
	private Car c;
	
	public WaxOff(Car c){
		this.c = c;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				c.waitingForWaxing();
				System.out.println(" WaxOff ");
				TimeUnit.MILLISECONDS.sleep(200);
				c.buffed();
			}
		}catch(InterruptedException e){
			System.out.println("WF exiting via InterruptingException.");
		}
		System.out.println("Exiting WaxOff run().");
	}
	
}

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		Car c = new Car();
		exe.execute(new WaxOn(c));
		exe.execute(new WaxOff(c));
		
		TimeUnit.SECONDS.sleep(3);
		exe.shutdownNow();
	}
}
