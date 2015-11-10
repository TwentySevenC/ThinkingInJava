package concurrency.simulation;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;




//: concurrency/BankTellerSimulation.java
//Using queues and multiTherad

class Customer {
	private final int serverTime ;
	public Customer(int serverTime) {
		this.serverTime = serverTime;
	}
	
	public int getServerTimme(){
		return serverTime;
	}
	
	public String toString(){
		return "[" + serverTime + "]";
	}
}


class CustomerLine extends ArrayBlockingQueue<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerLine(int maxSize) {
		super(maxSize);
	}
	
	public String toString(){
		if(this.size() == 0){
			return "Empty";
		}
		
		StringBuilder builder = new StringBuilder();
		for(Customer customer : this){
			builder.append(customer);
		}
		
		return builder.toString();
	}
	
}

class CustomerGenerator implements Runnable{
	private Random random = new Random(47);
	private CustomerLine customers;
	public CustomerGenerator(CustomerLine customers) {
		this.customers = customers;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
				customers.offer(new Customer(random.nextInt(1000)));
			}
		}catch(InterruptedException e){
			System.out.println("CustomerGenerator interrupted.");
		}
		System.out.println("CustomerGenerator end.");
	}
	
}


class Teller implements Runnable, Comparable<Teller>{
	private static int count = 1;
	private final int id = count++;
	private CustomerLine customers;
	private int serverCount = 0;
	private boolean isServerCustomerLine = false;
	
	public Teller(CustomerLine customers){
		this.customers = customers;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				TimeUnit.MILLISECONDS.sleep(customers.take().getServerTimme());
				
				synchronized (this) {
					serverCount++;
					
					while(!isServerCustomerLine)
						wait();
				}
				
			}
		}catch(InterruptedException e){
			System.out.println("Teller " + id + " interrupted.");
		}
		System.out.println("Teller " + id + " exit.");
	}
	
	public synchronized void doingOtherThings(){
		isServerCustomerLine = false;
		serverCount = 0;
	}
	
	public synchronized void serverCustomerLine(){
		isServerCustomerLine = true;
		notifyAll();
	}
	
	public String shortString(){
		return "T " + id;
	}
	
	public String toString(){
		return "Teller " + id;
	}

	@Override
	public synchronized int compareTo(Teller otherTeller) {
		return serverCount < otherTeller.serverCount ? -1 : 
			(serverCount == otherTeller.serverCount ? 0 : 1);
	}
	
}


class TellerManager implements Runnable{
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
	private Queue<Teller> tellersDoingOtherthing = new LinkedList<>();
	private ExecutorService exec;
	private CustomerLine customers;
	private int adjustPeriod;
	
	public TellerManager(ExecutorService exec, CustomerLine customers, int adjustPeriod) {
		this.exec = exec;
		this.customers = customers;
		this.adjustPeriod = adjustPeriod;
		
		//Create a single teller
		Teller teller = new Teller(customers);
		workingTellers.add(teller);
		exec.execute(teller);
	}
	
	public void adjustTellerNumber(){
		if(customers.size()/workingTellers.size() > 2){
			if(tellersDoingOtherthing.size() > 0){
				Teller teller = tellersDoingOtherthing.poll();
				teller.serverCustomerLine();
				workingTellers.offer(teller);
				return ;
			}
			
			Teller teller = new Teller(customers);
			workingTellers.add(teller);
			exec.execute(teller);
			return ;
		}
		
		if(customers.size()/workingTellers.size() < 2 && workingTellers.size() > 1 && customers.size() > 0){
			reassignOneTeller();
			return ;
		}
		
		if(customers.size() == 0){
			while(workingTellers.size() > 1){
				reassignOneTeller();
			}
		}
		
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(adjustPeriod);
				adjustTellerNumber();
				System.out.print(customers + "{");
				for(Teller teller : workingTellers){
					System.out.print(teller + " ");
				}
				System.out.println("}");
			}
		}catch(InterruptedException e){
			System.out.println("TellerManager interrupted.");
		}
		System.out.println("TellerManager terminating");
		
	}
	
	public void reassignOneTeller(){
		Teller teller = workingTellers.poll();
		tellersDoingOtherthing.offer(teller);
	}
	
	
	public String toString(){
		return " TellerManager ";
	}
	
}


public class BankTellerSimulation {
	static int maxSize = 20;
	static int adjustPeriod = 200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(maxSize);
		CustomerGenerator generator = new CustomerGenerator(customers);
		exec.execute(generator);
		
		TellerManager manager = new TellerManager(exec, customers, adjustPeriod);
		exec.execute(manager);
		
		TimeUnit.SECONDS.sleep(4);
		
		exec.shutdownNow();
		
	}

}
