package containers;

import java.util.PriorityQueue;

// container/ToDoList.java

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	static class ToDoItem implements Comparable<ToDoItem>{
		private String item;
		private char primary;
		private int second;
		public ToDoItem(String item, char pri, int sec) {
			this.item = item;
			primary = pri;
			second = sec;
		}

		@Override
		public int compareTo(ToDoItem o) {
			
			if(primary > o.primary)
				return +1;
			
			if(primary == o.primary)
				if(second > o.second){
					return +1;
				}else if(second == o.second){
					return 0;
				}
			
			return -1;
		}
		
		public String toString(){
			return "" + primary + second + " : " + item;
		}
		
	}
	
	public void add(String item, char pri, int sec){
		super.add(new ToDoItem(item, pri, sec));
	}
	
	
	public static void main(String[] args) {
		ToDoList list = new ToDoList();
		list.add("Empty word.", 'C', 1);
		list.add("Hello Ketty.", 'A', 1);
		list.add("Tatanic", 'A', 2);
		list.add("I'm Legend.", 'C', 2);
		list.add("2012", 'B', 1);
		list.add("God eater.", 'G', 1);
		list.add("Gost", 'A', 3);
		
//		System.out.println(list);   //不是按顺序排列
		
		while(!list.isEmpty())
			System.out.println(list.remove());
	}
	
}
