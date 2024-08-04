/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jashandeep Singh
 */
import java.util.ArrayList;

public class Player {
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
