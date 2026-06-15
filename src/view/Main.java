package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);

        Scene scene = new Scene(root, 600, 400);

        Button btnCreate = new Button("Lernkarten erstellen");
        Button btnLoad = new Button("Deck laden");
        Button btnQuiz = new Button("Quiz starten");

        root.getChildren().addAll(btnCreate, btnLoad, btnQuiz);

        root.setAlignment(Pos.CENTER);
        btnCreate.setPrefWidth(200);
        btnLoad.setPrefWidth(200);
        btnQuiz.setPrefWidth(200);

        btnCreate.setOnAction(e -> {
            CreateCardView view = new CreateCardView();
            System.out.println("Lernkarten erstellen");
            view.show();
        });

        btnLoad.setOnAction(e -> {
            LoadDeckView load = new LoadDeckView();
            System.out.println("Deck laden");
            load.show();
        });

        btnQuiz.setOnAction(e -> {
            System.out.println("Quiz starten");
        });

        stage.setTitle("StudyHub");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
