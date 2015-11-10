package concurrency.simulation;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import enumerated.menu.Course;
import enumerated.menu.Food;

//: concurrency/RestaurantWithQueues.java


//This is given to the waiter who gives it to chef.
class Order {
	private static int count = 0;
	private final int id = count++;
	private Customer2 customer;
	private WaitPerson waitPerson;
	private Food food;
	
	public Order(Customer2 customer, WaitPerson waitPerson, Food f) {
		this.customer = customer;
		this.waitPerson = waitPerson;
		food = f;
	}
	
	public Customer2 getCustomer(){
		return customer;
	}
	
	public WaitPerson getWaitPerson(){
		return waitPerson;
	}
	
	public Food getFood(){
		return food;
	}
	
	public String toString(){
		return "Order: " + id + " food: " + food 
				+ " for " + customer + " severed by " + waitPerson;
	}
	
}

//This is what comes back from chef
class Plate {
	private Order order;
	private Food f;
	
	public Plate(Order order, Food food) {
		this.order = order;
		f = food;
	}
	
	public Food getFood(){
		return f;
	}
	
	public Order getOrder(){
		return order;
	}
	
	public String toString(){
		return f.toString();
	}
}



class Customer2 implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private WaitPerson waitPerson;
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();
	
	public Customer2(WaitPerson waitPerson) {
		this.waitPerson = waitPerson;
	}
	
	public void deliver(Plate p) throws InterruptedException{
		placeSetting.put(p);
	}
	

	@Override
	public void run() {
		for(Course course : Course.values()){
			Food food = course.randomSelection();
			try{
				waitPerson.placeOrder(this, food);
				//Blocks until course has been delivered.
				System.out.println(this + " eating " + placeSetting.take());
			}catch(InterruptedException e){
				System.out.println(this + " waiting for " + course + "interrupted.");
				break;
			}
		}
		System.out.println(this + " finishing meal, leaving");
	}
	
	public String toString(){
		return "Customer " + id;
	}
	
}

class WaitPerson implements Runnable{
	private static int count = 0;
	private final int id = count++;
	
	private final Restaurant restaurant;
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();
	
	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	} 
	
	public void placeOrder(Customer2 customer, Food food){
		try{
			restaurant.orders.put(new Order(customer, this, food));
		}catch(InterruptedException e){
			System.out.println(this + " place order interrupted.");
		}
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				//Blocks until a course is ready
				Plate plate = filledOrders.take();
				System.out.println(this + " received " + plate + " delevering to " 
						+ plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		}catch(InterruptedException e){
			System.out.println(this + " interrupted.");
		}
		System.out.println(this + " off duty.");
	}
	
	public String toString(){
		return "WaitPerson " + id + " ";
	}
	
}


class Chef implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private final Restaurant restaurant;
	private static Random random = new Random(47);
	
	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				//Blocks until a order appear
				Order order = restaurant.orders.take();
				Food food = order.getFood();
				TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
				Plate plate = new Plate(order, food);
				order.getWaitPerson().filledOrders.put(plate);
			}
		}catch(InterruptedException e){
			System.out.println(this + " interrupted.");
		}
		
		System.out.println(this + " off duty.");
	}
	
	public String toString(){
		return "Chef " + id + " ";
	}
	
}

class Restaurant implements Runnable{
	private List<WaitPerson> waitPersons = new ArrayList<>();
	private List<Chef> chefs = new ArrayList<>();
	private ExecutorService exec;
	
	private static Random random = new Random(47);
	BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
	
	public Restaurant(ExecutorService exec, int nWaitPersons, int nChefs) {
		this.exec = exec;
		for(int i = 0; i < nWaitPersons; i++){
			WaitPerson waitPerson = new WaitPerson(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
		}
		
		for(int i = 0; i < nChefs; i++){
			Chef chef = new Chef(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				//a new customer arrive, assign a waitPerson
				WaitPerson wp = waitPersons.get(random.nextInt(waitPersons.size()));
				Customer2 cust = new Customer2(wp);
				exec.execute(cust);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch(InterruptedException e){
			System.out.println("Restaurant interrupted.");
		}
		System.out.println("Restaurant closing.");
	}
	
}


public class RestaurantWithQueues {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Restaurant(exec, 5, 2));
		
		TimeUnit.SECONDS.sleep(3);
		
		exec.shutdownNow();
	}
}
