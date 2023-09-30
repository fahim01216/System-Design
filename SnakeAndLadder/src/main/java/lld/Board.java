package lld;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Board {
    int boardSize;
    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<String, Integer> playerCurrentPosition;

    public Board(int boardSize, Dice dice, Queue<Player> nextTurn, List<Jumper> snakes, List<Jumper> ladders,
                 Map<String, Integer> playerCurrentPosition) {
        this.boardSize = boardSize;
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playerCurrentPosition = playerCurrentPosition;
    }

    void startGame() {
        System.out.println("*********** SNAKE AND LADDER GAME ***********");
        while(nextTurn.size() > 1) {
            Player player = nextTurn.poll();
            int currentPosition = playerCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice();
            int newPosition = currentPosition + diceValue;

            if(newPosition > boardSize) {
                nextTurn.offer(player);
            }
            else if(newPosition == boardSize) {
                System.out.println(player.getPlayerName() + " wins the game");
            }
            else {
                int[] nextPos = new int[1];
                AtomicBoolean isLadder = new AtomicBoolean(false);
                nextPos[0] = newPosition;
                snakes.forEach(snake -> {
                    if(snake.startPosition == newPosition) {
                        nextPos[0] = snake.getEndPosition();
                    }
                });

                if(nextPos[0] != newPosition) {
                    System.out.println(player.getPlayerName() + " bitten by snake present at: " + newPosition);
                }

                ladders.forEach(ladder -> {
                    if(ladder.startPosition == newPosition) {
                        nextPos[0] = ladder.endPosition;
                        isLadder.set(true);
                    }
                });

                if(nextPos[0] != newPosition && isLadder.get()) {
                    System.out.println(player.getPlayerName() + " got ladder present at: " + newPosition);
                }

                if(nextPos[0] == boardSize) {
                    System.out.println(player.getPlayerName() + " wins the game");
                }
                else {
                    playerCurrentPosition.put(player.getPlayerName(), nextPos[0]);
                    System.out.println(player.getPlayerName() + " is at position: " + nextPos[0]);
                    nextTurn.offer(player);
                }
            }
        }
    }
}
