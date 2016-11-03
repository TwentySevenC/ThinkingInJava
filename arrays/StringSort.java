package arrays;

import util.util.Generated;
import util.util.RandomGenerator;

import static util.util.Print.*;

import java.util.Arrays;
import java.util.Collections;

public class StringSort {
	public static void main(String[] args) {
		String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
		print("Before sort: " + Arrays.toString(sa));
		
		Arrays.sort(sa);
		print("After sort: " + Arrays.toString(sa));
		
		Arrays.sort(sa, Collections.reverseOrder());
		print("Reverse sort: " + Arrays.toString(sa));
		
		Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
		print("Case-insensitive sort: " + Arrays.toString(sa));
	}
}
