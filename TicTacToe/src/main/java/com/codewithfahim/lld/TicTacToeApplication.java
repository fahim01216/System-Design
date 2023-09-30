package com.codewithfahim.lld;

public class TicTacToeApplication {
	public static void main(String[] args) {
		System.out.println("********************* TIC TAC TOE GAME *********************");
		Game game = new Game();
		game.initializeBoard();
		System.out.println("Game winner: " + game.startGame());
	}
}
