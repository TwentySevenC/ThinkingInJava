package generics;

import util.util.BasicGenerator;
import util.util.Generator;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
		for(int i = 0; i < 6; i++) {
			System.out.println(gen.next());
		}
	}
}
