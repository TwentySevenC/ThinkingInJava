package concurrency.shareLimitedResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//:concurrency/AtomTest.java

public class AtomTest implements Runnable{
	
	private int val = 0;
	
	//必须同步化
	public synchronized int getVal(){
		return val;
	}
	
	public synchronized int evenIncreament(){
		++val;
		Thread.yield();
		++val;
		return val;
	}

	@Override
	public void run() {
		while(true){
			evenIncreament();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
		AtomTest atomTest = new AtomTest();
		exe.execute(atomTest);
		exe.shutdown();
		
		while(true){
			int value = atomTest.getVal();        //即使是认为的原子操作，返回一个值，也会出错,getVal()方法也必须同步化
			if(value % 2 != 0){
				System.out.println(value);
				System.exit(0); ;
			}
		}
	}

}
