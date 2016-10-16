package innerclasses;

import static util.util.Print.*;

//Nested classes can access all members of all levels of the classes they are nested within
//一个内部类被嵌套多少层并不重要，它能透明地访问所有它所嵌入的外围类的所有成员。

class MNA {
	private void f() {print("MNA f()");}
	class A {
		private void g() {print("A g()");}
		public class B {
			void h() {
				print("B h()");
				f();
				g();
			}
		}
	}
}

public class MultiNestingAccess {
	public static void main(String[] args) {
		MNA mna = new MNA();
		MNA.A a = mna.new A();
		MNA.A.B b = a.new B();
		b.h();
	}
}
