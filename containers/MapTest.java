package containers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *  浅拷贝与深拷贝(shallow copy & deep copy)：
	在设置一个类的构造器时，可以考虑设置一个利用该类实例作为参数的构造器，实现一个深拷贝构造。
	容器类的以容器实例作为构造函数参数的构造函数，例如：HashMap<K,V>(Map<K,V> map)
	如果参数map的值不是基本类型数据，那么新创建的一个map的值只是复制了参数map的值的引用，
	如果修改参数值对象内部的域，那么新创建的map中相应值也会发生改变。如果想要两个map中的值
	完全没有关联，那么需要深拷贝。一般的方法采用遍历进行复制
	
	class Point{
		private int x;
		private int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public Point(Point p){   //产生深拷贝的构造器
			this.x = p.x;
			this.y = p.y;
		}
		
		public void setX(int x){
			this.x = x;
		}
	}
	
	Map<String, Point> map = new HashMap<>();
	Point p1 = new Point(0,0);
	Point p2 = new Point(1,1);
	map.put(“one”, p1);
	map.put(“two”, p2);
		
	//实现浅拷贝 shallow copy
	Map<String, Point> newMap1 = new HashMap<>(map);
	
	//实现深拷贝 deepCopy
	Map<String, Point> newMap2 = new HashMap<>();
	for(String id : map.keySet)
		newMap.put(id, new Point(map.get(id)));
	
	此时如果修改p1 的值：
	p1.setX(1);
	
	那么map，newMap1中的值p1都发生了改变，而newMap2中的值没有发现改变。
	
	多线程并发中一定要 “小心” 浅拷贝与深拷贝混淆而造成的问题！！
 *
 */

class Point{
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p){
		x = p.x;
		y = p.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public String toString(){
		return "[" + x + ", " + y + "]";
	}
}


public class MapTest {
	
	public static void main(String[] args) {
		Map<Integer, Point> map = new HashMap<>();
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 2);
		Point p3 = new Point(3, 3);
		
		Point p11 = p1;
		Point p22 = new Point(p2);
		
		map.put(1, p1);
		map.put(2, p2);
		map.put(3, p3);
		
		Map<Integer, Point> map2 = new HashMap<>(map);
		Map<Integer, Point> map3 = Collections.unmodifiableMap(map);
		
		p1.setX(0);
		p2.setX(0);
		
		System.out.println("p1: " + p11);
		System.out.println("p2: " + p22);
		
		System.out.println("map" + map);
		System.out.println("map2: " + map2);
		System.out.println("map3: " + map3);
		
	}

}
