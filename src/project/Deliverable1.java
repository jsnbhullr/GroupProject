import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Deliverable1 {

    public static void main(String[] args) {
        HighCardDrawGame game = new HighCardDrawGame();
        game.play();
    }

    public static class Card {
        private String rank;
        private String suit;

        public Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String getRank() {
            return rank;
        }

        public int getRankValue() {
            switch (rank) {
                case "2": return 2;
                case "3": return 3;
                case "4": return 4;
                case "5": return 5;
                case "6": return 6;
                case "7": return 7;
                case "8": return 8;
                case "9": return 9;
                case "10": return 10;
                case "J": return 11;
                case "Q": return 12;
                case "K": return 13;
                case "A": return 14;
                default: return 0;
            }
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

    public static class Deck {
        private ArrayList<Card> cards;

        public Deck() {
            String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            cards = new ArrayList<>();

            for (String suit : suits) {
                for (String rank : ranks) {
                    cards.add(new Card(rank, suit));
                }
            }

            Collections.shuffle(cards);
        }

        public Card drawCard() {
            if (cards.isEmpty()) {
                return null;
            }
            return cards.remove(cards.size() - 1);
        }

        public ArrayList<Card> drawHand(int numCards) {
            ArrayList<Card> hand = new ArrayList<>();
            for (int i = 0; i < numCards; i++) {
                hand.add(drawCard());
            }
            return hand;
        }
    }

    public static class Player {
        private String name;
        private ArrayList<Card> hand;
        private Card card;
        private int score;

        public Player(String name) {
            this.name = name;
            this.score = 0;
            this.hand = new ArrayList<>();
        }

        public void drawHand(Deck deck, int numCards) {
            this.hand = deck.drawHand(numCards);
        }

        public void playCard(int cardIndex) {
            if (cardIndex >= 0 && cardIndex < hand.size()) {
                this.card = hand.remove(cardIndex);
            }
        }

        public Card getCard() {
            return card;
        }

        public String getName() {
            return name;
        }

        public void addScore() {
            this.score++;
        }

        public int getScore() {
            return score;
        }

        public ArrayList<Card> getHand() {
            return hand;
        }

        @Override
        public String toString() {
            return name + " drew: " + card;
        }
    }

    public static class HighCardDrawGame {
        private Deck deck;
        private Player player;
        private Player computer;
        private static final int MAX_LEVELS = 5;
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
            } else if (player.getScore() < computer.getScore()) {
                System.out.println("Computer is the overall winner!");
            } else {
                System.out.println("It's an overall tie!");
            }
        }
    }
}