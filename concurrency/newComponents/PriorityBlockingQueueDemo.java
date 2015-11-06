package concurrency.newComponents;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

//: concurrency/PriorityBlockingQueueDemo.java


class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>{
	private static int count = 0;
	private final int id = count ++;
	private final int priority;
	protected static List<PrioritizedTask> squence = new ArrayList<>();
	private Random random = new Random(47);
	public PrioritizedTask(int priority) {
		this.priority = priority;
		squence.add(this);
	}

	@Override
	public int compareTo(PrioritizedTask arg) {
		
		return priority < arg.priority ? 1 : (priority > arg.priority ? -1 : 0);
	}

	@Override
	public void run() {
		
		try {
			TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
		} catch (InterruptedException e) {
			System.out.println("priority task interrupted.");
		}
		System.out.print(this);
	}
	
	public String toString(){
		return String.format(" [%1$-2d]", priority) + " Task " + id;
	}
	
	public String summary(){
		return "(" + id + " : " + priority + ")";
	}
	
	public static class EndSentinel extends PrioritizedTask{
		private ExecutorService exe;
		public EndSentinel(ExecutorService exe){
			super(-1);           //lowest priority
			this.exe = exe;
		}
		
		@Override
		public void run(){
			System.out.println("");
			for(PrioritizedTask task : squence){
				System.out.print(task.summary());
			}
			System.out.println("");
			System.out.println(this);
			System.out.println("Last task finishing.");
			exe.shutdownNow();
		}
		
	}
	
}


class PrioritizedTaskProducer implements Runnable{
	private PriorityBlockingQueue<PrioritizedTask> priorityQueue ;
	private Random random =  new Random(47);
	private ExecutorService exec;
	public PrioritizedTaskProducer(PriorityBlockingQueue<PrioritizedTask> queue, ExecutorService exe) {
		this.priorityQueue = queue;
		this.exec = exe;
	} 

	@Override
	public void run() {
		for(int i = 1; i < 10; i++){
			priorityQueue.add(new PrioritizedTask(random.nextInt(10)));
		}
		
		priorityQueue.add(new PrioritizedTask.EndSentinel(exec));
		
		System.out.println("Produced task finished.");
	}
	
}


class PrioritizedTaskConsumer implements Runnable{
	private PriorityBlockingQueue<PrioritizedTask> priorityQueue;
	public PrioritizedTaskConsumer(PriorityBlockingQueue<PrioritizedTask> priorityQueue) {
		this.priorityQueue = priorityQueue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				priorityQueue.take().run();
			}
		}catch(InterruptedException e){
			System.out.println("Consumer task interrupted.");
		}
		System.out.println("Consumer task finished.");
	}
	
}

public class PriorityBlockingQueueDemo {
	
	public static void main(String[] args) {
		PriorityBlockingQueue<PrioritizedTask> priorityQueue = new PriorityBlockingQueue<>();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new PrioritizedTaskProducer(priorityQueue, exec));
		exec.execute(new  PrioritizedTaskConsumer(priorityQueue));
	}

}
