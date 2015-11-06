package concurrency.basicThread;

//:concurrency/SingleThreadPool.java

/**
 * SingleThreadPool 会将提交给他的所有的任务，组成一个队列，一个任务执行完了才会执行接下来的任务
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i++){
			service.execute(new LiftOff());
		}
		service.shutdown();
	}

}
