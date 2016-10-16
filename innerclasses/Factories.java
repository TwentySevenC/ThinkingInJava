package innerclasses;

import static util.util.Print.print;

//工厂方法-使用匿名内部类的实现方式, 工厂方法使用匿名内部类，
//类的构造器可以使用private，另外，通常只需要单一的工厂方法，申明为static域更具时机意义

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
		print("Implementation1 method1");
	}

	@Override
	public void method2() {
		print("Implementation1 method2");
	}
	
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new Implementation1();
		}
	};
	
}

class Implementation2 implements Service {

	@Override
	public void method1() {
		print("Implementation2 method1");
	}

	@Override
	public void method2() {
		print("Implementation2 method2");
	}
	
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new Implementation2();
		}
	};
	
}

public class Factories {
	public static void serviceConsumer(ServiceFactory factory) {
		Service service = factory.getService();
		service.method1();
		service.method2();
	}
	public static void main(String[] args) {
		serviceConsumer(Implementation1.factory);
		serviceConsumer(Implementation2.factory);
	}
}
