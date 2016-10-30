package util.util;

import java.util.ArrayList;
import java.util.List;

public class GenericsVarargs {
	public static <T> List<T> makeList(@SuppressWarnings("unchecked") T... args) {
		List<T> result = new ArrayList<>();
		for(T t : args) {
			result.add(t);
		}
		return result;
	}
}
