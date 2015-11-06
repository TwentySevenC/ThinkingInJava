package concurrency.basicThread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex6 implements Runnable{
	private int id;
	public Ex6(int id){
		this.id = id;
	}
	
	Random random = new Random();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int time = random.nextInt(10);
		try {
			TimeUnit.SECONDS.sleep(time);
			
			System.out.println("Thread: " + id + ", Time: " + time);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 10; i++){
			executorService.execute(new Ex6(i));
		}
		executorService.shutdown();
	}
	

}
