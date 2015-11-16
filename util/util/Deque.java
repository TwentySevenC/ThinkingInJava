package util.util;

import java.util.LinkedList;

public class Deque<T> {
	
	private LinkedList<T> list = new LinkedList<>();
	
	public void  addFirst(T e){
		list.addFirst(e);
	}
	
	public void addLast(T e){
		list.addLast(e);
	}
	
	public T getFirst(){
		return list.getFirst();
	}
	
	public T getLast(){
		return list.getLast();
	}
	
	public T removeFirst(){
		return list.removeFirst();
	}
	
	public T removeLast(){
		return removeLast();
	}
	
	public int size(){
		return list.size();
	}
	
	public String toString(){
		return list.toString();
	}

}
