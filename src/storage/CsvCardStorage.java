package storage;

import model.Card;
import model.Deck;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvCardStorage implements CardStorage{
    @Override
    public void saveDeck(Deck deck) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(deck.getName() + ".csv"))) {

            for (Card card : deck.getCards()) {
                bw.write(card.getQuestion() + ";" + card.getAnswer());
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Deck loadDeck(String name) {
        Deck deck = new Deck(name);

        try (BufferedReader br = Files.newBufferedReader(Paths.get(name + ".csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    deck.addCard(new Card(parts[0].trim(), parts[1].trim()));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return deck;
    }

}
