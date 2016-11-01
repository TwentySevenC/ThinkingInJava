package generics;

import java.util.Arrays;
import java.util.List;

public class GenericReading {
	static <T> T readExact(List<T> list) {
		return list.get(0);
	}
	
	static List<Apple> apples = Arrays.asList(new Apple());
	static List<Fruit> fruit = Arrays.asList(new Fruit());
	
	@SuppressWarnings("unused")
	static void f1() {
		Apple a = readExact(apples);
		Fruit f = readExact(fruit);
		f = readExact(apples);
	}
	
	// If, however, you have a class, then its type is established when the class is instantiated
	static class Reader<T> {
		T readExact(List<T> list) {
			return list.get(0);
		}
	}
	
	static void f2() {
		Reader<Fruit> fruitReader = new Reader<>();
		Fruit f = fruitReader.readExact(fruit);
//		fruitReader.readExact(apples);
	}
}
