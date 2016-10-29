package generics;

//A stack

public class LinkedStack<T> {
	private static class Node<U> {
		U item;
		Node<U> next;
		Node() {
			item = null;
			next = null;
		}
		
		Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}
		
		boolean end() {
			return item == null && next == null;
		}
	}
	
	private Node<T> top = new Node<>();
	
	public void push(T item) {
		top = new Node<>(item, top);
	}
	
	public T pop() {
		T result = top.item;
		if(!top.end()) {
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> ls = new LinkedStack<>();
		for(String s : "To be or not to be, that's a question!".split(" ")) {
			ls.push(s);
		}
		
		String ss;
		while ((ss = ls.pop()) != null) {
			System.out.println(ss);
		}
	}
	
}
