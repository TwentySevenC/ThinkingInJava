package basicThread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


//:concurrency/CallableDemo.java
/**
 * 使用 Callable 接口，可以从任务中产生返回值
 * 
 * @author liujian
 *
 */

class TaskWithResult implements Callable<String>{
	private int id;
	
	public TaskWithResult(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return "Result of TaskWithResult: " + id;
	}
	
}

public class CallableDemo {
	
	public static void main(String[] args) {
		ArrayList<Future<String>> results = new ArrayList<>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 10; i++){
			results.add(executorService.submit(new TaskWithResult(i)));
		}
		executorService.shutdown();
		
		for(Future<String> fs : results){
			try {
				//Blocking until completed
				String result = fs.get();
				
				System.out.println(result);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
