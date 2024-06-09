package project;

import java.util.ArrayList;
import java.util.Collections;

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
    }

    public static class HighCardDrawGame {
        private Deck deck;
        private Card playerCard;
        private Card computerCard;

        public HighCardDrawGame() {
            deck = new Deck();
        }

        public void play() {
            playerCard = deck.drawCard();
            computerCard = deck.drawCard();

            System.out.println("You drew: " + playerCard);
            System.out.println("Computer drew: " + computerCard);

            if (playerCard.getRankValue() > computerCard.getRankValue()) {
                System.out.println("You win!");
            } else if (playerCard.getRankValue() < computerCard.getRankValue()) {
                System.out.println("Computer wins!");
            } else {
                System.out.println("It's a tie!");
            }
        }
    }
}
