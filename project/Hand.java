// Hand.java, represents a hand of playing cards

import java.util.ArrayList;
import java.util.List;

public class Hand { // a hand of cards
    private List<Card> cards; // list of cards in the hand

    public Hand() {
        cards = new ArrayList<>(); // initializes an empty hand
    }

    public void addCard(Card card) {
        cards.add(card); // adds a card to the hand
    }

    public int getTotal() {
        int total = 0; // total value of the hand
        int aceCount = 0; // count of aces in the hand

        for (Card card : cards) {
            total += card.getValue(); // add card value to total
            if (card.getRank().equalsIgnoreCase("ACE")) { // check for ace
                aceCount++; 
            }
        }

        while (total > 21 && aceCount > 0) { // adjust for aces if total is less than 21
            total -= 10; // count ace as 1 instead of 11
            aceCount--; // reduce ace count
        }

        return total;
    }

    public List<Card> getCards() {
        return cards; // returns the list of cards in the hand
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(); // string representation of the hand
        for (Card c : cards) {
            sb.append(c.toString()).append(", "); // append each cards string representation
        }

        if (!cards.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove trailing comma and space
        }

        return sb.toString(); // return the final string
    }
}