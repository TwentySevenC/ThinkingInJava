package concurrency.performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//: concurrency/ReadWriteListTest.java

class ReaderWriterList<T> {
	private ArrayList<T> list;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	
	public ReaderWriterList(int size, T inizialValue){
		list = new ArrayList<T>(Collections.nCopies(size, inizialValue));
	}
	
	public T get(int index){
		Lock rLock = lock.readLock();
		rLock.lock();
		
		try{
			
			if(lock.getReadLockCount() > 1){
				System.out.println("Read Count: " + lock.getReadLockCount());
			}
			
			return list.get(index);
		}finally{
			rLock.unlock();
		}

	}
	
	public void set(int index, T element){
		Lock wLock = lock.writeLock();
		wLock.lock();
		
		try{
			list.set(index, element);
		}finally{
			wLock.unlock();
		}
	}
}

public class ReaderWriterListTest {
	private static final int SIZE = 100;
	private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);
	private Random random = new Random(47);
	
	class Reader implements Runnable{

		@Override
		public void run() {
			for(int i = 0; i < 20; i++){
				list.get(i);
			}
		}
		
	}
	
	class Writer implements Runnable{

		@Override
		public void run() {
			for(int i = 0; i < 20; i++){
				list.set(i, random.nextInt());
			}
		}
		
	}
	
	
	public void test(int nReaders, int nWriters) throws InterruptedException{
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0; i < nReaders; i++){
			executorService.execute(new Reader());
		}
		
		for(int i = 0; i < nWriters; i++){
			executorService.execute(new Writer());
		}
		
		TimeUnit.SECONDS.sleep(1);
		executorService.shutdownNow();
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReaderWriterListTest().test(100, 5);

	}
	
}
