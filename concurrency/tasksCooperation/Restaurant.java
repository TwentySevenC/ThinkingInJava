package tasksCooperation;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/Restaurant.java
//The producer-consumer approach to task cooperation 

class Meal{
	private int mealNum;
	public Meal(int mealNum){
		this.mealNum = mealNum;
	}
	
	public String toString(){
		return "Meal " + mealNum;
	}
}


class WaitPerson implements Runnable{
	private Restaurant r;
	public WaitPerson(Restaurant r) {
		this.r = r;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this) {
					while(r.meal == null){
						wait();
					}
				}
				
				synchronized (r.chef) {
					System.out.println("WaitPerson send " + r.meal);
					r.meal = null;
					r.cleanup = false;
					r.chef.notifyAll();
				}
				
			}
		}catch(InterruptedException e){
			System.out.println("WaitPerson InterruptedException.");
		}
	}
	
}


class BusBoy implements Runnable{
	private Restaurant r;
	public BusBoy(Restaurant r){
		this.r = r;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (r.chef) {
					while(r.cleanup == true){
						r.chef.wait();        //当前线程不含有当前对象锁资源的时候，调用 object.wait(); 这里如果只用wait() 会抛出IllegalMonitorStateException
					}						  // 由于这里所使用的不是当前对象，而是r.chef	
				}
				
				synchronized (r.chef) {
					System.out.println("BusBoy clean meal..");
					r.cleanup = true;
				}
				
			}	
		}catch(InterruptedException e){
			System.out.println("BusBoy InterruptedException.");
		}
		
	}
		
}


class Chef implements Runnable{
	private Restaurant r;
	private int count = 0;

	public Chef(Restaurant r) {
		this.r = r;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this) {
					while(r.meal != null){
						wait();
					}
				}
				
				if(++count > 10){
					System.out.println("Out of meal, closing.");
					r.exe.shutdownNow();
					return ;
				}
				
				System.out.print("Order up!");
				
				synchronized (r.person) {
					r.meal = new Meal(count);
					r.person.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch(InterruptedException e){
			System.out.println("Chef InterruptedException.");
		}
		
	}
	
}

public class Restaurant {
	Meal meal;
	boolean cleanup = true;
	WaitPerson person = new WaitPerson(this);
	Chef chef = new Chef(this);
	BusBoy boy = new BusBoy(this);
	ExecutorService exe = Executors.newCachedThreadPool();
	public Restaurant(){
		exe.execute(person);
		exe.execute(chef);
		exe.execute(boy);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
	
}
