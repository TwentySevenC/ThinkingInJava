package generics.coffee;

import java.util.Iterator;
import java.util.Random;

import util.util.Generator;

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class<?>[] types = new Class[]{
			Latte.class, Mocha.class, Cappuccino.class, Breve.class, Americano.class
	};
	
	public CoffeeGenerator() {
	}
	
	private int size = 0;
	public CoffeeGenerator(int size) {
		this.size = size;
	}
	
	private Random random = new Random(47);

	@Override
	public Coffee next() {
		try {
			return (Coffee) types[random.nextInt(types.length)].newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator(size);
	}
	
	private class CoffeeIterator implements Iterator<Coffee> {
		int count;
		public CoffeeIterator(int size) {
			count = size;
		}
		
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}		
	}
	
	public static void main(String[] args) {
		CoffeeGenerator cg = new CoffeeGenerator();
		for(int i = 0; i < 5; i++) {
			System.out.println(cg.next());
		}
		
		for(Coffee coffee : new CoffeeGenerator(5)) {
			System.out.println(coffee);
		}
	}
	

}
