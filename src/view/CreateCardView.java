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

    public void show() {
        Deck deck = new Deck("MeinDeck");

        Label questionLabel = new Label("Frage:");
        TextField questionField = new TextField();

        Label answerLabel = new Label("Antwort:");
        TextField answerField = new TextField();

        Button addButton = new Button("Karte hinzufügen");

        addButton.setOnAction(e -> {

            String question = questionField.getText();
            String answer = answerField.getText();

            Card card = new Card(question, answer);

            deck.addCard(card);

            System.out.println("Karte hinzugefügt");
            System.out.println("Anzahl Karten: " + deck.getCards().size());
            questionField.clear();
            answerField.clear();
        });

        Button saveButton = new Button("Deck speichern");

        saveButton.setOnAction(e -> {
            CardStorage storage = new CsvCardStorage();
            storage.saveDeck(deck);
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(questionLabel, questionField, answerLabel, answerField, addButton, saveButton);

        Stage stage = new Stage();
        stage.setTitle("Lernkarten erstellen");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
