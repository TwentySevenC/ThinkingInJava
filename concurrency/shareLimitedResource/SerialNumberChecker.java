package concurrency.shareLimitedResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//:concurrency/SerialNumberChecker.java


class CircularSet{
	private int[] array;
	private static int index = 0;
	private int length ;
	
	public CircularSet(int size){
		array = new int[size];
		length = size;
	}
	
	public synchronized void add(int val){
		array[index] = val;
		index = (++index) % length;
	}
	
	public synchronized boolean contains(int val){
		
		for(int i = 0; i < length; i++){
			if(array[i] == val) 
				return true;
		}
	
		return false;
	}
	
}


public class SerialNumberChecker {
	private static CircularSet set = new CircularSet(1000);
	
	private static ExecutorService exe = Executors.newCachedThreadPool();
	
	static class SerialChecker implements Runnable{

		@Override
		public void run() {
			while(true){
				int n = SerialNumberGenerator.nextSerialNumber();
				if(set.contains(n)){
					System.out.println("Same number:" + n);
					System.exit(0);
				}else{
					set.add(n);
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			exe.execute(new SerialChecker());
		}
		exe.shutdown();
	}
}
