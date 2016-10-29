package generics;

import java.util.Iterator;

//Adapt the Fibonacci class to make it Iterable

public class IterableFibnacci extends Fibonacci implements Iterable<Integer> {
	private int count;
	
	public IterableFibnacci(int count) {
		this.count = count;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public Integer next() {
				count--;
				return IterableFibnacci.super.next();
			}
		};
	}
	
	
	public static void main(String[] args) {
		for(int i : new IterableFibnacci(18)) {
			System.out.println(i + " ");
		}
	}

}
