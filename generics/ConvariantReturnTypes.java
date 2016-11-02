package generics;

//协变返回类型

class Base {}
class DerivedBase extends Base {}

interface OrdinaryGetter {
	Base get();
}

interface DerivedGetter extends OrdinaryGetter {
	//Return type of overridden method is allowed to vary
	DerivedBase get();
}

public class ConvariantReturnTypes {
	@SuppressWarnings("unused")
	void test(DerivedGetter d) {
		DerivedBase d2 = d.get();
	}
}
