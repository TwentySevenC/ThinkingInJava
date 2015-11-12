package concurrency.performance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import util.util.CountingGenerator;
import util.util.MapData;



//: concurrency/MapComparisons.java

//Rough comparisons of thread-safe Map performance

abstract class MapTest extends ContainerPerformanceTester<Map<Integer, Integer>>{

	public MapTest(String testId, int readers, int writers) {
		super(testId, readers, writers);
	}
	
	class Reader extends TestTask{
		private long result = 0;

		@Override
		void test() {
			for(int i = 0; i < testCycles; i++){
				for(int index = 0; index < containerSize; index++)
					result += testContainer.get(index);
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
			for(int i = 0; i < testCycles; i++ ){
				for(int j = 0; j < containerSize; j++)
					testContainer.put(j, writerData[j]);
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

class SynchronizedHashMapTest extends MapTest{

	public SynchronizedHashMapTest(int readers, int writers) {
		super("Sync-HashMap", readers, writers);
	}

	@Override
	Map<Integer, Integer> containerInitialize() {
		return Collections.synchronizedMap(new HashMap<>(MapData.map(new CountingGenerator.Integer(), 
				new CountingGenerator.Integer(), containerSize)));
	}
	
}

class ConcurrencyHashMapTest extends MapTest{

	public ConcurrencyHashMapTest(int readers, int writers) {
		super("Concurrency-HashMap", readers, writers);
	}

	@Override
	Map<Integer, Integer> containerInitialize() {
		return new ConcurrentHashMap<>(MapData.map(new CountingGenerator.Integer(), 
				new CountingGenerator.Integer(), containerSize));
	}
	
}


public class MapComparisons {
	public static void main(String[] args) {
		ContainerPerformanceTester.initMain(args);
		new SynchronizedHashMapTest(10, 0);
		new SynchronizedHashMapTest(9, 1);
		new SynchronizedHashMapTest(5, 5);
		
		new ConcurrencyHashMapTest(10, 0);
		new ConcurrencyHashMapTest(9, 1);
		new ConcurrencyHashMapTest(5, 5);
		
		ContainerPerformanceTester.exec.shutdown();
	}
}
