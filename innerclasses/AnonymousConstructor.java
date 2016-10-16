package innerclasses;

import static util.util.Print.*;

abstract class Base {
	public Base(int i) {
		print("Base constructor, i = " + i);
	}
	public abstract void f();
}

public class AnonymousConstructor {
	public static Base getBase(int i) {
		return new Base(i) {
			{
				print("Inside instance initializer");//实例初始化
			}
			
			@Override
			public void f() {
				print("In anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = getBase(2);
		base.f();
	}
}
