package concurrency.performance;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//: concurrency/SimpleMicroBenchmark.java

//The dangers of microBenchMarking. 微基准测试

abstract class Increamtable{
	protected long counter = 0;
	abstract void increament();
}

class SychronizedTest extends Increamtable{

	@Override
	synchronized void increament() {
		counter++;
	}
	
}

class LockTest extends Increamtable{
	Lock lock = new ReentrantLock();
	@Override
	void increament() {
		lock.lock();
		try{
			counter++;
		}finally{
			lock.unlock();
		}
	}
	
}


public class SimpleMicroBenchmark {
	public static long test(Increamtable inc){
		long startTime = System.nanoTime();
		for(long i = 0; i < 10000000L; i++){
			inc.increament();
		}
		
		return System.nanoTime() - startTime;
	}
	
	public static void main(String[] args) {
		long sycTime = test(new SychronizedTest());
		long lockTime = test(new LockTest());
		
		System.out.println("Sychronized time: " + sycTime);
		System.out.println("Lock time: " + lockTime);
		System.out.println("Sych/lock: " + sycTime/lockTime);
	}
	
	
}
