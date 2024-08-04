/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jashandeep Singh
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HighCardDrawGame {
    private Deck deck;
    private Player player;
    private Player computer;
    private static final int MAX_LEVELS = 4;
    private static final int HAND_SIZE = 5;

    public HighCardDrawGame() {
        deck = new Deck();
        player = new Player("Player");
        computer = new Player("Computer");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();
        player = new Player(playerName);

        for (int level = 1; level <= MAX_LEVELS; level++) {
            System.out.println("\nLevel " + level);
            player.drawHand(deck, HAND_SIZE);
            computer.drawHand(deck, HAND_SIZE);
            displayHand(player);

            int cardIndex = getPlayerCardChoice(scanner);
            player.playCard(cardIndex);

            int computerCardIndex = (int) (Math.random() * computer.getHand().size());
            computer.playCard(computerCardIndex);

            System.out.println(player);
            System.out.println(computer);

            determineRoundWinner();
        }

        determineOverallWinner();
    }

    private void displayHand(Player player) {
        System.out.println(player.getName() + "'s hand:");
        ArrayList<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }

    private int getPlayerCardChoice(Scanner scanner) {
        int cardIndex = -1;
        while (cardIndex < 0 || cardIndex >= HAND_SIZE) {
            try {
                System.out.println("Choose a card to play (enter the card number):");
                cardIndex = scanner.nextInt() - 1;
                if (cardIndex < 0 || cardIndex >= HAND_SIZE) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + HAND_SIZE + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return cardIndex;
    }

    private void determineRoundWinner() {
        if (player.getCard().getRankValue() > computer.getCard().getRankValue()) {
            player.addScore();
            System.out.println("You win this round!");
        } else if (player.getCard().getRankValue() < computer.getCard().getRankValue()) {
            computer.addScore();
            System.out.println("Computer wins this round!");
        } else {
            System.out.println("This round is a tie!");
        }
    }

    private void determineOverallWinner() {
        System.out.println("\nFinal Scores:");
        System.out.println(player.getName() + ": " + player.getScore());
        System.out.println(computer.getName() + ": " + computer.getScore());
      
        if (player.getScore() > computer.getScore()) {
            System.out.println("Congratulations! You are the overall winner!");
            System.out.println("||          ||  ||  |||  ||  |||  ||  |||||||  ||||||  \n"+
                               "||    ||    ||  ||  |||| ||  |||| ||  ||       ||   || \n"+
                               " ||  ||||  ||   ||  || ||||  || ||||  |||||    ||||||  \n"+
                               "  ||||  ||||    ||  ||  |||  ||  |||  ||       || ||   \n"+
                               "  ||      ||    ||  ||   ||  ||   ||  |||||||  ||  ||  ");
        } else if (player.getScore() < computer.getScore()) {
            System.out.println("Sorry! Computer is the overall winner!");
            System.out.println(" |||||||   |||||   ||||||   ||||||   ||    ||  \n"+
                               "||        ||   ||  ||   ||  ||   ||   ||  ||   \n"+
                               " ||||||   ||   ||  ||||||   ||||||     ||||    \n"+
                               "      ||  ||   ||  || ||    || ||       ||     \n"+
                               "|||||||    |||||   ||  ||   ||  ||      ||     \n"+
                               "                                                                                \n"+
                               " ||||||||  ||||||   ||    ||     |||||    ||||||   |||||   ||  |||  ||          \n"+
                               "    ||     ||   ||   ||  ||     ||   ||  ||       ||   ||  ||  |||| ||          \n"+
                               "    ||     ||||||     ||||      |||||||  || ||||  |||||||  ||  || ||||          \n"+
                               "    ||     || ||       ||       ||   ||  ||   ||  ||   ||  ||  ||  |||          \n"+
                               "    ||     ||  ||      ||       ||   ||   ||||||  ||   ||  ||  ||   ||          ");
        } else {                              
            System.out.println(" || |||||||| ||   |||||||       ||||||      ||||||||  ||  |||||||       \n"+
                               " ||    ||     |  ||            ||    ||        ||     ||  ||            \n"+
                               " ||    ||         ||||||       ||||||||        ||     ||  |||||         \n"+
                               " ||    ||              ||      ||    ||        ||     ||  ||            \n"+
                               " ||    ||        |||||||       ||    ||        ||     ||  |||||||       ");
        }
    }
}
