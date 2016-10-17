package typeinfo;

import static util.util.Print.print;

interface Interface {
	void doSomething();
	void doSomethingElse(String args);
}

class RealObject implements Interface {

	@Override
	public void doSomething() {
		print("doSomething");
	}

	@Override
	public void doSomethingElse(String args) {
		print("doSomethingElse " + args);
	}
	
}


class SimpleProxy implements Interface {
	private Interface proxied;
	
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}

	@Override
	public void doSomething() {
		print("SimpleProxy doSomething");
		proxied.doSomething();
	}

	@Override
	public void doSomethingElse(String args) {
		print("SimpleProxy doSomethingElse " + args);
		proxied.doSomethingElse(args);
	}
	
}

public class SimpleProxyDemo {
	
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.doSomethingElse("bonobo");
	}

	
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}

}
