package concurrency.shareLimitedResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//concurrency/ExplicityCriticalSelection.java

class ExplicityPairManager1 extends PairManager{
	private Lock lock = new ReentrantLock();
	@Override
	public void increment() {
		lock.lock();
		try{
			pair.incrementX();
			pair.incrementY();
			store(getPair());
		}finally{
			lock.unlock();
		}		
	}
	
}

class ExplicityPairManager2 extends PairManager{
	private Lock lock = new ReentrantLock();
	@Override
	public void increment() {
		Pair p;
		lock.lock();
		try{
			pair.incrementX();
			pair.incrementY();
			p = getPair();
		}finally{
			lock.unlock();
		}
		store(p);
	}
	
}


public class ExplicityCriticalSection {
	public static void main(String[] args) {
		ExplicityPairManager1 p1 = new ExplicityPairManager1();
		ExplicityPairManager2 p2 = new ExplicityPairManager2();
		
		CriticalSection.testPairManager(p1, p2);
	}
}
