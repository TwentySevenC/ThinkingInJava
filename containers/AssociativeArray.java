package containers;


// container/AssociativeArray.java

public class AssociativeArray<K, V> {
	private int size;
	private Object[][] pairs;
	private int index;
	public AssociativeArray(int length){
		pairs = new Object[length][2];
		size = length;
	}
	

	public void put(K key, V value){
		if(index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		pairs[index++] = new Object[]{key, value};
	}
	
	@SuppressWarnings("unchecked")
	public V get(K key){
		for(int i = 0; i < pairs.length; i++){
			if(pairs[i][0].equals(key)){
				return (V)pairs[i][1];
			}
		}
		return null;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < size; i++){
			builder.append(pairs[i][0]);
			builder.append(" : ");
			builder.append(pairs[i][1]);
			if(i < size - 1){
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		AssociativeArray<String, String> map = new AssociativeArray<>(5);
		map.put("daughter", "king");
		map.put("dog", "Enzo");
		map.put("son", "mackey");
		map.put("wife", "Lee");
		map.put("me", "Leo");
		
		System.out.println(map);
		
		try{
			map.put("dad", "andree");
		}catch(IndexOutOfBoundsException e){
			System.out.println(e);
		}
	}
}
