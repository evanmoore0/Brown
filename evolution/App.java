package evolution;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * App class creates an instance of PaneOrganizer, then based on which button the user presses the game class is called
 * and the corresponding class is called.
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Creates an instance of PaneOrganizer, and sets the scene up
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Flappy Bird");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}