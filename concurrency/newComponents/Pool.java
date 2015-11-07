package concurrency.newComponents;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<>();
	private boolean[] checkedout;
	private Semaphore semaphore;
	
	public Pool(Class<T> object, int size) throws InstantiationException, IllegalAccessException{
		this.size = size;
		checkedout = new boolean[size];
		for(int i = 0; i < size; i++){
			items.add(object.newInstance());
		}
		this.semaphore = new Semaphore(size, true);
	}
	
	public synchronized T getItem(){
		for(int i = 0; i < size; i++){
			if(!checkedout[i])
				return items.get(i);
		}
		return null;
	}
	
	public synchronized boolean releaseItem(T item){
		int index = items.indexOf(item);
		if(index == -1) return false;   
		
		return false;
	}
	
}
