package lld;

public class Dice {
    int noOfDice;

    public Dice(int noOfDice) {
        this.noOfDice = noOfDice;
    }

    public int rollDice() {
        return (int)(Math.random() * (6 * this.noOfDice - 1 * this.noOfDice)) + 1;
    }
}
