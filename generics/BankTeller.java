package generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import util.util.Generator;

class Customer {
	private static long count = 1;
	private final long id = count++;
	private Customer() { }
	@Override
	public String toString() {
		return "Customer " + id;
	}
	
	public static Generator<Customer> generator() {
		return new Generator<Customer>() {

			@Override
			public Customer next() {
				return new Customer();
			}
		};
	}
}


class Teller {
	private static long count = 1;
	private final long id = count++;
	private Teller() {
		
	}
	
	@Override
	public String toString() {
		return "Teller " + id;
	}
	
	public static Generator<Teller> generator() {
		return new Generator<Teller>() {

			@Override
			public Teller next() {
				return new Teller();
			}
		};
	}
}


public class BankTeller {
	public static void serve(Teller teller, Customer customer) {
		System.out.println(teller + " serves " + customer);
	}
	
	public static void main(String[] args) {
		Random rand = new Random(47);
		Queue<Customer> customers = new LinkedList<>();
		Generators.fill(customers, Customer.generator(), 15);
		List<Teller> tellers = new ArrayList<>();
		Generators.fill(tellers, Teller.generator(), 4);
		for(Customer customer : customers) {
			serve(tellers.get(rand.nextInt(tellers.size())), customer);
		}
	}
}
