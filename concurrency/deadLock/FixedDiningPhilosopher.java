package concurrency.deadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/FixedDiningPhilosopher.java
//Dining philosopher no dead lock
/**
 * 发生死锁的四个条件：
 * 1. 互斥条件
 * 2. 资源不能被任务抢占
 * 3. 至少有一个任务它必须持有一个资源且正在等待获取一个当前被别的任务所持有的资源
 * 4. 必须有循环等待
 * 该修正的，破坏了第四个条件。
 */

public class FixedDiningPhilosopher {
	public static void main(String[] args) throws Exception{
		int ponder = 5;
		if(args.length > 0){
			ponder = Integer.parseInt(args[0]);
		}
		
		int size = 5;
		if(args.length > 1){
			size = Integer.parseInt(args[1]);
		}
		
		ExecutorService exe = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for(int i = 0; i < size; i++){
			sticks[i] = new Chopstick();
		}
		
		for(int i = 0; i < size-1; i++){
			exe.execute(new Philosopher(sticks[i], sticks[(i+1)%size], i, ponder));
		}
		exe.execute(new Philosopher(sticks[0], sticks[size-1], size, ponder));
		
		
		if(args.length == 3 && args[2].equals("timeout")){
			TimeUnit.SECONDS.sleep(5);
		}else{
			System.out.println("Press 'enter' to quite.");
			System.in.read();
		}
		
		exe.shutdownNow();
	}

}
