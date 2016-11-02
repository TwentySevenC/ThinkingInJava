package generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static util.util.Tuple.*;
import util.util.TwoTuple;

//动态代理-混型

class MixinProxy implements InvocationHandler {
	Map<String, Object> delegateByMethod;
	public MixinProxy(@SuppressWarnings("unchecked") TwoTuple<Object, Class<?>> ... pairs) {
		delegateByMethod = new HashMap<>();
		for(TwoTuple<Object, Class<?>> pair : pairs) 
			for(Method method : pair.second.getMethods()) {
				String methodName = method.getName();
				if(!delegateByMethod.containsKey(methodName)) {
					delegateByMethod.put(methodName, pair.first);
				}
			}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object delegate = delegateByMethod.get(methodName);
		return method.invoke(delegate, args);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object newInstance(TwoTuple ... pairs) {
		Class<?>[] classes = new Class[pairs.length];
		for(int i = 0; i < pairs.length; i++) {
			classes[i] = (Class<?>)pairs[i].second;
		}
		ClassLoader cl = pairs[0].first.getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, classes, new MixinProxy(pairs));
	}
	
}

public class DynamicProxyMixin {
	public static void main(String[] args) {
		Object mixin = MixinProxy.newInstance(
				tuple(new BasicImp(), Basic.class),
				tuple(new TimeStampedImp(), TimeStamped.class),
				tuple(new SerialNumberedImp(), SerialNumbered.class));
		
		Basic b = (Basic) mixin;
		TimeStamped t = (TimeStamped) mixin;
		SerialNumbered s = (SerialNumbered) mixin;
		b.set("Mixin");
		System.out.println(b.get());
		System.out.println(t.getStamp());
		System.out.println(s.getSerialNumber());
	}
}
