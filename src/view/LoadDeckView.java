package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import storage.CardStorage;
import storage.CsvCardStorage;

public class LoadDeckView {
    public void show() {
        Label deckNameLabel = new Label("Deckname:");
        TextField deckNameField = new TextField();

        Button loadButton = new Button("Deck laden");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        loadButton.setOnAction(e -> {

            String deckName = deckNameField.getText();

            if (deckName.isBlank()) {
                System.out.println("Bitte Decknamen eingeben!");
                return;
            }

            try {
                CardStorage storage = new CsvCardStorage();

                Deck deck = storage.loadDeck(deckName);

                outputArea.clear();

                outputArea.appendText("Deck: " + deck.getName() + "\n\n");

                for (Card card : deck.getCards()) {
                    outputArea.appendText("Frage: " + card.getQuestion() + "\nAntwort: " + card.getAnswer() + "\n\n");
                }
            } catch (Exception ex) {
                outputArea.setText("Deck nicht gefunden!");
            }
        });

        VBox root = new VBox(10);

        root.getChildren().addAll(deckNameLabel, deckNameField, loadButton, outputArea);

        Scene scene = new Scene(root, 500, 400);

        Stage stage = new Stage();
        stage.setTitle("Deck laden");
        stage.setScene(scene);
        stage.show();
    }
}
