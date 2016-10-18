package typeinfo;

import java.lang.reflect.Field;

class WithPrivateFinalFields {
	private int i = 1;
	private final String s = "I'm totally safe.";
	private String s2 = "Am I safe?";
	@Override
	public String toString() {
		return "i = " + i + ", " + s + ", " + s2;
	}
}

public class ModifyingPrivateFields {

	public static void main(String[] args) throws Exception {
		WithPrivateFinalFields wpff = new WithPrivateFinalFields();
		System.out.println(wpff);
		Field f = wpff.getClass().getDeclaredField("i");
		f.setAccessible(true);
		System.out.println("f.getInt(wpff): " + f.getInt(wpff));
		f.setInt(wpff, 47);
		System.out.println(wpff);
		f = wpff.getClass().getDeclaredField("s");
		f.setAccessible(true);
		System.out.println("f.get(wpff): " + f.get(wpff));
		f.set(wpff, "No, you are not!");
		System.out.println(wpff);
		f = wpff.getClass().getDeclaredField("s2");
		f.setAccessible(true);
		System.out.println("f.get(wpff): " + f.get(wpff));
		f.set(wpff, "No, you are not!");
		System.out.println(wpff);
	}
}
