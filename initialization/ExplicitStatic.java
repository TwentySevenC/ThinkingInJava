package initialization;

import static util.util.Print.print;

/**
 * 静态子句
 * @author liujian
 *
 */

class Cup {
	Cup(int marker) {
		print("Cup(" + marker + ")");
	}
	
	void f(int marker) {
		print("f(" + marker + ")");
	}
}

class Cups {
	static Cup cup1;
	static Cup cup2;
	static {//静态子句只会执行一次，区别于实例初始化语句
		cup1 = new Cup(1);
		cup2 = new Cup(2);
		print("cup1 & cup2 initialized");
	}
	
	Cups() {
		print("Cups()");
	}
}

public class ExplicitStatic {
	public static void main(String[] args) {
		print("Inside main()");
//		Cups.cup1.f(1);
	}
	
	static Cups cups1 = new Cups();
	static Cups cups2 = new Cups();
}
