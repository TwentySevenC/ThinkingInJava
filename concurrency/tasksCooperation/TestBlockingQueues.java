package concurrency.tasksCooperation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import concurrency.basicThread.LiftOff;

//:concurrency/TestBlockingQueues.java

class LiftOffRunner  implements Runnable{
	private BlockingQueue<LiftOff> queue;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		this.queue = queue;
	}
	
	public void add(LiftOff l){
		try {
			queue.put(l);
		} catch (InterruptedException e) {
			System.out.println("Interrupt during put().");
		}
	}
 	

	@Override
	public void run() {
		try {
			
			while(!Thread.interrupted()){
				LiftOff liftOff = queue.take();
				liftOff.run();
			}
			
		} catch (InterruptedException e) {
			System.out.println("LiftOffRunner exit via interrupted exception..");
		}
		System.out.println("Exit via while");
	}
	
}

public class TestBlockingQueues {
	public static void main(String[] args) throws InterruptedException {
		Runnable runner = new LiftOffRunner(new ArrayBlockingQueue<>(5));
		
		Thread t = new Thread(runner);
		for(int i = 0; i < 5; i++){
			((LiftOffRunner) runner).add(new LiftOff());
		}
		
		t.start();
		
		TimeUnit.MILLISECONDS.sleep(1);
		
		t.interrupt();
		
	}
}
