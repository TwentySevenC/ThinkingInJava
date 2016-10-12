package initialization;

import static util.util.Print.print;

/**
 * Java Instance Initialization
 * @author liujian
 *
 */

class Mug {
	Mug(int marker) {
		print("Mug(" + marker + ")");
	}
	void f(int marker) {
		print("f(" + marker + ")");
	}
}

class Mugs {
	Mug mug1;
	Mug mug2;
	{//实例初始化 区别于 显示的静态初始化
		mug1 = new Mug(1);
		mug2 = new Mug(2);
		print("mug1 & mug2 initialized");//每次创建新对象时都会执行，显示的静态初始化只会执行一次
	}
	
	public Mugs() {
		print("Mugs()");
	}
	
	public Mugs(int marker) {
		print("Mugs(int)");
	}
}

public class InstanceInitialization {
	public static void main(String[] args) {
		print("Inside main");
		new Mugs();
		print("new Mugs() completed.");
		new Mugs(2);
		print("new Mugs(2) completed.");
	}
}
