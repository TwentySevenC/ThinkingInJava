package containers;

import java.util.ArrayList;
import java.util.HashSet;

import util.util.CollectionData;
import util.util.RandomGenerator;

//: container/CollectionDataGeneration.java

public class CollectionDataGeneration {
	public static void main(String[] args) {
		System.out.println(new ArrayList<String>(CollectionData.list(new RandomGenerator.String(9), 10)));
		
		System.out.println(new HashSet<Integer>(CollectionData.list(new RandomGenerator.Integer(), 10)));
	}
}
