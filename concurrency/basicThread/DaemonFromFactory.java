package concurrency.basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import util.util.DaemonThreadFactory;

public class DaemonFromFactory implements Runnable{

	private int id;
	
	public DaemonFromFactory(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public String toString(){
		return Thread.currentThread() + ":" + id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			
			System.out.println(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
		
		for(int i = 0; i < 4; i++){
			executorService.execute(new DaemonFromFactory(i));
		}
		executorService.shutdown();
		
		System.out.println("DaemonThread started..");
		
		try {
			TimeUnit.MILLISECONDS.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
