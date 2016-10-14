package polymorphism;

import static util.util.Print.*;

class Instrument2 {
	void play(Note n) {
		print("Instrument.play() " + n);
	}
	
	String what() {
		return "Instrument";
	}
	
	void adjust() {
		print("Adjusting Instrument");
	}
}

class Wind2 extends Instrument2 {
	@Override
	void play(Note n) {
		print("Wind.play() " + n);
	}
	
	@Override
	String what() {
		return "Wind";
	}
	
	@Override
	void adjust() {
		print("Adjustint Wind");
	}
}

class Percussion2 extends Instrument2 {
	@Override
	void play(Note n) {
		print("Percussion.play() " + n);
	}
	
	@Override
	String what() {
		return "Percussion";
	}
	
	@Override
	void adjust() {
		print("Adjusting Percussion");
	}
}

class Stringed2 extends Instrument2 {
	@Override
	void play(Note n) {
		print("Stringed.play() " + n);
	}
	
	@Override
	String what() {
		return "Stringed";
	}
	
	@Override
	void adjust() {
		print("Adjusting Stringed");
	}
}

class WoodWind2 extends Wind2 {
	@Override
	void play(Note n) {
		print("WoodWind.play() " + n);
	}
	
	@Override
	String what() {
		return "WoodWind";
	}
}

class Brass2 extends Wind2 {
	@Override
	void play(Note n) {
		print("Brass.play() " + n);
	}
	
	@Override
	String what() {
		return "Brass";
	}
}
 
public class Music3 {
	public static void tune(Instrument2 i) {
		i.play(Note.C_SHARP);
	}
	
	public static void tuneAll(Instrument2[] is) {
		for(Instrument2 i : is) {
			i.play(Note.B_FLAT);
		}
	}
	
	public static void main(String[] args) {
		Instrument2[] orchestra = new Instrument2[]{
			new Wind2(),
			new Percussion2(),
			new Stringed2(),
			new WoodWind2(),
			new Brass2()
		};
		tuneAll(orchestra);
	}
}
