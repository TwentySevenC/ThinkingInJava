package concurrency.basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private int priority;
	@SuppressWarnings("unused")
	private volatile double d = 0.0;
	
	public SimplePriorities(int priority) {
		// TODO Auto-generated constructor stub
		this.priority = priority;
	}
	
	public String toString(){
		return Thread.currentThread() + ":" + countDown;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setPriority(priority);
		while(true){
			for(int i = 1; i < 100000; i++){
				d += ( Math.PI + Math.E )/i;
			}
			
			System.out.println(this);
			if(--countDown < 0) return ;
			
		}

	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		executorService.shutdown();
		
	}

}
