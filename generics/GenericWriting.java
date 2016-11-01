package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericWriting {
	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
		System.out.println(item.getClass().getSimpleName());
	}
	
	static List<Apple> apples = new ArrayList<Apple>();
	static List<Fruit> fruits = new ArrayList<Fruit>();
	
	static void f1() {
		writeExact(apples, new Apple());
		writeExact(fruits, new Apple());
	}
	
	static <T> void writeWithWildcast(List<? super T> list, T item) {
		list.add(item);
	}
	
	static void f2() {
		writeWithWildcast(apples, new Apple());
		writeWithWildcast(fruits, new Apple());
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		f1();
		f2();
		Apple a = (Apple) fruits.get(0);
	}
}
