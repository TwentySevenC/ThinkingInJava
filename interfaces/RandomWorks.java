package interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class RandomWorks implements Readable {
	private static Random rand = new Random();
	private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] lows = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final char[] vowels = "aeiou".toCharArray();
	
	private int count;
	public RandomWorks(int count) {
		this.count = count;
	}

	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count-- == 0) {
			return -1;
		}
		
		cb.append(capitals[rand.nextInt(capitals.length)]);
		for(int i = 0; i < 4; i++) {
			cb.append(vowels[rand.nextInt(vowels.length)]);
			cb.append(lows[rand.nextInt(lows.length)]);
		}
		cb.append(" ");
		return 10;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new RandomWorks(10));
		try {
			while (scanner.hasNext()) {
				System.out.println(scanner.next());
			}
		} finally {
			scanner.close();
		}
	}

}
