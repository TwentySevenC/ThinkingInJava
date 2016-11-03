package arrays;

import util.util.CountingGenerator;
import util.util.Generator;

public class GeneratorsTest {
	
	public static int size = 10;
	public static void test(Class<?> surroundingClass) {
		for(Class<?> clazz : surroundingClass.getClasses()) {
			System.out.print(clazz.getSimpleName() + ":");
			try {
				Generator<?> g = (Generator<?>) clazz.newInstance();
				for(int i = 0; i < size; i++) {
					System.out.print(g.next() + " ");
				}
				System.out.println();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
	
	public static void main(String[] args) {
		test(CountingGenerator.class);
	}
}
