package basicThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Ex3 implements Runnable{
	private static int tastCount = 0;
	private final int id = tastCount ++ ;
	
	public Ex3() {
		// TODO Auto-generated constructor stub
		System.out.println("Start Ex1 class" + "#" + id);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("#" + id + "In run() One.");
		Thread.yield();
		System.out.println("#" + id + "In run() Two.");
		Thread.yield();
		System.out.println("#" + id + "In run() Three.");
		Thread.yield();
		return;
	}
	
	
	public static void main(String[] args) {
		Executor executor = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i++){
			executor.execute(new Ex3());
		}
	
		
		System.out.println("After the Threads.");
	}
}
