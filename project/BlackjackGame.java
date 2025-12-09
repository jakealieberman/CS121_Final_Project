// BlackjackGame.javam, main game logic for a simple console Blackjack game

import java.util.Scanner;

public class BlackjackGame { // main game class
    private Deck deck; // deck of cards
    private Player player; // player
    private Dealer dealer; // dealer
    private Scanner scanner; // for user input

    public BlackjackGame() { // constructor
        DeckAPI api = new DeckAPI();
        deck = new Deck(api, 1);
        player = new Player();
        dealer = new Dealer();
        scanner = new Scanner(System.in);
    }
    public void start() { // starts the game
        boolean playAgain = true; // control game loop
        while (playAgain) { // main game loop
            player = new Player();
            dealer = new Dealer();
            player.getHand().addCard(deck.dealCard());
            player.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());
            dealer.getHand().addCard(deck.dealCard());
            playerTurn(); // players turn
            if (player.getTotal() <= 21) { // dealer only plays if player hasnt busted
                dealerTurn();
            }
            compareHands(); // compare results
            System.out.print("Play again? (y/n): "); // ask to play again
            String ans = scanner.nextLine().trim().toLowerCase(); // get input
            playAgain = ans.equals("y"); // update playAgain based on input
        }
        System.out.println("Thanks for (probably) losing money!"); // end message
    }
    public void playerTurn() { // players turn logic
        while (true) {
            System.out.println("Your hand: " + player.getHand()); // show hand
            System.out.println("Your total: " + player.getTotal()); // show total
            System.out.println("Dealer shows: " + dealer.getHand().getCards().get(0)); // show dealers upcard
            if (player.getTotal() > 21) { // check for bust
                System.out.println("Bust. Dealer wins."); // bust message
                return;
            }
            System.out.print("hit or stand (h/s): "); // prompt for action
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("h")) { // hit
                player.getHand().addCard(deck.dealCard());
            } else if (choice.equals("s")) { // stand
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
    public void dealerTurn() { // dealers turn logic
        System.out.println("Dealer's hand: " + dealer.getHand()); // show dealer hand
        System.out.println("Dealer's total: " + dealer.getTotal()); // show dealer total
        dealer.playTurn(deck);
        System.out.println("Dealer's final hand: " + dealer.getHand()); // final dealer hand
        System.out.println("Dealer's total: " + dealer.getTotal()); // final dealer total

        if (dealer.getTotal() > 21) { // dealer bust check
            System.out.println("Dealer busts. You win!"); // dealer bust message
        }
    }
    public void compareHands() { // compare player and dealer hands
        int p = player.getTotal(); // player total
        int d = dealer.getTotal(); // dealer total
        if (p > 21 || d > 21) return;
        System.out.println("Your total: " + p); // show totals
        System.out.println("Dealer's total: " + d); // show totals
        if (p > d) {
            System.out.println("You win!"); // player wins
        } else if (p < d) {
            System.out.println("Dealer wins."); // dealer wins
        } else {
            System.out.println("Push."); // push
        }
    }
    public static void main(String[] args) { // main method
    BlackjackGame game = new BlackjackGame(); // create game instance
    game.start(); // start the game
    }
}