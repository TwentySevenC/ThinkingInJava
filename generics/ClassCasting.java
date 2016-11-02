package generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ClassCasting {
	@SuppressWarnings({"resource", "unused"})
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
//		List<Widget> lw1 = List<Widget>.class.cast(ois.readObject());
		@SuppressWarnings("unchecked")
		List<Widget> lw2 = List.class.cast(ois.readObject());
	}
}
