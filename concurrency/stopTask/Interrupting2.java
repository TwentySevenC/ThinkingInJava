package concurrency.stopTask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



//: concurrency/Interrupting2.java

//Interrupting a task blocked by a ReentrantLock 


class BlockedMutex{
	private Lock lock = new ReentrantLock();
	
	public BlockedMutex(){
		lock.lock();
	}
	
	public void f(){
		try {
			//This will never be available to a second task
			lock.lockInterruptibly();
			System.out.println("Lock aquired in f().");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrupted in f().");
		}
	}
}


class Block2 implements Runnable{
	BlockedMutex bm = new BlockedMutex();
	
	@Override
	public void run() {
		System.out.println("trying to run method f()");
		bm.f();
		System.out.println("f() completed..");
	}
	
}

public class Interrupting2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Block2());
		t.start();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Interrupt thread.");
		t.interrupt();
	}
}
