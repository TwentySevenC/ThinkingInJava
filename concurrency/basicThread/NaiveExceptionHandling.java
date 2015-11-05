package basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//:concurrency/NaiveExceptionHandling.java

//从子线程里跑出来的异常，即使在main Thread中也捕获不了


public class NaiveExceptionHandling {
	public static void main(String[] args) {
		try{
			ExecutorService exc = Executors.newCachedThreadPool();
			exc.execute(new ExceptionThread());
		}catch(Exception e){
			System.out.println("Naive handling exception.");  //never execute
		}
	}
}
