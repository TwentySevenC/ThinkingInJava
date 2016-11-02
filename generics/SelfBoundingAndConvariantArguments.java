package generics;

interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
	void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {
	
}

public class SelfBoundingAndConvariantArguments {
	@SuppressWarnings("rawtypes")
	void testA(Setter s1, Setter s2, SelfBoundSetter s3) {
		s1.set(s2);
//		s1.set(s3);
		
	}
}
