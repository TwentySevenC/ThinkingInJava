package polymorphism;

import static util.util.Print.print;

enum Note {
	MIDDLE_C, C_SHARP, B_FLAT;
}

class Instrument {
	public void play(Note n) {
		print("Instrument play()");
	}
}


class Wind extends Instrument {
	@Override
	public void play(Note n) {
		print("Wind.play() " + n);
	}
}

public class Music {
	
	public static void tune(Instrument i) {
		i.play(Note.MIDDLE_C);
	}
	
	public static void main(String[] args) {
		Wind wind = new Wind();
		tune(wind);
	}

}
