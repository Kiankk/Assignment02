package com.mycompany.assignment02;

import com.mycompany.assignment02.controller.CarRaceController;
import com.mycompany.assignment02.controller.SlideshowController;
import com.mycompany.assignment02.model.CarRaceModel;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Starts the car race application.
 *
 * @author Kian
 */
public class App extends Application {

    private final CarRaceModel model = new CarRaceModel();
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new StackPane(), 1000, 640);
        scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        stage.setTitle("Car Race");
        stage.setScene(scene);
        stage.show();
        showSlideshow();
    }

    private void showSlideshow() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("slideshow.fxml"));
        Parent root = loader.load();
        SlideshowController controller = loader.getController();
        controller.setModel(model);
        controller.setOnFinished(this::showRace);
        scene.setRoot(root);
        controller.play();
    }

    private void showRace() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("race.fxml"));
            Parent root = loader.load();
            CarRaceController controller = loader.getController();
            controller.setModel(model);
            scene.setRoot(root);
        } catch (IOException e) {
            throw new IllegalStateException("Could not load the race view", e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
