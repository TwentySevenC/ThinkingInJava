package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static util.util.Print.*;

class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler(Object obj) {
		this.proxied = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		print("**** proxy: " + proxy.getClass() + ", method : " + method + ", args: " + args);
		if(args != null) {
			for(Object obj : args) {
				print(" " + obj);
			}
		}
		return method.invoke(proxied, args);
	}
	
}

public class SimpleDynamicProxy {
	
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.doSomethingElse("bonobo");
	}

	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		
		Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), 
				new Class[]{Interface.class}, 
				new DynamicProxyHandler(real));
		consumer(proxy);
	}

}
