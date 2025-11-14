# CS121 Final Project
Final project in CS121 

```mermaid
classDiagram

class Card {
    - String rank
    - String suit
    - int value
    + Card(String rank, String suit, int value)
    + int getValue()
    + String getRank()
    + String getSuit()
    + String toString()
}

class Deck {
    - List<Card> cards
    + Deck()
    + void shuffle()
    + Card dealCard()
    + boolean isEmpty()
}

class Hand {
    - List<Card> cards
    + Hand()
    + void addCard(Card card)
    + int getTotal()
    + List<Card> getCards()
    + String toString()
}

class Player {
    - Hand hand
    + Player()
    + Hand getHand()
    + int getTotal()
}

class Dealer {
    - Hand hand
    + Dealer()
    + Hand getHand()
    + int getTotal()
    + void playTurn(Deck deck)
}

class BlackjackGame {
    - Deck deck
    - Player player
    - Dealer dealer
    + BlackjackGame()
    + void start()
    + void playerTurn()
    + void dealerTurn()
    + void compareHands()
}

Deck --> Card
Player --> Hand
Dealer --> Hand
BlackjackGame --> Deck
BlackjackGame --> Player
BlackjackGame --> Dealer
```

## Algorithm
* console blackjack game with classes; Card, Deck, Hand, Player, Dealer, and BlackjackGame

1. start
   * public static void main(String[] args) creates a BlackjackGame object
   * call game.start()
2. initialize game
   * create a Deck object
   * call deck.shuffle()
   * create Player and Dealer objects with their own hands
3. deal cards
   * deal 2 cards to player
     * player.getHand().addCard(deck.dealCard());
    * deal 2 cards to dealer
      * dealer.getHand().addCard(deck.dealCard());
4. player turn
   * show players hand and total; player.getHand().getTotal()
   * show one dealer card
   * loop
     * ask user "hit or stand (h/s)"
     * if hit
       * deal one card to player
       * recalculate total
       * if total > 21; print "Bust. Dealer wins." and end game
    * if stand
      * break loop
5. dealer turn
   * reveal dealers full hand
   * while dealers total < 17
     * dealer hits
     * if dealer total > 21; "Dealer busts. Player wins." and end game
6. compare final scores
   * get playerTotal and dealerTotal
   * if playerTotal > dealerTotal "Player wins."
   * else if playerTotal < dealerTotal "Dealer wins."
   * else "Push."
7. end program
   * ask user if they want to play again
