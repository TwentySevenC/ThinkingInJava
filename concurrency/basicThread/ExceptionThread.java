package concurrency.basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//concurrency/ExceptionThread.java
//{ThrowException}

//由于线程的本质特性，是的你不能捕获从线程中逃逸出来的异常，一旦线程逃出线程的run（）
//方法，他就会向外传播到控制台，除非采用特殊的步骤捕获这些异常

public class ExceptionThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new ExceptionThread());
	}

}
