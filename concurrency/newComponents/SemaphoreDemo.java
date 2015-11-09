package concurrency.newComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


//: concurrency/SemaphoreDemo.java
//Test the pool class

class CheckoutTask<T> implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private Pool<T> pool;
	
	public CheckoutTask(Pool<T> pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		T item;
		try {
			item = pool.checkout();
			System.out.println(this + " checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + " checked in " + item);
			pool.checkin(item);
		} catch (InterruptedException e) {
			System.out.println("Check out task interrupt.");
		}
		
	}
	
	public String toString(){
		return "CheckedoutTask " + id;
	}
	
}

public class SemaphoreDemo {
	private static final int SIZE = 25;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
		final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < SIZE; i++){
			exec.execute(new CheckoutTask<Fat>(pool));
		}
		
		System.out.println("Check out all tasks.");
		
		List<Fat> list = new ArrayList<>();
		for(int i = 0; i < SIZE; i++){
			Fat f = pool.checkout();
			System.out.print(i + " main thread check out.");
			f.operation();
			list.add(f);
		}
		
		Future<?> blocked = exec.submit(new Runnable() {
			
			@Override
			public void run() {
				//Semaphore present additional check out
				//so call is blocked 
				try {
					pool.checkout();
				} catch (InterruptedException e) {
					System.out.println("blocked check out interrupt.");
				}
			}
		});
		
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		for(Fat fat : list){
			pool.checkin(fat);
		}
			
		
		for(Fat fat : list){
			pool.checkin(fat);
		}
			
		
		exec.shutdown();
	}
	
}
