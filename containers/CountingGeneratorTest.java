package containers;

import java.util.ArrayList;
import java.util.List;


class IntegerAddress {
	private int index;
	public IntegerAddress(int value){
		index = value;
	}
	public String toString(){
		return super.toString() + index;
	}
}

public class CountingGeneratorTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int index = 0;
		
		for(int i = 0; i < 10; i++){
			list.add(index++);
		}
		
		for(Integer i : list){
			System.out.println(i.toString());
			i.hashCode();
		}
		
		Integer value = 0;
		System.out.println(value.hashCode());
		value++;
		System.out.println(value.hashCode());
	}
}
