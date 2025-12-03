// Deck.java, represents a deck of playing cards

import java.util.List;

public class Deck { // represents a deck of playing cards
    private DeckAPI api; // api to interact with deck
    private String deckId; // unique deck identifier
    private int remaining; // cards remaining in deck
    private int deckCount; // number of decks combined

    public Deck(DeckAPI api, int deckCount) { // constructor
        this.api = api;
        this.deckCount = deckCount;
        this.deckId = api.createShuffledDeck(deckCount);
        this.remaining = 52 * deckCount;
    }

    public Card dealCard() { // deals a card from the deck
        if (remaining <= 0) { // reshuffle if deck is empty
            api.reshuffle(deckId, true); // reshuffle the deck
            remaining = 52 * deckCount; // reset remaining count
        }

        List<Card> drawn = api.drawCards(deckId, 1);
        if (drawn == null || drawn.isEmpty()) { // error handling
            return null;
        }

        remaining--; 
        return drawn.get(0); // return the drawn card
    }

    public boolean isEmpty() { // check if deck is empty
        return remaining <= 0;
    }

    public int getRemaining() { // get number of remaining cards
        return remaining;
    }
}
