package containers;


import java.util.LinkedHashSet;

import util.util.CollectionData;
import util.util.Generator;

//: container/CollectionDataTest.java

class Goverment implements Generator<String>{
	private String[] strs = "Hello Mack. How are you? I am fine, thank you, and you? yeah, I'm fine too.".split(" ");
	private int index;
	@Override
	public String next() {
		return strs[index++];
	}
	
}

public class CollectionDataTest {
	public static void main(String[] args) {
		LinkedHashSet<String> lhs = new LinkedHashSet<>(new CollectionData<>(new Goverment(), 14));
		System.out.println(lhs);
		lhs.addAll(CollectionData.list(new Goverment(), 14));
		System.out.println(lhs);
	}
}
