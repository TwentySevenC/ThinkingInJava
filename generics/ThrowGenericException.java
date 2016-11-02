package generics;

import java.util.ArrayList;
import java.util.List;

interface Processor<T, E extends Throwable> {
	void process(List<T> resultCollector) throws E;
}

class ProcessRunnable<T, E extends Throwable> extends ArrayList<Processor<T, E>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<T> processAll() throws E {
		List<T> resultCollector = new ArrayList<>();
		for(Processor<T, E> p : this) {
			p.process(resultCollector);
		}
		return resultCollector;
	}
	
}

class Failure1 extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1764526624772808839L;}

class Processor1 implements Processor<String, Failure1> {
	static int count = 3;
	@Override
	public void process(List<String> resultCollector) throws Failure1 {
		if(count-- > 1) {
			resultCollector.add("Hep!");
		} else {
			resultCollector.add("Ho!");
		}
		if(count < 0) {
			throw new Failure1();
		}
	}
}

class Failure2 extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

class Processor2 implements Processor<Integer, Failure2> {
	static int count = 2;
	@Override
	public void process(List<Integer> resultCollector) throws Failure2 {
		if(count-- == 0) 
			resultCollector.add(47);
		else
			resultCollector.add(11);
		if(count < 0) 
			throw new Failure2();
	}
	
}

public class ThrowGenericException {
	public static void main(String[] args) {
		ProcessRunnable<String, Failure1> runner = new ProcessRunnable<>();
		for(int i = 0; i < 3; i++) {
			runner.add(new Processor1());
		}
		
		try {
			System.out.println(runner.processAll());
		} catch (Failure1 e) {
			System.out.println(e);
		}
		
		ProcessRunnable<Integer, Failure2> runner2 = new ProcessRunnable<>();
		for(int i = 0; i < 3; i++) {
			runner2.add(new Processor2());
		}
		
		try {
			System.out.println(runner2.processAll());
		} catch (Failure2 e) {
			System.out.println(e);
		}
	}
}
