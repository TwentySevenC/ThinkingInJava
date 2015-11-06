package concurrency.shareLimitedResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//:concurrency/CriticalSection.java

//Synchronizing blocks instead of entire methods, Also demonstrate protection
//of a non-thread-safe class with a thread-safe one..


//not thread-safe class
class Pair{
	private int x, y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pair() {
		this(0, 0);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void incrementX(){
		x++; 
	}
	public void incrementY(){
		y++;
	}
	
	
	public class PairValuesNotEquealException extends RuntimeException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public PairValuesNotEquealException() {
			super("Pair values not equeal: " + Pair.this);
		}
	}
	
	public String toString(){
		return "x: " + x + ", y: " + y;
	} 
	
	public void checkState(){
		if(x != y){
			throw new PairValuesNotEquealException();
		}
	}

}

//protect a Pair inside a thread safe class 

abstract class PairManager{
	public AtomicInteger ai = new AtomicInteger(0);
	protected Pair pair = new Pair();
	
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	
	public synchronized Pair getPair(){
		//make a copy to keep the original safe
		return new Pair(pair.getX(), pair.getY());
	}
	
	protected void store(Pair pair){
		storage.add(pair);
		
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract void increment();
}


class PairManager1 extends PairManager{

	@Override
	public synchronized void increment() {
		pair.incrementX();
		pair.incrementY();
		
		store(getPair());;
	}
	
}


class PairManager2 extends PairManager{

	@Override
	public void increment() {
		Pair p ;
		synchronized (this) {
			pair.incrementX();
			pair.incrementY();
			p = getPair();
		}
		store(p);
	}
	
}


class PairManipulator implements Runnable{
	private PairManager pairManager;
	
	public PairManipulator(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while(true)
			pairManager.increment();
	}
	
	public String toString(){
		return "Pair: " + pairManager.getPair() + ", checkState: " + pairManager.ai.get();
	}
	
}

class PairChecker implements Runnable{
	private PairManager pairManager;
	
	public PairChecker(PairManager pairManager) {
		this.pairManager = pairManager;
	}

	@Override
	public void run() {
		while(true){
			pairManager.ai.incrementAndGet();
			pairManager.getPair().checkState();
		}
	}
	
}


public class CriticalSection {
	
	public static void testPairManager(PairManager p1, PairManager p2){
		ExecutorService exe = Executors.newCachedThreadPool();
		PairManipulator pm1 = new PairManipulator(p1);
		PairManipulator pm2 = new PairManipulator(p2);
		
		exe.execute(pm1);
		exe.execute(pm2);
		exe.execute(new PairChecker(p1));
		exe.execute(new PairChecker(p2));
		exe.shutdown();
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pm1);
		System.out.println(pm2);
		
		System.exit(0);
		
	}
	
	public static void main(String[] args) {
		PairManager pairManager1 = new PairManager1();
		PairManager pairManager2 = new PairManager2();
		
		testPairManager(pairManager1, pairManager2);
		

	}
}
