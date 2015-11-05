package shareLimitedResource;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//:concurrency/AtomicIntegerTest.java

//原子类

public class AtomicIntegerTest implements Runnable{
	
	private AtomicInteger ai = new AtomicInteger();
	
	public int getValue(){
		return ai.get();
	}

	
	public int increatValue(){
		return ai.addAndGet(2);
	}

	@Override
	public void run() {
		while(true){
			increatValue();
		}
		
	}
	
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.err.println("abort");
				System.exit(0);
			}
		}, 5000);
		
		
		AtomicIntegerTest at = new AtomicIntegerTest();
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(at);
		exe.shutdown();
		while(true){
			int val = at.getValue();
			if(val % 2 != 0){
				System.err.println("" + val);
				System.exit(0);
			}
		}
	}

}
