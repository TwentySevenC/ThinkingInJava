package concurrency.shareLimitedResource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//concurrency/AttempLocking.java
//Locks in the concurrency library 
//allow you to give up on trying to acquire a lock

public class AttempLocking {
	private ReentrantLock lock = new ReentrantLock();
	
	public void timed(){
		boolean capture = lock.tryLock();
		try{
			System.out.println("timed try lock:" + capture);
		}finally{
			if(capture)
				lock.unlock();
		}
	}
	
	public void unTimed(){
		boolean capture = false;
		try {
			capture = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			System.out.println("unTimed try lock: " + capture);
		}finally{
			if(capture)
				lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		AttempLocking al = new AttempLocking();
		al.timed();
		al.unTimed();
		
		new Thread(){
			{setDaemon(true);}
			
			public void run(){
				al.lock.lock();                  //attempt to acquire the lock fist and don't unlock
				System.out.println("acquared");
			}
		}.start();
		
		Thread.yield();
		al.unTimed();
		al.timed();
	}
}
