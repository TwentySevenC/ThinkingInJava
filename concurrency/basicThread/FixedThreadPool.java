package basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 5; i++){
			service.execute(new LiftOff());
		}
		service.shutdown();
	}
}
