package generics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class NeedCasting {
	@SuppressWarnings({ "unchecked", "resource", "unused" })
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
		List<Widget> shapes = (List<Widget>) ois.readObject();
	}
}
