package concurrrency.practice;

import java.util.concurrent.TimeUnit;

// Java concurrency in practice

/**
 * 这个程序演示了拷贝构造函数使用时候的非线程安全性。
 * SafePoint2 中 x, y 决定了类的线程安全性状态，它们有同步机制保护吗？
 * 在getXY（），setXY（）方法中，使用了内部同步，而在拷贝构造函数中没有，
 * 下面的程序新开了两个线程，理论上，最后拷贝的点应该是 （1，1）或（2，2）
 * 
 * 但是在拷贝构造函数这里产生了竞态条件，使得输出的结果是（2，1）。
 * 在拷贝构造函数中x, y 不是同时获得了，没有采用任何同步机制。
 */

public class SafePoint2 {
	private int x ;
	private int y;
	
	private SafePoint2(int[] a){
		this(a[0], a[1]);
	}
	
	public SafePoint2(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public SafePoint2(SafePoint2 p){
//		this.x = p.x;
//		this.y = p.y;
		this(p.getXY());
	}
	
	public synchronized int[] getXY(){
		return new int[] {x,y};
	}
	
	public synchronized void setXY(int x, int y){
		this.x = x;
		
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		this.y = y;
	}
	
	/**
	 * 采用方法
	 */
	public static SafePoint2 cloneSafePoint2(SafePoint2 p){
		int [] xy = p.getXY();
	
		return new SafePoint2(xy[0], xy[1]);
	}
	
	public String toString(){
		return "[" + x + ", " + y + "]";
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		final SafePoint2 originalPoint = new SafePoint2(1, 1);
		
		new Thread(){
			@Override
			public void run(){
				originalPoint.setXY(2, 2);
			}
		}.start();
		
		new Thread(){
			@Override
			public void run(){
//				SafePoint2 copyPoint = new SafePoint2(originalPoint);
				
				SafePoint2 copyPoint2 = cloneSafePoint2(originalPoint);
				
				System.out.println("Copy: " + copyPoint2);
			}
			
			
		}.start();
		
	}

}
