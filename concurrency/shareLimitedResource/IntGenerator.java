package shareLimitedResource;


//:concurrency/IntGenerator.java

public abstract class IntGenerator {
	
	private volatile boolean isCanceled = false;
	
	public abstract int next();
	
	public void cancel(){
		isCanceled = true;
	}
	
	public boolean isCancel(){
		return isCanceled;
	}
	

}
