package generics;

import java.util.List;

public class SuperTypeWildcards {
	/**
	 * apples 是由某个Apple的基类所组成的list，那么向其中加入Apple及Apple的子类是类型安全的
	 * @param apples
	 */
	static void writeTo(List<? super Apple> apples) {
		apples.add(new Apple());
		apples.add(new Jonathan());
//		apples.add(new Fruit());
		
	}
}
