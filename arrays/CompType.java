package arrays;

import java.util.Arrays;
import java.util.Random;

import util.util.Generated;
import util.util.Generator;

import static util.util.Print.*;

public class CompType implements Comparable<CompType> {
	
	int i, j;
	private static int count = 1;
	
	public CompType(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public String toString() {
		String result = "( i = " + i + ", j = " + j + ")";
		if(count++%3 == 0) result += "\n";
		return result;
	}
	

	@Override
	public int compareTo(CompType o) {
		return i < o.i ? -1 : (i == o.i ? 0 : 1);
	}
	
	private static Random rand = new Random(47);
	public static Generator<CompType> generator() {
		return new Generator<CompType>() {

			@Override
			public CompType next() {
				return new CompType(rand.nextInt(100), rand.nextInt(100));
			}
		};
	}
	
	
	public static void main(String[] args) {
		CompType[] a = Generated.array(CompType.class, CompType.generator(), 10);
		print("Before sorting");
		print(Arrays.toString(a));
		Arrays.sort(a);
		print("After sorting");
		print(Arrays.toString(a));
	}

}
