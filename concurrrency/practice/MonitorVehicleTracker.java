package concurrrency.practice;

import java.util.HashMap;
import java.util.Map;

// Java concurrency in practice

class MutablePoint{
	public int x, y;
	public MutablePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public MutablePoint(MutablePoint p){
		this.x = p.x;
		this.y = p.y;
	}
	
}

//线程安全的类，采用了实例封闭。尽管MutablePoint不是线程安全的，但是线程安全的MonitorVehicleTracker是线程安全的
//它将MutablePoint封闭起来了，两个同步方法返回的结果都是 深拷贝 的结果。安全发布。
public class MonitorVehicleTracker {
	private final Map<String, MutablePoint> locations;
	
	public MonitorVehicleTracker(Map<String, MutablePoint> locations){
		this.locations = deepCopy(locations);
	}
	
	public synchronized Map<String, MutablePoint> getLocations(){
		return deepCopy(locations);
	}
	
	public synchronized MutablePoint getLocation(String id){
		MutablePoint loc = locations.get(id);
		return loc == null ? null : new MutablePoint(loc);
	}
	
	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
		Map<String, MutablePoint> result = new HashMap<>();
		for(String id : m.keySet())
			result.put(id, new MutablePoint(m.get(id)));
		return result;
	}
	
}
