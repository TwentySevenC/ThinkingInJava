package concurrrency.practice;


/**
 * 
 * 一个线程可以同时获得多个不同的锁, 并且可以同时获得同一个锁多次。
 * 在同一个类中，采用内部锁申明的静态方法和非静态方法，不是同一个锁
 * 静态方法以Class对象作为锁。
 * 
 * 静态的synchronized 方法以class作为锁
 *
 */

class Point{
	public static synchronized void draw(String threadName){
		System.out.println("Point " + threadName);
		while(true)
			Thread.yield();
	}
	
	public synchronized void canvas(String threadName){
		System.out.println("Point " + threadName);
	}
}

class Shape{
	public synchronized void draw(String threadName){
		System.out.println("Shape." + threadName);
		Point.draw(threadName);
	}
	
}

class Rectangle extends Shape{
	public synchronized void draw(String threadName){
		System.out.println("Rectangle" + threadName);
		super.draw(threadName);
	}
}

class Circle extends Shape{
	@Override
	public synchronized void draw(String threadName){
		System.out.println("Circle" + threadName);
		super.draw(threadName);
	}
}

public class ClassTest {
	public static void main(String[] args) {
		Circle circle = new Circle();
		
		new Thread(){
			@Override
			public void run(){
				circle.draw(Thread.currentThread().getName());
			}
		}.start();
	
		new Thread(){
			@Override
			public void run(){
//				circle.draw(Thread.currentThread().getName());
				new Rectangle().draw(Thread.currentThread().getName());
			}
		}.start();
	
	}
}
