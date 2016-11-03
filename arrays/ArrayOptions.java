package arrays;

import static util.util.Print.*;

import java.util.Arrays;

// Initialization & re-assignment of arrays

//对象数组和基本类型数组在使用上几乎是相同的，唯一的区别就是对象数组保存的是引用，基本类型数组直接保存基本类型的值。

class BerylliumSphere {
	private static long counter;
	private final long id = counter++;
	@Override
	public String toString() {
		return "Sphere " + id;
	}
}

public class ArrayOptions {
	public static void main(String[] args) {
		//Arrays of objects
		BerylliumSphere[] a; //Local uninitialized variable
		BerylliumSphere[] b = new BerylliumSphere[5];
		//The references inside the array are automatically initialized to null
		print("b: " + Arrays.toString(b));
		
		BerylliumSphere[] c = new BerylliumSphere[4];
		for(int i = 0; i < 4; i++) {
			if(c[i] == null) //can test for null reference
				c[i] = new BerylliumSphere();
		}
		//Aggregate initialized
		BerylliumSphere[] d = new BerylliumSphere[]{
			new BerylliumSphere(),
			new BerylliumSphere(),
			new BerylliumSphere()
		};
		
		//Dynamic aggregate initialized
		a = new BerylliumSphere[] {
				new BerylliumSphere(),
				new BerylliumSphere(),
		};
		
		print("a.length: " + a.length);
		print("b.length: " + b.length);
		print("c.length: " + c.length);
		print("d.length: " + d.length);
		
		a = d;
		print("a.length: " + a.length);
		
		//Arrays of primitives
		int[] e; //Null reference
		int[] f = new int[5];
		print("f: " + Arrays.toString(f));
		int[] g = new int[4];
		//The primitives inside the array are automatically initialized to zero
		for(int i = 0; i < 4; i++) {
			g[i] = i*i;
		}
		int[] h = {11, 47, 93};
		//Compile error: variable e not initialized
		//print("e.length: " + e.length);
		print("f.length: " + f.length);
		print("g.length: " + g.length);
		print("h.length: " + h.length);
		e = h;
		print("e.length: " + e.length);
		e = new int[] {1, 2};
		print("e.length: " + e.length);
	}
}
