package concurrency.stopTask;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//:concurrency/OrnamentalGarden.java


class Count{
	private int count = 0;
	private Random rand = new Random();
	
	public synchronized int increment(){
		int temp = count;
		if(rand.nextBoolean()){
			Thread.yield();
		}
		
		return count = ++ temp;
	}
	
	public synchronized int value(){
		return count;
	}
	
}


class Entrances implements Runnable{
	private static Count count = new Count();
	
	private static ArrayList<Entrances> entrances = new ArrayList<>();
	
	private final int id;
	
	private int number = 0;
	
	private static volatile boolean cancel = false;
	
	public static synchronized void cancel(){
		cancel = true;
	}
	
	public static synchronized boolean isCancel(){
		return cancel;
	}
	
	public Entrances(int id) {
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		while(!isCancel()){
			synchronized (this) {
				++number;
			}
			
			System.out.println(this + " Total number: " + count.increment());
			
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Interrupted.");
			}
		}
		
		System.out.println("Entrance " + id + " stop. ");
		
	}
	
	public String toString(){
		return "Entrance " + id + ", number: " + number;
	}
	
	public synchronized int getValue(){
		return number;
	}
	
	public static synchronized int getTotalCount(){
		return count.value();
	}
	
	public static int sumEntrances(){
		int total = 0;
		for(Entrances entrance: entrances){
			total += entrance.getValue();
		}
		return total;
	}
	
}


public class OrnamentalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exe.execute(new Entrances(i));
		}
		
		TimeUnit.MILLISECONDS.sleep(3000);
		Entrances.cancel();
		exe.shutdown();
		
		if(!exe.awaitTermination(250, TimeUnit.MILLISECONDS)){
			System.out.println("some thread not stop yet!");
		}
		
		System.out.println("Total: " + Entrances.getTotalCount());
		System.out.println("Sum entrances: " + Entrances.sumEntrances());
	}
}
