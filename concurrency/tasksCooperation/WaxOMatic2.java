package tasksCooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//: concurrency/WaxOMatic2.java

//Using Lock and Condition Object

class Car2{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	
	public void waxed(){
		lock.lock();
		try{
			waxOn = true;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void buffed(){
		lock.lock();
		try{
			waxOn = false;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try{
			while(waxOn == true)
				condition.await();
			
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForBuffing() throws InterruptedException{
		lock.lock();
		try{
			while(waxOn == false)
				condition.await();
			
		}finally{
			lock.unlock();
		}
	}
	
}


class WaxOn2 implements Runnable{
	private Car2 car;
	public WaxOn2(Car2 car2) {
		this.car = car2;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				car.waitForWaxing();
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("WaxOn!");
				car.waxed();
			}
		}catch(InterruptedException e){
			System.out.println("WaxOn2 exit via interruped.");
		}
		System.out.println("Exiting from WaxOn2 run().");
	}
	
}


class WaxOff2 implements Runnable{
	private Car2 car;
	public WaxOff2(Car2 car) {
		this.car = car;
	}

	@Override
	public void run() {
		try{
			
			while(!Thread.interrupted()){
				car.waitForBuffing();
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("WaxOff!");
				car.buffed();
			}
		}catch(InterruptedException e){
			System.out.println("WaxOff2 exit via interrupt.");
		}
		System.out.println("Exiting from WaxOff2 run().");
		
	}
	
}

public class WaxOMatic2 {
	public static void main(String[] args) throws InterruptedException {
		Car2 car = new Car2();
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new WaxOn2(car));
		exe.execute(new WaxOff2(car));
		
		TimeUnit.SECONDS.sleep(1);
		
		exe.shutdownNow();
	}
}
