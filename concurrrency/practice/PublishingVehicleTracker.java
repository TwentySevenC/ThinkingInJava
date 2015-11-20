package concurrrency.practice;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// java concurrency in practice

//如果将拷贝构造函数实现为this(p.x, p.y), 那么会产生竞态条件，而利用私有构造器将避免这种竞态条件
/**
 * 这里的几点疑问：
 * 1。 为什么不能将拷贝构造函数 实现为this(p.x, p.y)
 * 2. 为什么要增加一个构造函数的形式 private SafePoint(int[] a)，参数采用数组的形式
 * 3. 为什么是 private 而不是 public
 *
 */

/**
 * 问题解答：
 * 1. 对于为什么不能将 拷贝构造函数 实现为this(p.x, p.y)的形式，由于在构建新的拷贝时，无法保证p.x 与 p.y 的完整性
 * 有可能在多线程的情况下，当线程从p中取得x的值后，再取得y的值前，另一个线程更改了y的值，那么该线程再取得y的值，最后总的
 * 取得了一个失效的p实例。参见SafePoint2.java的演示。 
 * 
 * 解决方法：对于SafePoint的线程安全性，就是控制状态变量x，y的同步。
 * 
 * synchronized constructor so that the read will synchronize on the same lock, but Constructors 
 * in Java can not use the synchronized keyword - which is logic of course.
 * 
 * may be use a different lock, like Reentrant lock (if the synchronized keyword can not be used). 
 * But it will also not work, because the first statement inside a constructor must be a call to this/super. 
 * If we implement a different lock then the first line would have to be something like this:
 * 
 * lock.lock() //where lock is ReentrantLock, the compiler is not going to allow this for the reason stated above.
 * "call to this must be first statement in constructor"
 * 
 * 这样也不行：
 * public SafePoint(SafePoint p){
 * 	int[] values = p.get();
 * 	this(value[0], value[1]);
 * }
 *
 * what if we make the constructor a method? Of course this will work!
 * 
 * public static SafePoint cloneSafePoint(SafePoint p){
 * 	int [] xy = originalPoint.getXY();
 * 	return new SafePoint(xy[0], xy[1]);
 * }
 * 
 * 但是如果不能使用函数怎么办呢？
 * 我们的目的是需要传入一个由同步获得的x，y的值。
 * get() 是已经具备的同步方法，它可以同步获得x，y的值。那么只需要有一个构造函数可以传入get方法返回的值就可以了，
 * 于是就有 private SafePoint(int[] a) 和 public SafePoint（SafePoint p） 先不管到底是public还是private
 * 
 * public 与 private 的区别在于不能将带有 数组参数 的构造函数公布，因为单就 这个参数来说 不清楚传入的参数需要什么样
 * 的数组，不清楚 长度，是否可以为null，数组内数值先后的意义等。这样会造成这个类本身使用起来不安全。
 * 
 * 可参考：http://stackoverflow.com/questions/12028925/private-constructor-to-avoid-race-condition
 *
 */

//线程安全
class SafePoint{
	private int x, y;
	
	public SafePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public SafePoint(SafePoint p){
		this(p.get());
	}
	
	
	private SafePoint(int[] a) {
		this(a[0], a[1]);
	}

	public synchronized int[] get(){
		return new int[] {x,y};
	}
	
	public synchronized void set(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class PublishingVehicleTracker {
	private final Map<String, SafePoint> locations;
	private final Map<String, SafePoint> unmodifiedMap;
	
	public PublishingVehicleTracker(Map<String, SafePoint> locations){
		this.locations = new ConcurrentHashMap<>(locations);
		this.unmodifiedMap = Collections.unmodifiableMap(locations);
	}
	
	public Map<String, SafePoint> getLocations(){
		return unmodifiedMap;
	}
	
	public SafePoint getLocation(String id){  //此处安全发布，由于SafePoint本身有同步控制
		return locations.get(id);
	}
	
	public void setLocation(String id, int x, int y){
		if(!locations.containsKey(id)){
			throw new IllegalArgumentException("Invalid vehicle name: " + id);
		}
		locations.get(id).set(x, y);
	}

}
