package containers;

// container/Individual.java

public class Individual implements Comparable<Individual>{
	private static int count = 0;
	private final int id = count++;
	
	private String name;
	public Individual(String name) {
		this.name = name;
	}
	
	public Individual() {}
	
	@Override
	public String toString(){
		return getClass().getSimpleName() + " " + name == null ? "" : name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Individual && ((Individual)obj).id == id;
	};
	
	@Override
	public int hashCode(){
		int result = 17;
		if(name != null)
			result = result * 37 + name.hashCode();
		result = result * 37 + id;
		return result;
	}
	
	@Override
	public int compareTo(Individual o) {
		String first = getClass().getSimpleName();
		String argsFirst = o.getClass().getSimpleName();
		int firstCompare = first.compareTo(argsFirst);
		if(firstCompare != 0)
			return firstCompare;
		
		if(name != null && o.name != null){
			int secondCompare = name.compareTo(o.name);
			if(secondCompare != 0)
				return secondCompare;
		}
		
		return id > o.id ? 1 : (id == o.id ? 0 : -1);
	}
	
}
