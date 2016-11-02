package generics;

import java.util.Date;

interface TimeStamped {
	long getStamp();
}

class TimeStampedImp implements TimeStamped {
	
	private final long timeStamp;
	public TimeStampedImp() {
		timeStamp = new Date().getTime();
	}

	@Override
	public long getStamp() {
		return timeStamp;
	}
	
}

interface SerialNumbered {
	long getSerialNumber();
}

class SerialNumberedImp implements SerialNumbered {
	private static long count = 1;
	private final long serialNumber = count++;

	@Override
	public long getSerialNumber() {
		return serialNumber;
	}
	
}

interface Basic {
	public void set(String val);
	public String get();
}

class BasicImp implements Basic {
	
	private String value;

	@Override
	public void set(String val) {
		value = val;
	}

	@Override
	public String get() {
		return value;
	}
	
}

class Mixin extends BasicImp implements TimeStamped, SerialNumbered {
	
	private TimeStamped timeStamped = new TimeStampedImp();
	private SerialNumbered serivalNumbered = new SerialNumberedImp();
	

	@Override
	public long getSerialNumber() {
		return serivalNumbered.getSerialNumber();
	}

	@Override
	public long getStamp() {
		return timeStamped.getStamp();
	}
	
}

public class Mixins {
	public static void main(String[] args) {
		Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
		mixin1.set("Test string 1");
		mixin2.set("Test string 2");
		System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
		System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
	}
}
