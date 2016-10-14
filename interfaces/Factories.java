package interfaces;

import static util.util.Print.*;

/**
 * 接口和工厂方法
 * @author liujian
 *
 */

interface Service {
	void method1();
	void method2();
}

interface ServiceFactory {
	Service getService();
}

class Implementation1 implements Service {

	@Override
	public void method1() {
		print("Implementation1 method1()");
	}

	@Override
	public void method2() {
		print("Implementation1 method2()");
	}
}

class Implementation1Factory implements ServiceFactory {

	@Override
	public Service getService() {
		return new Implementation1();
	}
	
}

class Implementation2 implements Service {

	@Override
	public void method1() {
		print("Implementation2 method1()");
	}

	@Override
	public void method2() {
		print("Implementation2 method2()");
	}
	
}

class Implementation2Factory implements ServiceFactory {

	@Override
	public Service getService() {
		return new Implementation2();
	}
	
}

public class Factories {
	public static void serviceConsumer(ServiceFactory factory) {
		Service s = factory.getService();
		s.method1();
		s.method2();
	}
	
	public static void main(String[] args) {
		serviceConsumer(new Implementation1Factory());
		serviceConsumer(new Implementation2Factory());
	}
}
