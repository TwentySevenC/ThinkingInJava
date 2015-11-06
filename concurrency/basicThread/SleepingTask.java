package concurrency.basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/ SleepingTask.java

public class SleepingTask extends LiftOff{
	
	public void run(){
		while(countDown-- > 0){
			System.out.println(status());
			
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
	
	
	public static void main(String[] args) {
		ExecutorService excute = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 5; i++){
			excute.submit(new SleepingTask());
		}
		excute.shutdown();
	}
}
