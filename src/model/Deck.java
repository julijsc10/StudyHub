package model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private List<Card> cards = new ArrayList<>();

    public Deck(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}

