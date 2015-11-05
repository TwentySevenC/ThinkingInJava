package basicThread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//:concurrency/CaptureUncaughtException.java

//在线程对象上附加一个异常处理器 UncaughtExceptionHandler

class ExceptionThread2 implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("Run by: " + t);
		System.out.println("eh2: " + t.getUncaughtExceptionHandler());
		
		throw new RuntimeException();
	}
	
}


class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("Caught exception: " + e);
	}
	
}


class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + "created a new thread.");
		Thread t = new Thread(r);
		System.out.println("Created by: " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh1: " + t.getUncaughtExceptionHandler());
		return t;
	}
	
}


public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
}
