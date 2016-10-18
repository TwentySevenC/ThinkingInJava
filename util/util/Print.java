package util.util;

import java.io.PrintStream;

public class Print {
	// Print with a new line
	public static void print(Object s){
		System.out.println(s);
	}
	
	// Print a new line
	public static void print() {
		System.out.println();
	}
	
	
	//Print with no line break
	public static void printnb(Object obj) {
		System.out.print(obj);
	}
	
	//
	public static PrintStream printf(String format, Object ... args) {
		return System.out.printf(format, args);
	}
}
