package shareLimitedResource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



//:concurrency/ThreadLocalVariableHolder.java
//Automatically giving each thread its own storage


class Accessor implements Runnable{
	private int id = 0;
	
	public Accessor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
		
	}
	
	public String toString(){
		return "#" + id + "-" + ThreadLocalVariableHolder.get();
	}
	
}




public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> values = new ThreadLocal<Integer>(){
		private Random random = new Random(47);
		protected synchronized Integer initialValue(){
			return random.nextInt(100);
		}
	};
	
	public static void increment(){
		values.set(get() + 1);
	}
	
	public static int get(){
		return values.get();
	}
	
	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exe.execute(new Accessor(i));
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exe.shutdownNow();
	}

}
