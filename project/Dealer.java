// Dealer.java, represents the dealer in a card game

public class Dealer { // dealer in a card game
    private Hand hand; // dealers hand

    public Dealer() { // constructor
        hand = new Hand(); // initializes an empty hand
    }

    public Hand getHand() {
        return hand; // returns the dealers hand
    }

    public int getTotal() { 
        return hand.getTotal(); // returns the total value of the hand
    }

    public void playTurn(Deck deck) { // dealers turn logic
        while (getTotal() < 17 && !deck.isEmpty()) { // dealer hits on totals less than 17
            hand.addCard(deck.dealCard()); // deals a card to the dealers hand
        }
    }
}