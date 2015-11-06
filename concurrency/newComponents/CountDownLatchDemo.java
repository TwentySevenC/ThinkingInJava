package concurrency.newComponents;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//: concurrency/CountfDownLatchDemo.java

class TaskPortion implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private CountDownLatch latch;
	private Random random = new Random(47);
	
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	

	@Override
	public void run() {
		try{
			doWork();
			latch.countDown();
		}catch(InterruptedException e){
			
		}
		
	}
	
	private void doWork() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
		System.out.println(this + " done.");
	}
	
	public String toString(){
		return "TaskPortion " + id; 
	}
	
}


class WaitTask implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private CountDownLatch latch;
	public WaitTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println(this);
		} catch (InterruptedException e) {
			
			System.out.println(this + " interrupted.");
		}
		
	}
	
	public String toString(){
		return "WaitTask " + id;
	}
	
}

public class CountDownLatchDemo {
	public static final int SIZE = 100;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for(int i = 0; i < 10; i++){
			exe.execute(new WaitTask(latch));
		}
		
		for(int i = 0; i < SIZE; i++){
			exe.execute(new TaskPortion(latch));
		}
		
		
		TimeUnit.SECONDS.sleep(1);
		
		exe.shutdown();
	}
}
