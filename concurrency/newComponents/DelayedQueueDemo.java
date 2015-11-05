package newComponents;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;


//: concurrency/DelayedQueueDemo.java

class DelayedTask implements Runnable, Delayed{
	private static int count = 0;
	private final int id = count++;
	protected static List<DelayedTask> squence = new ArrayList<>();
	private final int delta;
	public final long trigger;
	
	public DelayedTask(int deltaInMillisecond) {
		this.delta = deltaInMillisecond;
		trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
		squence.add(this);
	}
	

	@Override
	public int compareTo(Delayed o) {
		DelayedTask dt = (DelayedTask)o;
		if(trigger > dt.trigger) return 1;
		if(trigger == dt.trigger) return 0;
		return -1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		System.out.print(this + " ");
		
	}
	
	public String toString(){
		return String.format("[%1$-4d]",delta) + " Task " + id;
	}
	
	public String summary(){
		return "(" + id + " : " + delta + ")";
	}
	
	
	public static class EndSentinel extends DelayedTask{
		private ExecutorService exec;
		public EndSentinel(int deltaInMillisecond, ExecutorService exec) {
			super(deltaInMillisecond);
			this.exec = exec;
		}
		
		@Override
		public void run(){
			System.out.println("");
			for(DelayedTask task : squence){
				System.out.print(task.summary());
			}
			System.out.println("");
			System.out.println(this);
			
			System.out.println("Finishing the end task.");
			exec.shutdownNow();
		}
		
	}
	
}


class DelayedTaskConsumer implements Runnable{
	private DelayQueue<DelayedTask> queue ;
	
	public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				queue.take().run();
			}
		}catch(InterruptedException e){
			//
		}
		System.out.println("finish consumer.");
	}
	
}



public class DelayedQueueDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<>();
		Random random = new Random(47);
		for(int i = 0; i < 10; i++){
			queue.put(new DelayedTask(random.nextInt(500)));
		}
		
		DelayedTask endTask = new DelayedTask.EndSentinel(500, exec);
		queue.put(endTask);
		
		exec.execute(new DelayedTaskConsumer(queue));
		
	}
}
