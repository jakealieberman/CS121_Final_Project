# CS121 Final Project
Final project in CS121 

```mermaid
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

Deck "1" -> "*" Card
Player "1" -> "1" Hand
Dealer "1" -> "1" Hand
BlackjackGame "1" -> "1" Deck
BlackjackGame "1" -> "1" Player
BlackjackGame "1" -> "1" Dealer
```
