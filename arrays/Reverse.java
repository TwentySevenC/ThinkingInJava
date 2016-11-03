package arrays;

import util.util.Generated;

import static util.util.Print.*;

import java.util.Arrays;
import java.util.Collections;

public class Reverse {
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		print("Before sorting");
		print(Arrays.toString(a));
		print("After sorting");
		Arrays.sort(a, Collections.reverseOrder());
		print(Arrays.toString(a));
	}
}
