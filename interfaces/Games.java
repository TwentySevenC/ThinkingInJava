package interfaces;

import static util.util.Print.*;

// A Game framework using Factory method.

interface Game {
	boolean move();
}

interface GameFactory {
	Game getGame();
}

class Checkers implements Game {
	private int moves = 0;
	private static final int MOVES = 3;

	@Override
	public boolean move() {
		print("Checkers move " + moves);
		return ++moves != MOVES;
	}
	
}


class CheckersFactory implements GameFactory {

	@Override
	public Game getGame() {
		return new Checkers();
	}
	
}

class Chess implements Game {
	private int moves = 0;
	private static final int MOVES = 4;

	@Override
	public boolean move() {
		print("Chess move " + moves);
		return ++moves != MOVES;
	}
	
}

class ChessFactory implements GameFactory {

	@Override
	public Game getGame() {
		return new Chess();
	}
	
}

public class Games {
	public static void playGame(GameFactory factory) {
		Game game = factory.getGame();
		while(game.move())
			;
	}
	
	public static void main(String[] args) {
		playGame(new CheckersFactory());
		playGame(new ChessFactory());
	}
}
