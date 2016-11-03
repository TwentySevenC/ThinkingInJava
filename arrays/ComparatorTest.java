package arrays;

import static util.util.Print.print;

import java.util.Arrays;
import java.util.Comparator;

import util.util.Generated;

class CompTypeComparator implements Comparator<CompType> {

	@Override
	public int compare(CompType o1, CompType o2) {
		return o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 : 1);
	}
	
}

public class ComparatorTest {
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		print("Before sorting");
		print(Arrays.toString(a));
		print("After sorting");
		Arrays.sort(a, new CompTypeComparator());
		print(Arrays.toString(a));
	}
}
