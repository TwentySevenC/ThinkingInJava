package arrays;

import java.util.Arrays;

import util.util.ConvertTo;
import util.util.CountingGenerator;
import util.util.Generated;

public class PrimitiveConvasionDemonstration {
	public static void main(String[] args) {
		Integer[] a = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
		int[] b = ConvertTo.primitive(a);
		System.out.println(Arrays.toString(b));
		
		boolean[] c = ConvertTo.primitive(Generated.array(Boolean.class, new CountingGenerator.Boolean(), 7));
		System.out.println(Arrays.toString(c));
	}
}
