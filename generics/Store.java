package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.util.Generator;

class Product {
	private final int id;
	private double price;
	private String description;
	
	public Product(int IDnumber, String descr, double price) {
		id = IDnumber;
		description = descr;
		this.price = price;
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return id + ": " + description + ", price: $" + price;
	}
	
	public void priceChange(double change) {
		price += change;
	}
	
	public static Generator<Product> generator 
		= new Generator<Product>() {
			private Random random = new Random(47);
			@Override
			public Product next() {
				return new Product(random.nextInt(1000), "Test", 
						Math.round(random.nextDouble() * 1000.0) + 0.99);
			}
		};
}

class Shelf extends ArrayList<Product> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Shelf(int nProducts) {
		Generators.fill(this, Product.generator, nProducts);
	}
}

class Aisle extends ArrayList<Shelf> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Aisle(int nShelves, int nProducts) {
		for(int i = 0; i < nShelves; i++) {
			add(new Shelf(nProducts));
		}
	}
	
}


class CheckoutStand {}

class Office {}

@SuppressWarnings("unused")
public class Store extends ArrayList<Aisle> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8105447956427555056L;	
	private List<CheckoutStand> checkoutStands = new ArrayList<>();
	private Office office = new Office();
	public Store(int nAisles, int nShelves, int nProducts) {
		for(int i = 0; i < nAisles; i++) {
			add(new Aisle(nShelves, nProducts));
		}
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for(Aisle aisle : this) 
			for(Shelf shelf : aisle)
				for(Product product : shelf) {
					result.append(product);
					result.append("\n");
				}
				
		return result.toString();
	}
	
	public static void main(String[] args) {
		Store store = new Store(3, 4, 10);
		System.out.println(store);
	}
}
