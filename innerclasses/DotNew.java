package innerclasses;

// Creating an inner class directly using the .new syntax

public class DotNew {
	class Inner {}
	public static void main(String[] args) {
		DotNew dn = new DotNew();
		DotNew.Inner inner = dn.new Inner();
		System.out.println(inner.getClass().getName());
	}
}
