package concurrency.performance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.util.Generated;
import util.util.RandomGenerator;

//: concurrency/ContainerPerformanceTester.java

//Framework to test performance of concurrency containers.

public abstract class ContainerPerformanceTester<C> {
	static int testReps = 1;
	static int testCycles = 1000;
	static int containerSize = 1000;
	
	String testId;
	C testContainer;
	
	//initialize container
	abstract C containerInitialize();
	
	//Start read and write task.
	abstract void startReadersAndWriters();
	
	static ExecutorService exec = Executors.newCachedThreadPool();
	CountDownLatch endLatch;
	
	protected volatile long readTime = 0;
	protected volatile long writeTime = 0;
	protected volatile long resultValue;
	
	protected int nReaders;
	protected int nWriters;
	
	Integer[] writerData;
	
	public ContainerPerformanceTester(String testId, int readers, int writers){
		nReaders = readers;
		nWriters = writers;
		this.testId = testId + " " + nReaders + "r " + nWriters + "w";
		
		writerData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
		
		for(int i = 0; i < testReps; i++){
			runTest();
			readTime = 0;
			writeTime = 0;
		}
	}
	
	void runTest(){
		endLatch = new CountDownLatch(nReaders + nWriters) ;
		testContainer = containerInitialize();
		
		startReadersAndWriters();
		
		try {
			endLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.printf("%-30s %20d %20d\n", testId, readTime, writeTime);
		
		if(readTime != 0 && writeTime != 0){
			System.out.printf("%-30s %20d\n", "ReadTime + WriteTime", readTime + writeTime);
		}
	}
	
	abstract class TestTask implements Runnable{
		long duration = 0;
		
		abstract void test();
		abstract void putResult();
		
		@Override 
		public void run(){
			long startTime = System.nanoTime();
			test();
			duration = System.nanoTime() - startTime;
			synchronized (ContainerPerformanceTester.this) {   //
				putResult();
			}
			
			endLatch.countDown();
		}
	}
	
	public static void initMain(String[] args){
		if(args.length > 0){
			testReps = new Integer(args[0]);
		}
		
		if(args.length > 1){
			testCycles = new Integer(args[1]);
		}
		
		if(args.length > 2){
			containerSize = new Integer(args[2]);
		}
		
		System.out.printf("%-30s %20s %20s\n", "Type", "Read Time", "Write Time");
	}
	
}
