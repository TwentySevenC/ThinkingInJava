package containers;

import java.util.Iterator;

import util.util.Generator;
import util.util.MapData;
import util.util.Pair;
import util.util.RandomGenerator;

//: containers/MapDataTest.java

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer>{
	private int number = 1;
	private int size = 9;
	private char letter = 'A';

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return number < size;
			}

			@Override
			public Integer next() {
				return number++;
			}
		};
	}

	@Override
	public Pair<Integer, String> next() {
		return new Pair<Integer, String>(number++, "" + letter++);
	}
	
}

public class MapDataTest {
	public static void main(String[] args) {
		System.out.println(MapData.map(new Letters(), 10));
		
		System.out.println(MapData.map(new Letters(), new RandomGenerator.String()));
		
		System.out.println(MapData.map(new Letters(), " hello "));
	}

}
