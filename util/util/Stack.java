package util.util;

import java.util.LinkedList;

//Making a stack from a linkedList

public class Stack <T>{
	private LinkedList<T> storage = new LinkedList<>();
	public void push(T t){
		storage.addFirst(t);
	}
	
	public T pop(){
		return storage.removeFirst();
	}
	
	public T peek(){
		return storage.getFirst();
	}
	
	public boolean isEmpty(){
		return storage.isEmpty();
	}
	
	public String toString(){
		return storage.toString();
	}
}
