package generics;

class Shape {}

class Circle extends Shape {}

public class Node<T> {
	public T data;
	
	public Node(T data) {
		this.data = data;
	}
	
	public void setData(T data) {
		System.out.println("Node.setData");
		this.data = data;
	}
	
}
