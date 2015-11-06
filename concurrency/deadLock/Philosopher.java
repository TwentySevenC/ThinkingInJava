package concurrency.deadLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//: concurrency/Philosopher.java

//A dining philosopher

public class Philosopher implements Runnable{
	private Chopstick left, right;
	private final int id;
	private final int pondFactor ;
	private Random random = new Random(47);
	
	public void pause() throws InterruptedException{
		if(pondFactor == 0)  return ;
		TimeUnit.MILLISECONDS.sleep(random.nextInt(pondFactor * 250));
	}
	
	public Philosopher(Chopstick left, Chopstick right, int id, int pondFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.pondFactor = pondFactor;
	}
	
	public String toString(){
		return "Philosopher : " + id;
	}
	

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println(this + " thinking.");
				pause();
				System.out.println(this + " take right chopstick.");
				right.take();
				System.out.println(this + " take left chopstick.");
				left.take();
				System.out.println(this + " eating.");
				pause();
				System.out.println(this + " drop right chopstick.");
				right.drop();
				System.out.println(this + " drop left chopstick.");
				left.drop();
			}
		}catch(InterruptedException e){
			System.out.println("exit via interrupted.");
		}
		
	}
	
}
