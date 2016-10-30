package generics;

import java.util.ArrayList;
import java.util.Collection;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import util.util.Generator;


// A utility to use with Generators

public class Generators {
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> g, int size) {
		for(int i = 0; i < size; i++) {
			coll.add(g.next());
		}
		return coll;
	}
	
	public static void main(String[] args) {
		Collection<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 5);
		for(Coffee co : coffees) {
			System.out.println(co);
		}
		
		Collection<Integer> fnumbers = fill(new ArrayList<>(), new Fibonacci(), 12);
		for(int i : fnumbers) {
			System.out.println(i + " ");
		}
	}
	
}
