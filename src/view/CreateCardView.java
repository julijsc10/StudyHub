package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import storage.CardStorage;
import storage.CsvCardStorage;

public class CreateCardView {

    private Deck deck;

    public void show() {
        Label deckNameLabel = new Label("Deckname:");
        TextField deckNameField = new TextField();

        Label questionLabel = new Label("Frage:");
        TextField questionField = new TextField();

        Label answerLabel = new Label("Antwort:");
        TextField answerField = new TextField();

        Button addButton = new Button("Karte hinzufügen");
        Button saveButton = new Button("Deck speichern");

        saveButton.setDisable(true);

        addButton.setOnAction(e -> {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (question.isBlank() || answer.isBlank()) {
                System.out.println("Bitte Frage und Antwort eingeben!");
                return;
            }

            if (deck == null) {
                String deckName = deckNameField.getText().trim();
                if (deckName.isBlank()) {
                    System.out.println("Bitte Decknamen eingeben!");
                    return;
                }
                deck = new Deck(deckName);
            }

            Card card = new Card(question, answer);
            deck.addCard(card);
            deckNameField.setDisable(true);

            System.out.println("Karte hinzugefügt");
            System.out.println("Anzahl Karten: " + deck.getCards().size());

            questionField.clear();
            answerField.clear();
            saveButton.setDisable(false);
        });

        saveButton.setOnAction(e -> {
            if (deck == null) {
                System.out.println("Keine Karten vorhanden!");
                return;
            }
            try {
                CardStorage storage = new CsvCardStorage();
                storage.saveDeck(deck);
                System.out.println("Deck gespeichert!");
            } catch (Exception ex) {
                System.out.println("Fehler beim Speichern!");
            }

            deck = null;

            deckNameField.clear();
            deckNameField.setDisable(false);
            saveButton.setDisable(true);

            questionField.clear();
            answerField.clear();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(deckNameLabel, deckNameField, questionLabel, questionField, answerLabel, answerField, addButton, saveButton);
        Stage stage = new Stage();
        stage.setTitle("Lernkarten erstellen");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
