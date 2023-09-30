package com.codewithfahim.lld;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public Deque<Player> players;
    public Board gameBoard;
    public int freeSpaces;

    public void initializeBoard() {
        players = new LinkedList<>();
        PlayingPieceX playingPieceX = new PlayingPieceX();
        PlayingPieceO playingPieceO = new PlayingPieceO();

        Player player1 = new Player("fahim", playingPieceX);
        Player player2 = new Player("fajila", playingPieceO);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
        freeSpaces = gameBoard.size * gameBoard.size;
    }

    public String startGame() {
        System.out.println(players.getFirst().playerName + " playing piece: " + players.getFirst().playingPiece.pieceType);
        System.out.println(players.getLast().playerName + " playing piece: " + players.getLast().playingPiece.pieceType);

        boolean noWinner = true;

        while(noWinner) {
            Player player = players.removeFirst();

            gameBoard.printBoard();

            if(freeSpaces == 0) {
                noWinner = false;
                continue;
            }

            System.out.println("Player: " + player.playerName + " enter row, col: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int row = Integer.valueOf(values[0]);
            int col = Integer.valueOf(values[1]);

            boolean pieceAdded = gameBoard.addPiece(row, col, player.playingPiece);

            if(!pieceAdded) {
                System.out.println("Incorrect position, try again");
                players.addFirst(player);
                continue;
            }

            players.addLast(player);
            boolean winner = isWinner(row, col, player.playingPiece.pieceType);
            if(winner) {
                gameBoard.printBoard();
                return player.playerName;
            }
            freeSpaces--;
        }
        return "tie";
    }

    public boolean isWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;


        //need to check in row
        for(int i = 0; i < gameBoard.size; i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i = 0; i < gameBoard.size; i++) {

            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i = 0, j = 0; i < gameBoard.size; i++, j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i = 0, j = gameBoard.size-1; i < gameBoard.size; i++, j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }
        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
