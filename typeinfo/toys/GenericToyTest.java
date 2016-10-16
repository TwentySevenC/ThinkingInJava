package typeinfo.toys;

public class GenericToyTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		Class<FancyToy> clazz = FancyToy.class;
		FancyToy ft = clazz.newInstance();
		Class<? super FancyToy> up = clazz.getSuperclass();
//		Toy toy = up.newInstance();
		Object obj = up.newInstance();
	}
}
