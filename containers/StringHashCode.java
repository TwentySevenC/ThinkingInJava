package containers;

//: container/StringHashCode.java

public class StringHashCode {
	public static void main(String[] args) {
		String[] hellos = new String[]{"hello", "hello"};
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}

}
