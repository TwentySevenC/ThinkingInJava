package concurrency.stopTask;

import java.util.concurrent.TimeUnit;

//: concurrency/InteruptingIdiom.java
//General idiom for interrupting a task

class NeedsCleanup{
	private final int id ;
	
	public NeedsCleanup(int id){
		this.id = id;
	}
	
	public void cleanup(){
		System.out.println("clean up, id " + id);
	}		
}

class Block3 implements Runnable{
	
	private volatile double d = 0.0;
	@Override
	public void run() {
		
		try{
			while(!Thread.interrupted()){
				NeedsCleanup nc1 = new NeedsCleanup(1);
				
				try{
					//point1
					System.out.println("Sleeping.");
					TimeUnit.SECONDS.sleep(1);
					
					NeedsCleanup nc2 = new NeedsCleanup(2);
					
					try{
						//point2
						System.out.println("Calculate.");
						for(int i = 1; i < 2500000; i++){
							d = d + (Math.PI + Math.E)/d;
						}
						System.out.println("Completing time-consume operation..");
					}finally{
						nc2.cleanup();
					}
				}finally{
					nc1.cleanup();
				}
			}
			System.out.println("Exiting after for-loop");
		}catch(InterruptedException e){
			System.out.println("Exiting via interrupting exception.");
		}
		
	}
	
}

public class InterruptingIdiom {
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		if(args.length != 1){
			System.out.println("usage: java InterruptingIdiom delay-in-ms");
			System.exit(1);
		}
		
		Thread t = new Thread(new Block3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
		
	}
}
