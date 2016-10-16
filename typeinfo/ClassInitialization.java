package typeinfo;

import static util.util.Print.print;

import java.util.Random;

//类字面常量 ClassA.class
//当使用.class来创建对Class对象的引用时，不会自动初始化该Class对象。为了使用类而做的准备工作实际包括3个步骤：
// 1.加载，这是由类加载器执行的。该步骤将查找字节码（通常在classpath所指定的路径中查找，但并非是必须的），
//   并从这些字节码中创建一个Class对象。
// 2.链接。在链接阶段将验证类的字节码，为静态域分配存储空间，并且如果必须的话，将解析这个类创建的对其他类的所有引用。
// 3.初始化。如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块。
//

class Initiable {
	static final int staticFinal = 47;
	//非编译器常量
	static final int staticFinal2 = ClassInitialization.rand.nextInt(47);
	static {
		print("Initializing Initiable");
	}
}

class Initiable2 {
	static int staticNonFinal = 147;
	static {
		print("Initializing Initiable2");
	}
}

class Initiable3 {
	static int staticNonFinal = 47;
	static {
		print("Initializing Initiable3");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) throws ClassNotFoundException {
		Class initiable = Initiable.class;//.class 不会引起初始化
		print("After creating Initiable ref");//static final 编译器常量 不会引起初始化
		// does not trigger initialization
		print(Initiable.staticFinal);// static final 但是非编译器常量 会引起初始化
		// trigger initialization
		print(Initiable.staticFinal2);// static 会引起静态域初始化
		// trigger initialization
		print(Initiable2.staticNonFinal);
		Class initiable2 = Class.forName("typeinfo.Initiable3");//会引用静态域初始化
		print("After creating Initiable3 ref");
		print(Initiable3.staticNonFinal);
	}
}
