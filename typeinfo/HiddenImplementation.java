package typeinfo;

import java.lang.reflect.Method;

import typeinfo.interfacea.A;
import typeinfo.packageaccess.HiddenC;

public class HiddenImplementation {

	static void callHiddenMethod(Object a, String methodName) throws Exception {
		Method m = a.getClass().getDeclaredMethod(methodName);
		m.setAccessible(true);
		m.invoke(a);
	}
	
	public static void main(String[] args) throws Exception {
		A a = HiddenC.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		callHiddenMethod(a, "g");
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "v");
		callHiddenMethod(a, "w");
	}

}
