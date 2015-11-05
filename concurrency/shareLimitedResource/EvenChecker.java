package shareLimitedResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//:concurrency/EvenCheckor.java

//Multiply threads to access the same resources

public class EvenChecker implements Runnable {
	private IntGenerator generator ;
	private int id;
	
	public EvenChecker(IntGenerator generator, int id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run() {
		while(!generator.isCancel()){
			int val = generator.next();
			if(val%2 != 0){
				System.out.println("Runnable " + id + ":" + val + " is not even.");
				generator.cancel();
			}
		}
		
	}
	
	public static void test(IntGenerator g, int count){
		System.out.println("Press control-c to stop.");
		ExecutorService exe = Executors.newCachedThreadPool();
		for(int i = 0; i < count; i++){
			exe.execute(new EvenChecker(g, i));
		}
		exe.shutdown();
	}
	
	public static void test(IntGenerator g){
		test(g, 10);
	}

}
