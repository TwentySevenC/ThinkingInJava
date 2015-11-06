package concurrency.basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			service.execute(new LiftOff());
		}
		service.shutdown();
	}
}
