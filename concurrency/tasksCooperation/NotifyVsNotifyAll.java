package concurrency.tasksCooperation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/NotifyVsNotifyAll.java

class Blocker{
	public synchronized void waitingCall(){
		try{
			while(!Thread.interrupted()){
				wait();
				System.out.print(Thread.currentThread() + "  ");
			}
		}catch(InterruptedException e){
			System.out.println("Exiting via InterruptedException" + Thread.currentThread());
		}
	}
	
	public synchronized void prod(){
		notify();
	}
	
	public synchronized void prodAll(){
		notifyAll();
	}
}


class Task implements Runnable{
	static Blocker bloocker = new Blocker();
	@Override
	public void run() {
		bloocker.waitingCall();
	}
	
}

class Task2 implements Runnable {
	static Blocker blocker = new Blocker();
	@Override
	public void run() {
		blocker.waitingCall();
	}
	
}


public class NotifyVsNotifyAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exe.execute(new Task());
		}
		
		exe.execute(new Task2());
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;
			@Override
			public void run() {
				if(prod){
					System.out.println("\n notify ");
					Task.bloocker.prod();
					prod = false;
				}else{
					System.out.println("\n notify all ");
					Task.bloocker.prodAll();
					prod = true;
				}
				
			}
		}, 400, 400);
		
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println("\n Timer Cancel..");
		timer.cancel();
		
		TimeUnit.MILLISECONDS.sleep(400);
		System.out.println("Task2 notify all.");
		Task2.blocker.prodAll();
		
		TimeUnit.MILLISECONDS.sleep(400);
		System.out.println("\n Ending all tasks.");
		exe.shutdownNow();
	}
	
}
