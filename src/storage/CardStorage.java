package storage;

import model.Deck;

public interface CardStorage {
    void saveDeck(Deck deck);
    Deck loadDeck(String name);
}
