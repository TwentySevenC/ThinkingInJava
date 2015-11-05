package shareLimitedResource;

public class EvenGenerator extends IntGenerator{
	private int val = 0;
	
	@Override
	public int next() {
		++val;           //The danger point here. (the operation "++" is not atom)
		++val; 
		return val;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
