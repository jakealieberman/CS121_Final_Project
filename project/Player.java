// Player.java, player in a card game

public class Player { // player in a card game
    private Hand hand;

    public Player() { // constructor
        hand = new Hand(); // initalizes an empty hand
    }

    public Hand getHand() {
        return hand; // returns the players hand
    }

    public int getTotal() {
        return hand.getTotal(); // returns total value of the hand
    }
}