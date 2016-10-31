package generics;

class ClassAsFactory<T> {
	T x;
	public ClassAsFactory(Class<T> kind) {
		try {
			x = kind.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public String toString() {
		return x.getClass().getSimpleName();
	}
}

class Employee {}


public class InstantiateGenericType {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
		System.out.println("ClassAsFactory<Employee> succeeded " + fe);
		try {
			ClassAsFactory<Integer> fi = new ClassAsFactory<Integer>(Integer.class);//Integer没有任何默认的构造器
		} catch (Exception e) {
			System.out.println("ClassAsFactory<Integer> failed");
		}
	}
}
