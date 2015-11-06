package concurrency.shareLimitedResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//:concurrency/MutexEventGenerator.java

//显示锁，使用在某种特殊情况下

public class MutexEvenGenerator extends IntGenerator{
	private int val = 0;
	private Lock lock = new ReentrantLock();

	@Override
	public int next() {
		lock.lock();
		try{
			++val;
			Thread.yield();
			++val;
			return val;
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}

}
