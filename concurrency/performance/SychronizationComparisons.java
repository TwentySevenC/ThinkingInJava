package concurrency.performance;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//: concurrency/SychronizationComparisons.java

// Comparisons the performance of explicit locks
// and Atomics versus the synchronized keyword

// 程序的一个bug：BaseAccumulator 在多线程的状态下由于没有使用互斥，所以preLoad[]很容易越界抛出异常（index超出范围）。


abstract class Accumulator{
	private String name = "error";
	protected static final int N = 4;
	protected static final int SIZE = 100000;
	protected static long cycles = 50000L;
	private  CyclicBarrier barrier = new CyclicBarrier(2 * N + 1);
	public static ExecutorService executorService = Executors.newFixedThreadPool(2 * N);
	protected volatile long value = 0L;
	protected volatile int index = 0;
	protected volatile long duration;
	
	protected static int[] preLoad = new int[SIZE];
	
	static{
		Random random = new Random(47);
		for(int i = 0; i < SIZE; i++){
			preLoad[i] = random.nextInt();
		}
	}
	
	public Accumulator(String name){
		this.name = name;
	}
	
	abstract void accumulate();
	abstract long read();
	
	private class Modifier implements Runnable{

		@Override
		public void run() {
			for(int i = 0; i < cycles; i++){
				accumulate();
			}
				try {
					barrier.await();
				} catch (InterruptedException e) {
					System.out.println("Modifier interrupted.");
				} catch (BrokenBarrierException e) {
					throw new RuntimeException();
				}
		}
		
	}
	
	private class Reader implements Runnable{
		
		@Override
		public void run() {
			for(int i = 0; i < cycles; i++){
				read();
			}
			try {
				barrier.await();
			} catch (InterruptedException e) {
				System.out.println("Reader interrupted.");
			} catch (BrokenBarrierException e) {
				throw new RuntimeException();
			}
		}
		
	}
	
	
	public void timeTest(){
		long startTime = System.nanoTime();
		for(int i = 0; i < N; i++){
			executorService.execute(new Modifier());
			executorService.execute(new Reader());
		}
		
		try{
			barrier.await();
		}catch(InterruptedException e){
			System.out.println("timeTest interrupted.");
		}catch (BrokenBarrierException e) {
			throw new RuntimeException();
		}
		
		duration = System.nanoTime() - startTime;
		System.out.println(String.format("%-40s : %13d", name, duration));
	}
	
	
	public static void report(Accumulator acc1, Accumulator acc2){
		System.out.println(String.format("%-40s : %13.2f", 
				acc1 + "/" + acc2, (double)acc1.duration/(double)acc2.duration));
	}
	
	public String toString(){
		return name;
	}
	
}

class BaseAccumulator extends Accumulator{

	public BaseAccumulator() {
		super("BaseAccumulator");
	}

	@Override
	void accumulate() {
		value += preLoad[index++];
		if(index >= SIZE)
			index = 0;
	}

	@Override
	long read() {
		return value;
	}
	
}


class SynchAccumulator extends Accumulator{

	public SynchAccumulator() {
		super("SynchAccumulator");
	}

	@Override
	synchronized void accumulate() {
		value += preLoad[index++];
		if(index >= SIZE)
			index = 0;
	}

	@Override
	synchronized long read() {
		return value;
	}
	
}

class LockAccumulator extends Accumulator{
	private Lock lock = new ReentrantLock();

	public LockAccumulator() {
		super("LockAccumulator");
	}

	@Override
	void accumulate() {
		lock.lock();
		try{
			value += preLoad[index++];
			if(index >= SIZE)
				index = 0;
		}finally{
			lock.unlock();
		}
		
	}

	@Override
	long read() {
		lock.lock();
		try{
			return value;
		}finally{
			lock.unlock();
		}
	}
	
}

class AtomicAccumulator extends Accumulator{
	private AtomicInteger index = new AtomicInteger(0);
	private AtomicLong value = new AtomicLong(0);

	public AtomicAccumulator() {
		super("AtomicAccumulator");
	}

	@Override
	void accumulate() {
		int i = index.getAndIncrement();
		value.addAndGet(i);
		if(++i >= SIZE)
			index.set(0);
	}

	@Override
	long read() {
		return value.get();
	}
	
}


public class SychronizationComparisons {
	static BaseAccumulator baseAccumulator = new BaseAccumulator();
	static SynchAccumulator synchAccumulator = new SynchAccumulator();
	static LockAccumulator lockAccumulator = new LockAccumulator();
	static AtomicAccumulator atomicAccumulator = new AtomicAccumulator();
	
	public static void test(){
		System.out.println("==========================================================");
		System.out.println(String.format("%-40s : %13d", "Cycles", Accumulator.cycles));
		baseAccumulator.timeTest();
		synchAccumulator.timeTest();
		lockAccumulator.timeTest();
		atomicAccumulator.timeTest();
		Accumulator.report(synchAccumulator, baseAccumulator);
		Accumulator.report(lockAccumulator, baseAccumulator);
		Accumulator.report(atomicAccumulator, baseAccumulator);
		Accumulator.report(synchAccumulator, lockAccumulator);
		Accumulator.report(synchAccumulator, atomicAccumulator);
		Accumulator.report(lockAccumulator, atomicAccumulator);
	}
	
	public static void main(String[] args) {
		int iterators = 4;
		System.out.println("Warmup");
		baseAccumulator.timeTest();
		
		for(int i = 0; i < iterators; i++){
			test();
			Accumulator.cycles *=2;
		}
		
		Accumulator.executorService.shutdown();
	}
}
