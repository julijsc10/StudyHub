package storage;

import model.Card;
import model.Deck;

import java.io.*;

public class CsvCardStorage implements CardStorage{
    @Override
    public void saveDeck(Deck deck) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(deck.getName() + ".csv"))) {

            for (Card card : deck.getCards()) {
                writer.write(card.getQuestion() + ";" + card.getAnswer());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Deck loadDeck(String name) {
        Deck deck = new Deck(name);

        try (BufferedReader reader = new BufferedReader(new FileReader(name + ".csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    deck.addCard(new Card(parts[0], parts[1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return deck;
    }

}
