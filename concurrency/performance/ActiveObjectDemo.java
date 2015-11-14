package concurrency.performance;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


//concurrency/ActiveObjectDemo.java

//活动对象，每个对象都维护着它自己的工作器线程和消息队列

public class ActiveObjectDemo {
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	
	private Random random = new Random(47);
	
	private void pause(int time){
		try {
			TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(time));
		} catch (InterruptedException e) {
			System.out.println("pause() interrupted.");
		}
	}
	
	public Future<Integer> add(final int x, final int y){
		return exec.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("Starting x: " + x + " y: " + y);
				pause(500);
				return x + y;
			}
			
		});
	}
	
	public Future<Float> add(final float x, final float y){
		
		return exec.submit(new Callable<Float>() {

			@Override
			public Float call() throws Exception {
				System.out.println("Starting x: " + x + " y: " + y);
				pause(2000);
				return x + y;
			}
		});
	}
	
	
	public void shutDown(){
		exec.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ActiveObjectDemo demo = new ActiveObjectDemo();
		List<Future<?>> tasks = new CopyOnWriteArrayList<Future<?>>();  //防止产生ConcurrentModifiyException
		for(int i = 0; i < 4; i++){
			tasks.add(demo.add(i, i));
		}
		
		for(float f = 0; f < 1.0; f += 0.2){
			tasks.add(demo.add(f, f));
		}
		
		
		while(tasks.size() > 0){
			for(Future<?>  f : tasks){
				if(f.isDone()){
					System.out.println(f.get());
					tasks.remove(f);
				}
			}
		}
		
		demo.shutDown();
	}
	
}
