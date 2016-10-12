package initialization;

import static util.util.Print.print;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组初始化
 * 1. Java中可以将一个数组赋值给另一个数组，但是只是赋值了引用
 * 2. 当数组的类型是基本数据类型时，例如，int[] a = new int[5]; 数据元素中的基本数据类型值会自动初始化为空值，该数组已经获得了内存空间
 * 3. 当数组的类型是非基本数据类型时，Integer[] aa = new Integer[5]; aa还只是一个引用数组，并且直到通过创建Integer对象，并把对象赋值为引用，
 *    初始化进程才算结束。
 * @author liujian
 *
 */

public class ArrayNew {
	public static void main(String[] args) {
		int[] a;
		Random rand = new Random(47);
		a = new int[rand.nextInt(20)];
		print("length of a = " + a.length);
		print(Arrays.toString(a));
		
		Integer[] aa = new Integer[rand.nextInt(20)];
		print("length of aa = " + aa.length);
		for(int i = 0; i < aa.length; i++) {
			aa[i] = rand.nextInt(500);
		}
		print(Arrays.toString(aa));
	}
}
