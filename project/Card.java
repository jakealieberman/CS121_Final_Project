// Card.java, represents a playing card in a card game

public class Card { // represents a playing card
    private String code; // card code
    private String rank; // card rank
    private String suit; // card suit
    private int value; // blackjack value

    public Card(String code, String rank, String suit, int value) { // constructor
        this.code = code;
        this.rank = rank; 
        this.suit = suit;
        this.value = value;
    }

    public int getValue() { // returns the blackjack value of the card
        return value;
    }

    public String getRank() { // returns the rank of the card
        return rank;
    }

    public String getSuit() { // returns the suit of the card
        return suit;
    }

    public String getCode() { // returns the code of the card
        return code;
    }

    public String toString() { // string representation of the card
        return rank + " of " + suit;
    }
}