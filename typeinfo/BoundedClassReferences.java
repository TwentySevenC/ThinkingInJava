package typeinfo;

//向Class引用添加泛型语法的原因仅仅是为了提供编译期类型检查

public class BoundedClassReferences {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Class<? extends Number> bounded = int.class;
		bounded = double.class;
		bounded = short.class;
	}
}
