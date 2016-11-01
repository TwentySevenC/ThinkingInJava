package generics;

import java.util.List;
import java.util.ArrayList;

public class GenericsAndCovariance {
	public static void main(String[] args) {
		List<? extends Fruit> fList = new ArrayList<Apple>();
		//compile error
//		fList.add(new Apple());
//		fList.add(new Orange());
//		fList.add(new Fruit());
//		fList.add(new Object());
		fList.add(null);
		@SuppressWarnings("unused")
		Fruit f = fList.get(0);
	}
}
