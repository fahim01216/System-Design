package lld;

import java.util.*;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Dice dice = new Dice(1);

        Player player1 = new Player(01, "fahim");
        Player player2 = new Player(02, "fajila");
        Queue<Player> allPlayers = new LinkedList<>();
        allPlayers.offer(player1);
        allPlayers.offer(player2);

        Jumper snake1 = new Jumper(24, 6);
        Jumper snake2 = new Jumper(78, 13);
        Jumper snake3 = new Jumper(94, 31);
        Jumper snake4 = new Jumper(57, 7);
        List<Jumper> snakes = new ArrayList<>();
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        snakes.add(snake4);

        Jumper ladder1 = new Jumper(9, 61);
        Jumper ladder2 = new Jumper(34, 77);
        Jumper ladder3 = new Jumper(51, 69);
        Jumper ladder4 = new Jumper(73, 91);
        List<Jumper> ladders = new ArrayList<>();
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);
        ladders.add(ladder4);

        Map<String, Integer> playerCurrentPosition = new HashMap<>();
        playerCurrentPosition.put("fahim", 0);
        playerCurrentPosition.put("fajila", 0);

        Board board = new Board(100, dice, allPlayers, snakes, ladders, playerCurrentPosition);
        board.startGame();
    }
}
