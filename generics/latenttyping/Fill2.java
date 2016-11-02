package generics.latenttyping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import generics.SimpleQueue;
import generics.coffee.Coffee;
import generics.coffee.Latte;
import generics.coffee.Mocha;
import util.util.Generator;

interface Addable<T> {
	void add(T t);
}

public class Fill2 {
	//ClassToken version
	public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
		for(int i = 0; i < size; i++) {
			try {
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
	
	//Generator version
	public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
		for(int i = 0; i < size; i++) {
			addable.add(generator.next());
		}
	}
}


//To adapt a base type, you must use composition, make any Collection Addable using composition
class AddableCollectionAdapter<T> implements Addable<T> {
	private Collection<T> collection;
	
	public AddableCollectionAdapter(Collection<T> c) {
		collection = c;
	}

	@Override
	public void add(T t) {
		collection.add(t);
	}
	
}

class Adapter {
	public static <T> Addable<T> collectionAdapter(Collection<T> c) {
		return new AddableCollectionAdapter<>(c);
	}
}


//To adapt a specific type, you can use inheritance.
//Make a SimpleQueue Addable using inheritance
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {

	@Override
	public void add(T t) {
		super.add(t);
	}
	
}

class FillTest2 {
	public static void main(String[] args) {
		//Adapt a Collection
		List<Coffee> carrier = new ArrayList<>();
		Fill2.fill(new AddableCollectionAdapter<>(carrier), Coffee.class, 3);
		Fill2.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
		for(Coffee coffee : carrier)
			System.out.println(coffee);
		
		System.out.println();
		
		//Using a adapted class
		AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<>();
		Fill2.fill(coffeeQueue, Mocha.class, 4);
		Fill2.fill(coffeeQueue, Latte.class, 1);
		for(Coffee co : coffeeQueue) 
			System.out.println(co);
	}
}


