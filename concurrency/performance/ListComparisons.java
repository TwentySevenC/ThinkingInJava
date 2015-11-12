package concurrency.performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import util.util.CountingIntegerList;


//: concurrency/ListComparisons.java

abstract class ListTest extends ContainerPerformanceTester<List<Integer>>{

	public ListTest(String testId, int readers, int writers) {
		super(testId, readers, writers);
	}
	
	class Reader extends TestTask{
		long result = 0;
		@Override
		void test() {
			for(int j = 0; j < testCycles; j++){
				for(int i = 0; i < containerSize; i++){
					result += testContainer.get(i);
				}
			}
			
		}

		@Override
		void putResult() {
			resultValue += result;
			readTime += duration;
		}
		
	}
	
	class Writer extends TestTask{

		@Override
		void test() {
			for(int j = 0; j < testCycles; j++){
				for(int i = 0; i < containerSize; i++){
					testContainer.set(i, writerData[i]);
				}
			}
			
		}

		@Override
		void putResult() {
			writeTime += duration;
		}
		
	}
	
	@Override
	void startReadersAndWriters() {
		for(int i = 0; i < nReaders; i++){
			exec.execute(new Reader());
		}
		
		for(int i = 0; i < nWriters; i++){
			exec.execute(new Writer());
		}
		
	}
	
}


class SynchronizedListTest extends ListTest{

	public SynchronizedListTest(int readers, int writers) {
		super("Synchronized-list", readers, writers);
	}

	@Override
	List<Integer> containerInitialize() {
		return Collections.synchronizedList(new ArrayList<Integer>(new CountingIntegerList(containerSize)));
	}
	
}

class CopyOnWriteArrayListTest extends ListTest{

	public CopyOnWriteArrayListTest(int readers, int writers) {
		super("CopyOnWriteArrayList", readers, writers);
	}

	@Override
	List<Integer> containerInitialize() {
		return new CopyOnWriteArrayList<Integer>(new CountingIntegerList(containerSize));
	}
	
}

public class ListComparisons {
	public static void main(String[] args) {
		ContainerPerformanceTester.initMain(args);
		
		new SynchronizedListTest(10, 0);
		new SynchronizedListTest(9, 1);
		new SynchronizedListTest(5, 5);
		new CopyOnWriteArrayListTest(10, 0);
		new CopyOnWriteArrayListTest(9, 1);
		new CopyOnWriteArrayListTest(5, 5);
		
		ContainerPerformanceTester.exec.shutdown();
	}
}
