package tasksCooperation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//: concurrency/ToastOMatic.java

class Toast{
	private final int id;
	public enum Status {DRY, BUTTERED, JAMMED};
	private Status status = Status.DRY;
	public Toast(int id){
		this.id = id;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public int getId(){
		return id;
	}
	
	public void butter(){
		status = Status.BUTTERED;
	}
	
	public void jam(){
		status = Status.JAMMED;
	}
	
	public String toString(){
		return "Toast " + id + " : " + status;
	}
	
}

class ToastQueue extends LinkedBlockingQueue<Toast>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	}


class Toaster implements Runnable{
	private ToastQueue toastedQueue;
	private static int count = 0;
	private Random random = new Random(47);
	
	public Toaster(ToastQueue queue) {
		this.toastedQueue = queue;
	}
	
	@Override
	public void run() {
		try{
			
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(200 + random.nextInt(100));
				Toast toast = new Toast(count++);
				System.out.println(toast);
				toastedQueue.put(toast);
			}
			
		}catch(InterruptedException e){
			System.out.println("Toaster Interrupted.");
		}
		System.out.println("Toaster Off.");
	}
	
}

class Butter implements Runnable{
	private ToastQueue toastedQueue, butteredQueue;
	
	public Butter(ToastQueue toastedQueue, ToastQueue butteredQueue) {
		this.butteredQueue = butteredQueue;
		this.toastedQueue = toastedQueue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100);
				Toast toast = toastedQueue.take();
				toast.butter();
				System.out.println(toast);
				butteredQueue.put(toast);
			}
		}catch(InterruptedException e){
			System.out.println("Butter interrupted.");
		}
		System.out.println("Buttered off");
	}
	
}


class Jammer implements Runnable{
	private ToastQueue butteredQueue, finishedQueue;
	
	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue){
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100);
				Toast toast = butteredQueue.take();
				toast.jam();
				System.out.println(toast);
				finishedQueue.put(toast);
			}
		}catch(InterruptedException e){
			System.out.println("Jam interrupted.");
		}
		System.out.println("Jamed off.");
	}
	
}

class Eater implements Runnable{
	private ToastQueue finishedQueue;
	private int count = 0;
	public Eater(ToastQueue finishedQueue){
		this.finishedQueue = finishedQueue;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Toast t = finishedQueue.take();
				if(t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED){
					System.out.println("error.");
					System.exit(1);
				}else{
					System.out.println(t + " eated.");
				}
			}
		}catch(InterruptedException e){
			System.out.println("Eat interrupt.");
		}
		System.out.println("Eatered off.");
	}
	
}


public class ToastOMatic{
	public static void main(String[] args) throws InterruptedException {
		ToastQueue toastedQueue = new ToastQueue(), 
				butteredQueue = new ToastQueue(), 
				finishedQueue = new ToastQueue();
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new Toaster(toastedQueue));
		exe.execute(new Butter(toastedQueue, butteredQueue));
		exe.execute(new Jammer(butteredQueue, finishedQueue));
		exe.execute(new Eater(finishedQueue));
		
		TimeUnit.SECONDS.sleep(2);
		
		exe.shutdownNow();
	}
}
