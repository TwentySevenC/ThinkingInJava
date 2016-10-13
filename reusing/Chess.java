package reusing;

import static util.util.Print.*;

class Game {
	Game(int i) {
		print("Game constructor");
	}
	
	void f() {
		print("Game f()");
	}
	
	void f2() {
		f();
	}
}

class BoardGame extends Game {
	public BoardGame(int i) {
		super(i);
		print("BoardGame constructor");
	}
	
	@Override
	void f() {
		print("BoardGame f()");
	}
	
}

public class Chess extends BoardGame {
	public Chess() {
		super(1);
		print("Chess constructor");
	}
	
	public static void main(String[] args) {
		Chess ch  = new Chess();
		ch.f2();
	}
	
}
