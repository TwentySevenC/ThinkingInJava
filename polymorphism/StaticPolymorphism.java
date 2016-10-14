package polymorphism;

/**
 * Static method are not polymorphism
 * 只有普通方法的调用可以是多态的
 * @author liujian
 *
 */

class StaticSuper {
	public static String staticGet() {
		return "Base staticGet()";
	}
	
	public String dynamicGet() {
		return "Base dynamicGet()";
	}
}

class StaticSub extends StaticSuper {
	public static String staticGet() {
		return "Derived staticGet()";
	}
	
	public String dynamicGet() {
		return "Derived dynamicGet()";
	}
}

public class StaticPolymorphism {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		StaticSuper sup = new StaticSub();
		System.out.println(sup.staticGet());
		System.out.println(sup.dynamicGet());
	}
}
