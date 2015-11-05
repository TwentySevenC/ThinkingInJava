package stopTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//:concurrency/Interrupting.java
//Interrupt a blacked thread

//IO 阻塞 和 synchronized 同步块儿 阻塞， 中断不了

class SleepBlock implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedExcetion.");
		}
		System.out.println("Exit SleepBlock.run()");
	}
	
}


class IOBlock implements Runnable{
	private InputStream in;
	
	public IOBlock(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			System.out.println("Waiting for read..");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()){
				System.out.println("Interrupted from block I/O");
			}else{
				throw new RuntimeException();
			}
		}
		
		System.out.println("Exit IOBlock.run().");
	}
	
}


class SychronizedBlock implements Runnable{
	
	public SychronizedBlock() {
		new Thread(){
			public void run(){
				f();                 //firstly grab the lock
			}
		}.start();
	}
	
	public synchronized void f(){
		while(true){               //never release the lock
			Thread.yield();
		}
	}

	@Override
	public void run() {
		System.out.println("try to call f().");
		f();
		
		System.out.println("Exit SychronizedBlock.run().");
	}
	
}



public class Interrupting {
	public static ExecutorService  exe = Executors.newCachedThreadPool();
	
	public static void test(Runnable r) throws InterruptedException{
		Future<?> f = exe.submit(r);
		TimeUnit.MILLISECONDS.sleep(10);
		System.out.println("Interrupting: " + r.getClass().getName());
		f.cancel(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		test(new SleepBlock());
		test(new IOBlock(System.in));
		test(new SychronizedBlock());
		
		TimeUnit.SECONDS.sleep(3);
		System.exit(0);
	}
}
