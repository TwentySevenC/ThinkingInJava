package generics.latenttyping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fill {
	public static <T> void fill(Collection<T> collection, Class<? extends T> classToken, int size) {
		for(int i = 0; i < size; i++) {
			try {
				collection.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
}

class Constract {
	private static int counter = 1;
	private final int id = counter++;
	@Override
	public String toString() {
		return getClass().getSimpleName() + id;
	}
}

class TitleTransfer extends Constract {}

class FillTest {
	public static void main(String[] args) {
		List<Constract> constracts = new ArrayList<>();
		Fill.fill(constracts, Constract.class, 3);
		Fill.fill(constracts, TitleTransfer.class, 2);
		for(Constract c : constracts) {
			System.out.println(c);
		}
		
//		SimpleQueue<Constract> constractQueue = new SimpleQueue<>();
//		Fill.fill(constractQueue, Constract.class, 2);
	}
}
