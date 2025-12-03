# makefile

Card.class: Card.java
	javac -g -cp .:gson-2.13.1.jar Card.java

DeckAPI.class: DeckAPI.java
	javac -g -cp .:gson-2.13.1.jar DeckAPI.java

Deck.class: Deck.java DeckAPI.class Card.class
	javac -g -cp .:gson-2.13.1.jar Deck.java

Hand.class: Hand.java Card.class
	javac -g -cp .:gson-2.13.1.jar Hand.java

Player.class: Player.java Hand.class
	javac -g -cp .:gson-2.13.1.jar Player.java

Dealer.class: Dealer.java Hand.class
	javac -g -cp .:gson-2.13.1.jar Dealer.java

BlackjackGame.class: BlackjackGame.java Deck.class Player.class Dealer.class Hand.class Card.class DeckAPI.class
	javac -g -cp .:gson-2.13.1.jar BlackjackGame.java


# build everything
all: BlackjackGame.class


# run the game
run: BlackjackGame.class
	java -cp .:gson-2.13.1.jar BlackjackGame


# clean all compiled files
clean:
	rm -f *.class